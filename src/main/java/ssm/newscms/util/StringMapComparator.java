package ssm.newscms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMapComparator implements Comparator<Map<String, String>> {

	private String compareKey;
	private boolean desc = false;
	
	public StringMapComparator(String compareKey,boolean desc){
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
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		int result = 0;
		String v1 = o1.get(compareKey);
		String v2 = o2.get(compareKey);
		if(v1==null&&v2!=null){
			result = -1;
		}else if(v1!=null&&v2==null){
			result = 1;
		}else if(v1!=null&&v2!=null){
			result = ((String) v1).compareTo((String)v2);
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
