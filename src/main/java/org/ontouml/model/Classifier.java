package org.ontouml.model;


import org.ontouml.MultilingualText;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Classifier<T extends Classifier<T, S>, S extends Stereotype>
        extends Decoratable<S> {
  boolean isAbstract;
  boolean isDerived;
  Map<String, Property> properties = new HashMap<>();

  public Classifier(String id, MultilingualText name, S ontoumlStereotype) {
    super(id, name, ontoumlStereotype);
  }

  public Classifier(String id, MultilingualText name, String stereotypeName) {
    super(id, name, stereotypeName);
  }

  public static boolean areAbstract(Collection<? extends Classifier<?, ?>> classifiers) {
    return classifiers.stream().allMatch(c -> c.isAbstract());
  }

  public static boolean areDerived(Collection<? extends Classifier<?, ?>> classifiers) {
    return classifiers.stream().allMatch(c -> c.isDerived());
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

  public List<Property> getProperties() {
    return properties.values().stream().toList();
  }

  public void setProperties(Collection<String> properties) {
    this.properties.clear();

    if (properties != null) properties.forEach(p -> addProperty(p));
  }

  public void addProperty(String propertyId) {
    if (propertyId != null) {
      properties.put(propertyId, null);
    }
  }

  public void addProperty(Property property) {
    if (property != null) {
      property.setContainer(this);
      properties.put(property.getId(), property);
    }
  }

  public boolean hasProperties() {
    return properties.size() > 0;
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
