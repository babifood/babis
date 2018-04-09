package com.seeyon.shbb.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class CherishXor {
    public static String KEY = "XDWL-CHERISH-KEY-1221-%@!";
	
    public static void main(String[] args) throws UnsupportedEncodingException {
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(map==null || map.isEmpty()) {
    		System.out.println("map is empty!!");
    	} else {
    		System.out.println("map is not empty!!");
    	}
    	
        String value = "oatest";
        //String key = KEY;
        
        // 加密前输出
        print("加密前", value);
        
        System.out.print( encodeString("oatest"));
    }
    
    /**
     * 加密字符串
     * @param value
     * @param key
     * @return
     */
    public static String encodeString(String value) {
    	String newValue = "";
    	
    	byte[] byteEncodeArray = encode(value.getBytes(), KEY);
    	newValue = new String(byteEncodeArray);
    	
    	return newValue;
    }
    
    /**
     * 解密字符串
     * @param value
     * @param key
     * @return
     */
    public static String dncodeString(String value) {
    	String newValue = "";
    	String _value = StringUtils.isBlank(value)?"":value;
    	
    	byte[] byteDecodeArray = decode(_value.getBytes(), KEY);
    	newValue = new String(byteDecodeArray);
    	
    	return newValue;
    }
    
    /**
     * 加密处理
     * @param src
     * @param key
     * @return
     */
    private static byte[] encode(byte[] src, String key) {
        byte[] byteKeyArray = new byte[0];
        byte[] byteEncArray = new byte[src.length];
        
        // 转换加密钥匙的循环处理
        while(byteKeyArray.length < src.length) {
            byteKeyArray = (new String(byteKeyArray) + key).getBytes();
        }
        
        // 转换
        for (int i = 0; i < src.length; i++) {
            byteEncArray[i] = (byte)(src[i]^byteKeyArray[i]);
        }
        return byteEncArray;
    }
    
    /**
     * 解密
     * @param src
     * @param key
     * @return
     */
    private static byte[] decode(byte[] src, String key) {
        return encode(src, key);
    }
    
    /**
     * 转换成16进制文字
     * @param value
     * @return
     */
    private static String getDump16(byte[] value) {
        
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < value.length; i++) {
            String hex = Integer.toHexString((int)value[i] & 255);
            
            // 添补前4位
            hex = "0000" + hex;
            hex = hex.substring(hex.length() - 4, hex.length());
            
            // 添加空白并且每10位变行(空白区切り、10桁ずつ改行)
            buf.append(hex + (i % 10 == 9?System.getProperty("line.separator"):" "));
        }
        return buf.toString().trim();
    }
    
    private static void print(String title, String value) {
        System.out.println("【 " + title + " 】");
        System.out.println("-----------------------------");
        System.out.println(value);
        System.out.println(getDump16(value.getBytes()));
        System.out.println();
        System.out.println();
    }
}