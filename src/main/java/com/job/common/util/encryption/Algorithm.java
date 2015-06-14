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

	public static Algorithm valueOf(int value) {
		switch (value) {
			case 1:
				return SHA0;
			case 2:
				return SHA1;
			case 3:
				return SHA224;
			case 4:
				return SHA256;
			case 5:
				return SHA384;
			case 6:
				return SHA512;
			default:
				throw new AssertionError("Unkown value: " + value);
		}
	}
}
