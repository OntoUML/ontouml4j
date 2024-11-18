package org.ontouml.model.stereotype;

import java.util.EnumSet;
import java.util.Optional;

public interface Stereotype {
  static <S extends Enum<S> & Stereotype> Optional<S> findByName(
      java.lang.Class<S> enumeration, String name) {
    return EnumSet.allOf(enumeration).stream()
        .filter(value -> value.getStereotypeName().equals(name))
        .findFirst();
  }

  String getStereotypeName();
}
