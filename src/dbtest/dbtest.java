package dbtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class dbtest {
	private static DBUtil util = new DBUtil();
	private static ExcelWrite ew=new ExcelWrite();

	public static void main(String[] args) {
		dbDaoImp dao=new dbDaoImp();
		String tbname="geneid";
		Object[] params= {"geneID"};
		String tbname1="lr32_lt32";
		String geneid="";
		
		//type参数为埋点
		List<Map<String, Object>> tblist=dao.getObjs(tbname,params,1);
		List<String> ls=new ArrayList<String>();
		
		if(tblist.size()<1) {
			System.out.println("查询无结果");
		}
		
		//输出字段名称和字段值
		try {
			for (int i = 0; i < tblist.size(); i++) {
				for (Map.Entry<String, Object> map1 : tblist.get(i).entrySet()) {
					//System.out.println(map1.getKey()+","+map1.getValue());
					geneid="%"+(String) map1.getValue()+"%";
				}
			}
			Object[] params1= {"geneID",geneid};
			List<Map<String, Object>> tblist1=dao.getObjs(tbname1,params1,2);
			if(tblist1.size()>0) {
				for (int i = 0; i < tblist1.size(); i++) {
					for (Map.Entry<String, Object> map2 : tblist1.get(i).entrySet()) {
						System.out.println(map2.getKey()+","+map2.getValue());
						
					}
				}
			}else {
				System.out.println(tbname1+" 找不到 "+geneid);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close();
		}
	}
}
