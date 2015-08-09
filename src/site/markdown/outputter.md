# Outputter

An outputter sends data through an output I/O operation, hiding the actual implementation or API being used.
	
## Interface

[![Outputter interface][outputter-class_tree]][outputter-class_tree]

The [Outputter][outputter] consists of just two methods, both called _output_, which send an object through the accompanying output object, which may be a _Writer_ or an _OutputStream_.
	
Note that the output object is expected to be closed once the operation is finished.

## Implementations

[![Outputter class hierarchy tree][outputter-class_hierarchy]][outputter-class_hierarchy]

---

### XML

The [XMLOutputter][xml_outputter] allows storing data into XML files. It requires a JDOM 2 Document.
	
It is possible to add a validation file to the resulting XML file. Both DTD and XSD files are valid.
	
For example, generating an XML file with XSD validation just requires the following lines:

```java
public final void writeValidation(final Document doc, final Writer writer) {
	final Outputter<Document> outputter;
	
	outputter = new XMLOutputter(XMLValidationType.XSD, "/validation.xsd");
	
	outputter.output(doc, writer);
}
```

### YAML

The [YAMLOutputter][yaml_outputter] allows storing data into YAML files. It requires an object, which will be processed into the file.
	
The easiest way to use this class is by giving it a _Map_, as it adapts best to the structure of a YAML file.
	
```java
public final void writeYAML(final Writer writer) {
	final Map<String, Object> data;
	final Outputter<Object> outputter;
	
	outputter = new YAMLOutputter();
	
	data = new LinkedHashMap<String, Object>();
	data.put("key1", "value1");
	data.put("key2", 2);
	data.put("key3", true);
	
	outputter.output(data, writer);
}
```

---

## Dependencies to third party libraries

To reduce the size of this library, the third party dependencies required by concrete outputters are using the 'provided' scope set on Maven. This means, that to use certain outputters there are some third party libraries which must be added to the project.

The XML outputter requires JDOM2, which uses the following dependency:

```xml
<dependency>
	<!-- JDom 2 -->
	<groupId>org.jdom</groupId>
	<artifactId>jdom2</artifactId>
	<version>${jdom.version}</version>
</dependency>
```

The YAML outputter requires SnakeYAML, which uses the following dependency:

```xml
<dependency>
	<!-- SnakeYAML -->
	<groupId>org.yaml</groupId>
	<artifactId>snakeyaml</artifactId>
	<version>${snakeyaml.version}</version>
</dependency>
```

[outputter]: ./apidocs/com/wandrell/pattern/outputter/Outputter.html
[outputter-class_hierarchy]: ./images/outputter_class_tree.png
[outputter-class_tree]: ./images/outputter_class.png
[xml_outputter]: ./apidocs/com/wandrell/pattern/outputter/xml/XMLOutputter.html
[yaml_outputter]: ./apidocs/com/wandrell/pattern/outputter/yaml/YAMLOutputter.html