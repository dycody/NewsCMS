package ssm.newscms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class DaoBase {

	public String parseQueryCondition(Map<String,Object> conMap,boolean isOr){
		String condition = null;
		if(conMap==null){
			return null;
		}
		Set<String> keys = conMap.keySet();
		for (String key : keys) {
			if(conMap.get(key)==null){
				continue;
			}
			String conSql = null;
			Object conValue = conMap.get(key);
			if(conValue instanceof String){
				conSql = key + " like '%"+conValue+"%' ";
			} else {
				conSql = key + " = "+conValue+" ";
			}
			if(condition==null){
				condition = " where ( " + conSql;
			}else{
				condition += (isOr?" or ":" and ") + conSql;
			}
		}
		if(StringUtils.isNotEmpty(condition)){			
			condition += " ) ";
		}
		return condition;
	}
	
	public String parseQueryCondition(Map<String,Object> conMap,boolean isOr,String table){
		String condition = null;
		if(conMap==null){
			return null;
		}
		Set<String> keys = conMap.keySet();
		for (String key : keys) {
			if(conMap.get(key)==null){
				continue;
			}
			String conSql = null;
			Object conValue = conMap.get(key);
			if(conValue instanceof String){
				conSql = table + "." + key + " like '%"+conValue+"%' ";
			} else {
				conSql = table + "." + key + " = "+conValue+" ";
			}
			if(condition==null){
				condition = " where ( " + conSql;
			}else{
				condition += (isOr?" or ":" and ") + conSql;
			}
		}
		if(StringUtils.isNotEmpty(condition)){			
			condition += " ) ";
		}
		return condition;
	}
	
	public String partitionIntInSql(String inEle,List<Integer> ids){
		String result = null;
		if(CollectionUtils.isEmpty(ids)){
			return result;
		}
		List<List<Integer>> subLists = new ArrayList<List<Integer>>();
		for (int i=0;i<ids.size();i++) {
			if(i%1000==0){				
				List<Integer> subIds = new ArrayList<Integer>();
				for (int j=i;(j<i+1000&&j<ids.size());j++) {
					subIds.add(ids.get(j));
				}
				subLists.add(subIds);
			}
		}
		for (List<Integer> list : subLists) {
			String partSql = null;
			for (Integer object : list) {
				if(partSql==null){
					partSql = object+"";
				}else{
					partSql += ","+(object+"");
				}
			}
			partSql = inEle + " in ("+partSql+") ";
			if(result==null){
				result = partSql;
			}else{
				result += " or " + partSql;
			}
		}
		return result;
	}
	
	public String partitionStringInSql(String inEle,List<String> ids){
		String result = null;
		if(CollectionUtils.isEmpty(ids)){
			return result;
		}
		List<List<String>> subLists = new ArrayList<List<String>>();
		for (int i=0;i<ids.size();i++) {
			if(i%1000==0){				
				List<String> subIds = new ArrayList<String>();
				for (int j=i;(j<i+1000&&j<ids.size());j++) {
					subIds.add(ids.get(j));
				}
				subLists.add(subIds);
			}
		}
		for (List<String> list : subLists) {
			String partSql = null;
			for (String object : list) {
				if(partSql==null){
					partSql = "'"+object+"'";
				}else{
					partSql += ","+"'"+object+"'";
				}
			}
			partSql = inEle + " in ("+partSql+") ";
			if(result==null){
				result = partSql;
			}else{
				result += " or " + partSql;
			}
		}
		return result;
	}
	
}
