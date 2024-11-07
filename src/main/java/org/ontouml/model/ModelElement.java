package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.MultilingualText;
import org.ontouml.OntoumlElement;
import org.ontouml.Project;
import org.ontouml.serialization.ModelElementSerializer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@JsonSerialize(using = ModelElementSerializer.class)
public abstract class ModelElement extends OntoumlElement {
  private Map<String, Object> propertyAssignments = new TreeMap<>();

  public ModelElement(String id, MultilingualText name) {
    super(id, name);
  }

  public ModelElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, alternativeNames);
  }

  public void addPropertyAssignment(String name, Object value) {
    if (name == null)
      throw new IllegalArgumentException("The name of a property assignment cannot be null.");

    propertyAssignments.put(name, value);
  }

  public void removePropertyAssignment(String name) {
    if (name == null)
      throw new IllegalArgumentException("The name of a property assignment cannot be null.");

    propertyAssignments.remove(name);
  }

  public void clearPropertyAssignments() {
    propertyAssignments.clear();
  }

  public Optional<Object> getPropertyAssignment(String name) {
    return Optional.ofNullable(propertyAssignments.get(name));
  }

  public Map<String, Object> getPropertyAssignments() {
    return propertyAssignments != null ? new TreeMap<>(propertyAssignments) : new TreeMap<>();
  }

  public void setPropertyAssignments(Map<String, Object> map) {
    if (map == null)
      throw new IllegalArgumentException("Cannot set a null map as the property assignments.");

    this.propertyAssignments.putAll(map);
  }

  public boolean hasPropertyAssignments() {
    return propertyAssignments != null && propertyAssignments.size() > 0;
  }

  /**
   * Returns the root package (aka the model) in which the element is contained or itself if the
   * element is the root package. Returns null if the element is not contained in any package.
   */
  public Optional<Package> getRootPackage() {
    Optional<Project> project = getProject();

    if (project.isPresent()) return project.get().getModel();

    OntoumlElement element = this;
    Optional<OntoumlElement> container = element.getContainer();

    while (container.isPresent()) {
      element = container.get();
      container = element.getContainer();
    }

    return element instanceof Package ? Optional.of((Package) element) : Optional.empty();
  }
}
