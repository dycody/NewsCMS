package ssm.newscms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ssm.newscms.util.StringUtil;


@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@RequestMapping(value="/{path}")
	public ModelAndView topLevel(@PathVariable String path,HttpServletRequest request){
		if(StringUtil.isNullOrEmpty(path)){
			path = "index";
		}
		//System.out.println(request.getParameter("testParam")+"-"+request.getParameter("testZh_cn"));
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String,String[]> params = request.getParameterMap();
		for (String paramName : params.keySet()) {
			if(StringUtils.isEmpty(paramName)){
				continue;
			}
			String[] values = params.get(paramName);
			if(ArrayUtils.isNotEmpty(values)){
				if(values.length==1){					
					model.put(paramName, values[0]);
				}else{
					model.put(paramName, values);
				}
			}
		}
		
		model.put("moduleCode", path);
		ModelAndView mav = new ModelAndView(path,model);
        return mav;
	}
	
	@RequestMapping(value="/{path}/{subPath}")
	public ModelAndView twoLevel(@PathVariable String path,@PathVariable String subPath,HttpServletRequest request){
		//System.out.println(request.getParameter("testParam")+"-"+request.getParameter("testZh_cn"));
		Map<String, Object> model = new HashMap<String, Object>();
		String fullPath = null;
		if(StringUtil.isNullOrEmpty(path)){
			fullPath = "index";
		}else if(StringUtil.isNotNullOrEmpty(subPath)){
			fullPath = path+"/"+subPath;
		}else{
			fullPath = path;
		}
		
		Map<String,String[]> params = request.getParameterMap();
		for (String paramName : params.keySet()) {
			if(StringUtils.isEmpty(paramName)){
				continue;
			}
			String[] values = params.get(paramName);
			if(ArrayUtils.isNotEmpty(values)){
				if(values.length==1){					
					model.put(paramName, values[0]);
				}else{
					model.put(paramName, values);
				}
			}
		}
		
		model.put("moduleCode", fullPath.replace("/", "#"));
		ModelAndView mav = new ModelAndView(fullPath,model);
        return mav;
	}
}
