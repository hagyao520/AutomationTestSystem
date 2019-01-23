package AutomationTestSystem.Xml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * @description:
 * @author King
 * @date 2018年8月1日 18:18:074
 * @version 1.0.0
 */

public abstract class XmlCoder {

	protected final Logger logger = Logger.getLogger(this.getClass());
	protected String xml_base = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected String xml_root = "root";
	protected String mapKey = "dataKey";
	protected String mapValue = "data";
	protected String arrayNodeName = "array";
	protected String charset = "UTF-8";
	protected DefaultConversionService conversionService = new DefaultConversionService();
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected Map<Object, Map<String, Field>> fieldMap = new HashMap<Object, Map<String, Field>>();
	protected Map<Object, PropertyDescriptor[]> propertyMap = new HashMap<Object, PropertyDescriptor[]>();
	protected Map<Object, List<PropertyDescriptor>> readMethodPropertyDescriptorListMap = new HashMap<Object, List<PropertyDescriptor>>();
	protected Map<Object, List<PropertyDescriptor>> writeMethodPropertyDescriptorListMap = new HashMap<Object, List<PropertyDescriptor>>();

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.xml_base = this.xml_base.replace(this.charset, charset);
		this.charset = charset;
	}

	public boolean isNull(Object o) {
		return (null == o || "".equals(o));
	}

	public boolean isNotNull(Object o) {
		return (null != o && !"".equals(o));
	}

	/**
	 * 获取类的开头小写的简单类名（不包含包名）
	 *
	 * @param o
	 * @return
	 */
	public String getSimpleNameAsProperty(Class<?> classType) {
		String valueName = classType.getSimpleName();
		return valueName = valueName.substring(0, 1).toLowerCase() + valueName.substring(1);
	}

	/**
	 * 获取对象小写开头的简单类名（不包含包名）
	 *
	 * @param o
	 * @return
	 */
	public String getSimpleNameAsProperty(Object o) {
		return getSimpleNameAsProperty(o.getClass());
	}

	/**
	 * 判断对象是否为8种基本类型的对象。
	 *
	 * @param o
	 * @return
	 */

	public boolean isPrimitive(Class<?> o) {
		return (o.isPrimitive()) || (o == Integer.class) || (o == Long.class) || (o == Float.class) || (o == Double.class) || (o == Byte.class) || (o == Character.class) || (o == Boolean.class) || (o == Short.class);
	}

	/**
	 * 判断对象是否为8种基本类型的对象。
	 *
	 * @param o
	 * @return
	 */
	public boolean isPrimitive(Object o) {
		return (o instanceof Integer) || (o instanceof Long) || (o instanceof Float) || (o instanceof Double) || (o instanceof Byte) || (o instanceof Character) || (o instanceof Boolean) || (o instanceof Short) || o.getClass().isPrimitive();
	}

	/**
	 * 是否为字符串类型
	 *
	 * @param o
	 * @return
	 */
	public boolean isString(Object o) {
		return (o instanceof String);
	}

	/**
	 * 是否为字符串类型
	 *
	 * @param o
	 * @return
	 */
	public boolean isString(Class<?> o) {
		return (o == String.class);
	}

	/**
	 * 获取类中有get方法是属性
	 *
	 * @param classType
	 * @return
	 */
	public List<PropertyDescriptor> getReadMethodPropertyDescriptorList(Class<?> classType) {
		List<PropertyDescriptor> propertyDescriptorList = readMethodPropertyDescriptorListMap.get(classType);
		try {
			if (null == propertyDescriptorList) {
				propertyDescriptorList = new ArrayList<PropertyDescriptor>();
				BeanInfo bi = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = bi.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if (null != pd.getReadMethod()) {
						propertyDescriptorList.add(pd);
					}
				}
				readMethodPropertyDescriptorListMap.put(classType, propertyDescriptorList);
			}
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
		}
		return propertyDescriptorList;
	}

	/**
	 * 获取类中有set方法是属性
	 *
	 * @param classType
	 * @return
	 */
	public List<PropertyDescriptor> getWriteMethodPropertyDescriptorList(Class<?> classType) {
		List<PropertyDescriptor> propertyDescriptorList = writeMethodPropertyDescriptorListMap.get(classType);
		try {
			if (null == propertyDescriptorList) {
				propertyDescriptorList = new ArrayList<PropertyDescriptor>();
				BeanInfo bi = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = bi.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if (null != pd.getWriteMethod()) {
						propertyDescriptorList.add(pd);
					}
				}
				writeMethodPropertyDescriptorListMap.put(classType, propertyDescriptorList);
			}
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
		}
		return propertyDescriptorList;
	}

	public Field getField(Class<?> classType, String fieldName) {
		Map<String, Field> map = fieldMap.get(classType);
		if (null == map) {
			map = getClassFieldMap(classType);
			fieldMap.put(classType, map);
		}
		return map.get(fieldName);
	}

	public Map<String, Field> getClassFieldMap(Class<?> classType) {
		Map<String, Field> map = new HashMap<String, Field>();
		return getClassFieldMap(map, classType);
	}

	public Map<String, Field> getClassFieldMap(Map<String, Field> map, Class<?> className) {
		Field[] fields = className.getDeclaredFields();
		for (Field field : fields) {
			String propertyName = field.getName();
			map.put(propertyName, field);
		}
		Class<?> superClass = className.getSuperclass();
		if (null != superClass) {
			map = getClassFieldMap(map, superClass);
		}
		return map;
	}
}
