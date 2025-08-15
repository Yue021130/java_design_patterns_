package com.pattern.abstractfactory.aliyun;

import com.pattern.abstractfactory.ObjectStorage;

/**
 * 阿里云 OSS：具体产品 A1。
 */
public class AliyunOss implements ObjectStorage {

    private final String accessKeyId;
    private final String accessKeySecret;
    private final String region;

    public AliyunOss(String accessKeyId, String accessKeySecret, String region) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.region = region;
    }

    @Override
    public String upload(String bucket, String fileName, byte[] data) {
        return "aliyun-oss://" + region + "/" + bucket + "/" + fileName
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
