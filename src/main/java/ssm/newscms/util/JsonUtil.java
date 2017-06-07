package ssm.newscms.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	public static String object2String(Object obj){
		String ret =  "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			ret = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static Object string2Object(String strObj,Class<?> clazz){
		Object ret = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			ret = mapper.readValue(strObj, clazz);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String strObj = "{\"curPage\":0,\"keyWord\":\"杭州\",\"hlFore\":null,\"begin\":\"\",\"topSize\":null,\"otherCondition\":{\"isExact\":\"0\"},\"exportCount\":null}";
		//OperLogPageCondition pc = (OperLogPageCondition)string2Object(strObj,OperLogPageCondition.class);
		//System.out.println(pc);
	}
	
}
