package com.dream.city.base.utils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *  
 * */
public class CryptUtil {

    private static String digest(String s, String algorithm) {
        if (s == null || s.length() <= 0) {
            return s;
        }

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance(algorithm);
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String sha1(String s) {
        return digest(s, "SHA1");
    }

    public static String md5(String s) {
        return digest(s, "MD5");
    }

    public static void main(String[] args) {
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", "rW7CLCVl3NZl");
		params.put("equipmentId", "rW7CLCVl3NZl");
		params.put("password", "vfChain1");
		params.put("coinType", "ETH");
		Set<String> set = new TreeSet<String>(params.keySet());
		String queryString = "";
		for (String key : set) { // 遍历所有key
			String value = params.get(key) + "";
			queryString += key + "=" + value + "&";
		}
		queryString += "key=cGFuaG06OTg2OTI1X19weqYW5obQada" ;
		System.out.println(queryString);
        // System.out.println(sha1("456123"));
         System.out.println(md5(queryString));
    }
}
