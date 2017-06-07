package ssm.newscms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapComparator implements Comparator<Map<String, Object>> {

	private String compareKey;
	private boolean desc = false;
	
	public MapComparator(String compareKey,boolean desc){
		this.compareKey = compareKey;
		this.desc = desc;
	}

	public String getCompareKey() {
		return compareKey;
	}

	public void setCompareKey(String compareKey) {
		this.compareKey = compareKey;
	}
	
	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		int result = 0;
		Object v1 = o1.get(compareKey);
		Object v2 = o2.get(compareKey);
		if(v1==null&&v2!=null){
			result = -1;
		}else if(v1!=null&&v2==null){
			result = 1;
		}else if(v1!=null&&v2!=null){
			if(v1 instanceof String){
				result = ((String) v1).compareTo((String)v2);
			}else if(v1 instanceof Integer||v1 instanceof Long||v1 instanceof Short
					||v1 instanceof Double){
				double calResult = Double.parseDouble(v1.toString())-Double.parseDouble(v2.toString());
				if(calResult>0){
					result = 1;
				}else if(calResult<0){
					result = -1;
				}else{
					result = 0;
				}
			}else if(v1 instanceof List){
				int size = ((List<?>)v1).size();
				int size2 = ((List<?>)v2).size();
				if(size>size2){
					result = 1;
				}else if(size<size2){
					result = -1;
				}else{
					result = 0;
				}
			}else if(v1 instanceof Date){
				long mill = ((Date)v1).getTime();
				long mill2 = ((Date)v2).getTime();
				if(mill>mill2){
					result = 1;
				}else if(mill<mill2){
					result = -1;
				}else{
					result = 0;
				}
			}
		}
		if(desc){
			if(result<0){
				result = 1;
			}else if(result>0){
				result = -1;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operDayCount", 1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("operDayCount", 3);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("operDayCount", 2);
		results.add(map);
		results.add(map2);
		results.add(map3);
		Collections.sort(results, new MapComparator("operDayCount",true));
		System.out.println(results);
	}

}
