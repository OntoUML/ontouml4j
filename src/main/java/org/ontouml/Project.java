package org.ontouml;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.deserialization.ProjectDeserializer;
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
  private Package model;
  private List<Diagram> diagrams = new ArrayList<>();
  List<String> keywords = new ArrayList<>();

  public Project(OntoumlElement container, String id, MultilingualText name, List<MultilingualText> alternativeNames, Date created, Date modified, List<MultilingualText> editorialNotes, List<Resource> creators, List<Resource> contributors, Package model, List<Diagram> diagrams, List<String> keywords) {
    super(container, id, name, alternativeNames, created, modified, editorialNotes, creators, contributors);
    this.model = model;
    this.diagrams = diagrams;
    this.keywords = keywords;
  }

  public Project(String id, MultilingualText name, Date created, Date modified) {
    super(null, id, name, new ArrayList<>(), created, modified, null, null, null );
    setProject(this);
  }

  public Project(String id, String name, Date created, Date modified) {
    super(null, id, new MultilingualText(name), new ArrayList<>(), created, modified, null, null, null);
    setProject(this);
  }

  public Project(MultilingualText name) {
    this(null, name, null, null);
  }

  public Project() {
    this(null, (MultilingualText) null, null, null);
  }

  public Project(String id, MultilingualText name, Package model, List<Diagram> diagrams, List<String> keywords) {
    super(id, name);
    this.model = model;
    this.diagrams = diagrams;
    this.keywords = keywords;
  }

  public Project(String id, MultilingualText name, List<MultilingualText> alternativeNames, Package model, List<Diagram> diagrams, List<String> keywords) {
    super(id, name, alternativeNames);
    this.model = model;
    this.diagrams = diagrams;
    this.keywords = keywords;
  }

  public Project(String id, MultilingualText name, Date created, Date modified, Package model, List<Diagram> diagrams, List<String> keywords) {
    super(id, name, created, modified);
    this.model = model;
    this.diagrams = diagrams;
    this.keywords = keywords;
  }

  @Override
  public String getType() {
    return "Project";
  }

  public Optional<Package> getModel() {
    return Optional.ofNullable(model);
  }

  public Package createModel() {
    return createModel(null, "Model");
  }

  public Package createModel(String modelName) {
    return createModel(null, modelName);
  }

  public Package createModel(String id, String modelName) {
    Package model = new Package(id, modelName);
    setModel(model);
    return model;
  }

  public void setModel(Package model) {
    this.model = model;

    if (model != null) model.setContainer(this);
  }

  public boolean hasModel() {
    return getModel().isPresent();
  }

 public List<Diagram> getDiagrams() {
    return new ArrayList<>(diagrams);
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

  public void setDiagrams(List<Diagram> diagrams) {
    this.diagrams.clear();

    if (diagrams == null) return;

    addDiagrams(diagrams);
  }

  @Override
  public List<OntoumlElement> getContents() {
    List<OntoumlElement> contents = new ArrayList<>();

    contents.addAll(diagrams);

    if (getModel().isPresent()) contents.add(getModel().get());

    return contents;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public List<String> getKeywords() {
    return keywords;
  }
}
