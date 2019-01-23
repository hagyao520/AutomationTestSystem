package AutomationTestSystem.Xml;

import java.lang.reflect.Type;

public abstract class XmlDecoder extends XmlCoder {
	/**
	 * 完整的xml格式数据转换为对象（包含头部和root节点）
	 * 
	 * @param xml
	 * @param classType
	 * @return
	 */
	public abstract <T> T xmlToObject(String xml, Class<?> classType);

	/**
	 * 不包含头部和root节点的xml转换为对象（仅以此对象为开始节点）
	 * 
	 * @param xml
	 * @param classType
	 * @return
	 */
	public abstract <T> T xmlElementToObject(String xml, Class<?> classType);
	
	public abstract <T> T xmlToObject(String xml, Type type);
}
