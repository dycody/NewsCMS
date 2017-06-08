package ssm.newscms.vo;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

	// true调用正常，false不正常（比如参数错误）
	private boolean success = true;
	// 调用不正常时页面的提示信息
	private String msg;
	// 实际返回到前台的数据
	private Map<String, Object> data = new HashMap<String, Object>();
	// 错误代码（错误代码，保留，暂时不用）
	private String errorCode;

	public ResponseData() {
	}

	public ResponseData(boolean success, String msg, Map<String, Object> data,
			String errorCode) {
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.errorCode = errorCode;
	}
	
	public ResponseData(boolean success, String msg, Map<String, Object> data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public ResponseData(boolean success, String msg, String errorCode) {
		this.success = success;
		this.msg = msg;
		this.errorCode = errorCode;
	}

	public ResponseData(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public ResponseData(boolean success) {
		this.success = success;
	}

	public ResponseData(Map<String, Object> data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
