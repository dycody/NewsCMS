package ssm.newscms.util;
/*package com.zjipst.sign.util;

import org.apache.cxf.jaxrs.client.WebClient;


public class CXFClientUtil {

	public static WebClient getClient(String baseUrl) {
		//String baseUrl = "http://127.0.0.1:8080/zhsj-web/services/alarm/";
		WebClient client = WebClient.create(baseUrl); 
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("a", 1);
        map.put("b", "b");
        AlarmKeyWordDeployCondition alarmKeyWordDeployCondition = new AlarmKeyWordDeployCondition();
        alarmKeyWordDeployCondition.setDeployBegin("201205");
        alarmKeyWordDeployCondition.setDeployEnd("201506");
        System.out.println(client.reset().path("alarmKeyWord/query").accept(MediaType.APPLICATION_XML).post(alarmKeyWordDeployCondition, AlarmKeyWords.class));
        AlarmKeyWords alarmKeyWords = new AlarmKeyWords();
        System.out.println(client.reset().path("alarmKeyWord").accept(MediaType.TEXT_PLAIN).get(String.class));
        System.out.println(client.reset().path("alarmKeyWord/request/234234").accept(MediaType.TEXT_PLAIN).get(String.class));
        System.out.println(client.reset().path("alarmKeyWord/list").accept(MediaType.APPLICATION_XML).get(AlarmKeyWords.class));
        System.out.println(client.reset().path("alarmKeyWord/putData/2014-03-12 23:59:59").accept(MediaType.APPLICATION_XML).put(new AlarmKeyWord()).getEntity());
        client.reset().path("alarmKeyWord/remove/23").delete();
        client.close();
		return client;
	}
	
}
*/