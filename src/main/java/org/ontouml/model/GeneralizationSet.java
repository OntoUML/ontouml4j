package org.ontouml.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.OntoumlUtils;

/**
 * A model element that contains an annotation about the ontology or some of its elements. A note
 * can also be used to represent a constraint in both natural or structured language (i.e.,
 * first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GeneralizationSet extends ModelElement {
  /**
   * Determines whether the specific classifiers in the generalization set have disjoint extensions.
   * Examples include the generalization set involving "Child" and "Adult" generalized into
   * "Person", where the "is disjoint" as "true" indicates that no person is simultaneously an
   * instance of "Child" and "Adult."
   */
  private boolean isDisjoint;

  /**
   * Determines whether the specific classifiers in the generalization set completely cover the
   * extension of the general classifier. Examples include the generalization set involving "Child"
   * and "Adult" generalized into "Person", where the "is complete" as "true" indicates that every
   * person is either an instance of "Child" or "Adult."
   */
  private boolean isComplete;

  /** Identifies all generalizations that are involved by the generalization set. */
  private Set<Generalization> generalizations;

  /**
   * Identifies the high-order class that classifies (i.e., is instantiated by) every specific class
   * in the generalization set. For example, "Academic Role" as the categorizer of the
   * generalization set of "Person" into "Student" and "Teacher" representing the specific classes
   * as instances of the categorizer. A categorizer can only be present in generalization sets
   * involving exclusively classes.
   */
  private Class categorizer;

  public boolean isDisjoint() {
    return isDisjoint;
  }

  public void setDisjoint(boolean disjoint) {
    isDisjoint = disjoint;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  public Optional<Class> getCategorizer() {
    return Optional.ofNullable(categorizer);
  }

  public Set<Generalization> getGeneralizations() {
    return new HashSet<>(generalizations);
  }

  public void setGeneralizations(Collection<Generalization> generalizations) {
    this.generalizations.clear();
    OntoumlUtils.addIfNotNull(this.generalizations, generalizations);
  }

  public void addGeneralization(Generalization generalization) {
    if (generalization == null)
      throw new NullPointerException("Cannot add a null generalization to the generalization set.");

    this.generalizations.add(generalization);
  }

  @Override
  public String getType() {
    return "GeneralizationSet";
  }
}
