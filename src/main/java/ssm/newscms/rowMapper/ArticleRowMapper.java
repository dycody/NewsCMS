package ssm.newscms.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ssm.newscms.model.Article;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		Article result = new Article();
		result.setArticleId(rs.getInt("id"));
		result.setTitle(rs.getString("title"));
		result.setBody(rs.getString("body"));
		result.setCreateDate(rs.getDate("create_date"));
		result.setCreateUserId(rs.getInt("create_user_id"));
		result.setUpdateDate(rs.getDate("update_date"));
		result.setUpdateUserId(rs.getInt("update_user_id"));
		result.setCreateUserName(rs.getString("create_user_name"));
		result.setUpdateUserName(rs.getString("update_user_name"));
		result.setUpdateUserType(rs.getInt("update_user_type"));
		return result;
	}

}
