# Parser

A parser receives a data structure, and transforms it into another equivalent data structure.
	
## Interface

![Parser interface][parser-interface]

The [Parser][parser] only contains one method, _parse_, which takes and input object and generates and output structure.
	
The actual way this is achieved will depend on each implementation.
	
It should be noted that if the parsing process is complex, it may be possible, and desirable, to divide it into several chained parsers, each of them receiving as input the output of the previous parser.

## Implementations for XML files

The only default implementations for the parser patterns are for XML files.

![XML parsers class hierarchy tree][xml_parser-class_tree]

---

### XML file parser

The basic parsers for XML files are [ValidatedXMLFileParser][validated_xml_parser] and [NotValidatedXMLFileParser][not_validated_xml_parser]. The difference between them is that the first allows applying validation files, while the second doesn't, but in exchange is faster.
	
To avoid having to choose between them the [XMLFileParser][xml_parser] exists, which automatically switchs between one and the other depending on if the validation data has been set or not.
	
No matter this, setting the validation data is simple.
	
It can be set through the constructor:
	
```java
public final void setValidation(final Reader reader) {
	final Parser<Reader, Document> parser;
	
	parser = new ValidatedXMLFileParser(XMLValidationType.XSD, reader);
}
```
	
Or through the setter:
	
```java
public final void setValidation(final Reader reader) {
	final Parser<Reader, Document> parser;
	
	parser = new ValidatedXMLFileParser();
	
	parser.setValidation(XMLValidationType.XSD, reader);
}
```

And removing it equally simple:
	
```java
public final void removeValidation() {
	final Parser<Reader, Document> parser;
	
	parser = new ValidatedXMLFileParser();
	
	parser.setValidation(XMLValidationType.NONE, null);
}
```

Note that the reader can only be null if the validation is set to _NONE_.

### Filtered parser

The [FilteredEntriesXMLFileParser][filtered_xml_parser] filters the nodes on an XML file, taking only those with an specific name, and an specific set of boolean attributes.
	
For example, having a file as follows:
		
```xml
<test>
	<node attribute1="false" attribute2="false">value1</node>
	<node attribute1="false" attribute2="true">value2</node>
	<node attribute1="true" attribute2="true">value3</node>
</test>
```

To get the node with the text "value2" the parser would have to be set up as:
	
```java
public final void parseFile(final Reader reader) {
	final FilteredEntriesXMLFileParser parser;
	final Document doc;
	
	parser = new FilteredEntriesXMLFileParser("node");
	
	parser.addRequiredAttribute("attribute2");
	
	parser.addRejectedAttribute("attribute1");
	
	doc = parser.parse(reader);
}
```
	
Note that it is possible to apply a validation file to the parser, which can be used to apply default values to the attributes.

---

## Dependencies to third party libraries

To reduce the size of this library, the third party dependencies required by concrete parsers are using the 'provided' scope set on Maven. This means, that to use certain parsers there are some third party libraries which must be added to the project.

First, all the XML parsers require JDOM2, which uses the following dependency:

```xml
<dependency>
	<!-- JDom 2 -->
	<groupId>org.jdom</groupId>
	<artifactId>jdom2</artifactId>
	<version>${jdom.version}</version>
</dependency>
```

All the filtered XML parsers require Jaxen, which uses the following dependency:

```xml
<dependency>
	<!-- Jaxen -->
	<groupId>jaxen</groupId>
	<artifactId>jaxen</artifactId>
	<version>${jaxen.version}</version>
</dependency>
```

[parser]: ./apidocs/com/wandrell/pattern/parser/Parser.html
[parser-interface]: ./images/parser_class_tree.png
[xml_parser-class_tree]: ./images/xml_parser_class_tree.png
[validated_xml_parser]: ./apidocs/com/wandrell/pattern/parser/xml/ValidatedXMLFileParser.html
[not_validated_xml_parser]: ./apidocs/com/wandrell/pattern/parser/xml/NotValidatedXMLFileParser.html
[xml_parser]: ./apidocs/com/wandrell/pattern/parser/xml/XMLFileParser.html
[filtered_xml_parser]: ./apidocs/com/wandrell/pattern/parser/xml/FilteredEntriesXMLFileParser.html