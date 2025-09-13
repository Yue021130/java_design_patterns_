# Java 23 种设计模式：场景实践与深度解析

> 一个基于 **Java 17+** 的可运行设计模式知识库。每个模式都是一个独立的 Maven 子模块，代码贴近真实业务场景，拒绝 Animal/Dog/Cat 式玩具示例。

---

## 技术栈

- **Java 17+**（Record、Lambda、Stream、Optional、Sealed Class 等现代特性）
- **Maven 3.9+** 多模块项目
- **JUnit 5** 单元与并发测试
- 后续部分模式会引入 **Spring Boot** 做框架级印证

---

## 快速开始

### 1. 克隆 / 进入项目

```bash
cd java_design_patterns_
```

### 2. 编译并运行全部测试

```bash
mvn clean test
```

### 3. 运行单个模式的示例

以单例模式为例：

```bash
mvn -pl singleton compile exec:java \
    -Dexec.mainClass="com.pattern.singleton.SingletonDemo"
```

> 如果 `exec-maven-plugin` 未配置，可先用 `mvn -pl singleton compile`，然后：
> ```bash
> java -cp singleton/target/classes com.pattern.singleton.SingletonDemo
> ```

---

## 项目结构

```
java_design_patterns_/
├── pom.xml                     # 父 POM：统一 Java 版本、JUnit 5、插件
├── README.md                   # 本文件
└── singleton/                  # 每个设计模式一个独立文件夹
    ├── pom.xml
    ├── README.md               # 该模式的结构化讲解
    └── src/
        ├── main/java/...       # 核心实现 + 客户端 Demo
        └── test/java/...       # JUnit 5 测试
```

每个模式目录均包含：

- `README.md`：严格按【场景映射 → 实战代码 → 架构师点评 → 速记口诀】四段式撰写
- 可独立编译运行的 Java 源码
- 覆盖核心行为的单元 / 并发测试

---

## 模式目录与进度

按 **创建型 → 结构型 → 行为型** 顺序逐步填充。

### 创建型模式 (Creational Patterns)

| 序号 | 模式 | 模块 | 状态 | 真实业务场景 |
| :-: | --- | --- | --- | --- |
| 1 | 单例模式 Singleton | `singleton/` | ✅ 已完成 | 分布式雪花 ID 生成器 |
| 2 | 工厂方法模式 Factory Method | `factory-method/` | ✅ 已完成 | 电商聚合支付渠道处理器 |
| 3 | 抽象工厂模式 Abstract Factory | `abstract-factory/` | ✅ 已完成 | 多云厂商服务族（OSS/SMS/MQ） |
| 4 | 建造者模式 Builder | `builder/` | ✅ 已完成 | 金融风控规则分步构建 |
| 5 | 原型模式 Prototype | `prototype/` | ✅ 已完成 | 电商商品模板克隆 |

### 结构型模式 (Structural Patterns)

| 序号 | 模式 | 模块 | 状态 | 真实业务场景 |
| :-: | --- | --- | --- | --- |
| 6 | 适配器模式 Adapter | `adapter/` | ✅ 已完成 | 第三方物流查询接口统一适配 |
| 7 | 桥接模式 Bridge | `bridge/` | ✅ 已完成 | 消息类型与发送渠道解耦 |
| 8 | 组合模式 Composite | `composite/` | ⏳ 待实现 | 待补充 |
| 9 | 装饰器模式 Decorator | `decorator/` | ⏳ 待实现 | 待补充 |
| 10 | 外观模式 Facade | `facade/` | ⏳ 待实现 | 待补充 |
| 11 | 享元模式 Flyweight | `flyweight/` | ⏳ 待实现 | 待补充 |
| 12 | 代理模式 Proxy | `proxy/` | ⏳ 待实现 | 待补充 |

### 行为型模式 (Behavioral Patterns)

| 序号 | 模式 | 模块 | 状态 | 真实业务场景 |
| :-: | --- | --- | --- | --- |
| 13 | 责任链模式 Chain of Responsibility | `chain-of-responsibility/` | ⏳ 待实现 | 待补充 |
| 14 | 命令模式 Command | `command/` | ⏳ 待实现 | 待补充 |
| 15 | 解释器模式 Interpreter | `interpreter/` | ⏳ 待实现 | 待补充 |
| 16 | 迭代器模式 Iterator | `iterator/` | ⏳ 待实现 | 待补充 |
| 17 | 中介者模式 Mediator | `mediator/` | ⏳ 待实现 | 待补充 |
| 18 | 备忘录模式 Memento | `memento/` | ⏳ 待实现 | 待补充 |
| 19 | 观察者模式 Observer | `observer/` | ⏳ 待实现 | 待补充 |
| 20 | 状态模式 State | `state/` | ⏳ 待实现 | 待补充 |
| 21 | 策略模式 Strategy | `strategy/` | ⏳ 待实现 | 待补充 |
| 22 | 模板方法模式 Template Method | `template-method/` | ⏳ 待实现 | 待补充 |
| 23 | 访问者模式 Visitor | `visitor/` | ⏳ 待实现 | 待补充 |

---

## 学习路径建议

1. **先通读单个模式的 `README.md`**，重点看“场景映射”和“架构师点评”。
2. **打开源码 + 测试**，先跑通 `mvn test`，再逐行理解实现细节。
3. **对照 JDK / Spring 源码印证**，例如单例模式可查看 `DefaultSingletonBeanRegistry`。
4. **尝试改造**：把单例改成 Spring Bean、把策略模式改成函数式接口，体会“现代替代方案”。

---

## 代码规范

- 禁止使用 `Animal` / `Dog` / `Cat` / `Shape` / `Circle` 等教学玩具示例。
- 每个模式必须指出：**适用边界、反模式警告、性能/复杂度影响、现代替代方案**。
- 优先使用 Java 17+ 语法糖，但不得为了炫技而牺牲可读性。
- 每个模块必须通过 `mvn test`。

---

## 许可证

本项目仅用于学习与教学，示例代码可自由复制和修改。
