package ssm.newscms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ESShellUtil {

	@SuppressWarnings("unused")
	private static String copyFile(String sourFilePath, String destFilePath,
			String sourContent, String destContent, String dateMonth) {
		String fileContent = "";
		// 目标地址
		File file = new File(sourFilePath);
		if (file.isFile() && file.exists()) {
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				try {
					// 循环，每次读一行
					while ((line = reader.readLine()) != null) {
						// 10.118.1.211
						fileContent += line.replace(sourContent, destContent)
								//.replace("10.118.1.211", "10.10.10.214")
								/*.replace("10.118.1.211", "10.118.1.211")
								.replace("10.10.10.214", "10.118.1.212")
								.replace("10.10.10.215", "10.118.1.213")
								.replace("10.10.10.216", "10.118.1.214")*/;
					}
					reader.close();
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		/*
		 * if(sourFilePath.indexOf(".bat")!=-1){
		 * System.out.println(fileContent); }
		 */
		try {
			FileOutputStream output = new FileOutputStream(destFilePath);
			output.write(fileContent.getBytes("UTF-8"));
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (destFilePath.indexOf(".bat") != -1) {
			fileContent = fileContent.replace("operate_log_mapping.js",
					dateMonth + "\\operate_log_mapping.js");
			fileContent = fileContent.replace("operate_log.js", dateMonth
					+ "\\operate_log.js");
		}
		return fileContent;
	}

	private static void removeFiles(File rootFolder) {
		if ("no_operate_time".equals(rootFolder.getName())) {
			return;
		}
		File[] files = rootFolder.listFiles();
		if(files!=null){			
			for (File file : files) {
				if(file.getName().indexOf(".bat")==-1){
					continue;
				}
				if (file.isFile()) {
					file.delete();
				} else if (file.listFiles().length > 0) {
					removeFiles(file);
				} else {
					file.delete();
				}
			}
		}
	}

	public static void main(String[] args) {

		String[] years = new String[] { "2011", "2012", "2013", "2014", "2015","2016" 
				};
		String rootPath = "E:\\product_index";
		File root = new File(rootPath);
		if(root.exists()){			
			removeFiles(root);
		}
		String batContent = "";
		for (String year : years) {
			for (int i = 1; i <= 12; i++) {
				String month = i + "";
				if (i < 10) {
					month = "0" + i;
				}
				batContent += "curl -XPUT http://10.118.1.211:9200/zhsj_"+year+month+"\r\n";
				batContent += "curl -XPOST http://10.118.1.211:9200/zhsj_"+year+month+"/OPERATE_LOG/_mapping -d @operate_log_mapping.js\r\n";
				batContent += "\r\n";
			}
		}

		batContent += "curl -XPUT http://10.118.1.211:9200/no_operate_time\r\n";
		batContent += "curl -XPOST http://10.118.1.211:9200/no_operate_time/OPERATE_LOG/_mapping -d @no_operate_time_mapping.js\r\n";
		
		batContent += "\r\n";
		
		batContent += "curl -XPUT http://10.118.1.211:9200/no_insert_time\r\n";
		batContent += "curl -XPOST http://10.118.1.211:9200/no_insert_time/OPERATE_LOG/_mapping -d @no_insert_time_mapping.js\r\n";
		
		batContent += "\r\n";
		
		batContent += "curl -XPUT http://10.118.1.211:9200/no_operate_insert_time\r\n";
		batContent += "curl -XPOST http://10.118.1.211:9200/no_operate_insert_time/OPERATE_LOG/_mapping -d @no_operate_insert_time_mapping.js";
		
		try {
			FileOutputStream output = new FileOutputStream(rootPath
					+ "\\create_index.bat");
			output.write(batContent.getBytes("UTF-8"));
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
