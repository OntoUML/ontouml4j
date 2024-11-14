package org.ontouml;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ontouml.deserialization.ProjectDeserializer;
import org.ontouml.model.*;
import org.ontouml.model.Class;
import org.ontouml.model.Package;
import org.ontouml.serialization.ProjectSerializer;
import org.ontouml.view.Diagram;

// TODO: Implement Anchor
// TODO: Implement Node
// TODO: Change elements to a Map
// TODO: One map for each element kind
// TODO: Error when an attribute is not referenced in any class
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize(using = ProjectSerializer.class)
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project extends OntoumlElement
    implements ModelElementContainer, DiagramElementContainer {
  ProjectMetaProperties metaProperties = new ProjectMetaProperties();
  private Package root;

  private Map<String, Class> classes = new HashMap<>();
  private Map<String, Package> packages = new HashMap<>();
  private Map<String, Relation> relations = new HashMap<>();
  private Map<String, Property> properties = new HashMap<>();
  private Map<String, Literal> literals = new HashMap<>();
  private Map<String, GeneralizationSet> generalizationSets = new HashMap<>();
  private Map<String, Generalization> generalizations = new HashMap<>();
  //  private Map<String, String> notes;
  // TODO: Think about the optimization on the diagram elements as well
  private List<Diagram> diagrams = new ArrayList<>();

  public Project(String id, MultilingualText description, Date created, Date modified) {
    super(null, id, description, null, description, created, modified, null, null, null);
  }

  public Project(
      String id, MultilingualText name, Package root, ProjectMetaProperties metaProperties) {
    super(id, name);
    this.root = root;
    this.metaProperties = metaProperties;
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
    contents.addAll(getElements());

    return contents;
  }

  public void setRoot(Package root) {
    this.root = root;

    if (root != null) root.setContainer(this);
  }

  public List<? extends ModelElement> getElements() {
    List<ModelElement> elements = new ArrayList<>();

    elements.addAll(classes.values());
    elements.addAll(relations.values());
    elements.addAll(packages.values());
    elements.addAll(properties.values());
    elements.addAll(literals.values());
    elements.addAll(generalizationSets.values());
    elements.addAll(generalizations.values());

    return elements;
  }

  public void setElements(List<ModelElement> elements) {
    elements.forEach(this::addElement);
  }

  private void addElement(ModelElement element) {
    switch (element.getType()) {
      case "Class":
        this.classes.put(element.getId(), (Class) element);
        break;
      case "Relation":
        this.relations.put(element.getId(), (Relation) element);
        break;
      case "Package":
        this.packages.put(element.getId(), (Package) element);
        break;
      case "Property":
        this.properties.put(element.getId(), (Property) element);
        break;
      case "Literal":
        this.literals.put(element.getId(), (Literal) element);
        break;
      case "GeneralizationSet":
        this.generalizationSets.put(element.getId(), (GeneralizationSet) element);
        break;
      case "Generalization":
        this.generalizations.put(element.getId(), (Generalization) element);
        break;
    }
  }
}
