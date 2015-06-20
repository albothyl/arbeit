package com.job.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWarpper {
	private String responseCode;
	private String message;
	private Object data;

	public void setResponseOK() {
		this.responseCode = "OK";
	}

	public void setResponseNG() {
		this.responseCode = "NG";
	}
}
