package ssm.newscms.util;

import org.apache.http.client.fluent.Request;

public class HttpClientUtil {

	public static boolean sniff(String url){
		boolean isNomal = false;
    	try{    		
    		Request.Get(url)
    				.execute().returnContent();
    		isNomal = true;
    	}catch(Exception e){
    		isNomal = false;
    	}
    	//System.out.println(isNomal);
    	return isNomal;
	}
	
	public static void main(String[] args) {
		System.out.println(HttpClientUtil.sniff("http://127.0.0.1:8080/zhsj-web/actions/index"));
	}
	
}
