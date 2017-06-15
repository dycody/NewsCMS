package ssm.newscms.shiro;

public class OnlineSession {
	/** 
     * 客户计算机的ip. 
     */  
    private String ip = null;  
    /** 
     * 客户登录名. 
     */  
    private String userAccount = null;  
    /** 
     * 客户登录系统时间. 
     */  
    private String onlineTime = null;  
      
    /** 
     * 构造器. 
     * @param ip 
     * @param loginId 
     * @param onlineTime 
     */  
    public OnlineSession(String ip,String userAccount,String onlineTime){  
        this.ip=ip;  
        this.userAccount=userAccount;  
        this.onlineTime=onlineTime;  
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}  
      
}
