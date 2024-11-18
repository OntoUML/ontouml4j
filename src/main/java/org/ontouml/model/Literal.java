package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * A model element that represents a specific value within an enumerated set of values. Examples
 * include each letter in an A to F letter grading scale, listed in a class "Letter Grade" decorated
 * with the stereotype "enumeration".
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Literal extends ModelElement {
  @Override
  public String getType() {
    return "Literal";
  }
}
