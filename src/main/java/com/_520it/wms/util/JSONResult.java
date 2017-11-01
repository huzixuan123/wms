package com._520it.wms.util;

import lombok.Getter;

@Getter
public class JSONResult {
	private boolean success = true;//响应是否成功
	private String msg;//错误信息

	//创建对象时,返回success为true,msg为null
	public JSONResult() {
	}

	//标记为响应失败
	public void mark(String errorMsg) {
		this.success = false;
		this.msg = errorMsg;
	}
}
