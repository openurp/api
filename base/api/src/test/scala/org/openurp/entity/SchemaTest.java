/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.entity;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SchemaTest {

  public void testValidate() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(true);
    try {
      factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
          "http://www.w3.org/2001/XMLSchema");
    } catch (IllegalArgumentException ex) {
      ParserConfigurationException pcex = new ParserConfigurationException(
          "Unable to validate using XSD: Your JAXP provider [" + factory
              + "] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? "
              + "Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
      pcex.initCause(ex);
      throw pcex;
    }
    DocumentBuilder docBuilder = factory.newDocumentBuilder();
    docBuilder.setEntityResolver(new PluggableSchemaResolver());
    //docBuilder.setErrorHandler(new DefaultHandler());
    URL url= getClass().getResource("/test.xml");
    InputSource source = new InputSource(url.openStream());
    Document doc=docBuilder.parse(source);
    System.out.println(doc);
  }
}
