package ssm.newscms.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import ssm.newscms.model.Article;
import ssm.newscms.rowMapper.ArticleRowMapper;
import ssm.newscms.util.CollectionUtil;
import ssm.newscms.util.StringUtil;

@Repository
public class ArticleDaoImpl extends DaoBase implements ArticleDao {
	
	@Autowired(required = false)
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired(required = false)
	@Qualifier("namedParamJDBCResource")
	private NamedParameterJdbcTemplate namedParamJDBCResource;

	@Override
	public int insert(Article instance) {
		if(instance == null) {
			return 0;
		}
		String sql = "insert into acticle (title,body,create_date,create_user,update_date,update_user) "
				+ "values (:title,:body,CURRENT_TIMESTAMP,:createUserId,CURRENT_TIMESTAMP,:updateUserId)";
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(
				instance);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJDBCResource.update(sql, sqlParameterSource, keyHolder,
				new String[] { "id" });
		return keyHolder.getKey().intValue();
	}

	@Override
	public int delete(Integer id) {
		if (id == null) {
			return 0;
		}
		String sql = "delete from acticle where id = " + id;
		return jdbcTemplate.update(sql);
	}

	@Override
	public int update(Article instance) {
		if(instance == null) {
			return 0;
		}
		String sql = "update acticle set title=:title,body=:body,update_date=CURRENT_TIMESTAMP,update_user=:updateUserId "
				+ "where id = :acticleId";
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(
				instance);
		return namedParamJDBCResource.update(sql, sqlParameterSource);
	}

	@Override
	public List<Article> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findById(Integer id) {
		if (id == null) {
			return null;
		}
		String sql = "select * from v_acticle where id = "+id;
		List<Article> list = jdbcTemplate.query(sql,
				new ArticleRowMapper());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Article> queryByPageAndParams(int curPage, int pageSize,
			Date startDate, Date endDate, Map<String, Object> conMap) {
		if (conMap == null) {
			conMap = new HashMap<String, Object>();
		}
		Integer startRowNum = pageSize * (curPage - 1);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRowNum",
				Integer.toString(pageSize * (curPage - 1) + 1));
		paramMap.put("endRowNum", Integer.toString(pageSize * curPage));
		String condition = parseQueryCondition(conMap, false,"article");
		
		String sql = "select * from v_article "
				+ (StringUtil.isNullOrEmpty(condition) ? "" : condition);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate!=null){
			String startDateStr = sdf.format(startDate) + " 00:00:00";
			if(CollectionUtil.isNullOrEmpty(conMap)){
				sql +=" where ";
			}else{
				sql +=" and ";
			}
			sql += "update_date >= str_to_date('"+startDateStr+"','%Y-%m-%d %H:%i:%s')";
		}
		if(endDate!=null){
			String endDateStr = sdf.format(endDate) + " 23:59:59";
			if(CollectionUtil.isNullOrEmpty(conMap) && startDate==null){
				sql +=" where ";
			}else{
				sql +=" and ";
			}
			sql += "update_date <= str_to_date('"+endDateStr+"','%Y-%m-%d %H:%i:%s')";
		}
		sql += " order by update_date desc, id desc ";
		
		sql = "select A.* from (" + sql + ") A limit "+startRowNum+","+pageSize+"";
		List<Article> result = namedParamJDBCResource.query(sql,
				new ArticleRowMapper());
		if (result == null) {
			return new ArrayList<Article>();
		}else{
			return result;
		}
		
	}

	@Override
	public int queryTotalCountByParams(Date startDate, Date endDate,
			Map<String, Object> conMap) {
		if (conMap == null) {
			conMap = new HashMap<String, Object>();
		}
		String condition = parseQueryCondition(conMap, false);
		String sql = "select count(*) from article "
				+ (StringUtil.isNullOrEmpty(condition) ? "" : condition);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate!=null){
			String startDateStr = sdf.format(startDate) + " 00:00:00";
			if(CollectionUtil.isNullOrEmpty(conMap)){
				sql +=" where ";
				
			}else{
				sql +=" and ";
			}
			sql += "update_date >= str_to_date('"+startDateStr+"','%Y-%m-%d %H:%i:%s')";
		}
		if(endDate!=null){
			String endDateStr = sdf.format(endDate) + " 23:59:59";
			if(CollectionUtil.isNullOrEmpty(conMap) && (startDate==null)){
				sql +=" where ";
				
			}else{
				sql +=" and ";
			}
			sql += "update_date <= str_to_date('"+endDateStr+"','%Y-%m-%d %H:%i:%s')";
		}
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
