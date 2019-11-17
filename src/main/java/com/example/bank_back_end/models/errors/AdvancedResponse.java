package com.example.bank_back_end.models.errors;

public class AdvancedResponse extends SimpleResponse {
	private Object data;

	public AdvancedResponse() {
	}

	public AdvancedResponse(int code, String message, Object data) {
		super(code, message);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static AdvancedResponse Create(Object data, String message, int code) {
		return new AdvancedResponse(code, message, data);
	}
}
