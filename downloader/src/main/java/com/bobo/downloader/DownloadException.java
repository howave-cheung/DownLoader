package com.bobo.downloader;

/**
 * @author bobo
 * @date 2022/12/30
 */
public class DownloadException extends RuntimeException {
	private int code;
	private String msg;

	public DownloadException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
