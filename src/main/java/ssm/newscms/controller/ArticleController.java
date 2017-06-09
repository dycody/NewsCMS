package ssm.newscms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.newscms.common.AjaxURI;
import ssm.newscms.model.Article;
import ssm.newscms.service.ArticleService;
import ssm.newscms.util.DateTimeUtil;
import ssm.newscms.util.SerialUtil;
import ssm.newscms.vo.PageData;
import ssm.newscms.vo.ResponseData;

@Controller
@RequestMapping(AjaxURI.ROOT + "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData save(@RequestBody Article article,
			HttpServletRequest request) {
		int articleId = articleService.save(article);
		if (articleId > 0) {
			return new ResponseData(false, "添加失败");
		}
		return new ResponseData(true, "添加成功");
	}

	@RequestMapping(value = "/query/id", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryByid(@RequestBody Map<String, Object> map,
			HttpServletRequest request) {
		Integer articleId = new Integer(map.get("articleId").toString());
		ResponseData result = new ResponseData();
		Article instance = articleService.findById(articleId);
		if (instance == null) {
			result.setSuccess(false);
			result.setMsg("该新闻不存在！");
			return result;
		}
		
		Map<String, Object> data = result.getData();
		data.put("instance", instance);
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query/page", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData queryPage(@RequestBody Map<String, Object> map,
			HttpServletRequest request) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (map.get("condition") != null) {
			condition = (Map<String, Object>) map.get("condition");
		}

		String params = SerialUtil.serializer(map);
		Date startDate = null;
		Date endDate = null;
		String startDateStr = null;
		String endDateStr = null;
		if (map.get("startDate") != null) {
			startDateStr = String.valueOf(map.get("startDate"));
			startDate = DateTimeUtil.getDate(String.valueOf(map
					.get("startDate")));
		}
		if (map.get("endDate") != null) {
			endDateStr = String.valueOf(map.get("endDate"));
			endDate = DateTimeUtil.getDate(String.valueOf(map.get("endDate")));
		}

		Integer pageSize = (Integer) map.get("pageSize");
		Integer curPage = (Integer) map.get("curPage");
		ResponseData result = new ResponseData();
		if (pageSize == null || curPage == null) {
			result.setSuccess(false);
			result.setMsg("参数错误！");
			return result;
		}
		PageData<Article> pageData = articleService.queryByPageAndParams(
				curPage, pageSize, startDate, endDate, condition);
		Map<String, Object> data = result.getData();
		data.put("pageData", pageData);
		data.put("startDateStr", startDateStr);
		data.put("endDateStr", endDateStr);
		data.put("params", params);
		return result;
	}

}
