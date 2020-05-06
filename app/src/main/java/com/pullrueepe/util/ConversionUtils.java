package com.pullrueepe.util;

import android.util.Log;


import com.pullrueepe.base.AppConstants;

import static com.pullrueepe.base.AppConstants.EXCEPTION;

public class ConversionUtils implements AppConstants {

    private static String FORMAT_CFM_DATA = "%.2f";

    public static String md5Format(String s) {
        StringBuffer sb = null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes());
            sb = new StringBuffer();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            Log.e(EXCEPTION, e.toString());
        }
        assert sb != null;
        return sb.toString();
    }


    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static byte[] hexStringToByteArray(String response) {
        int len = response.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(response.charAt(i), 16) << 4)
                    + Character.digit(response.charAt(i + 1), 16));
        }
        return data;
    }


}
