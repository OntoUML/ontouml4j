package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import java.util.stream.Stream;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.ClassDeserializer;
import org.ontouml.ontouml4j.model.stereotype.ClassStereotype;
import org.ontouml.ontouml4j.serialization.ClassSerializer;

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
@JsonDeserialize(using = ClassDeserializer.class)
@JsonSerialize(using = ClassSerializer.class)
public class Class extends Classifier<Class, ClassStereotype> {

  public static Integer ORDERLESS = Integer.MAX_VALUE;
  public static String ORDERLESS_STRING = "*";

  /** Identifies the literals of an enumeration class. */
  @Builder.Default List<Literal> literals = new ArrayList<>();

  /**
   * Determines the possible ontological natures of the instances of a class using an array of
   * enumerated strings. Examples include the class "Vehicle" restricted to having
   * "functional-complex" instances and the class "Insured Item" restricted to "functional-complex"
   * and "relator" (e.g., employment insurance).
   */
  @Builder.Default List<Nature> restrictedTo = new ArrayList<>();

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
  Integer order;

  private Project projectContainer;

  public Class(String id, String name, ClassStereotype classStereotype) {
    super();
    this.id = id;
    this.literals = new ArrayList<>();
    this.restrictedTo = new ArrayList<>();
    this.customProperties = new HashMap<>();
    this.setName(new MultilingualText(name));
    this.setStereotype(classStereotype.getStereotypeName());
    this.setOntoumlStereotype(classStereotype);
  }

  public Class(String id, String name, String customStereotype) {
    super();
    this.id = id;
    this.literals = new ArrayList<>();
    this.restrictedTo = new ArrayList<>();
    this.setName(new MultilingualText(name));
    this.setCustomStereotype(customStereotype);
    this.setStereotype(customStereotype);
  }

  public static Class createKind(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.KIND);
    clazz.setRestrictedTo(Nature.FUNCTIONAL_COMPLEX);
    return clazz;
  }

  public static Class createKind(String name) {
    return createKind(null, name);
  }

  public static Class createCollective(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.COLLECTIVE);
    clazz.setRestrictedTo(Nature.COLLECTIVE);
    return clazz;
  }

  public static Class createQuantity(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.QUANTITY);
    clazz.setRestrictedTo(Nature.QUANTITY);
    return clazz;
  }

  public static Class createRelator(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.RELATOR);
    clazz.setRestrictedTo(Nature.RELATOR);
    return clazz;
  }

  public static Class createMode(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.MODE);
    clazz.setRestrictedToNatures(List.of(Nature.INTRINSIC_MODE, Nature.EXTRINSIC_MODE));
    return clazz;
  }

  public static Class createMode(String name) {
    return createMode(null, name);
  }

  public static Class createQuality(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.QUALITY);
    clazz.setRestrictedTo(Nature.QUALITY);
    return clazz;
  }

  public static Class createSubkind(String id, String name) {
    return new Class(id, name, ClassStereotype.SUBKIND);
  }

  public static Class createRole(String id, String name) {
    return new Class(id, name, ClassStereotype.ROLE);
  }

  public static Class createPhase(String id, String name) {
    return new Class(id, name, ClassStereotype.PHASE);
  }

  public static Class createHistoricalRole(String id, String name) {
    return new Class(id, name, ClassStereotype.HISTORICAL_ROLE);
  }

  public static Class createMixin(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.MIXIN);
    clazz.setAbstract(true);
    clazz.setRestrictedToNatures(Nature.SUBSTANTIAL_NATURES);
    return clazz;
  }

  public static Class createCategory(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.CATEGORY);
    clazz.setAbstract(true);
    clazz.setRestrictedToNatures(Nature.SUBSTANTIAL_NATURES);
    return clazz;
  }

  public static Class createRoleMixin(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.ROLE_MIXIN);
    clazz.setAbstract(true);
    clazz.setRestrictedToNatures(Nature.SUBSTANTIAL_NATURES);
    return clazz;
  }

  public static Class createPhaseMixin(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.PHASE_MIXIN);
    clazz.setAbstract(true);
    clazz.setRestrictedToNatures(Nature.SUBSTANTIAL_NATURES);
    return clazz;
  }

  public static Class createHistoricalRoleMixin(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.HISTORICAL_ROLE_MIXIN);
    clazz.setAbstract(true);
    clazz.setRestrictedToNatures(Nature.SUBSTANTIAL_NATURES);
    return clazz;
  }

  public static Class createEvent(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.EVENT);
    clazz.setRestrictedTo(Nature.EVENT);
    return clazz;
  }

  public static Class createSituation(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.SITUATION);
    clazz.setRestrictedTo(Nature.TYPE);
    return clazz;
  }

  public static Class createType(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.TYPE);
    clazz.setRestrictedTo(Nature.FUNCTIONAL_COMPLEX);
    return clazz;
  }

  public static Class createAbstract(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.ABSTRACT);
    clazz.setRestrictedTo(Nature.ABSTRACT);
    return clazz;
  }

  public static Class createDatatype(String id, String name) {
    Class clazz = new Class(id, name, ClassStereotype.DATATYPE);
    clazz.setRestrictedTo(Nature.ABSTRACT);
    return clazz;
  }

  public static Class createEnumeration(String id, String name, String... literals) {
    Class enumeration =
        Class.builder()
            .id(id)
            .name(new MultilingualText(name))
            .ontoumlStereotype(ClassStereotype.ENUMERATION)
            .build();
    enumeration.setRestrictedTo(Nature.ABSTRACT);
    enumeration.createLiterals(literals);
    return enumeration;
  }

  public Optional<Integer> getOrder() {
    return Optional.ofNullable(order);
  }

  public void setOrder(String value) {
    if (value != null) {
      Integer intValue = Integer.parseInt(value);
      if (value.equals("*")) {
        intValue = ORDERLESS;
      }
      order = intValue;
    } else {
      order = ORDERLESS;
    }
  }

  public Optional<String> getOrderAsString() {
    if (order == null) return Optional.empty();
    else
      return ORDERLESS.equals(order)
          ? Optional.of(ORDERLESS_STRING)
          : Optional.of(order.toString());
  }

  public List<Literal> createLiterals(String[] names) {
    return Stream.of(names).map(this::createLiteral).toList();
  }

  /**
   * This method creates a Literal and add it to the current project
   *
   * @param name - Name of the literal
   * @return the created literal
   */
  public Literal createLiteral(String name) {
    if (literals == null) literals = new ArrayList<>();

    Literal literal = new Literal(name, new MultilingualText(name));
    addLiteral(literal);
    return literal;
  }

  /**
   * This method gets a created literal, and add it to this class
   *
   * @param literal
   */
  public void addLiteral(Literal literal) {
    if (literals == null) literals = new ArrayList<>();

    if (this.projectContainer != null) {
      literal.setProjectContainer(projectContainer);
      this.projectContainer.addLiteral(literal);
    }
    literal.setContainer(this);
    literals.add(literal);
  }

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

  public void setRestrictedTo(Optional<Nature> restrictedTo) {
    if (this.restrictedTo == null) {
      this.restrictedTo = new ArrayList<>();
    }
    this.restrictedTo.clear();
    restrictedTo.ifPresent(this.restrictedTo::add);
  }

  public void setRestrictedTo(Nature restrictedTo) {
    if (this.restrictedTo == null) {
      this.restrictedTo = new ArrayList<>();
    }
    this.restrictedTo.clear();
    this.restrictedTo.add(restrictedTo);
  }

  public void setRestrictedTo(Collection<String> restrictedTo) {
    this.restrictedTo.clear();
    restrictedTo.forEach(
        item -> {
          Nature nature = Nature.forValue(item);
          if (nature != null) {
            this.restrictedTo.add(nature);
          }
        });
  }

  public void setRestrictedToNatures(Collection<Nature> restrictedTo) {
    if (this.restrictedTo == null) {
      this.restrictedTo = new ArrayList<>();
    }
    this.restrictedTo.clear();
    this.restrictedTo.addAll(restrictedTo);
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
    this.properties.clear();
    this.properties.addAll(newProperties);
  }

  public Property createAttribute(String name, Classifier<?, ?> type) {
    return createAttribute(null, name, type);
  }

  public Property createAttribute(String id, String name, Classifier<?, ?> type) {
    Property attribute = new Property(id, name, type);
    attribute.setContainer(this);
    properties.add(attribute);
    attribute.setProjectContainer(this.projectContainer);

    this.projectContainer.addElement(attribute);

    return attribute;
  }

  public List<Property> getAttributes() {
    return getProperties();
  }

  public void setAttributes(Collection<Property> attributes) {
    if (properties == null) properties = new ArrayList<>();
    else properties.clear();

    if (attributes == null) return;

    attributes.forEach(this::addAttribute);
  }

  public void addAttribute(Property attribute) {
    if (attribute == null) return;

    projectContainer.addElement(attribute);

    attribute.setContainer(this);
    properties.add(attribute);
  }
}
