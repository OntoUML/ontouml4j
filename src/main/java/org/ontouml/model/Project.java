package org.ontouml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.utils.ProjectMetaProperties;
import org.ontouml.model.view.View;

/**
 * A named element that serves as the container of an entire OntoUML ontology, including the
 * elements of both the abstract syntax (i.e., model elements) and the concrete syntax (i.e.,
 * diagrams, view, and shapes).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class Project extends NamedElement {
  /** Contains the OntoUML elements that are part of the project. */
  ProjectMetaProperties metaProperties = new ProjectMetaProperties();

  private Package root;

  private Map<String, Class> classes = new HashMap<>();
  private Map<String, Package> packages = new HashMap<>();
  private Map<String, Relation> relations = new HashMap<>();
  private Map<String, Property> properties = new HashMap<>();
  private Map<String, Literal> literals = new HashMap<>();
  private Map<String, GeneralizationSet> generalizationSets = new HashMap<>();
  private Map<String, Generalization> generalizations = new HashMap<>();

  private List<View> diagrams = new ArrayList<>();

  @Override
  public String getType() {
    return "Project";
  }

  public void setElements(List<ModelElement> elements) {
    elements.forEach(element -> {
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
      }
    });
  }
}
