package org.ontouml;

import org.ontouml.model.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class OntoumlElement extends Element {
  private OntoumlElement container;
  private Project project;

  public OntoumlElement(OntoumlElement container,
                        String id,
                        MultilingualText name,
                        List<MultilingualText> alternativeNames,
                        Date created,
                        Date modified,
                        List<MultilingualText> editorialNotes,
                        List<Resource> creators,
                        List<Resource> contributors) {
    super(id, name, alternativeNames, created, modified, editorialNotes, creators, contributors);
    this.container = container;

    if (container != null) project = container.project;
  }

  public OntoumlElement(String id, MultilingualText name) {
    super(id, name, new ArrayList<>());
  }

  public OntoumlElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, new ArrayList<>());
  }

  public OntoumlElement(String id, MultilingualText name, Date created, Date modified) {
    super(id, name, new ArrayList<>(), created, modified);
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

  public boolean hasProject() {
    return getProject().isEmpty();
  }

  /** Setting the project of an element propagates to all of its contents */
  public void setProject(Project project) {
    this.project = project;
    getAllContents().forEach(elem -> elem.setProject(project));
  }

  public List<OntoumlElement> getContents(Predicate<OntoumlElement> filter) {
    if (filter == null) return getContents();

    return getContents().stream().filter(filter).collect(Collectors.toList());
  }

  public abstract List<OntoumlElement> getContents();

  public List<OntoumlElement> getAllContents() {
    List<OntoumlElement> children = getContents();

    if (children.isEmpty()) return children;

    List<OntoumlElement> childrenContents =
        children.stream()
            .flatMap(child -> child.getAllContents().stream())
            .collect(Collectors.toList());

    childrenContents.addAll(children);

    return childrenContents;
  }

  @Override
  public String toString() {
    return getType() + " { id: " + id + "(hash: " + hashCode() + "), name: " + getName() + "}";
  }

  public abstract String getType();
}
