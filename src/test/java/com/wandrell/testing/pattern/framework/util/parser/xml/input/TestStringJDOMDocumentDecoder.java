package com.wandrell.testing.pattern.framework.util.parser.xml.input;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;

public final class TestStringJDOMDocumentDecoder implements
        JDOMDocumentDecoder<Collection<Collection<Object>>> {

    public TestStringJDOMDocumentDecoder() {
        super();
    }

    @Override
    public final Collection<Collection<Object>> decode(final Document doc) {
        final Element root;
        final Collection<Collection<Object>> result;
        Collection<Object> entry;

        root = doc.getRootElement();

        result = new LinkedHashSet<>();
        for (final Element node : root.getChildren()) {
            entry = new LinkedList<>();

            entry.add(node.getText());

            result.add(entry);
        }

        return result;
    }

}
