package com.dream.city.base.utils;

import com.dream.city.base.model.vo.Base64Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * RSA非对称加密解密工具类
 * 
 * @ClassName RsaEncryptUtil
 * @author kokjuis 189155278@qq.com
 * @date 2016-4-6
 * @content
 */
public class RsaEncryptUtil {
	protected static final Log log = LogFactory.getLog("RsaEncryptUtil");
	/** */
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";// RSA/ECB/PKCS1Padding

	/**
	 * String to hold name of the encryption padding.
	 */
	public static final String PADDING = "RSA/NONE/PKCS1Padding";// RSA/NONE/NoPadding

	/**
	 * String to hold name of the security provider.
	 */
	public static final String PROVIDER = "BC";

	/** */
	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "rsa_public_key";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "rsa_private_pkcs8";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	private static BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();

	private static Map<String, Object> rsaMap = new HashMap<String, Object>();

	/**
	 * @Title: encryptByPublicKey
	 * @Description: 公钥加密
	 * @param @param str
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型
	 */
	public static String encryptByPublicKey(String str) throws Exception {
		Security.addProvider(bouncyCastleProvider);

		Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

		// 获得公钥
		Key publicKey = getPublicKey();

		// 用公钥加密
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		// 读数据源
		byte[] data = str.getBytes("UTF-8");

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();

		return Base64Util.encode(encryptedData);
	}

	/**
	 * 私钥加密
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 * @author kokJuis
	 * @date 2016-4-7 下午12:53:15
	 * @comment
	 */
	public static String encryptByPrivateKey(String str) throws Exception {

		Security.addProvider(bouncyCastleProvider);

		Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

		// 获得私钥
		Key privateKey = getPrivateKey();

		// 用私钥加密
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		// 读数据源
		byte[] data = str.getBytes("UTF-8");

		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();

		return Base64Util.encode(encryptedData);
	}

	/**
	 * @Title: decryptByPublicKey
	 * @Description: 公钥解密
	 * @param @param str
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型
	 */
	public static String decryptByPublicKey(String str) throws Exception {
		Security.addProvider(bouncyCastleProvider);

		Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

		// 获得公钥
		Key publicKey = getPublicKey();

		// 用公钥解密
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		// 读数据源
		byte[] encryptedData = Base64Util.decode(str);

		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();

		return new String(decryptedData, "UTF-8");
	}

	/**
	 * @Title: decryptByPrivateKey
	 * @Description: 私钥解密
	 * @param @param str
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型
	 */
	public static String decryptByPrivateKey(String str) throws Exception {
		str = str.replaceAll(" ", "+");
		Security.addProvider(bouncyCastleProvider);

		Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
		// 得到Key
		Key privateKey = getPrivateKey();
		// 用私钥去解密
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		// 读数据源
		byte[] encryptedData = Base64Util.decode(str);

		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();

		// 二进制数据要变成字符串需解码
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * 从文件中读取公钥
	 * 
	 * @return
	 * @throws Exception
	 * @author kokJuis
	 * @date 2016-4-6 下午4:38:22
	 * @comment
	 */
	private static Key getPublicKey() throws Exception {

		if (null != rsaMap.get("getPublicKey")) {
			return (Key) rsaMap.get("getPublicKey");
		}

		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config/rsa_key.properties");
		Properties properties = new Properties();
		properties.load(stream);

		String key = properties.getProperty(PUBLIC_KEY);

		byte[] keyBytes;
		keyBytes = Base64Util.decode(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		rsaMap.put("getPublicKey", publicKey);
		return publicKey;
	}

	/**
	 * 从文件中读取公钥String
	 * 
	 * @return
	 * @throws Exception
	 * @author kokJuis
	 * @date 2016-4-6 下午4:38:22
	 * @comment
	 */
	public static String getStringPublicKey() throws Exception {
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config/rsa_key.properties");
		Properties properties = new Properties();
		properties.load(stream);

		String key = properties.getProperty(PUBLIC_KEY);

		return key;
	}

	/**
	 * 获取私钥
	 * 
	 * @return
	 * @throws Exception
	 * @author kokJuis
	 * @date 2016-4-7 上午11:46:12
	 * @comment
	 */
	private static Key getPrivateKey() throws Exception {

		if (null != rsaMap.get("getPrivateKey")) {
			return (Key) rsaMap.get("getPrivateKey");
		}

		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config/rsa_key.properties");
		Properties properties = new Properties();
		properties.load(stream);
		String key = properties.getProperty(PRIVATE_KEY);
//		log.info("PRIVATE_KEY========================="+key);
		byte[] keyBytes;
		keyBytes = Base64Util.decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		rsaMap.put("getPrivateKey", privateKey);
		return privateKey;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
//			String password = "{\"password\":\"12345678\",'password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678','password':'12345678'}";
			String password = "Dwk%5&*NBgkqAki2";
			try {
				String endata = encryptByPublicKey(password);
				System.out.println("公钥加密："+endata);
				String dedata = decryptByPrivateKey(endata);
				System.out.println("私钥加解密："+dedata);
				
				String aa="PKfDiAEX02Uw1ymw9WpvCi2xh6ISs3ikA36RD9pthZ1MQBF3AVEoPL0BCE+r0kXnEMvLNomeeljctCqF+hv2JyqoB//d/AKMF+cyq1x52HaBKgsX/xYsswzvWMXXokB2WZew97EUq3XZhM0NSJpNLPneJnacr0YbBhBXzsUqMLE=";
				String ed = decryptByPublicKey(aa);
				System.out.println(ed);
/*				ObjectMapper objectMapper = new ObjectMapper();
//				Map readValue = objectMapper.readValue(dedata, Map.class);
//				System.out.println(readValue);
//				System.out.println(readValue.get("password"));
//				JSONObject jSONObject = objectMapper.readValue(dedata, JSONObject.class);
//				System.out.println(jSONObject.toJSONString());
//				System.out.println(jSONObject.getString("password"));
//				System.out.println(jSONObject.getBigDecimal("password"));
				String encryptByPrivateKey = encryptByPrivateKey(password);
				System.out.println(encryptByPrivateKey);
				String decryptByPublicKey = decryptByPublicKey(encryptByPrivateKey);
				System.out.println(decryptByPublicKey);
//				String rsaData = decryptByPrivateKey(rsa);
//				System.out.println("私钥加解密："+rsaData);
//				JSONObject jSONObject2 = objectMapper.readValue(rsaData, JSONObject.class);
//				System.out.println(jSONObject2.toJSONString());
//				System.out.println(jSONObject2.getString("fromWalletId"));
//				System.out.println(jSONObject2.getString("amount"));*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//		String rsa = "YRkmn+5OTYuhx11DUi01KWmfIvE1Br6Yto1ZzxKnebEf8YMmxSOXRBDotLbmeJj8FFy2VnzTDzfVRZ+C91sMlrvOuHD0mflO8PGWdYRmaRjHJWT8fE8FLgRnwU/yvjEeSTNaWNfIcVI/NsYjP1yUpPBnnBzduBPWboK1CkHpfL+MjMh3OGcnBD9DixhnvcYRQJ2mBWx6x5M6CstOGUBbQeTHmT/Vw9gITJWycx6gIVnryxOGJJ3CJWHgNynSi4PKJTgepCdNuNMZQnk2E1axXH9GFhhznP8QZS0/d4MuvYrkdT9mszX5fQZj0fCiAN46Tq8n7vnpBIS9CbjjcjbziA==";
//		String dedata;
//		try {
//			dedata = decryptByPrivateKey(rsa);
//			System.out.println("私钥加解密：" + dedata);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
