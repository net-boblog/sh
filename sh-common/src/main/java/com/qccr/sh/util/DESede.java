package com.qccr.sh.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * DESede(3DES)加解密--庞健松 2013.12.04
 */
public class DESede {
	
	private static final Logger log = LoggerFactory.getLogger(DESede.class);
	
	/** 定义加密算法。 可用 DES,DESede,Blowfish*/
	private static final String Algorithm = "DESede"; 
	
	private static final char[] hex = "0123456789ABCDEF".toCharArray();
	
	public static final String keyStr = "3A167C2E5D53A827E86A3B16C376C2C31536765BCBA1EEC9";
	
	/**必须是24位*/
	private static final byte[] key = strToBytes(keyStr);
	
	/**
	 * 加密，参数有问题返回null
	 * @param src 明文
	 * @return 16进制字符串密文
	 */
	public static String encrypt(final String src) {
		if(src == null){
			return null;
		}
		try {
			byte[] origin = src.getBytes("utf-8");
			return bytesToStr(des3Init(Cipher.ENCRYPT_MODE, origin));
		} catch (Exception e) {
			log.error("encrypt fail.", e);
			return null;
		}
	}
	
	/**
	 * 解密，参数有问题返回null
	 * @param cipher （16进制字符串密文）
	 * @return 明文
	 */
	public static String decrypt(final String cipher) {
		if(cipher == null){
			return null;
		}
		try {
			byte[] origin = des3Init(Cipher.DECRYPT_MODE, strToBytes(cipher));
			return new String(origin, "utf-8");
		} catch (Exception e) {
			log.error("decrypt fail.", e);
			return null;
		}
	}
	
	/**
	 * 3des加解密
	 * @param mode Cipher.ENCRYPT_MODE | Cipher.DECRYPT_MODE
	 * @param input byte[]
	 * @return byte[]
	 * @throws Exception NoSuchAlgorithm,InvalidKey,NoSuchPadding,BadPadding,IllegalBlockSize
	 */
	private static byte[] des3Init(final int mode, final byte[] input) throws Exception{
		// 根据【给定的字节数组key】和【 指定算法DESede(3des)】构造一个密钥
		SecretKey secretKey = new SecretKeySpec(key, Algorithm);
		// 加解密
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(mode, secretKey);
		return cipher.doFinal(input);
	}

	/**
	 * byte数组转换为16进制字符串
	 * @param b [1, 33, 79, 88, -120]
	 * @return "01214f5888"
	 */
	private static String bytesToStr(final byte[] b){
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			s.append(hex[(b[i]>>>4)&0xf]);
			s.append(hex[b[i]&0xf]);
		}
		return s.toString();
	}
	
	/**
	 * 16进制字符串转换为相应的byte数组
	 * @param src "81214f5888"
	 * @return [-127, 33, 79, 88, -120]
	 */
	private static byte[] strToBytes(final String src){
		char[] c = src.toCharArray();
		byte[] b = new byte[c.length/2];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)((Character.digit((int)c[2*i], 16)<<4) 
						| Character.digit((int)c[2*i+1], 16));
		}
		return b;
	}
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		System.out.println(DESede.decrypt("ED30649818CACB78F91E39574DE68957D73BAC14A98C1FDD"));
		System.out.println(encrypt("IP_Entry_Internal"));
		System.out.println("IP_Entry_Internal".equals(decrypt("6BE710B951109FB77CDD7A3C046C30D7BD2745BEE74D5266")));
		System.out.println(System.currentTimeMillis()-a);
	}
}
