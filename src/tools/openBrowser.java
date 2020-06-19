package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class openBrowser {
	public static void main(String[] args) throws IOException {
//        //url链接地址
//        String localpath = "http://www.baidu.com/s?tn=ichuner&lm=-1&word=" +
//                URLEncoder.encode("江苏省会", "gb2312") + "&rn=1";
//        URL url = new URL(localpath);
//        //根据url直接获得返回的数据
//        BufferedReader breaded = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
//        String osName = System.getProperty("os.name", "");
//        if (osName.startsWith("Windows")) {    //如果是windows系统
//            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + localpath);    //打开浏览器
//        }
		try {
			String url = "http://www.baidu.com/";
			java.net.URI uri = java.net.URI.create(url);
			// 获取当前系统桌面扩展
			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
			// 判断系统桌面是否支持要执行的功能
			if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
				dp.browse(uri);
				// 获取系统默认浏览器打开链接
			}
		} catch (java.lang.NullPointerException e1) {
			// 此为uri为空时抛出异常
			e1.printStackTrace();
		} catch (java.io.IOException e1) {
			// 此为无法获取系统默认浏览器
			e1.printStackTrace();

		}
	}

}
