package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class muscleExport {
	public static String loadJson(String url) throws Exception {
		// 读取url,返回json串
		StringBuilder json = new StringBuilder();
		String time13=timestampToDate(13);
		String cookie="_ga=GA1.2.2143043021."+time13+"; _gid=GA1.2.674968454."+time13;
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		yc.setRequestProperty("Cookie", cookie);
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
	/**
	 * 时间戳转时间(10位时间戳)
	 * @param time
	 * @return
	 */
	public static String timestampToDate(int i) {
		if(i==10) {
			long time1 = System.currentTimeMillis() / 1000;
			String timedata=String.valueOf(time1);
			return timedata;
		}else if(i==13) {
			long time1 = System.currentTimeMillis();
			String timedata=String.valueOf(time1);
			return timedata;
		}
		String err="时间转换错误";
		return err;
			    
	}

	public static void main(String[] args) throws Exception {
		String timedata=timestampToDate(10);
		String start="0";
		String rows="739";
		String wrf="jQuery21403476231210988254";
		String host="http://golr.geneontology.org";
		String url = "/solr/select?defType=edismax&qt=standard&indent=on&wt=json&rows="+rows+"&start="+start+"&fl=bioentity,bioentity_name,taxon,panther_family,type,source,synonym,bioentity_label,taxon_label,panther_family_label,score,id&facet=true&facet.mincount=1&facet.sort=count&json.nl=arrarr&facet.limit=25&hl=true&hl.simple.pre=%3Cem%20class=%22hilite%22%3E&hl.snippets=1000&fq=document_category:%22bioentity%22&fq=taxon_subset_closure_label:%22Sus%20scrofa%22&facet.field=source&facet.field=taxon_subset_closure_label&facet.field=type&facet.field=panther_family_label&facet.field=annotation_class_list_label&facet.field=regulates_closure_label&q=muscle*&qf=bioentity%5E2&qf=bioentity_label_searchable%5E2&qf=bioentity_name_searchable%5E1&qf=bioentity_internal_id%5E1&qf=synonym_searchable%5E1&qf=isa_partof_closure_label_searchable%5E1&qf=regulates_closure%5E1&qf=regulates_closure_label_searchable%5E1&qf=panther_family_searchable%5E1&qf=panther_family_label_searchable%5E1&qf=taxon_label_searchable%5E1&packet=6&callback_type=search&json.wrf="+wrf+"_"+timedata+"&_="+timedata;
		System.out.println(toJson(host+url));

	}

	public static String toJson(String url) throws Exception {
		StringBuffer sb = new StringBuffer();
		String s = loadJson(url);
		String str1=s.substring(0, s.indexOf("{"));
	    String str2=s.substring(str1.length(), s.length()-1);
		String str=str2;
		System.out.println("str="+str);
		System.out.println();
		
		
		// 待解析的json字符串
		try {
			// 因为json字符串是大括号包围，所以用JSONObject解析
			JSONObject json = new JSONObject(str);
			JSONObject response= json.getJSONObject("response");
			Object numFound = response.get("numFound");
			String t=String.valueOf(numFound);
			System.out.println(t);

			/*
			 * 普通元素，根据类型直接获取
			 */
//			JSONObject params = json.getJSONObject("params");
//			String fl = params.getString("fl");
//			sb.append("fl:" + fl+ "\r\n" + "params:" + params + "\r\n————————————————————————————————");

			/*
			 * 属性大括号包括，先获取JSONObject对象
			 */
			JSONObject res = json.getJSONObject("response");
			JSONArray docs= res.getJSONArray("docs");
			for (int i = 0; i < docs.length(); i++) {
				JSONObject honor = docs.getJSONObject(i);
				String bioentity_label = honor.getString("bioentity_label");
				sb.append(bioentity_label + "\r\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return sb.toString();
	}
}
