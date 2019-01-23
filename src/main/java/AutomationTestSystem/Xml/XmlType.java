package AutomationTestSystem.Xml;

public enum XmlType {
	/**
	 * 将对象转为xml格式时，所有属性都为节点。
	 */
	node, 
	/**
	 * 将对象的基本类型属性转为属性，对象属性转为节点。
	 */
	property
}
