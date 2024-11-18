package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * A model element that contains an annotation about the ontology or some of its elements. A note can also be used to
 * represent a constraint in both natural or structured language (i.e., first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GeneralizationSet extends ModelElement {
  /**
   * Determines whether the specific classifiers in the generalization set have disjoint extensions.
   * Examples include the generalization set involving "Child" and "Adult" generalized into "Person", where the
   * "is disjoint" as "true" indicates that no person is simultaneously an instance of "Child" and "Adult."
   */
  private boolean isDisjoint;

  /**
   * Determines whether the specific classifiers in the generalization set completely cover the extension of the
   * general classifier.
   * Examples include the generalization set involving "Child" and "Adult" generalized into "Person", where the
   * "is complete" as "true" indicates that every person is either an instance of "Child" or "Adult."
   */
  private boolean isComplete;

  /**
   * Identifies all generalizations that are involved by the generalization set.
   */
  private List<String> generalizations;

  /**
   * Identifies the high-order class that classifies (i.e., is instantiated by) every specific class in the
   * generalization set.
   * For example, "Academic Role" as the categorizer of the generalization set of "Person" into "Student" and "Teacher"
   * representing the specific classes as instances of the categorizer.
   * A categorizer can only be present in generalization sets involving exclusively classes.
   */
  private Class categorizer;

  @Override
  public String getType() {
    return "GeneralizationSet";
  }
}
