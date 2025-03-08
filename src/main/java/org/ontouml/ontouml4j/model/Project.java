package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import org.ontouml.ontouml4j.deserialization.ProjectDeserializer;
import org.ontouml.ontouml4j.model.utils.ElementContainer;
import org.ontouml.ontouml4j.model.utils.ProjectMetaProperties;
import org.ontouml.ontouml4j.model.view.*;
import org.ontouml.ontouml4j.serialization.ProjectSerializer;
import org.ontouml.ontouml4j.shape.*;

/**
 * A named element that serves as the container of an entire OntoUML ontology, including the
 * elements of both the abstract syntax (i.e., model elements) and the concrete syntax (i.e.,
 * diagrams, view, and shapes).
 */
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = ProjectSerializer.class)
@NoArgsConstructor
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project extends NamedElement implements ElementContainer {

  /** Contains the OntoUML elements that are part of the project. */
  ProjectMetaProperties metaProperties = new ProjectMetaProperties();

  /** Properties related to diagrams, views, and shapes that are part of the project. */
  Map<String, Diagram> diagrams = new HashMap<>();

  Map<String, View> views = new HashMap<>();
  Map<String, Shape> shapes = new HashMap<>();

  /**
   * Identifies the root package of a project (the package containing all other model elements of
   * the project) if present.
   */
  private Package root;

  private Map<String, Class> classes = new HashMap<>();
  private Map<String, Package> packages = new HashMap<>();
  private Map<String, Relation> relations = new HashMap<>();
  private Map<String, Property> properties = new HashMap<>();
  private Map<String, Literal> literals = new HashMap<>();
  private Map<String, GeneralizationSet> generalizationSets = new HashMap<>();
  private Map<String, Generalization> generalizations = new HashMap<>();
  private Map<String, Note> notes = new HashMap<>();
  private Map<String, Anchor> anchors = new HashMap<>();

  public Project(String id, String name) {
    super(id, new MultilingualText(name));
    this.classes = new HashMap<>();
    this.packages = new HashMap<>();
    this.relations = new HashMap<>();
    this.properties = new HashMap<>();
    this.literals = new HashMap<>();
    this.generalizationSets = new HashMap<>();
    this.generalizations = new HashMap<>();
    this.notes = new HashMap<>();
    this.anchors = new HashMap<>();
    this.diagrams = new HashMap<>();
    this.views = new HashMap<>();
    this.shapes = new HashMap<>();
  }

  @Override
  public String getType() {
    return "Project";
  }

  public List<OntoumlElement> getElements() {
    List<OntoumlElement> elements = new ArrayList<>();

    elements.addAll(classes.values().stream().toList());
    elements.addAll(packages.values().stream().toList());
    elements.addAll(relations.values().stream().toList());
    elements.addAll(properties.values().stream().toList());
    elements.addAll(literals.values().stream().toList());
    elements.addAll(generalizationSets.values().stream().toList());
    elements.addAll(generalizations.values().stream().toList());
    elements.addAll(notes.values().stream().toList());
    elements.addAll(anchors.values().stream().toList());
    elements.addAll(diagrams.values().stream().toList());
    elements.addAll(views.values().stream().toList());
    elements.addAll(shapes.values().stream().toList());

    return elements;
  }

  public <T extends OntoumlElement> void setElements(List<T> elements) {
    elements.forEach(
        element -> {
          if (element instanceof Class) {
            classes.put(element.getId(), (Class) element);
          } else if (element instanceof Package) {
            packages.put(element.getId(), (Package) element);
          } else if (element instanceof Relation) {
            relations.put(element.getId(), (Relation) element);
          } else if (element instanceof Property) {
            properties.put(element.getId(), (Property) element);
          } else if (element instanceof Literal) {
            literals.put(element.getId(), (Literal) element);
          } else if (element instanceof GeneralizationSet) {
            generalizationSets.put(element.getId(), (GeneralizationSet) element);
          } else if (element instanceof Generalization) {
            generalizations.put(element.getId(), (Generalization) element);
          } else if (element instanceof Note) {
            notes.put(element.getId(), (Note) element);
          } else if (element instanceof Anchor) {
            anchors.put(element.getId(), (Anchor) element);
          } else if (element instanceof View) {
            views.put(element.getId(), (View) element);
          } else if (element instanceof Shape) {
            shapes.put(element.getId(), (Shape) element);
          } else if (element instanceof Diagram) {
            diagrams.put(element.getId(), (Diagram) element);
          }
        });
  }

  public Map<String, OntoumlElement> getElementMap() {

    Map<String, OntoumlElement> elementMap = new HashMap<>(getModelElementMap());
    elementMap.putAll(diagrams);
    elementMap.putAll(views);
    elementMap.putAll(shapes);

    return elementMap;
  }

  public Map<String, ModelElement> getModelElementMap() {
    Map<String, ModelElement> elementMap = new HashMap<>();

    elementMap.putAll(classes);
    elementMap.putAll(packages);
    elementMap.putAll(relations);
    elementMap.putAll(properties);
    elementMap.putAll(literals);
    elementMap.putAll(generalizationSets);
    elementMap.putAll(generalizations);
    elementMap.putAll(notes);
    elementMap.putAll(anchors);

    return elementMap;
  }

  public <T> List<T> getAllContentsByType(java.lang.Class<T> type) {
    if (type == Class.class) {
      return classes.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Package.class) {
      return packages.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Relation.class) {
      return relations.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Property.class) {
      return properties.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Literal.class) {
      return literals.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == GeneralizationSet.class) {
      return generalizationSets.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Generalization.class) {
      return generalizations.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Note.class) {
      return notes.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Anchor.class) {
      return anchors.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == View.class) {
      return views.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Shape.class) {
      return shapes.values().stream().map(type::cast).collect(Collectors.toList());
    } else if (type == Diagram.class) {
      return diagrams.values().stream().map(type::cast).collect(Collectors.toList());
    } else {
      return List.of();
    }
  }

  public <T extends OntoumlElement> Optional<T> getElementById(String id, java.lang.Class<T> type) {
    if (type == ModelElement.class || type == Classifier.class || type == Decoratable.class) {
      return Optional.ofNullable(type.cast(getElementMap().get(id)));
    } else if (type == Class.class) {
      return Optional.ofNullable(type.cast(classes.get(id)));
    } else if (type == Package.class) {
      return Optional.ofNullable(type.cast(packages.get(id)));
    } else if (type == Relation.class
        || type == NaryRelation.class
        || type == BinaryRelation.class) {
      return Optional.ofNullable(type.cast(relations.get(id)));
    } else if (type == Property.class) {
      return Optional.ofNullable(type.cast(properties.get(id)));
    } else if (type == Literal.class) {
      return Optional.ofNullable(type.cast(literals.get(id)));
    } else if (type == GeneralizationSet.class) {
      return Optional.ofNullable(type.cast(generalizationSets.get(id)));
    } else if (type == Generalization.class) {
      return Optional.ofNullable(type.cast(generalizations.get(id)));
    } else if (type == Note.class) {
      return Optional.ofNullable(type.cast(notes.get(id)));
    } else if (type == Anchor.class) {
      return Optional.ofNullable(type.cast(anchors.get(id)));
    } else if (type == View.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == ClassView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == AnchorView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == BinaryRelationView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == BinaryConnectorView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == GeneralizationView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == GeneralizationSetView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == NaryRelationView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == NoteView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == PackageView.class) {
      return Optional.ofNullable(type.cast(views.get(id)));
    } else if (type == Shape.class
        || type == RectangularShape.class
        || type == Rectangle.class
        || type == Diamond.class
        || type == Path.class
        || type == Text.class) {
      return Optional.ofNullable(type.cast(shapes.get(id)));
    } else if (type == Diagram.class) {
      return Optional.ofNullable(type.cast(diagrams.get(id)));
    } else {
      return Optional.empty();
    }
  }

  public Optional<Class> getClassById(String id) {
    return Optional.ofNullable(this.classes.get(id));
  }

  public ModelElement addElement(ModelElement element) {
    if (element.getId() == null) {
      throw new RuntimeException("Element id cannot be null");
    }
    switch (element) {
      case Class clazz -> this.addClass(clazz);
      case Package pkg -> this.addPackage(pkg);
      case Relation relation -> this.addRelation(relation);
      case Property property -> this.addProperty(property);
      case Literal literal -> this.addLiteral(literal);
      case GeneralizationSet genset -> this.addGeneralizationSet(genset);
      case Generalization gen -> this.addGeneralization(gen);
      case Note note -> this.addNote(note);
      case Anchor anchor -> this.addAnchor(anchor);
      default -> throw new IllegalStateException("Unexpected value: " + element);
    }
    element.setProjectContainer(this);
    return element;
  }

  public OntoumlElement addElement(OntoumlElement element) {
    if (element.getId() == null) {
      throw new RuntimeException("Element id cannot be null");
    }
    switch (element) {
      case Diagram diagram -> this.diagrams.put(element.getId(), diagram);
      case View view -> this.views.put(element.getId(), view);
      case Package pkg -> this.addPackage(pkg);
      case Relation relation -> this.addRelation(relation);
      case Property property -> this.addProperty(property);
      case Literal literal -> this.addLiteral(literal);
      case GeneralizationSet genset -> this.addGeneralizationSet(genset);
      case Generalization gen -> this.addGeneralization(gen);
      case Note note -> this.addNote(note);
      case Anchor anchor -> this.addAnchor(anchor);
      case Shape shape -> this.shapes.put(element.getId(), shape);
      default -> throw new IllegalStateException("Unexpected value: " + element);
    }
    element.setProjectContainer(this);
    return element;
  }

  /**
   * Method that creates a new package and add it to the list of elements of the project
   *
   * @param id - Package id.
   * @param name - Package Name.
   * @return the created package.
   */
  public Package createPackage(String id, String name) {
    Package pkg = new Package(id, name);
    pkg.setProjectContainer(this);
    this.packages.put(id, pkg);
    return pkg;
  }

  public Diagram createDiagram(String id, String name) {
    Diagram diagram = new Diagram(id, name);
    diagram.setProjectContainer(this);
    this.diagrams.put(id, diagram);
    return diagram;
  }

  public Class addClass(Class clazz) {
    clazz.setProjectContainer(this);
    this.classes.put(clazz.getId(), clazz);
    return clazz;
  }

  public Package addPackage(Package pkg) {
    pkg.setProjectContainer(this);
    this.packages.put(pkg.getId(), pkg);
    return pkg;
  }

  public Relation addRelation(Relation relation) {
    relation.setProjectContainer(this);
    this.relations.put(relation.getId(), relation);
    return relation;
  }

  public Literal addLiteral(Literal literal) {
    literal.setProjectContainer(this);
    this.literals.put(literal.getId(), literal);
    return literal;
  }

  public Property addProperty(Property property) {
    property.setProjectContainer(this);
    this.properties.put(property.getId(), property);
    return property;
  }

  public Generalization addGeneralization(Generalization gen) {
    gen.setProjectContainer(this);
    this.generalizations.put(gen.getId(), gen);
    return gen;
  }

  public GeneralizationSet addGeneralizationSet(GeneralizationSet genset) {
    genset.setProjectContainer(this);
    this.generalizationSets.put(genset.getId(), genset);
    return genset;
  }

  public Note addNote(Note note) {
    note.setProjectContainer(this);
    this.notes.put(note.getId(), note);
    return note;
  }

  public Anchor addAnchor(Anchor anchor) {
    anchor.setProjectContainer(this);
    this.anchors.put(anchor.getId(), anchor);
    return anchor;
  }

  public Diagram addDiagram(Diagram diagram) {
    diagram.setProjectContainer(this);
    this.diagrams.put(diagram.getId(), diagram);
    return diagram;
  }

  private View addView(View view) {
    view.setProjectContainer(this);
    this.views.put(view.getId(), view);
    return view;
  }

  public Shape addShape(Shape shape) {
    shape.setProjectContainer(this);
    this.shapes.put(shape.getId(), shape);
    return shape;
  }

  public Diamond addDiamond(Diamond diamond) {
    diamond.setProjectContainer(this);
    this.shapes.put(diamond.getId(), diamond);
    return diamond;
  }

  public Path addPath(Path path) {
    path.setProjectContainer(this);
    this.shapes.put(path.getId(), path);
    return path;
  }

  public Rectangle addRectangle(Rectangle rectangle) {
    rectangle.setProjectContainer(this);
    this.shapes.put(rectangle.getId(), rectangle);
    return rectangle;
  }

  public void addRectangularShape(RectangularShape rectangle) {
    rectangle.setProjectContainer(this);
    this.shapes.put(rectangle.getId(), rectangle);
  }

  public void addText(Text text) {
    text.setProjectContainer(this);
    this.shapes.put(text.getId(), text);
  }

  public Optional<Property> getPropertyById(String id) {
    return Optional.ofNullable(this.properties.get(id));
  }

  public Optional<Relation> getRelationById(String id) {
    return Optional.ofNullable(this.relations.get(id));
  }

  public Optional<Generalization> getGeneralizationById(String id) {
    return Optional.ofNullable(this.generalizations.get(id));
  }

  public Optional<GeneralizationSet> getGeneralizationSetById(String id) {
    return Optional.ofNullable(this.generalizationSets.get(id));
  }

  @Override
  public String toString() {
    return "Project";
    //    return "Project{"
    //        + "type='"
    //        + getType()
    //        + '\''
    //        + ", metaProperties="
    //        + metaProperties.toString()
    //        + ", diagrams="
    //        + diagrams.keySet()
    //        + // Including keys to summarize data
    //        ", views="
    //        + views.keySet()
    //        + ", shapes="
    //        + shapes.keySet()
    //        + ", root="
    //        + (root != null ? root.getName() : "null")
    //        + // Assuming root has getName method
    //        ", classes="
    //        + classes.keySet()
    //        + ", packages="
    //        + packages.keySet()
    //        + ", relations="
    //        + relations.keySet()
    //        + ", properties="
    //        + properties.keySet()
    //        + ", literals="
    //        + literals.keySet()
    //        + ", generalizationSets="
    //        + generalizationSets.keySet()
    //        + ", generalizations="
    //        + generalizations.keySet()
    //        + ", notes="
    //        + notes.keySet()
    //        + ", anchors="
    //        + anchors.keySet()
    //        + '}';
  }

  public ProjectMetaProperties getMetaProperties() {
    return metaProperties;
  }

  public void setMetaProperties(ProjectMetaProperties metaProperties) {
    this.metaProperties = metaProperties;
  }

  public Iterable<? extends View> getAllDiagramElements() {
    return this.views.values();
  }

  public List<Anchor> getAllAnchors() {
    return anchors.values().stream().toList();
  }

  public List<Class> getAllClasses() {
    return classes.values().stream().toList();
  }

  public List<Relation> getAllRelations() {
    return relations.values().stream().toList();
  }

  public List<Diagram> getAllDiagrams() {
    return diagrams.values().stream().toList();
  }

  public List<Generalization> getAllGeneralizations() {
    return generalizations.values().stream().toList();
  }

  public List<GeneralizationSet> getAllGeneralizationSets() {
    return generalizationSets.values().stream().toList();
  }

  public Iterable<? extends Property> getAllProperties() {
    return this.properties.values();
  }

  public List<Literal> getAllLiterals() {
    return literals.values().stream().toList();
  }

  public List<Note> getAllNotes() {
    return notes.values().stream().toList();
  }

  public List<Package> getAllPackages() {
    return packages.values().stream().toList();
  }

  public List<Relation> getAlRelations() {
    return relations.values().stream().toList();
  }

  public List<Shape> getAllShapes() {
    return shapes.values().stream().toList();
  }

  public List<View> getAllViews() {
    return views.values().stream().toList();
  }

  public Package getRoot() {
    return root;
  }

  public void setRoot(Package root) {
    this.root = root;
  }
}
