package ssm.newscms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ssm.newscms.model.UserBasic;

public class UserBasicRowMapper implements RowMapper<UserBasic> {

	@Override
	public UserBasic mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBasic result = new UserBasic();
		result.setUserId(rs.getInt("id"));
		result.setAccount(rs.getString("account"));
		result.setPassword(rs.getString("password"));
		result.setType(rs.getInt("type"));
		result.setName(rs.getString("name"));
		result.setRemove(rs.getBoolean("remove"));
		return result;
	}
	

}
