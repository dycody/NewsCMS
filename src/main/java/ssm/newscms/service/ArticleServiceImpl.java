package ssm.newscms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssm.newscms.dao.ArticleDao;
import ssm.newscms.model.Article;
import ssm.newscms.vo.PageData;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public int save(Article article) {
		if(article == null) {
			return 0;
		}
		if(article.getArticleId() == null) {
			return articleDao.insert(article);
		}else {
			return articleDao.update(article);
		}
	}

	@Override
	public int delete(Integer id) {
		if(id == null) {
			return 0;
		}
		return articleDao.delete(id);
	}

	@Override
	public int update(Article article) {
		if(article == null){
			return 0;
		}
		return articleDao.update(article);
	}
	
	@Override
	public Article findById(Integer id) {
		return articleDao.findById(id);
	}

	@Override
	public List<Article> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<Article> queryByPageAndParams(int curPage, int pageSize,
			Date startDate, Date endDate, Map<String, Object> conMap) {
		if (conMap == null) {
			conMap = new HashMap<String, Object>();
		}
		PageData<Article> pageData = new PageData<Article>();
		int count = articleDao.queryTotalCountByParams(startDate, endDate, conMap);
		pageData.setCurPage(curPage);
		int pageCount = 0;
		if (count % pageSize == 0) {
			pageCount = (int) (count / pageSize);
		} else {
			pageCount = (int) (count / pageSize) + 1;
		}
		pageData.setPageCount(pageCount);
		pageData.setPageSize(pageSize);
		pageData.setTotalCount(count);
		List<Article> datas = articleDao.queryByPageAndParams(curPage, pageSize,startDate,endDate,conMap);
		pageData.setDatas(datas);
		pageData.setCondition(conMap);
		return pageData;
	}

}
