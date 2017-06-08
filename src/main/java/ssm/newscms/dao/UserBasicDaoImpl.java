package ssm.newscms.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ssm.newscms.model.UserBasic;
import ssm.newscms.rowMapper.UserBasicRowMapper;
import ssm.newscms.util.StringUtil;

@Repository
public class UserBasicDaoImpl extends DaoBase implements UserBasicDao {
	
	@Autowired(required = false)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired(required = false)
	@Qualifier("namedParamJDBCResource")
	private NamedParameterJdbcTemplate namedParamJDBCResource;

	@Override
	public int insert(UserBasic instance) {
		if(instance == null) {
			return 0;
		}
		String sql = "insert into user_basic (account,password,type,name) "
				+ "values (:account,MD5(:password),1,:name)";
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(
				instance);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJDBCResource.update(sql, sqlParameterSource, keyHolder,
				new String[] { "id" });
		return keyHolder.getKey().intValue();
	}

	@Override
	public int delete(Integer id) {
		if(id == null) {
			return 0;
		}
		String sql = "update user_basic set remove=1 where id = "+id;
		return jdbcTemplate.update(sql);
	}

	@Override
	public int update(UserBasic instance) {
		if(instance == null) {
			return 0;
		}
		String sql = "update user_basic set password=MD5(:password),name=:name,type=:type "
				+ "where id = :userId";
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(
				instance);
		return namedParamJDBCResource.update(sql, sqlParameterSource);
	}

	@Override
	public UserBasic findById(Integer id) {
		if (id == null) {
			return null;
		}
		String sql = "select * from user_basic where id = "+id;
		List<UserBasic> list = jdbcTemplate.query(sql,
				new UserBasicRowMapper());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public UserBasic findByAccountAndPassword(String account, String password) {
		if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)) {
			return null;
		}
		String sql = "select * from user_basic where account = "+account+" MD5(password) = "+password;
		List<UserBasic> list = jdbcTemplate.query(sql,
				new UserBasicRowMapper());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

}
