package com.dream.city.base.utils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.web3j.utils.Numeric;


public class AddressChecker {
	
	
    /*---- Class constants ----*/
 
	private final static String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
 
	private final static char[] arr = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * btc(bch,usdt)地址是否有效
     * 
     * return: true有效,false无效
     */
    public static boolean bitCoinAddressValidate(String addr) {
        if (addr.length() < 26 || addr.length() > 35)
            return false;
        byte[] decoded = decodeBase58To25Bytes(addr);
        if (decoded == null)
            return false;

        byte[] hash1 = sha256(Arrays.copyOfRange(decoded, 0, 21));
        byte[] hash2 = sha256(hash1);

        return Arrays.equals(Arrays.copyOfRange(hash2, 0, 4), Arrays.copyOfRange(decoded, 21, 25));
    }


    private static byte[] decodeBase58To25Bytes(String input) {
        BigInteger num = BigInteger.ZERO;
        for (char t : input.toCharArray()) {
            int p = ALPHABET.indexOf(t);
            if (p == -1)
                return null;
            num = num.multiply(BigInteger.valueOf(58)).add(BigInteger.valueOf(p));
        }

        byte[] result = new byte[25];
        byte[] numBytes = num.toByteArray();
        System.arraycopy(numBytes, 0, result, result.length - numBytes.length, numBytes.length);
        return result;
    }

    private static byte[] sha256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    
    public static boolean isETHValidAddress(String input) {
        if (StringUtils.isBlank(input) || !input.startsWith("0x"))
            return false;
        return isValidAddress(input);
    }
    
    public static boolean isValidAddress(String input) {
        String cleanInput = Numeric.cleanHexPrefix(input);

        try {
            Numeric.toBigIntNoPrefix(cleanInput);
        } catch (NumberFormatException e) {
            return false;
        }

        return cleanInput.length() == 40;
    }
    
    public static void main(String[] args) {
//		System.out.println(bitCoinAddressValidate("1HB5ySkzcaBzKEAhK1NhQkrujcJJnwt7so"));
//		System.out.println(isMANValidAddress("MAN.2pYHzCUb911LKa8HwTTXVuafFUsrW"));
//    	String a = "1312TSgsfgff";
//    	System.out.println(a.substring(a.length()-1));
	}
   
}
