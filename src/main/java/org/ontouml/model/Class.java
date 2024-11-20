package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.ClassDeserializer;
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
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using =  ClassDeserializer.class)
public class Class extends Classifier<Class, ClassStereotype> {

  /** Identifies the literals of an enumeration class. */
  List<Literal> literals = new ArrayList<>();

  /**
   * Determines the possible ontological natures of the instances of a class using an array of
   * enumerated strings. Examples include the class "Vehicle" restricted to having
   * "functional-complex" instances and the class "Insured Item" restricted to "functional-complex"
   * and "relator" (e.g., employment insurance).
   */
  List<Nature> restrictedTo = new ArrayList<>();

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

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<ClassStereotype> stereotype = ClassStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }

  public boolean restrictedToOverlaps(List<Nature> natures) {
    if (natures == null) return false;

    TreeSet<Nature> natureSet = new TreeSet<>(natures);
    natureSet.retainAll(restrictedTo);
    return !natureSet.isEmpty();
  }

  public boolean restrictedToContainedIn(List<Nature> natures) {
    if (natures == null) return false;

    return new HashSet<>(natures).containsAll(restrictedTo);
  }

  public boolean restrictedToContains(Nature nature) {
    return restrictedToContains(Collections.singletonList(nature));
  }

  public boolean restrictedToContains(List<Nature> natures) {
    if (natures == null) return false;

    return restrictedTo.containsAll(natures);
  }

  public boolean restrictedToEquals(Nature nature) {
    return restrictedToEquals(Collections.singletonList(nature));
  }

  public boolean restrictedToEquals(List<Nature> natures) {
    if (natures == null) return false;

    if (restrictedTo.size() != natures.size()) return false;

    TreeSet<Nature> naturesSet = new TreeSet<>(natures);
    return restrictedTo.equals(naturesSet);
  }

  public boolean isRestrictedToMoments() {
    return restrictedTo.stream().allMatch(Nature::isMoment);
  }

  public boolean isRestrictedToSubstantials() {
    return restrictedTo.stream().allMatch(Nature::isSubstantial);
  }

  public boolean isEnumeration() {
    return hasStereotype(ClassStereotype.ENUMERATION);
  }

  public boolean isRestrictedToEndurants() {
    return restrictedTo.stream().allMatch(Nature::isEndurant);
  }

  public boolean isRestrictedToFunctionalComplexes() {
    return restrictedToEquals(Nature.FUNCTIONAL_COMPLEX);
  }

  public boolean isRestrictedToCollectives() {
    return restrictedToEquals(Nature.COLLECTIVE);
  }

  public boolean isRestrictedToQuantity() {
    return restrictedToEquals(Nature.QUANTITY);
  }

  public boolean isRestrictedToIntrinsicMoments() {
    return restrictedTo.stream().allMatch(Nature::isIntrinsicMoment);
  }

  public boolean isRestrictedToExtrinsicMoments() {
    return restrictedTo.stream().allMatch(Nature::isExtrinsicMoment);
  }

  public boolean isRestrictedToRelators() {
    return restrictedToEquals(Nature.RELATOR);
  }

  public boolean isRestrictedToIntrinsicModes() {
    return restrictedToEquals(Nature.INTRINSIC_MODE);
  }

  public boolean isRestrictedToExtrinsicModes() {
    return restrictedToEquals(Nature.EXTRINSIC_MODE);
  }

  public boolean isRestrictedToQualities() {
    return restrictedToEquals(Nature.QUALITY);
  }

  public boolean isRestrictedToEvents() {
    return restrictedToEquals(Nature.EVENT);
  }

  public boolean isRestrictedToSituations() {
    return restrictedToEquals(Nature.SITUATION);
  }

  public boolean isRestrictedToTypes() {
    return restrictedToEquals(Nature.TYPE);
  }

  public boolean isRestrictedToAbstract() {
    return restrictedToEquals(Nature.ABSTRACT);
  }

  public boolean isDatatype() {
    return hasStereotype(ClassStereotype.DATATYPE);
  }

  public boolean isPrimitiveDatatype() {
    return isDatatype() && !hasAttributes();
  }

  public boolean hasAttributes() {
    return hasProperties();
  }

  public boolean hasProperties() {
    return !properties.isEmpty();
  }

  public void setLiterals(Collection<String> literals) {
    this.literals.clear();
    literals.forEach(
        literalId -> {
          Literal newLiteral = new Literal(literalId, new MultilingualText());
          newLiteral.setContainer(this);
          this.literals.add(newLiteral);
        });
  }

  public void setRestrictedTo(Collection<String> restrictedTo) {
    this.restrictedTo.clear();
    restrictedTo.forEach(item -> {
      Nature nature = Nature.forValue(item);
      if (nature != null) {
        this.restrictedTo.add(nature);
      }
    });
  }

    public void buildAllReferences(Project project) {
    this.buildLiteralsReferences(project);
    this.buildPropertiesReferences(project);
  }

  private void buildLiteralsReferences(Project project) {
    List<Literal> resolvedLiterals = new ArrayList<>();

    this.literals.forEach(
        (literal) -> {
          Optional<Literal> literalInProject =
              project.getElementById(literal.getId(), Literal.class);
          literalInProject.ifPresent(resolvedLiterals::add);
        });
    this.literals = resolvedLiterals;
  }

  private void buildPropertiesReferences(Project project) {
    List<Property> newProperties = new ArrayList<>();
    this.properties.forEach(
        property -> {
          Optional<Property> propertyInProject =
              project.getElementById(property.getId(), Property.class);
          propertyInProject.ifPresent(newProperties::add);
        });
    this.properties = newProperties;
  }
}
