package AutomationTestSystem.Xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * @description:
 * @author King
 * @date 2018年8月1日 18:18:0747
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
public class XmlTools {

	private Map<String, Class<?>> map = new HashMap<String, Class<?>>();
	private XmlNodeDecoder xmlDecoder = new XmlNodeDecoder();
	private XmlNodeEncoder xmlEncoder = new XmlNodeEncoder();

	public XmlTools() {

	}

	public void add(Class<?> classType) {
		map.put(xmlDecoder.getSimpleNameAsProperty(classType), classType);
	}

	public Class<?> remove(Class<?> classType) {
		return map.remove(classType);
	}

	/**
	 * 将对象转换为完整的xml格式数据（包含头部、root）
	 *
	 * @param o
	 * @return
	 */
	public String objectToXmlNode(Object o) {
		return xmlEncoder.objectToXml(o);
	}

	/**
	 * 将对象转换为xml格式数据（由root标签包含）
	 *
	 * @param o
	 * @return
	 */
	public String objectToXmlNodeWithoutHead(Object o) {
		return xmlEncoder.objectToXml(o);
	}

	/**
	 * 将对象转换为xml的一个节点（不包含头部、root）
	 *
	 * @param o
	 * @return
	 */
	public String objectToElementNode(Object o) {
		return xmlEncoder.objectToXml(o);
	}

	/**
	 * 将xml转换为对应的对象（可有可无头部，必须由root标签包含）
	 *
	 * @param xml
	 * @return
	 */
	public <T> T xmlNodeToObject(String xml) {
		T o = null;
		Element rootElement = xmlDecoder.getRootElement(xml);
		if (null != rootElement) {
			List<Element> elementList = rootElement.elements();
			if (null != elementList && !elementList.isEmpty()) {
				Element nodeElement = elementList.get(0);
				String name = nodeElement.getName();
				Class<?> dataValue = map.get(name);
				if (null != dataValue) {
					o = xmlDecoder.xmlNodeToObject(nodeElement, dataValue);
				}
			}
		}
		return o;
	}

	/**
	 * 将xml转换为对应的对象（可有可无头部，必须由root标签包含）
	 *
	 * @param xml
	 * @return
	 */
	public <T> T xmlNodeToObject(String xml, Class<?> classType) {
		return xmlDecoder.xmlToObject(xml, classType);
	}

	/**
	 * 将xml一个节点转换为对应的对象（对象的外面无标签包含）
	 *
	 * @param xml
	 * @return
	 */
	public <T> T xmlElementNodeToObject(String xml) {

		T o = null;
		Element rootElement = xmlDecoder.getRootElement(xml);
		if (null != rootElement) {

			String name = rootElement.getName();
			Class<?> dataValue = map.get(name);
			if (null != dataValue) {
				o = xmlDecoder.xmlNodeToObject(rootElement, dataValue);
			}

		}
		return o;

	}

	/**
	 * 将xml一个节点转换为对应的对象（对象的外面无标签包含）
	 *
	 * @param xml
	 * @return
	 */
	public <T> T xmlElementNodeToObject(String xml, Class<?> classType) {
		return xmlDecoder.xmlElementToObject(xml, classType);
	}

}
