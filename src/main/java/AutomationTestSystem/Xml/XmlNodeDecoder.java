package AutomationTestSystem.Xml;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @description:
 * @author King
 * @date 2018年8月1日 18:18:074
 * @version 1.0.0
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class XmlNodeDecoder extends XmlDecoder {

	public Element getRootElement(String xml) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			logger.error(e.getMessage(), e);
		}
		return null == document ? null : document.getRootElement();
	}

	public <T> T xmlToObject(String xml, Class<?> classType) {
		T o = null;
		if (null != classType) {
			Element rootElement = getRootElement(xml);
			if (null != rootElement) {
				Element nodeElement = rootElement.element(this.getSimpleNameAsProperty(classType));
				if (null != nodeElement) {
					o = xmlNodeToObject(nodeElement, classType);
				}
			}
		}
		return o;
	}

	public <T> T xmlElementToObject(String xml, Class<?> classType) {
		T o = null;
		if (null != classType) {
			Element rootElement = getRootElement(xml);
			if (null != rootElement) {
				o = xmlNodeToObject(rootElement, classType);
			}
		}
		return o;
	}

	/**
	 * @param element
	 * @param classType
	 * @return
	 */
	public <T> T xmlNodeToObject(Element element, Class classType) {
		Object o = null;
		if (isPrimitive(classType) || isString(classType)) {// 【1】判断是否为基本类型
			o = xmlNodeToPrimitive(element, classType);
		} else if (List.class.isAssignableFrom(classType)) {
			o = xmlNodeToList(element, classType);
		} else if (Map.class.isAssignableFrom(classType)) {//
			o = xmlNodeToMap(element, classType);
		} else if (Set.class.isAssignableFrom(classType)) {//
			o = xmlNodeToSet(element, classType);
		} else if (Date.class.isAssignableFrom(classType)) {//
			o = stringToDate(element.getText());
		} else if (XmlBean.class.isAssignableFrom(classType)) {
			o = xmlNodeToBean(element, classType);
		} else {//
			o = xmlNodeToBean(element, classType);
		}
		return (T) o;
	}

	public Object xmlNodeToBean(Element element, Class<?> classType) {
		Object object = null;

		try {

			object = classType.newInstance();// Class.forName(classType.getName()).newInstance();

			List<PropertyDescriptor> propertyDescriptorList = this.getWriteMethodPropertyDescriptorList(classType);
			for (PropertyDescriptor pd : propertyDescriptorList) {
				String propertyName = pd.getName();
				Method writeMethod = pd.getWriteMethod();
				Field field = this.getField(classType, propertyName);
				if (null != writeMethod && null != field) {
					Element nodeElement = element.element(propertyName);
					if (null != nodeElement) {
						Object value = xmlNodeToAttribute(nodeElement, field);
						writeMethod.invoke(object, value);
					}
				}
			}
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		}
		return object;
	}

	public Object xmlNodeToAttribute(Element element, Field field) {
		Class<?> classType = field.getType(); // 得到field的class及类型全路径
		Type type = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
		Object o = null;
		if (isPrimitive(classType) || isString(classType)) {// 【1】判断是否为基本类型
			o = xmlNodeToPrimitive(element, classType);
		} else if (List.class.isAssignableFrom(classType) && type != null) {
			o = xmlNodeToList(element, type);
		} else if (Map.class.isAssignableFrom(classType) && type != null) {//
			o = xmlNodeToMap(element, type);
		} else if (Set.class.isAssignableFrom(classType) && type != null) {//
			o = xmlNodeToSet(element, type);
		} else if (Date.class.isAssignableFrom(classType)) {
			o = stringToDate(element.getText());
		} else if (classType.isArray()) {
			o = xmlNodeToArray1(element, classType);
		} else if (XmlBean.class.isAssignableFrom(classType)) {
			o = xmlNodeToBean(element, classType);
		} else {//
			o = xmlNodeToBean(element, classType);
		}
		return o;
	}


	public Object parameterizedTypeToAttribute(Element element, Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			Type rawType = pt.getRawType();
			if (rawType instanceof Class) {
				Class<?> classType = (Class<?>) rawType;
				if (List.class.isAssignableFrom(classType)) {
					return xmlNodeToList(element, type);
				} else if (Set.class.isAssignableFrom(classType)) {
					return xmlNodeToSet(element, type);
				} else if (Map.class.isAssignableFrom(classType)) {
					return xmlNodeToMap(element, type);
				}
			}
		}
		return null;
	}

	public Object xmlNodeToPrimitive(Element element, Class<?> classType) {
		return (conversionService.canConvert(String.class, classType)) ? conversionService.convert(element.getText(), classType) : null;
	}

	public List<Object> xmlNodeToList(Element element, Type type) {
		if (type instanceof ParameterizedType) {
			List<Element> elementList = element.elements();
			if (null != elementList && !elementList.isEmpty()) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Type[] types = parameterizedType.getActualTypeArguments();
				if (types.length > 0) {
					List<Object> objectList = new ArrayList<Object>();
					Object o;
					if (types[0] instanceof Class<?>) {
						Class<?> genericClass = (Class<?>) types[0];
						for (Element node : elementList) {
							o = xmlNodeToObject(node, genericClass);
							objectList.add(o);
						}
					} else if (types[0] instanceof ParameterizedType) {
						for (Element node : elementList) {
							o = parameterizedTypeToAttribute(node, types[0]);
							objectList.add(o);
						}
					}

					return objectList;
				}
			}
		}
		return null;
	}

	public Set<Object> xmlNodeToSet(Element element, Type type) {
		if (type instanceof ParameterizedType) {
			List<Element> elementList = element.elements();
			if (null != elementList && !elementList.isEmpty()) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Type[] types = parameterizedType.getActualTypeArguments();
				if (types.length > 0) {
					Set<Object> objectSet = new HashSet<Object>();
					Object o;
					if (types[0] instanceof Class<?>) {
						Class<?> genericClass = (Class<?>) types[0];
						for (Element node : elementList) {
							o = xmlNodeToObject(node, genericClass);
							objectSet.add(o);
						}
					} else if (types[0] instanceof ParameterizedType) {
						for (Element node : elementList) {
							o = parameterizedTypeToAttribute(node, types[0]);
							objectSet.add(o);
						}
					}
					return objectSet;
				}
			}
		}
		return null;
	}

	public Map<Object, Object> xmlNodeToMap(Element element, Type type) {
		Map<Object, Object> map = null;
		if (type instanceof ParameterizedType) {// 【3】如果是泛型参数的类型

			List<Element> elementList = element.elements();
			if (null != elementList && !elementList.isEmpty()) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Type[] types = parameterizedType.getActualTypeArguments();
				if (types.length > 1) {
					map = new HashMap<Object, Object>();
					for (Element node : elementList) {

						Element keyElement = node.element(mapKey);
						Object key = null;
						if (null != keyElement) {
							if (types[0] instanceof Class<?>) {
								Class<?> genericClass = (Class<?>) types[0];
								key = xmlNodeToObject(keyElement, genericClass);
							} else if (types[0] instanceof ParameterizedType) {
								key = parameterizedTypeToAttribute(keyElement, types[0]);
							}
						}

						Element dataElement = node.element(mapValue);

						Object value = null;
						if (null != dataElement && null != dataElement.elements() && !dataElement.elements().isEmpty()) {
							Element dataValueElement = (Element) dataElement.elements().get(0);

							if (types[1] instanceof Class<?>) {
								Class<?> valueGenericClass = (Class<?>) types[1];
								value = xmlNodeToObject(dataValueElement, valueGenericClass);
							} else if (types[1] instanceof ParameterizedType) {
								value = parameterizedTypeToAttribute(dataValueElement, types[1]);
							}
						}

						map.put(key, value);
					}
				}
			}
		}
		return map;
	}

	public Object xmlNodeToArray1(Element element, Class<?> baseClassType) {

		Object o = xmlArrayNodeToArray1(element, baseClassType);
		Object array = conversionService.convert(o, baseClassType);

		return array;
	}

	public Object xmlArrayNodeToArray1(Element element, Class<?> baseClassType) {
		List<Object> list = new ArrayList<Object>();
		List<Element> elementList = element.elements();
		if (null != elementList && !elementList.isEmpty()) {

			int size = elementList.size();
			Class<?> componentType = baseClassType.getComponentType();

			Element nodeElement;
			Object o = null;
			for (int i = 0; i < size; i++) {
				nodeElement = elementList.get(i);
				if (this.arrayNodeName.equals(nodeElement.getName())) {
					o = xmlArrayNodeToArray1(nodeElement, componentType);
				} else {
					o = xmlNodeToObject(nodeElement, componentType);
				}
				list.add(o);
			}
		}
		return list;
	}

	public static Class<?> getArrayType(Class<?> classType) {

		Class<?> componentType = classType.getComponentType();
		if (null == componentType) {
			return classType;
		} else {
			return getArrayType(componentType);
		}

	}

	public Date stringToDate(String dateTime) {
		Date date = null;
		try {
			if (null != dateTime && dateTime.length() > 0) {
				date = dateFormat.parse(dateTime);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	@Override
	public <T> T xmlToObject(String xml, Type type) {
		T o = null;
		if (null != type) {
			Class<?> classType=(Class<?>)type;
			Element rootElement = getRootElement(xml);
			if (null != rootElement) {
				Element nodeElement = rootElement.element(this.getSimpleNameAsProperty(classType));
				if (null != nodeElement) {
					o = xmlNodeToObject(nodeElement, classType);
				}
			}
		}
		return o;
	}
}
