package org.ontouml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.PropertyStereotype;
import org.ontouml.model.utils.AggregationKind;

/**
 * A decoratable element that represents an attribute of a class, or one end of a relation. Examples
 * include the attribute "name" of the class "Person", and the ends of the binary relation "studies
 * in" connected to the classes "Student" and "University." Instances of class and relation elements
 * bear values for the properties these classifiers contain, according to the constraints specified
 * within each property. For example, the value assigned to a property in an instance must be itself
 * an instance of the classifier in property type.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public final class Property extends Decoratable<PropertyStereotype> {

  /**
   * Identifies the properties (relation ends) that provide subsetting constraints to the property.
   * Examples include, in the relations (i) "works for" from "Employee" to "Organization", and (ii)
   * "teaches for" from "Teacher" to "University", both relation ends of (ii) are subsets of their
   * respect ends in (i).
   *
   * <p>Subsetting can only occur on the relation ends when both sides of the involved relations are
   * connected to the same classifiers or classifiers that are in the same generalization chain.
   * Subsetting can also be represented through a generalization between relations.
   */
  private List<Property> subsettedProperties = new ArrayList<>();

  /**
   * Identifies the properties (relation ends) that provide redefinition constraints to the
   * property. Examples include, in the relations (i) "works in" from "Project Member" to "Project",
   * and (ii) "leads" from "Project Leader" to "Project", where the member end is redefined for the
   * leader who can only be connected to the project through that specific relation.
   *
   * <p>Redefinition can only occur on the relation ends when both sides of the involved relations
   * are connected to the same classifiers or classifiers that are in the same generalization chain.
   */
  private List<Property> redefinedProperties = new ArrayList<>();

  /**
   * Determines whether the property (a relation end) is a whole in a parthood (mereological)
   * relation using an enumerated string. Examples include "Project Member" as a "shared" part of a
   * "Project Team" and an "Engine" as a "composite" part of a "Vehicle." The possible values for
   * aggregation kind are "composite" (when parts are exclusive to one whole), "shared" (when parts
   * can be shared), and "none" for non-parthood relations. The "null" is also interpreted as
   * "none."
   */
  private AggregationKind aggregationKind;

  /**
   * Determines the cardinality of a property using a string. Examples include "Person" with
   * cardinality "1" (exactly one) in the property (attribute) "name", and cardinality "0..*" (zero
   * or more) in the property (related end) "friends" of the relation "friends with".
   *
   * <p>The cardinality should follow the regular expression "^\d+(\.\.(\d+|\*))?$", where the
   * number before ".." represents the lower bound, and the number or "*" after represents the upper
   * bound. In case the ".." is not present, a number represents an exact number of instances, and
   * "*" represents "zero or more" instances.
   *
   * <p>This regular expression is not enforced to accommodate theoretical ranges as expression,
   * such as, "a..b".
   */
  private Cardinality cardinality = new Cardinality();

  /** Not used in JSON. Must be determined by ontouml4j library */
  private boolean isDerived;

  /**
   * Determines whether the order of assignments of a property carries meaning using a boolean. This
   * boolean only regards properties whose maximum cardinality is greater than one.
   */
  private boolean isOrdered;

  /** Determines whether the assignments of a property are immutable using a boolean. */
  private boolean isReadOnly;

  /** Identifies the classifier instantiated by the values assigned to the property. */
  private Classifier<?, ?> propertyType;

  @Override
  public String getType() {
    return "Property";
  }

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<PropertyStereotype> stereotype = PropertyStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }

  public void setCardinality(Cardinality cardinality) {
    if (cardinality == null)
      throw new NullPointerException("Cannot set null cardinality object on property!");

    this.cardinality = cardinality;
  }

  public void setCardinality(String cardinality) {
    this.cardinality.setValue(cardinality);
  }

  public Optional<Classifier<?, ?>> getPropertyType() {
    return Optional.ofNullable(propertyType);
  }

  public boolean isPropertyTypeClass() {
    Optional<Classifier<?, ?>> type = getPropertyType();
    return type.isPresent() && type.get() instanceof Class;
  }

  public boolean isPropertyTypeRelation() {
    Optional<Classifier<?, ?>> type = getPropertyType();
    return type.isPresent() && type.get() instanceof Relation;
  }

  public Class getPropertyTypeAsClass() {
    if (!isPropertyTypeClass()) throw new IllegalCallerException("Property type is not a class.");

    return (Class) propertyType;
  }

  public Relation getPropertyTypeAsRelation() {
    if (!isPropertyTypeRelation())
      throw new IllegalCallerException("Property type is not a relation.");

    return (Relation) propertyType;
  }

  public boolean isAggregationEnd() {
    return aggregationKind == AggregationKind.COMPOSITE
        || aggregationKind == AggregationKind.SHARED;
  }

  public boolean isAttribute() {
    return getContainer() instanceof Class;
  }

  public boolean isRelationEnd() {
    return getContainer() instanceof Relation;
  }
}
