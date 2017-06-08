package ssm.newscms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ssm.newscms.model.Article;
import ssm.newscms.vo.PageData;

public interface ArticleService {
	
	public int save(Article article);
	
	public int delete(Integer id);
	
	public int update(Article article);
	
	public Article findById(Integer id);
	
	public List<Article> findByKeyword(String keyword);
	
	public PageData<Article> queryByPageAndParams(int curPage,
			int pageSize, Date startDate, Date endDate,
			Map<String, Object> conMap);

}
