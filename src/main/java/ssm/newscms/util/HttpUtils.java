package ssm.newscms.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONObject;

import com.alibaba.fastjson.JSON;

//Http请求的工具类  
public class HttpUtils {

	private static final int TIMEOUT_IN_MILLIONS = 5000;

	public interface CallBack {
		void onRequestComplete(String result);
	}

	/**
	 * 异步的Get请求
	 * 
	 * @param urlStr
	 * @param callBack
	 */
	public static void doGetAsyn(final String urlStr, final CallBack callBack) {
		new Thread() {
			public void run() {
				try {
					String result = doGet(urlStr, "utf-8");
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();
	}

	/**
	 * 异步的Post请求
	 * 
	 * @param urlStr
	 * @param params
	 * @param callBack
	 * @throws Exception
	 */
	public static void doPostAsyn(final String urlStr, final String params,
			final CallBack callBack) throws Exception {
		new Thread() {
			public void run() {
				try {
					String result = doPost(urlStr, params);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();

	}

	/**
	 * Get请求，获得返回数据
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String urlStr, String charSet) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];

				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				System.out.println(new String(baos.toByteArray(),Charset.forName(charSet)));
				return new String(baos.toByteArray(),Charset.forName(charSet));
			} else {
				throw new RuntimeException(" responseCode is not 200 ... ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
			}
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
			}
			conn.disconnect();
		}

		return null;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String doPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

			if (param != null && !param.trim().equals("")) {
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数为map类型。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> doPost(String urls, Map<String, Object> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("");
		try {
	        //创建连接 
	        URL url = new URL(urls);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setUseCaches(false);
	        connection.setInstanceFollowRedirects(true);
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.connect();
	        // POST请求 
	        if(param != null) {
	        	DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	        	out.writeBytes(JSONObject.toJSONString(param));
	        	out.flush();
	        	out.close();
	        }
	        // 读取响应 
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String lines;
	        
	        while ((lines = reader.readLine()) != null) {
	            lines = URLDecoder.decode(lines, "utf-8");
	            sb.append(lines);
	        }
	        reader.close();
	        
	        // 断开连接 
	        connection.disconnect(); 
	    } catch (MalformedURLException e) { 
	        e.printStackTrace(); 
	    } catch (UnsupportedEncodingException e) { 
	        e.printStackTrace(); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    }
		String str = sb.toString();
		if(StringUtil.isNotNullOrEmpty(str)) {
			Object succesResponse = JSON.parse(str); 
			result = (Map<String, Object>)succesResponse; 
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "http://10.10.10.57:8080/seeyon/rest/token";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "test");
		map.put("password", "Qq123456");
		//String result = HttpUtils.doPost(url, map);
		//Map<String, Object> result = HttpUtils.doPost(url, map);
		String orgName = java.net.URLEncoder.encode("浙江公共安全技术研究院","utf-8");
		url = "http://10.10.10.57:8080/seeyon/rest/data/members/"+orgName+"?token=63ed7418-228f-49a5-bfe4-a5094d61f825";
		System.out.println(url);
		System.out.println(doGet(url, "utf-8"));
		//System.out.println(result);
	}
}