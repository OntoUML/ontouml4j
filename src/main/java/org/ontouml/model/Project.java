package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.ProjectDeserializer;
import org.ontouml.model.utils.ElementContainer;
import org.ontouml.model.utils.ProjectMetaProperties;
import org.ontouml.model.view.*;
import org.ontouml.serialization.ProjectSerializer;
import org.ontouml.shape.*;

/**
 * A named element that serves as the container of an entire OntoUML ontology, including the
 * elements of both the abstract syntax (i.e., model elements) and the concrete syntax (i.e.,
 * diagrams, view, and shapes).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Getter
@JsonSerialize(using = ProjectSerializer.class)
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project extends NamedElement implements ElementContainer {

  /** Contains the OntoUML elements that are part of the project. */
  @Builder.Default ProjectMetaProperties metaProperties = new ProjectMetaProperties();

  /** Properties related to diagrams, views, and shapes that are part of the project. */
  @Builder.Default Map<String, Diagram> diagrams = new HashMap<>();

  @Builder.Default Map<String, View> views = new HashMap<>();
  @Builder.Default Map<String, Shape> shapes = new HashMap<>();

  /**
   * Identifies the root package of a project (the package containing all other model elements of
   * the project) if present.
   */
  private Package root;

  @Builder.Default private Map<String, Class> classes = new HashMap<>();
  @Builder.Default private Map<String, Package> packages = new HashMap<>();
  @Builder.Default private Map<String, Relation> relations = new HashMap<>();
  @Builder.Default private Map<String, Property> properties = new HashMap<>();
  @Builder.Default private Map<String, Literal> literals = new HashMap<>();
  @Builder.Default private Map<String, GeneralizationSet> generalizationSets = new HashMap<>();
  @Builder.Default private Map<String, Generalization> generalizations = new HashMap<>();
  @Builder.Default private Map<String, Note> notes = new HashMap<>();
  @Builder.Default private Map<String, Anchor> anchors = new HashMap<>();

  @Override
  public String getType() {
    return "Project";
  }

  public List<ModelElement> getElements() {
    List<ModelElement> elements = new ArrayList<>();

    elements.addAll(classes.values().stream().toList());
    elements.addAll(packages.values().stream().toList());
    elements.addAll(relations.values().stream().toList());
    elements.addAll(properties.values().stream().toList());
    elements.addAll(literals.values().stream().toList());
    elements.addAll(generalizationSets.values().stream().toList());
    elements.addAll(generalizations.values().stream().toList());
    elements.addAll(notes.values().stream().toList());
    elements.addAll(anchors.values().stream().toList());

    return elements;
  }

  public void setElements(List<OntoumlElement> elements) {
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

  public Iterable<? extends Property> getAllProperties() {
    return this.properties.values();
  }

  public Iterable<? extends Generalization> getAllGeneralizations() {
    return this.generalizations.values();
  }

  public Iterable<? extends GeneralizationSet> getAllGeneralizationSets() {
    return this.generalizationSets.values();
  }

  public ModelElement addElement(ModelElement element) {
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
    Package pkg = Package.builder().id(id).name(new MultilingualText(name)).build();
    pkg.setProjectContainer(this);
    this.packages.put(id, pkg);
    return pkg;
  }

  public void addClass(Class clazz) {
    clazz.setProjectContainer(this);
    this.classes.put(clazz.getId(), clazz);
  }

  public void addPackage(Package pkg) {
    pkg.setProjectContainer(this);
    this.packages.put(pkg.getId(), pkg);
  }

  public void addRelation(Relation relation) {
    relation.setProjectContainer(this);
    this.relations.put(relation.getId(), relation);
  }

  public void addLiteral(Literal literal) {
    literal.setProjectContainer(this);
    this.literals.put(literal.getId(), literal);
  }

  public void addProperty(Property property) {
    property.setProjectContainer(this);
    this.properties.put(property.getId(), property);
  }

  public void addGeneralization(Generalization gen) {
    gen.setProjectContainer(this);
    this.generalizations.put(gen.getId(), gen);
  }

  public void addGeneralizationSet(GeneralizationSet genset) {
    genset.setProjectContainer(this);
    this.generalizationSets.put(genset.getId(), genset);
  }

  public void addNote(Note note) {
    note.setProjectContainer(this);
    this.notes.put(note.getId(), note);
  }

  public void addAnchor(Anchor anchor) {
    anchor.setProjectContainer(this);
    this.anchors.put(anchor.getId(), anchor);
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
}
