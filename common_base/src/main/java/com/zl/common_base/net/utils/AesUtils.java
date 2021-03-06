package com.zl.common_base.net.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/***
 * 关键点在于：秘钥
 * 生产秘钥 -- 用秘钥进行加密 -- 解密
 * 思路来源：https://blog.csdn.net/qq_34853874/article/details/75066678?fps=1&locationNum=8
 */

public class AesUtils {
    private final static String HEX = "0123456789ABCDEF";
    private  static final String AES = "AES";//AES 加密
    private  static final String  SHA1PRNG="SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法


    public static final String SECRETKEY = "ZjG5eL9yLabZjGI5"; //密钥
    public static final String IV = "6MgKWKZPzAwN5kCd"; //偏移量
    /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/CBC/PKCS5Padding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式


    /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String secretKey, String iv) {
        try {
            SecretKeySpec key = createKey(secretKey);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 加密(结果为16进制字符串) **/
    public static String encrypt(String content, String secretKey, String iv) {
        byte[] data = null;
        try {
//            String encodeString = Base64.encodeToString(content.getBytes(),Base64.DEFAULT);
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
//         data = Base64.encode(data,Base64.DEFAULT);
         data = encrypt(data, secretKey, iv);
//         String result = byte2hex(data);
          String result = Base64.encodeToString(data,Base64.DEFAULT);
        return result;
    }


    /** 解密字节数组 **/
    public static byte[] decrypt(byte[] content, String secretKey, String iv) {
        try {
            SecretKeySpec key = createKey(secretKey);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /** 解密(输出结果为字符串) **/
    public static String decrypt(String content, String secretKey, String iv) {
        byte[] data = null;
        try {
//            data = hex2byte(content);
            data = Base64.decode(content,Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, secretKey, iv);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /** 字节数组转成16进制字符串 **/
    public static String byte2hex(byte[] b) { // 一个字节的数，
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp ;
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }


    /** 将hex字符串转换成字节数组 **/
    private static byte[] hex2byte(String inputString) {
        if (inputString == null || inputString.length() < 2) {
            return new byte[0];
        }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }



    /** ------------------------ 测试需要下方的自测 -------------------------  */

    /** 创建密钥 **/
    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }


        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    /** 创建偏移量 **/
    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }

}
