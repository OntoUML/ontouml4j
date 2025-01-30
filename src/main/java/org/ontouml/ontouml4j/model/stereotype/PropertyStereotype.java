package org.ontouml.ontouml4j.model.stereotype;

import java.util.Optional;

public enum PropertyStereotype implements Stereotype {
  BEGIN("begin"),
  END("end");

  public final String stereotypeName;

  PropertyStereotype(String name) {
    this.stereotypeName = name;
  }

  public static Optional<PropertyStereotype> findByName(String name) {
    return Stereotype.findByName(PropertyStereotype.class, name);
  }

  @Override
  public String getStereotypeName() {
    return stereotypeName;
  }
}
