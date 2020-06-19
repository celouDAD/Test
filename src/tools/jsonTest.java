package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class jsonTest {

	public static String loadJson(String url) throws Exception {
		// 读取url,返回json串
		StringBuilder json = new StringBuilder();
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
//		yc.setRequestProperty("Authorization",
//				"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiZW50ZXJwcmlzZUlkIjoxLCJpc0dvZCI6dHJ1ZSwiaWF0IjoxNTcxNjQzNzkyLCJleHAiOjE1NzIyNDg1OTJ9.YxgMHBZXQO-25Th0LUDEyWw6eKL-t6PbnZVH9tLPuzY");
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine = null;
		while ((inputLine = in.readLine()) != null) {
			json.append(inputLine);
		}
		in.close();

		return json.toString();
	}

	public static void main(String[] args) throws Exception {
		String url = "https://api.wide.meipai.com/comment/get_fold_list.json?client_channel_id=app%20store&client_country=CN&client_id=1189857304&client_language=zh-Hans&client_model=iPhone7%2C2&client_network=WIFI&client_operator=%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8&client_os=12.3.1&gid=1778462906&idfa=321E3CBE-92C4-44F5-9F5B-283F9D3EF7F6&idfv=B84A98CA-F86F-4682-ADFE-1E1FAEFC392F&lat=24.618884870000013&lng=118.03820354000003&mac=B84A98CA-F86F-4682-ADFE-1E1FAEFC392F&os_type=iOS&sig=f8e4a2ac364cbf88fac6322d0ec17ba3&sigTime=1571647235995&sigVersion=1.3&version=2.4.0&video_id=1533883013551888401";
		System.out.println(toJson(url));

	}

	public static String toJson(String url) throws Exception {
		StringBuffer sb = new StringBuffer();
		//String str = loadJson(url);
		String str="{\"type\":\"2\",\"customerInfo\":{\"id\":18935,\"tags\":[\"潜在客户\",\"请吃饭\"],\"updatedAt\":\"2019-10-30T10:40:12.180Z\"}}";
		System.out.println(str);
		System.out.println();
		
		
		// 待解析的json字符串
		try {
			// 因为json字符串是大括号包围，所以用JSONObject解析
			JSONObject json = new JSONObject(str);
			JSONObject customerInfo = json.getJSONObject("customerInfo");
			Object tags = customerInfo.get("tags");
			String t=String.valueOf(tags);
			System.out.println(t);

			/*
			 * 普通元素，根据类型直接获取
			 */
			JSONObject meta = json.getJSONObject("meta");
			int name = meta.getInt("code");
			String age = meta.getString("msg");
			sb.append("code:" + name + "\r\n" + "msg:" + age + "\r\n————————————————————————————————");

			/*
			 * 属性大括号包括，先获取JSONObject对象
			 */
			JSONObject res = json.getJSONObject("response");
			JSONArray data = res.getJSONArray("comments");
			for (int i = 0; i < data.length(); i++) {
				JSONObject honor = data.getJSONObject(i);
				String hero_name = honor.getString("id");
				String hero_position = honor.getString("content");
				sb.append("\r\ncomments:\r\n"+"id:" + hero_name + "\r\n"+"content:" + hero_position+ "\r\n————————————————————————————————");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return sb.toString();
	}
}
