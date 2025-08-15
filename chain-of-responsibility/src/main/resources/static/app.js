(function () {
    const form = document.getElementById('refundForm');
    const packet = document.getElementById('requestPacket');
    const resultPanel = document.getElementById('resultPanel');
    const resultText = document.getElementById('resultText');
    const stamp = document.getElementById('approvalStamp');
    const submitBtn = form.querySelector('.submit-btn');

    const nodes = {
        customerService: document.querySelector('[data-handler="customer-service"]'),
        supervisor: document.querySelector('[data-handler="supervisor"]'),
        finance: document.querySelector('[data-handler="finance"]')
    };

    const nodeOrder = ['customerService', 'supervisor', 'finance'];

    function resetChain() {
        Object.values(nodes).forEach(node => {
            node.classList.remove('active', 'handled', 'approved');
            node.querySelector('.node-status').textContent = '待处理';
        });
        packet.classList.remove('visible');
        stamp.classList.remove('animate');
    }

    function getHandlerForAmount(amount) {
        if (amount <= 100) return 'customerService';
        if (amount <= 500) return 'supervisor';
        return 'finance';
    }

    function getHandlerTitle(key) {
        const titles = {
            customerService: '客服审核',
            supervisor: '主管审批',
            finance: '财务复核'
        };
        return titles[key];
    }

    function setPacketPosition(targetNode) {
        const containerTop = document.querySelector('.chain-stage').getBoundingClientRect().top;
        const nodeTop = targetNode.getBoundingClientRect().top;
        const relativeTop = nodeTop - containerTop + targetNode.offsetHeight / 2 - packet.offsetHeight / 2;
        packet.style.top = `${relativeTop}px`;
    }

    function wait(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    async function runApprovalChain(amount) {
        resetChain();
        submitBtn.disabled = true;

        const targetKey = getHandlerForAmount(amount);
        const packetAmount = packet.querySelector('.packet-amount');
        packetAmount.textContent = `¥${amount.toFixed(2)}`;

        packet.classList.add('visible');
        setPacketPosition(nodes.customerService);
        resultText.textContent = '退款申请已进入审批链路...';

        for (const key of nodeOrder) {
            const node = nodes[key];
            node.classList.add('active');
            node.querySelector('.node-status').textContent = '审批中...';

            setPacketPosition(node);
            await wait(900);

            if (key === targetKey) {
                node.classList.remove('active');
                node.classList.add('approved');
                node.querySelector('.node-status').textContent = '已通过';

                resultText.innerHTML =
                    `退款金额 <strong>¥${amount.toFixed(2)}</strong> 已由 ` +
                    `<span style="color: var(--coral);">${getHandlerTitle(key)}</span> 审批通过。`;
                stamp.classList.add('animate');
                submitBtn.disabled = false;
                return;
            }

            node.classList.remove('active');
            node.classList.add('handled');
            node.querySelector('.node-status').textContent = '已转交';
            await wait(400);
        }

        submitBtn.disabled = false;
    }

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const amount = parseFloat(document.getElementById('amount').value);
        if (isNaN(amount) || amount < 0) {
            resultText.textContent = '请输入有效的退款金额。';
            return;
        }
        runApprovalChain(amount);
    });

    // Initial state
    resultText.textContent = '填写左侧表单并点击“启动审批流”，观察退款申请在责任链中的传递过程。';
})();
