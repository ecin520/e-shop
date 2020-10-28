package com.pytap.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 安全工具包
 * @author Ecin520
 * @date 2020/10/14 14:16
 */
public class SecretUtil {

    /**
     * KEY 密钥
     * */
    private static final String KEY = "1314520775852000";

    /**
     * 偏移量
     * */
    private static final String IV  = "1314520775852001";

    /**
     * 算法-模式-补码方式
     * */
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/NoPadding";

    public static String encrypt(String data) {
        return encrypt(data, KEY, IV);
    }

    public static String decrypt(String data) {
        try {
            data = URLDecoder.decode(data, "UTF-8").replaceAll(" ", "+");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decrypt(data, KEY, IV);
    }

    /**
     * 加密方法 返回base64加密字符串
     * 和前端保持一致
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;

            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     */
    public static String decrypt(String data, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(new Base64().decode(data));
            return new String(original).trim();
        } catch (Exception e) {
            return null;
        }
    }

}
