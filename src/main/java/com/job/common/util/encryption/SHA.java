package com.job.common.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class SHA {

	public String encryption(String inputPassword, String Algorithm) {
		MessageDigest messageDigest;
		String savePassword = "";
		try {
			messageDigest = MessageDigest.getInstance(Algorithm);
			messageDigest.update(inputPassword.getBytes());
			byte[] mb = messageDigest.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				savePassword += s;
			}
			return savePassword;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

}
