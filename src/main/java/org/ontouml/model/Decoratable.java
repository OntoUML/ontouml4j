package org.ontouml.model;

import java.util.ArrayList;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.Stereotype;

/**
 * A model element that can be decorated with a stereotype to identify its ontological properties
 * according to the Unified Foundational Ontology (UFO). Examples include a class decorated with the
 * stereotype "kind" identifying it as a type of object that provides an identity principle to its
 * instances.
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Decoratable<S extends Stereotype> extends PackageableElement {

  /** Determines a custom stereotype, not necessarily compliant to UFO. */
  private String customStereotype;

  /** determines the type of model element according to Unified Foundational Ontology (UFO). */
  private S ontoumlStereotype;

  /**
   * Determines whether the model element is derived from a different one, i.e., whether a different
   * element serves as its truthmaker. Examples include the comparative relation "is heavier than"
   * between two physical objects which is derived from their "Weight" quality.
   */
  private boolean isDerived;

  public Decoratable(String id, MultilingualText name, S ontoumlStereotype) {
    super(id, name, new ArrayList<>());
    setOntoumlStereotype(ontoumlStereotype);
  }

  public Decoratable(String id, MultilingualText name, String stereotypeName) {
    super(id, name, new ArrayList<>());
    setStereotype(stereotypeName);
  }

  public Optional<S> getOntoumlStereotype() {
    return Optional.ofNullable(ontoumlStereotype);
  }

  public void setOntoumlStereotype(S ontoumlStereotype) {
    this.ontoumlStereotype = ontoumlStereotype;
    this.customStereotype = null;
  }

  public boolean hasOntoumlStereotype() {
    return getOntoumlStereotype().isPresent();
  }

  public Optional<String> getCustomStereotype() {
    return Optional.ofNullable(customStereotype);
  }

  public void setCustomStereotype(String customStereotype) {
    this.ontoumlStereotype = null;
    this.customStereotype = customStereotype;
  }

  public boolean hasCustomStereotype() {
    return getCustomStereotype().isPresent();
  }

  public Optional<String> getStereotype() {
    String stereotype =
        hasOntoumlStereotype() ? ontoumlStereotype.getStereotypeName() : customStereotype;
    return Optional.ofNullable(stereotype);
  }

  /**
   * If the supplied stereotype string matches the name of an OntoUML stereotype, this method will
   * set it. Otherwise, it will set a custom stereotype.
   */
  public abstract void setStereotype(String stereotypeName);

  public void removeStereotype() {
    ontoumlStereotype = null;
    customStereotype = null;
  }

  public boolean hasStereotype() {
    return hasOntoumlStereotype() || hasCustomStereotype();
  }

  public boolean hasStereotype(S stereotype) {
    return hasOntoumlStereotype() && this.ontoumlStereotype.equals(stereotype);
  }

  public boolean hasStereotype(String stereotypeName) {
    Optional<String> stereotype = getStereotype();
    return stereotype.isPresent() && stereotype.get().equals(stereotypeName);
  }
}
