package org.ontouml.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.ClassStereotype;

/**
 * A classifier that defines the properties of a set of "individualized" entities (i.e.,
 * non-relational) of the subject domain. Examples include "Person", "Enrollment", and "Grade". The
 * instances of a class may include entities such as objects (e.g., people, organizations,
 * vehicles), reified properties (e.g., leafs' colors, agents' intentions, enrollments), and bare
 * values (e.g., a number or a literal).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Class extends Classifier<Class, ClassStereotype> {

  /** Identifies the literals of an enumeration class. */
  List<String> literals;

  /**
   * Determines the possible ontological natures of the instances of a class using an array of
   * enumerated strings. Examples include the class "Vehicle" restricted to having
   * "functional-complex" instances and the class "Insured Item" restricted to "functional-complex"
   * and "relator" (e.g., employment insurance).
   */
  List<Nature> restrictedTo;

  /**
   * Determines whether the high-order class is a "Cardelli powertype" using a boolean. In other
   * words, determines whether the high-order class is defined as the one whose instances are its
   * base type plus all possible specializations of it.
   */
  boolean isPowertype;

  /**
   * Determines the instantiation order of a class using a string. Examples include ordered classes
   * such as first-order classes (order "1"), second-order classes (order "2"), and third-order
   * classes (order "3"), as well as orderless classes (order "*").
   */
  String order;

  @Override
  public String getType() {
    return "Class";
  }
}
