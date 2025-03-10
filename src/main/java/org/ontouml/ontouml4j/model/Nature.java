package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum Nature {
  FUNCTIONAL_COMPLEX("functional-complex"),
  COLLECTIVE("collective"),
  QUANTITY("quantity"),
  RELATOR("relator"),
  INTRINSIC_MODE("intrinsic-mode"),
  EXTRINSIC_MODE("extrinsic-mode"),
  QUALITY("quality"),
  EVENT("event"),
  SITUATION("situation"),
  TYPE("type"),
  ABSTRACT("abstract");

  public static final Collection<Nature> ENDURANT_NATURES =
      Set.of(
          FUNCTIONAL_COMPLEX,
          COLLECTIVE,
          QUANTITY,
          RELATOR,
          EXTRINSIC_MODE,
          INTRINSIC_MODE,
          QUALITY);
  public static final Collection<Nature> SUBSTANTIAL_NATURES =
      Set.of(FUNCTIONAL_COMPLEX, COLLECTIVE, QUANTITY);
  public static final Collection<Nature> MOMENT_NATURES =
      Set.of(RELATOR, EXTRINSIC_MODE, INTRINSIC_MODE, QUALITY);
  public static final Collection<Nature> INTRINSIC_MOMENT_NATURES = Set.of(INTRINSIC_MODE, QUALITY);
  public static final Collection<Nature> EXTRINSIC_MOMENT_NATURES = Set.of(RELATOR, EXTRINSIC_MODE);

  public final String name;

  Nature(String name) {
    this.name = name;
  }

  public static Optional<Nature> findByName(String name) {
    return Stream.of(Nature.values()).filter(nature -> nature.getName().equals(name)).findFirst();
  }

  @JsonCreator
  public static Nature forValue(String value) {
    return Stream.of(Nature.values())
        .filter(nature -> nature.name.equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid Nature value: " + value));
  }

  public boolean isEndurant() {
    return ENDURANT_NATURES.contains(this);
  }

  public boolean isSubstantial() {
    return SUBSTANTIAL_NATURES.contains(this);
  }

  public boolean isMoment() {
    return MOMENT_NATURES.contains(this);
  }

  public boolean isIntrinsicMoment() {
    return INTRINSIC_MOMENT_NATURES.contains(this);
  }

  public boolean isExtrinsicMoment() {
    return EXTRINSIC_MOMENT_NATURES.contains(this);
  }

  @JsonValue
  public String toValue() {
    return name;
  }
}
