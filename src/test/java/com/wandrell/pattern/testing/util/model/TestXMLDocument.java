package com.wandrell.pattern.testing.util.model;

import org.jdom2.Document;
import org.jdom2.Element;

public final class TestXMLDocument extends Document {

	private static final long serialVersionUID = 1L;

	public TestXMLDocument(final String root, final String node, final String content) {
		super();

		final Element rootNode;

		rootNode = new Element(root);
		rootNode.addContent(new Element(node).addContent(content));

		addContent(rootNode);
	}

}
