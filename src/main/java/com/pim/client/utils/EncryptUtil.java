package com.pim.client.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EncryptUtil {

    public static String create16Key() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, 16);
    }

    public static String getUidKey(String uid) {
        return SecureUtil.md5(uid);
    }

    public static String encrypt(String key, String data) {
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.encryptBase64(data);
    }

    public static String decrypt(String key, String encryptStr) {
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.decryptStr(encryptStr, StandardCharsets.UTF_8);
    }

}
