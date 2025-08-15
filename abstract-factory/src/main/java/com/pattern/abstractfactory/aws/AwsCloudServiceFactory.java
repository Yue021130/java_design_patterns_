package com.pattern.abstractfactory.aws;

import com.pattern.abstractfactory.CloudServiceFactory;
import com.pattern.abstractfactory.MessageQueue;
import com.pattern.abstractfactory.ObjectStorage;
import com.pattern.abstractfactory.SmsService;

/**
 * AWS 具体工厂：创建 AWS 产品族。
 */
public class AwsCloudServiceFactory implements CloudServiceFactory {

    private final String accessKeyId;
    private final String secretAccessKey;
    private final String region;

    public AwsCloudServiceFactory(String accessKeyId, String secretAccessKey, String region) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.region = region;
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AwsS3(accessKeyId, secretAccessKey, region);
    }

    @Override
    public SmsService createSmsService() {
        return new AwsSns(accessKeyId, secretAccessKey, region);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AwsSqs(accessKeyId, secretAccessKey, region);
    }
}
