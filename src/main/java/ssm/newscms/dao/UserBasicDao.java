package ssm.newscms.dao;

import ssm.newscms.model.UserBasic;


public interface UserBasicDao {
	
	public int insert(UserBasic instance);

	public int delete(Integer id);

	public int update(UserBasic instance);
	
	public UserBasic findById(Integer id);
	
	public UserBasic findByAccountAndPassword(String account,String password);

}
