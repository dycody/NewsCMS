package ssm.newscms.util;

import java.util.Comparator;

public class StringListComparator implements Comparator<String> {

	private boolean desc = false;
	
	public StringListComparator(boolean desc){
		this.desc = desc;
	}
	
	@Override
	public int compare(String v1, String v2) {
		int result = 0;
		if(v1 instanceof String){
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

}
