package com.wandrell.pattern.testing.util.conf;

/**
 * Configuration class for the test Spring context files.
 * <p>
 * The paths to all the Spring context files used on the tests are contained
 * here.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class TestContextConfig {

    public static final String OUTPUTTER_XML_DTD_VALIDATION                = "classpath:context/outputter/test-outputter-xml-dtd-validation.xml";
    public static final String OUTPUTTER_XML_NO_VALIDATION                 = "classpath:context/outputter/test-outputter-xml-no-validation.xml";
    public static final String OUTPUTTER_XML_XSD_VALIDATION                = "classpath:context/outputter/test-outputter-xml-xsd-validation.xml";
    public static final String PARSER_XML_DTD_VALIDATION_EXCEPTION         = "classpath:context/parser/xml/test-parser-xml-dtd-exception.xml";
    public static final String PARSER_XML_DTD_VALIDATION_NO_VALIDATES      = "classpath:context/parser/xml/test-parser-xml-dtd-no-validates.xml";
    public static final String PARSER_XML_FILTERED_DTD_VALIDATION          = "classpath:context/parser/xml/filtered/test-parser-xml-dtd-filtered.xml";
    public static final String PARSER_XML_FILTERED_NO_VALIDATION           = "classpath:context/parser/xml/filtered/test-parser-xml-no-validation-filtered.xml";
    public static final String PARSER_XML_FILTERED_NO_VALIDATION_FULL      = "classpath:context/parser/xml/filtered/test-parser-xml-no-validation-filtered-full.xml";
    public static final String PARSER_XML_FILTERED_XSD_VALIDATION          = "classpath:context/parser/xml/filtered/test-parser-xml-xsd-filtered.xml";
    public static final String PARSER_XML_FILTERED_XSD_VALIDATION_DEFAULTS = "classpath:context/parser/xml/filtered/test-parser-xml-xsd-filtered-defaults.xml";
    public static final String PARSER_XML_NO_VALIDATION_EXCEPTION          = "classpath:context/parser/xml/test-parser-xml-no-validation-exception.xml";
    public static final String PARSER_XML_NOT_VALIDATED_EXCEPTION          = "classpath:context/parser/xml/test-parser-xml-not-validated-exception.xml";
    public static final String PARSER_XML_XSD_VALIDATION_EXCEPTION         = "classpath:context/parser/xml/test-parser-xml-xsd-exception.xml";
    public static final String PARSER_XML_XSD_VALIDATION_NO_VALIDATES      = "classpath:context/parser/xml/test-parser-xml-xsd-no-validates.xml";
    public static final String XML                                         = "classpath:context/test-xml.xml";

    /**
     * Private constructor.
     * <p>
     * The class should not be instantiated.
     */
    private TestContextConfig() {
        super();
    }

}
