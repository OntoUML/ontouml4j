package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.ontouml.ontouml4j.model.stereotype.Stereotype;
import org.ontouml.ontouml4j.serialization.ClassifierSerializer;

/**
 * A decoratable element (either a class or a relation) that defines properties exhibited by its
 * instances.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ClassifierSerializer.class)
public abstract class Classifier<T extends Classifier<T, S>, S extends Stereotype>
    extends Decoratable<S> {

  /**
   * Identifies the properties contained in a classifier. These properties are referred to as
   * attributes when contained by classes, and relation ends when contained by relations. In the
   * case of relations, the properties array must be ordered.
   */
  List<Property> properties = new ArrayList<>();

  /**
   * Determines whether the classifier can have direct instances using a boolean. Abstract
   * classifiers can only have instances when these are instances of some other classifier that is
   * not abstract (i.e., concrete) and is a specialization of the abstract one.
   */
  private boolean isAbstract;

  protected Classifier(String id) {
    super(id);
    properties = new ArrayList<>();
  }

  protected Classifier(String id, MultilingualText name, S ontoumlStereotype) {
    super(id, name, ontoumlStereotype);
    properties = new ArrayList<>();
  }

  protected Classifier(String id, MultilingualText name, String stereotypeName) {
    super(id, name, stereotypeName);
    properties = new ArrayList<>();
  }

  public void createProperty() {}

  public void addProperty(String propertyId) {
    if (propertyId != null) {
      properties.add(new Property(propertyId));
    }
  }

  public void addProperty(Property property) {
    if (property != null) {
      property.setContainer(this);
      properties.add(property);
    }
  }

  public void resolvePropertyReferences(Project project) {
    List<Property> newProperties = new ArrayList<>();
    this.properties.forEach(
        property -> {
          Optional<Property> propertyInProject = project.getPropertyById(property.getId());
          propertyInProject.ifPresent(newProperties::add);
        });
    this.properties.clear();
    this.properties.addAll(newProperties);
  }

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean anAbstract) {
    isAbstract = anAbstract;
  }

  public List<Property> getProperties() {
    return properties;
  }

  public void setProperties(Collection<String> properties) {
    this.properties.clear();

    if (properties != null) properties.forEach(this::addProperty);
  }

  public void setProperties(List<Property> properties) {
    this.properties = properties;
  }
}
