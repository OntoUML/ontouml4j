package org.ontouml.model.utils;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum AggregationKind {
  NONE("NONE"),
  SHARED("SHARED"),
  COMPOSITE("COMPOSITE");

  final String name;

  AggregationKind(String name) {
    this.name = name;
  }

  public static Optional<AggregationKind> findByName(String name) {
    return Stream.of(AggregationKind.values())
        .filter(nature -> nature.getName().equals(name))
        .findFirst();
  }

  public String getName() {
    return name;
  }
}
