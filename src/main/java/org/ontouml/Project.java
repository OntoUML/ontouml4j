package org.ontouml;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.deserialization.ProjectDeserializer;
import org.ontouml.model.ModelElement;
import org.ontouml.model.Package;
import org.ontouml.model.Resource;
import org.ontouml.serialization.ProjectSerializer;
import org.ontouml.view.Diagram;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JsonSerialize(using = ProjectSerializer.class)
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project extends OntoumlElement implements ModelElementContainer, DiagramElementContainer {
  ProjectMetaProperties metaProperties;
  private Package root;
  private List<? extends ModelElement> elements;
  private List<Diagram> diagrams = new ArrayList<>();

  public Project(OntoumlElement container,
                 String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 MultilingualText description,
                 Date created, Date modified,
                 List<MultilingualText> editorialNotes,
                 List<Resource> creators,
                 List<Resource> contributors,
                 Package root,
                 List<Diagram> diagrams,
                 ProjectMetaProperties metaProperties) {
    super(container, id, name, alternativeNames, description, created, modified, editorialNotes, creators, contributors);
    this.root = root;
    this.diagrams = diagrams;
    this.metaProperties = metaProperties != null ? new ProjectMetaProperties() : null;
  }

  public Project(String id, MultilingualText name, Date created, Date modified) {
    super(null, id, name, new ArrayList<>(), null, created, modified, null, null, null);
    setProject(this);
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project(String id, String name, Date created, Date modified) {
    super(null, id, new MultilingualText(name), new ArrayList<>(), null, created, modified, null, null, null);
    setProject(this);
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project(MultilingualText name) {
    this(null, name, null, null, new ArrayList<>());
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project() {
    this(null, (MultilingualText) null, null, null, new ArrayList<>());
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project(String id, MultilingualText name, Package root, List<Diagram> diagrams) {
    super(id, name);
    this.root = root;
    this.diagrams = diagrams;
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 Package root,
                 List<Diagram> diagrams) {
    super(id, name, alternativeNames);
    this.root = root;
    this.diagrams = diagrams;
    this.metaProperties = new ProjectMetaProperties();
  }

  public Project(String id, MultilingualText name, Date created, Date modified, Package root, List<Diagram> diagrams) {
    super(id, name, created, modified);
    this.root = root;
    this.diagrams = diagrams;
    this.metaProperties = new ProjectMetaProperties();
  }

  @Override
  public String getType() {
    return "Project";
  }

  public Optional<Package> getModel() {
    return Optional.ofNullable(root);
  }

  public Package createModel() {
    return createModel(null, "Model");
  }

  public Package createModel(String modelName) {
    return createModel(null, modelName);
  }

  public Package createModel(String id, String modelName) {
    Package model = new Package(id, modelName);
    setRoot(model);
    return model;
  }

  public boolean hasModel() {
    return getModel().isPresent();
  }

  public List<Diagram> getDiagrams() {
    return new ArrayList<>(diagrams);
  }

  public void setDiagrams(List<Diagram> diagrams) {
    this.diagrams.clear();

    if (diagrams == null) return;

    addDiagrams(diagrams);
  }

  public void addDiagram(Diagram diagram) {
    if (diagram == null) return;

    diagram.setContainer(this);
    diagrams.add(diagram);
  }

  public void addDiagrams(List<Diagram> diagrams) {
    if (diagrams == null) return;

    diagrams.forEach(d -> addDiagram(d));
  }

  @Override
  public List<OntoumlElement> getContents() {
    List<OntoumlElement> contents = new ArrayList<>();

    contents.addAll(diagrams);

    if (contents.addAll(elements)) ;

    return contents;
  }

  public ProjectMetaProperties getMetaProperties() {
    return this.metaProperties;
  }

  public void setMetaProperties(ProjectMetaProperties metaProperties) {
    this.metaProperties = metaProperties;
  }

  public Package getRoot() {
    return root;
  }

  public void setRoot(Package root) {
    this.root = root;

    if (root != null) root.setContainer(this);
  }

  public List<? extends ModelElement> getElements() {
    return elements;
  }

  public void setElements(List<? extends ModelElement> elements) {
    this.elements = elements;
  }
}
