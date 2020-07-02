package dbtest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class dbDaoImp implements dbDao {
	private DBUtil util = new DBUtil();

	@Override
	public boolean save() {
		String sql = "insert into log(title,author,content,time,userid,imgurl) values(?,?,?,?,?,?)";
		Object[] params = {};
		return util.update(sql, params) >= 1;
	}

	@Override
	public boolean update() {
		String sql = "update log set title=?,author=?,content=?,time=?,userid=?,imgurl=? where id=?";
		Object[] params = {};
		return util.update(sql, params) >= 1;
	}

	@Override
	public boolean delete(String tbname, String key, String value) {
		String sql = "delete from " + tbname + " where " + key + "=?";
		return util.update(sql, value) >= 1;
	}

	@Override
	public boolean delete(String tbname, String key, int value) {
		String sql = "delete from " + tbname + " where " + key + "=?";
		return util.update(sql, value) >= 1;
	}

	@Override
	public List<Map<String, Object>> getObjs(String tbname, Object[] params,int type) {
		String sql = "select p_adjust,geneID from " + tbname;
		String objstring="select ";
		if(type==1) {
			for(int i=0;i<params.length;i++) {
				if(i<params.length-1) {
					objstring=objstring+params[i]+",";
					sql=objstring+"from "+tbname;
				}else if(i==params.length-1){
					objstring=objstring+params[i];
					sql=objstring+" from "+tbname;
				}				
			}	
		}else if (type==2) {
			if(params.length>1 && params.length%2==0) {
				for (int i = 0; i < params.length/2; i++) {
					if(i==0) {
						sql=sql+" where "+params[i]+" like ?";
					}else {
						sql=sql+" and "+params[i]+"=?";
					}
				}
			}else if(params.length>1 && params.length%2!=0){
				System.out.println("参数错误:tbname="+tbname+",Object={"+objstring);
				System.exit(0);
			}
		}
		
		/**
		 * 将结果集存入list
		 */
		ResultSet rs = util.getSet(sql,type,params);
		ResultSetMetaData md;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			md = rs.getMetaData();//得到结果集列的属性  
			int columns = md.getColumnCount();// 得到记录有多少列
			int i;
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (i = 0; i < columns; i++) {
					map.put(md.getColumnName(i + 1),
							getValueByType(rs, md.getColumnType(i + 1), md.getColumnName(i + 1)));
					
				}
				list.add(map);
			} // 得到结果集列的属性

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return list;
	}
	
	/**
	 * 数据库返回字段类型转换
	 * @param rs
	 * @param type
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	private static Object getValueByType(ResultSet rs, int type, String name) throws SQLException {
		switch (type) {
		case Types.NUMERIC:
			return rs.getLong(name);
		case Types.VARCHAR:
			// if(rs.getString(name)==null){
			// return "";
			// }
			return rs.getString(name);
		case Types.DATE:
			// if(rs.getDate(name)==null){
			// return System.currentTimeMillis();
			// }
			return rs.getDate(name);
		case Types.TIMESTAMP:
			return rs.getTimestamp(name).toString().substring(0, rs.getTimestamp(name).toString().length() - 2);
		case Types.INTEGER:
			return rs.getInt(name);
		case Types.DOUBLE:
			return rs.getDouble(name);
		case Types.FLOAT:
			return rs.getFloat(name);
		case Types.BIGINT:
			return rs.getLong(name);
		default:
			return rs.getObject(name);
		}
	}

}
