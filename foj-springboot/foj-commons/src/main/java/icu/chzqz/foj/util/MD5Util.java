package icu.chzqz.foj.util;


import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMD5ByString(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
}
