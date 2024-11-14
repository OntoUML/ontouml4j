package org.ontouml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ontouml.model.Resource;

@AllArgsConstructor
@NoArgsConstructor
public abstract class OntoumlElement extends Element {
  private OntoumlElement container;
  private Project project;

  public OntoumlElement(
      OntoumlElement container,
      String id,
      MultilingualText name,
      List<MultilingualText> alternativeNames,
      MultilingualText description,
      Date created,
      Date modified,
      List<MultilingualText> editorialNotes,
      List<Resource> creators,
      List<Resource> contributors) {
    super(
        id,
        name,
        alternativeNames,
        description,
        created,
        modified,
        editorialNotes,
        creators,
        contributors);
    this.container = container;

    if (container != null) project = container.project;
  }

  public OntoumlElement(String id, MultilingualText name) {
    super(id, name, new ArrayList<>());
  }

  public OntoumlElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, alternativeNames);
  }

  public OntoumlElement(
      String id,
      MultilingualText name,
      List<MultilingualText> alternativeNames,
      Date created,
      Date modified) {
    super(id, name, alternativeNames, created, modified);
  }

  public OntoumlElement(String id, MultilingualText name, Date created, Date modified) {
    super(id, name, null, created, modified);
  }

  public Optional<OntoumlElement> getContainer() {
    return Optional.ofNullable(container);
  }

  public void setContainer(OntoumlElement container) {
    this.container = container;
    Project project = container != null ? container.project : null;
    setProject(project);
  }

  public boolean hasContainer() {
    return getContainer().isPresent();
  }

  public Optional<Project> getProject() {
    return Optional.ofNullable(project);
  }

  /** Setting the project of an element propagates to all of its contents */
  public void setProject(Project project) {
    this.project = project;
    getAllContents().forEach(elem -> elem.setProject(project));
  }

  public boolean hasProject() {
    return getProject().isEmpty();
  }

  public List<OntoumlElement> getContents(Predicate<OntoumlElement> filter) {
    if (filter == null) return getContents();

    return getContents().stream().filter(filter).collect(Collectors.toList());
  }

  public abstract List<OntoumlElement> getContents();

  /**
   * This method was modified because there is no more elements inside others. All elements are at
   * the root of the project JSON at the "elements" property
   */
  public List<OntoumlElement> getAllContents() {
    return getContents();
  }

  @Override
  public String toString() {
    return getType() + " { id: " + id + "(hash: " + hashCode() + "), name: " + getName() + "}";
  }

  public abstract String getType();
}
