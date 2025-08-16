package com.pattern.abstractfactory;

import com.pattern.abstractfactory.aliyun.AliyunCloudServiceFactory;
import com.pattern.abstractfactory.aliyun.AliyunMns;
import com.pattern.abstractfactory.aliyun.AliyunOss;
import com.pattern.abstractfactory.aliyun.AliyunSms;
import com.pattern.abstractfactory.aws.AwsCloudServiceFactory;
import com.pattern.abstractfactory.aws.AwsS3;
import com.pattern.abstractfactory.aws.AwsSns;
import com.pattern.abstractfactory.aws.AwsSqs;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CloudServiceFactoryTest {

    @Test
    void aliyunFactoryShouldCreateAliyunProductFamily() {
        CloudServiceFactory factory = new AliyunCloudServiceFactory(
                "LTAI123456", "secret123", "cn-hangzhou");

        assertInstanceOf(AliyunOss.class, factory.createObjectStorage());
        assertInstanceOf(AliyunSms.class, factory.createSmsService());
        assertInstanceOf(AliyunMns.class, factory.createMessageQueue());
    }

    @Test
    void awsFactoryShouldCreateAwsProductFamily() {
        CloudServiceFactory factory = new AwsCloudServiceFactory(
                "AKIA123456", "aws-secret", "ap-southeast-1");

        assertInstanceOf(AwsS3.class, factory.createObjectStorage());
        assertInstanceOf(AwsSns.class, factory.createSmsService());
        assertInstanceOf(AwsSqs.class, factory.createMessageQueue());
    }

    @Test
    void saasPlatformShouldUseConsistentProductFamily() {
        CloudServiceFactory factory = new AliyunCloudServiceFactory(
                "LTAI123456", "secret123", "cn-hangzhou");
        SaaSPlatform platform = new SaaSPlatform(factory);

        String url = platform.uploadFile("bucket", "file.txt", new byte[0]);
        assertTrue(url.startsWith("aliyun-oss://"));

        String smsResult = platform.sendNotification("13800138000", "TPL_001",
                Map.of("name", "Java"));
        assertTrue(smsResult.startsWith("[AliyunSMS]"));
    }

    @Test
    void switchingFactoryShouldSwitchProductFamily() {
        CloudServiceFactory aliyun = new AliyunCloudServiceFactory(
                "LTAI123456", "secret123", "cn-hangzhou");
        CloudServiceFactory aws = new AwsCloudServiceFactory(
                "AKIA123456", "aws-secret", "ap-southeast-1");

        SaaSPlatform aliyunPlatform = new SaaSPlatform(aliyun);
        SaaSPlatform awsPlatform = new SaaSPlatform(aws);

        assertTrue(aliyunPlatform.uploadFile("b", "f", new byte[0]).startsWith("aliyun-oss://"));
        assertTrue(awsPlatform.uploadFile("b", "f", new byte[0]).startsWith("aws-s3://"));
    }
}
