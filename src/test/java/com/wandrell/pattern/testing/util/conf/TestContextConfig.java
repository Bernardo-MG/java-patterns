package com.wandrell.pattern.testing.util.conf;

public final class TestContextConfig {

	public static final String OUTPUTTER_XML_DTD_VALIDATION = "classpath:context/test-outputter-xml-dtd-validation.xml";
	public static final String OUTPUTTER_XML_NO_VALIDATION = "classpath:context/test-outputter-xml-no-validation.xml";
	public static final String OUTPUTTER_XML_XSD_VALIDATION = "classpath:context/test-outputter-xml-xsd-validation.xml";
	public static final String PARSER_XML_DTD_VALIDATION = "classpath:context/test-parser-xml-dtd-no-validates.xml";
	public static final String PARSER_XML_XSD_VALIDATION = "classpath:context/test-parser-xml-xsd-no-validates.xml";
	public static final String PARSER_XML_FILTERED_XSD_VALIDATION = "classpath:context/test-parser-xml-xsd-filtered.xml";
	public static final String PARSER_XML_FILTERED_XSD_VALIDATION_DEFAULTS = "classpath:context/test-parser-xml-xsd-filtered-defaults.xml";
	public static final String PARSER_XML_FILTERED_DTD_VALIDATION = "classpath:context/test-parser-xml-dtd-filtered.xml";
	public static final String PARSER_XML_FILTERED_NO_VALIDATION_FULL = "classpath:context/test-parser-xml-no-validation-filtered-full.xml";
	public static final String PARSER_XML_FILTERED_NO_VALIDATION = "classpath:context/test-parser-xml-no-validation-filtered.xml";
	public static final String XML = "classpath:context/test-xml.xml";

	private TestContextConfig() {
		super();
	}

}
