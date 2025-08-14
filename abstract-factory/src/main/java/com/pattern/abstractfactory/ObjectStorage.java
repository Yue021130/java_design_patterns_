package com.pattern.abstractfactory;

/**
 * 对象存储服务：抽象产品 A。
 */
public interface ObjectStorage {

    String upload(String bucket, String fileName, byte[] data);

    byte[] download(String bucket, String fileName);
}
