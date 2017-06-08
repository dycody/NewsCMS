package ssm.newscms.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ssm.newscms.model.Article;

public interface ArticleDao {
	
	public int insert(Article instance);

	public int delete(Integer id);

	public int update(Article instance);
	
	public Article findById(Integer id);
	
	public List<Article> findByKeyword(String keyword);
	
	public List<Article> queryByPageAndParams(int curPage, int pageSize,Date startDate, Date endDate,
			Map<String, Object> conMap);

	public int queryTotalCountByParams(Date startDate, Date endDate,
			Map<String, Object> conMap);

}
