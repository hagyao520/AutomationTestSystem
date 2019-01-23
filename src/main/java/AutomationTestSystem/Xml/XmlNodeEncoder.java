package AutomationTestSystem.Xml;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @description:
 * @author King
 * @date 2018年8月1日 18:18:074
 * @version 1.0.0
 */
public class XmlNodeEncoder extends XmlEncoder {

	/**
	 * 将对象转换为xml格式数据，对象每个属性为一个节点。<br>
	 * (完整的格式，包含头部，root标签)
	 *
	 * @param Object
	 * @return String（xml字符串）
	 */
	public String objectToXml(Object o) {
		if (isNull(o)) {
			return "";
		}
		Element rootElement = DocumentHelper.createElement(xml_root);
		String valueName = this.getSimpleNameAsProperty(o);
		Element nodeElement = objectToElementNode(o, valueName);
		rootElement.add(nodeElement);
		StringBuilder xml = new StringBuilder(xml_base);
		xml.append(rootElement.asXML());
		return xml.toString();

	}

	/**
	 * 将对象转换为xml格式数据，对象每个属性为一个节点。<br>
	 * (不包含头部，含root标签)
	 *
	 * @param Object
	 * @return String（xml字符串）
	 */
	public String objectToXmlWithoutHead(Object o) {
		if (isNull(o)) {
			return "";
		}
		Element rootElement = DocumentHelper.createElement(xml_root);
		String valueName = this.getSimpleNameAsProperty(o);
		Element nodeElement = objectToElementNode(o, valueName);
		rootElement.add(nodeElement);
		return rootElement.asXML();

	}

	/**
	 * 将Java对象转为xml格式的数据，对象每个熟悉为xml的节点（不包含头部和root）
	 *
	 * @param o
	 * @return
	 */
	public String objectToElementXml(Object o) {
		if (isNull(o)) {
			return "";
		}
		String valueName = this.getSimpleNameAsProperty(o);
		Element nodeElement = objectToElementNode(o, valueName);
		return nodeElement.asXML();

	}

	public Element objectToElement(Object o) {
		if (isNull(o)) {
			return null;
		}
		String valueName = this.getSimpleNameAsProperty(o);
		Element nodeElement = objectToElementNode(o, valueName);
		return nodeElement;

	}

	/**
	 * 将对象转为xml元素节点
	 *
	 * @param Object
	 * @return Element
	 */
	@SuppressWarnings("unchecked")
	public Element objectToElementNode(Object o, String name) {
		Element nodeElement;
		if (isPrimitive(o) || isString(o)) {// 【1】判断是否为基本类型
			nodeElement = primitiveToElementNode(o, name);
		} else if (o instanceof List) {
			nodeElement = listToElementNode((List<Object>) o, name);
		} else if (o instanceof Map) {
			nodeElement = mapToElementNode((Map<Object, Object>) o, name);
		} else if (o instanceof Set) {
			nodeElement = setToElementNode((Set<Object>) o, name);
		} else if (o.getClass().isArray()) {
			nodeElement = arrayToElementNode(o, name);
		} else if (o instanceof Date) {
			nodeElement = dateToElementNode((Date) o, name);
		} else if (o instanceof XmlBean) {
			nodeElement = beanToElementNode(o, name);
		} else {
			nodeElement = beanToElementNode(o, name);
		}
		return nodeElement;
	}

	/**
	 * 将Java的基础数据类型的对象转换为xml的元素节点
	 *
	 * @param Object
	 * @param name
	 * @return Element
	 */
	public Element primitiveToElementNode(Object o, String name) {
		Element nodeElement = DocumentHelper.createElement(name);
		nodeElement.addText(objectToString(o));
		return nodeElement;
	}

	/**
	 * 将实现XmlBean接口的Java对象转换为xml元素节点
	 *
	 * @param Object
	 * @param name
	 * @return Element
	 */
	public Element beanToElementNode(Object o, String name) {
		Element nodeElement = DocumentHelper.createElement(name);
		try {
			List<PropertyDescriptor> propertyDescriptorList = this.getReadMethodPropertyDescriptorList(o.getClass());
			Element element;
			for (PropertyDescriptor pd : propertyDescriptorList) {
				String propertyName = pd.getName();
				Method readMethod = pd.getReadMethod();
				Object data = readMethod.invoke(o);
				if (null != data) {
					element = objectToElementNode(data, propertyName);
					nodeElement.add(element);
				}
			}

		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
		return nodeElement;
	}

	/**
	 * 将List集合对象转换为xml的元素节点
	 *
	 * @param list
	 * @param name
	 * @return
	 */
	public Element listToElementNode(List<Object> list, String name) {
		Element listElement = DocumentHelper.createElement(name);
		Element nodeElement;
		for (Object object : list) {
			String valueName = this.getSimpleNameAsProperty(object);
			nodeElement = objectToElementNode(object, valueName);
			listElement.add(nodeElement);
		}
		return listElement;
	}

	/**
	 * 将set集合转换为xml的元素节点
	 *
	 * @param set
	 * @param name
	 * @return
	 */
	public Element setToElementNode(Set<Object> set, String name) {
		Element listElement = DocumentHelper.createElement(name);
		Element nodeElement;
		for (Object object : set) {
			String valueName = this.getSimpleNameAsProperty(object);
			nodeElement = objectToElementNode(object, valueName);
			listElement.add(nodeElement);
		}
		return listElement;
	}

	/**
	 * 将Map转换为xml元素节点
	 *
	 * @param map
	 * @param property
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Element mapToElementNode(Map map, String property) {
		Element rootElement = DocumentHelper.createElement(property);
		Set<Object> keySet = map.keySet();
		Element dataElement;
		Element keyElement;
		Element valueElement;
		Element dataValueElement;
		for (Object key : keySet) {
			Object data = map.get(key);
			if (null != data) {

				keyElement = objectToElementNode(key, mapKey);
				dataElement = DocumentHelper.createElement(mapValue);
				dataValueElement = DocumentHelper.createElement(mapValue);

				String valueName = this.getSimpleNameAsProperty(data);
				valueElement = objectToElementNode(data, valueName);
				dataValueElement.add(valueElement);

				dataElement.add(keyElement);
				dataElement.add(dataValueElement);
				rootElement.add(dataElement);
			}
		}

		return rootElement;
	}

	/**
	 * 将日期转换为字符串
	 *
	 * @param date
	 * @param name
	 * @return
	 */
	public Element dateToElementNode(Date date, String name) {
		Element nodeElement = DocumentHelper.createElement(name);
		nodeElement.addText(dateFormat.format(date));
		return nodeElement;
	}

	/**
	 * 将对象转换为字符串
	 *
	 * @param o
	 * @return
	 */
	public String objectToString(Object o) {
		if (conversionService.canConvert(o.getClass(), String.class)) {
			return conversionService.convert(o, String.class);
		} else {
			return "";
		}
	}

	public Element arrayToElementNode(Object o, String name) {
		Element listElement = DocumentHelper.createElement(name);
		Element nodeElement;

		int b = Array.getLength(o);
		for (int i = 0; i < b; i++) {
			Object object = Array.get(o, i);
			if (object.getClass().isArray()) {
				nodeElement = arrayToElementNode(object, arrayNodeName);
			} else {
				String valueName = this.getSimpleNameAsProperty(object);
				nodeElement = objectToElementNode(object, valueName);
			}
			listElement.add(nodeElement);
		}

		return listElement;
	}
}
