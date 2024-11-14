package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ontouml.MultilingualText;
import org.ontouml.OntoumlElement;
import org.ontouml.OntoumlUtils;
import org.ontouml.Project;
import org.ontouml.deserialization.PropertyDeserializer;
import org.ontouml.serialization.PropertySerializer;

@JsonSerialize(using = PropertySerializer.class)
@JsonDeserialize(using = PropertyDeserializer.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Property extends Decoratable<PropertyStereotype> {
  private Cardinality cardinality = new Cardinality();
  private String propertyTypeId;
  private Classifier<?, ?> propertyType;
  private List<Property> subsettedProperties = new ArrayList<>();
  private List<Property> redefinedProperties = new ArrayList<>();
  private AggregationKind aggregationKind;
  private boolean isDerived;
  private boolean isOrdered;
  private boolean isReadOnly;

  public Property(
      String id,
      MultilingualText name,
      PropertyStereotype ontoumlStereotype,
      Classifier<?, ?> type) {
    super(id, name, ontoumlStereotype);
    setPropertyType(type);
  }

  public Property(String id, MultilingualText name, String stereotypeName, Classifier<?, ?> type) {
    super(id, name, stereotypeName);
    setPropertyType(type);
  }

  public Property(String id, MultilingualText name, Classifier<?, ?> type) {
    this(id, name, (PropertyStereotype) null, type);
  }

  public Property(String id, String name, Classifier<?, ?> type) {
    this(id, new MultilingualText(name), (PropertyStereotype) null, type);
  }

  public Property(String name, Classifier<?, ?> type) {
    this(null, name, type);
  }

  public Property(Classifier<?, ?> type) {
    this(null, null, (PropertyStereotype) null, type);

    if (type != null && type.getFirstName().isPresent()) {
      String propertyName = type.getFirstName().get().trim().toLowerCase();

      addName(propertyName);
    }
  }

  public Property(String id) {
    this.setId(id);
    this.setName(new MultilingualText());
  }

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

  @Override
  public List<OntoumlElement> getContents() {
    return Collections.emptyList();
  }

  public void setCardinality(Cardinality cardinality) {
    if (cardinality == null)
      throw new NullPointerException("Cannot set null cardinality object on property!");

    this.cardinality = cardinality;
  }

  public void setCardinality(String cardinality) {
    this.cardinality.setValue(cardinality);
  }

  public Optional<String> getCardinalityValue() {
    return cardinality.getValue();
  }

  public List<Property> getSubsettedProperties() {
    return new ArrayList<>(subsettedProperties);
  }

  public void setSubsettedProperties(String[] subsettedProperties) {
    this.subsettedProperties.clear();
    if (subsettedProperties != null) {
      for (String id : subsettedProperties) {
        this.subsettedProperties.add(new Property(id));
      }
    }
  }

  public void setSubsettedProperties(List<Property> subsettedProperties) {
    this.subsettedProperties.clear();
    if (subsettedProperties != null)
      OntoumlUtils.addIfNotNull(this.subsettedProperties, subsettedProperties);
  }

  public void addSubsettedProperty(Property property) {
    if (property == null)
      throw new NullPointerException(
          "Cannot add a null value to the list of subsetted properties.");

    subsettedProperties.add(property);
  }

  public void removeSubsettedProperty(Property property) {
    subsettedProperties.remove(property);
  }

  public void replaceSubsettedProperty(Property toReplace, Property replaceFor) {
    int i = subsettedProperties.indexOf(toReplace);
    if (i >= 0) {
      subsettedProperties.set(i, replaceFor);
    }
  }

  public List<Property> getRedefinedProperties() {
    return new ArrayList<>(redefinedProperties);
  }

  public void setRedefinedProperties(String[] redefinedProperties) {
    this.redefinedProperties.clear();
    if (redefinedProperties != null) {
      for (String id : redefinedProperties) {
        this.redefinedProperties.add(new Property(id));
      }
    }
  }

  public void setRedefinedProperties(List<Property> redefinedProperties) {
    this.redefinedProperties.clear();
    if (redefinedProperties != null)
      OntoumlUtils.addIfNotNull(this.redefinedProperties, redefinedProperties);
  }

  public void addRedefinedProperty(Property property) {
    if (property == null)
      throw new NullPointerException(
          "Cannot add a null value to the list of redefined properties.");

    redefinedProperties.add(property);
  }

  public void removeRedefinedProperty(Property property) {
    redefinedProperties.remove(property);
  }

  public void replaceRedefinedProperty(Property toReplace, Property replaceFor) {
    int i = redefinedProperties.indexOf(toReplace);
    if (i >= 0) {
      redefinedProperties.set(i, replaceFor);
    }
  }

  public Optional<AggregationKind> getAggregationKind() {
    return Optional.ofNullable(aggregationKind);
  }

  public boolean isDerived() {
    return isDerived;
  }

  public void setDerived(boolean derived) {
    isDerived = derived;
  }

  public boolean isOrdered() {
    return isOrdered;
  }

  public void setOrdered(boolean ordered) {
    isOrdered = ordered;
  }

  public boolean isReadOnly() {
    return isReadOnly;
  }

  public void setReadOnly(boolean readOnly) {
    isReadOnly = readOnly;
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
    return getContainer().orElse(null) instanceof Class;
  }

  public boolean isRelationEnd() {
    return getContainer().orElse(null) instanceof Relation;
  }

  public void buildAllReferences(Project project) {
    Optional<Classifier> type = project.getElementById(this.propertyTypeId, Classifier.class);
    type.ifPresent(this::setPropertyType);
  }
}
