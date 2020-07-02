package dbtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class dbtest {
	private static DBUtil util = new DBUtil();
	private static ExcelHelper ew = new ExcelHelper();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dbDaoImp dao = new dbDaoImp();
		String tbname = "geneid";
		Object[] params = { "geneID","muscleName" };
		String tbname1 = "lr32_lt32";
		Object[] geneid = {};
		List<String> tblistall = new ArrayList<String>();
		tblistall.add("geneID");
		// type参数为埋点
		List<Map<String, Object>> tblist = dao.getObjs(tbname, params, 1);

		if (tblist.size() < 1) {
			System.out.println("查询无结果");
		}
		String name="";
		String geneid1="";
		
		// 输出字段名称和字段值
		try {
			for (int i = 0; i < tblist.size(); i++) {
				for (Map.Entry<String, Object> map1 : tblist.get(i).entrySet()) {
					//System.out.println("tblist:"+map1.getKey()+","+map1.getValue());
					if(map1.getKey().equals("muscleName")) {
						name=(String)map1.getValue();
					}
					if(map1.getKey().equals("geneID")) {
						String p="%"+(String)map1.getValue()+"%";
						String geneidvs=(String)map1.getValue();
						
						Object[] params1= {"geneID",p};
						List<Map<String, Object>> findResult = dao.getObjs(tbname1, params1, 2);
						if (findResult.size() > 0) {
							for (int j = 0; j < findResult.size(); j++) {
								for (Map.Entry<String, Object> map2 : findResult.get(j).entrySet()) {
									if(map2.getKey().equals("geneID")) {
										geneid1=(String)map2.getValue();
									}
									if(map2.getKey().equals("p_adjust")) {
										System.out.println((String)map2.getValue());
										ew.addExcel(tbname1, geneidvs,geneid1, (String)map2.getValue(), name);
									}
								}
							}
						} else {
							//System.out.println(tbname1 + " 找不到 " + geneid);
						}
					}

					
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close();
		}
	}
}
