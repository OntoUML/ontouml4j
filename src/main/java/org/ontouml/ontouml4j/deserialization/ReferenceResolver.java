package org.ontouml.ontouml4j.deserialization;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.view.View;

/** This class is responsible for resolving the references inside elements after they are read. */
public class ReferenceResolver {
  public static void resolveReferences(Project project) {
    Map<String, ModelElement> elementMap = project.getModelElementMap();

    for (Property property : project.getAllProperties()) {
      resolvePropertyType(elementMap, property);
      resolveSubsettedProperties(elementMap, property);
      resolveRedefinedProperties(elementMap, property);
    }

    for (Generalization generalization : project.getAllGeneralizations()) {
      resolveGeneral(elementMap, generalization);
      resolveSpecific(elementMap, generalization);
    }

    for (GeneralizationSet gs : project.getAllGeneralizationSets()) {
      System.out.println(gs);
      resolveCategorizer(elementMap, gs);
      resolveGeneralizations(elementMap, gs);
    }

    //            for (Diagram diagram : project.getDiagrams()) {
    //              resolveOwner(elementMap, diagram);
    //            }
    //
    //            for (ElementView diagramElement : project.getAllDiagramElements()) {
    //              resolveModelElement(elementMap, diagramElement);
    //            }
    //
    //            for (ConnectorView connectorView : project.getAllConnectorViews()) {
    //              resolveSource(elementMap, connectorView);
    //              resolveTarget(elementMap, connectorView);
    //            }

    buildPropertyReferences(project);
    buildClassifierReferences(project);
    buildPackageReferences(project);
    buildGeneralizationReferences(project);
    buildGeneralizationSetReferences(project);
    buildAnchorReferences(project);
    buildViewsReferences(project);
    buildDiagramReferences(project);
  }

  //    private static void resolveOwner(Map<String, OntoumlElement> elementMap, Diagram diagram) {
  //      ModelElement reference = diagram.getOwner();
  //
  //      if (reference == null) return;
  //
  //      ModelElement source = resolve(elementMap, reference, ModelElement.class);
  //      diagram.setOwner(source);
  //    }

  //  private static void resolveSource(Map<String, OntoumlElement> elementMap, ConnectorView
  // element) {
  //    ElementView reference = element.getSource();
  //
  //    if (reference == null) return;
  //
  //    ElementView source = resolve(elementMap, reference, ElementView.class);
  //    element.setSource(source);
  //  }

  //    private static void resolveTarget(Map<String, OntoumlElement> elementMap, ConnectorView
  //   element) {
  //      ElementView reference = element.getTarget();
  //
  //      if (reference == null) return;
  //
  //      ElementView source = resolve(elementMap, reference, ElementView.class);
  //      element.setTarget(source);
  //    }

  private static void resolveModelElement(Map<String, OntoumlElement> elementMap, View element) {
    //    ModelElement reference = element.getModelElement();
    //
    //    if (reference == null) return;
    //
    //    ModelElement source = resolve(elementMap, reference, ModelElement.class);
    //    element.setModelElement(source);
  }

  private static void resolveGeneralizations(
      Map<String, ModelElement> elementMap, GeneralizationSet gs) {

    Set<Generalization> sources = new HashSet<>();

    for (Generalization reference : gs.getGeneralizations())
      sources.add(resolve(elementMap, reference, Generalization.class));

    gs.setGeneralizations(sources);
  }

  private static void resolveCategorizer(
      Map<String, ModelElement> elementMap, GeneralizationSet gs) {
    Optional<Class> reference = gs.getCategorizer();

    if (reference.isEmpty()) return;

    Class source = resolve(elementMap, reference.get(), Class.class);
    gs.setCategorizer(source);
  }

  private static void resolveGeneral(
      Map<String, ModelElement> elementMap, Generalization generalization) {
    Optional<Classifier<?, ?>> reference = generalization.getGeneral();

    if (reference.isEmpty()) return;

    Classifier<?, ?> source = resolve(elementMap, reference.get(), Classifier.class);
    generalization.setGeneral(source);
  }

  private static void resolveSpecific(
      Map<String, ModelElement> elementMap, Generalization generalization) {
    Optional<Classifier<?, ?>> reference = generalization.getSpecific();

    if (reference.isEmpty()) return;

    Classifier<?, ?> source = resolve(elementMap, reference.get(), Classifier.class);
    generalization.setSpecific(source);
  }

  private static void resolvePropertyType(Map<String, ModelElement> elementMap, Property property) {
    Optional<Classifier<?, ?>> reference = property.getPropertyType();

    if (reference.isEmpty()) return;

    Classifier<?, ?> source = resolve(elementMap, reference.get(), Classifier.class);
    property.setPropertyType(source);
  }

  private static void resolveSubsettedProperties(
      Map<String, ModelElement> elementMap, Property property) {
    for (Property reference : property.getSubsettedProperties()) {
      Property source = resolve(elementMap, reference, Property.class);
      property.replaceSubsettedProperty(reference, source);
    }
  }

  private static void resolveRedefinedProperties(
      Map<String, ModelElement> elementMap, Property property) {
    for (Property reference : property.getRedefinedProperties()) {
      Property source = resolve(elementMap, reference, Property.class);
      property.replaceRedefinedProperty(reference, source);
    }
  }

  private static <T extends ModelElement> T resolve(
      Map<String, ModelElement> elementMap, T reference, java.lang.Class<T> referenceType) {

    ModelElement source = elementMap.get(reference.getId());

    if (source == null)
      throw new NullPointerException("Referenced element in property type does not exist!");

    if (!referenceType.isInstance(source))
      throw new NullPointerException("Referenced element in property type is not a classifier!");

    return referenceType.cast(source);
  }

  /**
   * This method is responsible for building the literals references after the entire project is
   * parsed. This needs to be done because the OntoUML JSON Schema v1.2 only contains an array of
   * ids in the literals property
   *
   * @param project - the parsed project
   */
  private static void buildClassifierReferences(Project project) {
    project.getClasses().values().forEach(clazz -> clazz.buildAllReferences(project));
    project
        .getRelations()
        .values()
        .forEach(relation -> relation.resolvePropertyReferences(project));
  }

  /**
   * This method is responsible for building the propertyType references after the entire project is
   * parsed.
   *
   * @param project - the parsed project
   */
  private static void buildPropertyReferences(Project project) {
    project.getAllProperties().forEach(clazz -> clazz.buildAllReferences(project));
  }

  /**
   * This method is responsible for building the references for all elements in the Packages
   *
   * @param project - the parsed project
   */
  private static void buildPackageReferences(Project project) {
    project.getPackages().values().forEach(pkg -> pkg.buildAllReferences(project));
  }

  /**
   * This method is responsible for building the references for all elements in the Generalization
   *
   * @param project - the parsed project
   */
  private static void buildGeneralizationReferences(Project project) {
    project.getGeneralizations().values().forEach(gen -> gen.buildAllReferences(project));
  }

  /**
   * This method is responsible for building the references for all elements in the
   * GeneralizationSet
   *
   * @param project - the parsed project
   */
  private static void buildGeneralizationSetReferences(Project project) {
    project.getGeneralizationSets().values().forEach(gen -> gen.buildAllReferences(project));
  }

  /**
   * This method is responsible for building the references for all elements in the Generalization
   *
   * @param project - the parsed project
   */
  private static void buildAnchorReferences(Project project) {
    project.getAnchors().values().forEach(anchor -> anchor.buildAllReferences(project));
  }

  private static void buildViewsReferences(Project project) {
    project.getViews().values().forEach(view -> view.resolveAllReferences(project));
  }

  private static void buildDiagramReferences(Project project) {
    project.getDiagrams().values().forEach(diagram -> diagram.resolveAllReferences(project));
  }
}
