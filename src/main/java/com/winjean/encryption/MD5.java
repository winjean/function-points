package com.winjean.encryption;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/12/24 9:33
 * 修改人：winjean
 * 修改时间：2018/12/24 9:33
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class MD5 {

    private static byte[] encryptMD5(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
//            String msg = getStringFromException(gse);
//            throw new IOException(msg);
        }
        return bytes;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static void main(String[] args) throws Exception {
        String mdstring = byte2hex(encryptMD5("data"));
        System.out.println(mdstring.length());
        System.out.println(mdstring);

    }

}
