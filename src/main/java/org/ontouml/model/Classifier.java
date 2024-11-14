package org.ontouml.model;

import java.util.*;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.ontouml.MultilingualText;
import org.ontouml.Project;

@Getter
@Setter
public abstract class Classifier<T extends Classifier<T, S>, S extends Stereotype>
    extends Decoratable<S> {
  boolean isAbstract;
  boolean isDerived;
  List<Property> properties = new ArrayList<>();

  public Classifier(String id, MultilingualText name, S ontoumlStereotype) {
    super(id, name, ontoumlStereotype);
  }

  public Classifier(String id, MultilingualText name, String stereotypeName) {
    super(id, name, stereotypeName);
  }

  public static boolean areAbstract(Collection<? extends Classifier<?, ?>> classifiers) {
    return classifiers.stream().allMatch(Classifier::isAbstract);
  }

  public static boolean areDerived(Collection<? extends Classifier<?, ?>> classifiers) {
    return classifiers.stream().allMatch(Classifier::isDerived);
  }

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean anAbstract) {
    isAbstract = anAbstract;
  }

  public boolean isDerived() {
    return isDerived;
  }

  public void setDerived(boolean derived) {
    isDerived = derived;
  }

  public void setProperties(Collection<String> properties) {
    this.properties.clear();

    if (properties != null) properties.forEach(this::addProperty);
  }

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

  public boolean hasProperties() {
    return !properties.isEmpty();
  }

  public void resolvePropertyReferences(Project project) {
    List<Property> newProperties = new ArrayList<>();
    this.properties.forEach(
        property -> {
          Optional<Property> propertyInProject = project.getPropertyById(property.getId());
          propertyInProject.ifPresent(newProperties::add);
        });
    this.properties = newProperties;
  }

  public List<Generalization> getGeneralizations() {
    return null;
  }

  public List<GeneralizationSet> getGeneralizationSets() {
    return null;
  }

  public List<Generalization> getGeneralizationsWhereGeneral() {
    return null;
  }

  public List<Generalization> getGeneralizationsWhereSpecific() {
    return null;
  }

  public List<GeneralizationSet> getGeneralizationSetsWhereGeneral() {
    return null;
  }

  public List<GeneralizationSet> getGeneralizationSetsWhereSpecific() {
    return null;
  }

  public List<T> getParents() {
    return null;
  }

  public List<T> getChildren() {
    return null;
  }

  public List<T> getAncestors() {
    return null;
  }

  public List<T> getDescendants() {
    return null;
  }

  public List<T> getFilteredAncestors(Predicate<T> filter) {
    return null;
  }

  public List<T> getFilteredDescendants(Predicate<T> filter) {
    return null;
  }
}
