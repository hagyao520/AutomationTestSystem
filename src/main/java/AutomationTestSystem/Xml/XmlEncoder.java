package AutomationTestSystem.Xml;

import org.dom4j.Element;

public abstract class XmlEncoder extends XmlCoder {

	public abstract String objectToXml(Object object);
	
	public abstract String objectToXmlWithoutHead(Object object);

	public abstract String objectToElementXml(Object object);

	public abstract Element objectToElement(Object object);

}
