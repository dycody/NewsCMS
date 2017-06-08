package ssm.newscms.model;

public class UserBasic {
	
	private Integer userId;
	private String account;
	private String password;
	private Integer type;
	private String name;
	private Boolean remove;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getRemove() {
		return remove;
	}
	public void setRemove(Boolean remove) {
		this.remove = remove;
	}
	

}
