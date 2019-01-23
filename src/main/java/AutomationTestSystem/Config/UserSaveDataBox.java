package AutomationTestSystem.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author King
 * @date 2016年1月23日 上午12:21:59
 * @version 0.0.1
 */
public class UserSaveDataBox {

	public static final String path = "src/main/resources/data/UserSaveData.xml";
	private Map<String, UserSaveData> map = new HashMap<String, UserSaveData>();

	public Map<String, UserSaveData> getMap() {
		return map;
	}

	public void setMap(Map<String, UserSaveData> map) {
		this.map = map;
	}

	public void put(String key, UserSaveData value) {
		if(null==map){
			map = new HashMap<String, UserSaveData>();
		}
		map.put(key, value);
	}

	public UserSaveData get(String key) {
		return (null==map)?null:map.get(key);
	}

	public void remove(String key) {
		if(null!=map){
			map.remove(key);
		}
	}

	public int getSize() {
		return (null==map)?0:map.size();
	}

	public void remove(int count) {
		List<UserSaveData> list = new ArrayList<UserSaveData>(map.values());
		int size = list.size();
//		System.out.println(size);
		int index = (count > size) ? size : count;
//		System.out.println(index);
		for (int i = 0; i < index; i++) {
			map.remove(list.get(i).getAccount());
			System.out.println("移除成功："+list.get(i).getAccount());
		}
	}
}
