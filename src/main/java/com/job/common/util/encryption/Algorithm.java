package com.job.common.util.encryption;

public enum Algorithm {
	SHA0("SHA-0"),
	SHA1("SHA-1"),
	SHA224("SHA-224"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");

	private final String value;

	Algorithm(String value) {
		this.value = value;
	}

	public String stringValue() {
		return value;
	}
}
