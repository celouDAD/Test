package dbtest;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface dbDao {
	boolean save();
	boolean update();
	boolean delete(String tbname,String key,int value);
	/**
	 * 传入表名，字段和字段值删除对应数据
	 * @param tbname
	 * @param key
	 * @param value
	 * @return
	 */
	boolean delete(String tbname,String key,String value);
	/**
	 * 传入表名和表字段及字段值对象数组，判断数组长度做相应处理
	 * @param tbname
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getObjs(String tbname,Object[] params,int type);
}
