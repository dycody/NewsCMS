package ssm.newscms.util;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {

	@SuppressWarnings("rawtypes")
	public static boolean isNotNullOrEmpty(Collection c){
		return c!=null&&(!c.isEmpty());
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Collection c){
		return null==c||c.isEmpty();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotNullOrEmpty(Map m){
		return m!=null&&(!m.isEmpty());
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Map m){
		return null==m||m.isEmpty();
	}
	
}
