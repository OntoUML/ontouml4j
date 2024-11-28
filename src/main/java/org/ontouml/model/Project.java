package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.ProjectDeserializer;
import org.ontouml.model.utils.ProjectMetaProperties;
import org.ontouml.model.view.View;
import org.ontouml.serialization.ProjectSerializer;

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
public class Project extends NamedElement {

  /** Contains the OntoUML elements that are part of the project. */
  @Builder.Default ProjectMetaProperties metaProperties = new ProjectMetaProperties();

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
  @Builder.Default private List<View> diagrams = new ArrayList<>();

  private Map<String, ModelElement> allElements;

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

  public void setElements(List<ModelElement> elements) {
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
          }
        });
  }

  public Optional<Class> getClassById(String id) {
    return Optional.ofNullable(this.classes.get(id));
  }

  public Map<String, ModelElement> getElementMap() {
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

  public Iterable<? extends Property> getAllProperties() {
    return this.properties.values();
  }

  public Iterable<? extends Generalization> getAllGeneralizations() {
    return this.generalizations.values();
  }

  public Iterable<? extends GeneralizationSet> getAllGeneralizationSets() {
    return this.generalizationSets.values();
  }

  public Map<String, ModelElement> getAllElements() {
    return this.getElementMap();
  }

  /**
   * This method is less efficient because it does not take into account the class of the element
   *
   * @param id - The id of the element
   */
  public Optional<ModelElement> getElementById(String id) {
    if (this.allElements == null) {
      this.allElements = this.getElementMap();
    }
    return Optional.ofNullable(this.allElements.get(id));
  }

  public <T extends OntoumlElement> Optional<T> getElementById(String id, java.lang.Class<T> type) {
    if (type == Class.class) {
      return Optional.ofNullable(type.cast(this.classes.get(id)));
    } else if (type == Package.class) {
      return Optional.ofNullable(type.cast(this.packages.get(id)));
    } else if (type == Relation.class
        || type == BinaryRelation.class
        || type == NaryRelation.class) {
      return Optional.ofNullable(type.cast(this.relations.get(id)));
    } else if (type == Property.class) {
      return Optional.ofNullable(type.cast(this.properties.get(id)));
    } else if (type == Literal.class) {
      return Optional.ofNullable(type.cast(this.literals.get(id)));
    } else if (type == GeneralizationSet.class) {
      return Optional.ofNullable(type.cast(this.generalizationSets.get(id)));
    } else if (type == Generalization.class) {
      return Optional.ofNullable(type.cast(this.generalizations.get(id)));
    } else if (type == Note.class) {
      return Optional.ofNullable(type.cast(this.notes.get(id)));
    } else if (type == Anchor.class) {
      return Optional.ofNullable(type.cast(this.anchors.get(id)));
    } else if (type == Classifier.class) {
      Map<String, Classifier> classififers = new HashMap<>(this.classes);
      classififers.putAll(this.relations);
      return Optional.ofNullable(type.cast(classififers.get(id)));
    } else if (type == ModelElement.class) {
      return Optional.ofNullable(type.cast(this.getElementMap().get(id)));
    } else {
      return Optional.empty();
    }
  }

  public void addClass(Class clazz) {
    this.classes.put(clazz.getId(), clazz);
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
