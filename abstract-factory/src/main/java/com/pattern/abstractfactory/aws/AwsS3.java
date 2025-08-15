package com.pattern.abstractfactory.aws;

import com.pattern.abstractfactory.ObjectStorage;

/**
 * AWS S3：具体产品 A2。
 */
public class AwsS3 implements ObjectStorage {

    private final String accessKeyId;
    private final String secretAccessKey;
    private final String region;

    public AwsS3(String accessKeyId, String secretAccessKey, String region) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.region = region;
    }

    @Override
    public String upload(String bucket, String fileName, byte[] data) {
        return "aws-s3://" + region + "/" + bucket + "/" + fileName
                + " [ak:" + mask(accessKeyId) + "]";
    }

    @Override
    public byte[] download(String bucket, String fileName) {
        return new byte[0];
    }

    private String mask(String key) {
        return key.length() > 4 ? "****" + key.substring(key.length() - 4) : "****";
    }
}
