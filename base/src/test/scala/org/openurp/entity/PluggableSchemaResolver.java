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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PluggableSchemaResolver implements EntityResolver {

  Map<String,String> schemaMappings=new HashMap<String,String>();

  public PluggableSchemaResolver() {
    super();
    schemaMappings.put("http://www.openurp.org/schema/base/code/model.xsd", "/home/chaostone/openurp/spec/model/src/main/resources/org/openurp/base/code/model.xsd");
    schemaMappings.put("http://www.openurp.org/schema/entity/model.xsd", "/home/chaostone/openurp/spec/model/src/main/resources/org/openurp/entity/model.xsd");
    schemaMappings.put("http://www.openurp.org/schema/entity/util.xsd", "/home/chaostone/openurp/spec/model/src/main/resources/org/openurp/entity/util.xsd");
  }

  @Override
  public InputSource resolveEntity(String publicId, String systemId)
      throws SAXException, IOException {
    if (systemId != null) {
      String resourceLocation = schemaMappings.get(systemId);
      if (resourceLocation != null) {
        try {
          InputSource source = new InputSource(new FileInputStream(resourceLocation));
          source.setPublicId(publicId);
          source.setSystemId(systemId);
          return source;
        }
        catch (FileNotFoundException ex) {
          ex.printStackTrace();
        }
      }
    }
    return null;
  }

}
