package com.job.common.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class SHA {

	public String encryption(String inputPassword, String Algorithm) {
		MessageDigest md;
		String savePassword = "";
		try {
			md = MessageDigest.getInstance(Algorithm);
			md.update(inputPassword.getBytes());
			byte[] mb = md.digest();
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
