package ssm.newscms.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DeepMapComparator implements Comparator<Map<String, Object>> {

	private String key;
	private String subKey;
	private boolean desc = false;
	private boolean sameOrder = true;

	public DeepMapComparator(String key, String subKey, boolean desc, boolean sameOrder) {
		this.key = key;
		this.subKey = subKey;
		this.desc = desc;
		this.sameOrder = sameOrder;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSubKey() {
		return subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	public boolean isSameOrder() {
		return sameOrder;
	}

	public void setSameOrder(boolean sameOrder) {
		this.sameOrder = sameOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		int result = 0;
		Object v1 = o1.get(key);
		Object v2 = o2.get(key);
		if (v1 == null && v2 != null) {
			result = -1;
		} else if (v1 != null && v2 == null) {
			result = 1;
		} else if (v1 != null && v2 != null) {
			if(v1 instanceof List) {
				int size = ((List<?>) v1).size();
				int size2 = ((List<?>) v2).size();
				if (size > size2) {
					result = 1;
				} else if (size < size2) {
					result = -1;
				} else {
					result = 0;
				}
				Collections.sort((List<Map<String, Object>>) v1, new MapComparator(subKey, sameOrder?desc:(!desc)));
				Collections.sort((List<Map<String, Object>>) v2, new MapComparator(subKey, sameOrder?desc:(!desc)));
			}
		}
		if (desc) {
			if (result < 0) {
				result = 1;
			} else if (result > 0) {
				result = -1;
			}
		}
		return result;
	}

}
