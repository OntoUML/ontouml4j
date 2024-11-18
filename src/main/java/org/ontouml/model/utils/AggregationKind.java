package org.ontouml.model.utils;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum AggregationKind {
  NONE("NONE"),
  SHARED("SHARED"),
  COMPOSITE("COMPOSITE");

  final String name;

  AggregationKind(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Optional<AggregationKind> findByName(String name) {
    return Stream.of(AggregationKind.values())
        .filter(nature -> nature.getName().equals(name))
        .findFirst();
  }
}