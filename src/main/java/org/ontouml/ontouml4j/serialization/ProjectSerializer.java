package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.utils.ProjectMetaProperties;

public class ProjectSerializer extends JsonSerializer<Project> {

  private static JsonGenerator jsonGen;
  private static Project project;
  private static ProjectMetaProperties metaProperties;

  public static void serializeFields(Project project, JsonGenerator jsonGen) throws IOException {
    ProjectSerializer.jsonGen = jsonGen;
    ProjectSerializer.project = project;
    ProjectSerializer.metaProperties = project.getMetaProperties();

    NamedElementSerializer.serializeFields(project, jsonGen);
    jsonGen.writeStringField("type", "Project");

    serializeMetaProperties();
    Serializer.writeNullableStringField(
        "root", project.getRoot() == null ? null : project.getId(), jsonGen);
    serializeElements();
  }

  private static void serializeMetaProperties() throws IOException {
    if (metaProperties == null) {
      return;
    }
    Serializer.writeNullableOjectField("publisher", metaProperties.getPublisher(), jsonGen);
    Serializer.writeEmptyableArrayField(
        "designedForTasks", metaProperties.getDesignedForTasks(), jsonGen);
    Serializer.writeNullableOjectField("license", metaProperties.getLicense(), jsonGen);
    Serializer.writeEmptyableArrayField("accessRights", metaProperties.getAccessRights(), jsonGen);
    Serializer.writeEmptyableArrayField("themes", metaProperties.getThemes(), jsonGen);
    Serializer.writeEmptyableArrayField("contexts", metaProperties.getContexts(), jsonGen);
    Serializer.writeEmptyableArrayField(
        "ontologyTypes", metaProperties.getOntologyTypes(), jsonGen);
    Serializer.writeNullableOjectField(
        "representationStyle", metaProperties.getRepresentationStyle(), jsonGen);
    Serializer.writeNullableStringField(
        "namespace",
        metaProperties.getNamespace() == null ? null : metaProperties.getNamespace().toString(),
        jsonGen);

    Serializer.writeEmptyableArrayField("landingPages", metaProperties.getLandingPages(), jsonGen);
    Serializer.writeEmptyableArrayField("sources", metaProperties.getSources(), jsonGen);
    Serializer.writeEmptyableArrayField(
        "bibliographicCitations", metaProperties.getBibliographicCitations(), jsonGen);
    Serializer.writeEmptyableArrayField("acronyms", metaProperties.getAcronyms(), jsonGen);
    Serializer.writeEmptyableArrayField("languages", metaProperties.getLanguages(), jsonGen);
    Serializer.writeEmptyableArrayField("keywords", metaProperties.getKeywords(), jsonGen);
  }

  private static void serializeElements() throws IOException {
    List<ModelElement> elements = project.getElements();
    jsonGen.writeArrayFieldStart("elements");
    for (ModelElement element : elements) {
      jsonGen.writeStartObject();
      switch (element) {
        case Class clazz -> ClassSerializer.serializeFields(clazz, jsonGen);
        case Package pkg -> PackageSerializer.serializeFields(pkg, jsonGen);
        case Property property -> PropertySerializer.serializeFields(property, jsonGen);
        case Literal literal -> LiteralSerializer.serializeFields(literal, jsonGen);
        case BinaryRelation relation -> BinaryRelationSerializer.serializeFields(relation, jsonGen);
        case NaryRelation relation -> NaryRelationSerializer.serializeFields(relation, jsonGen);
        case Generalization gen -> GeneralizationSerializer.serializeFields(gen, jsonGen);
        case GeneralizationSet gen -> GeneralizationSetSerializer.serializeFields(gen, jsonGen);
        case Anchor anchor -> AnchorSerializer.serializeFields(anchor, jsonGen);
        case Note note -> NoteSerializer.serializeFields(note, jsonGen);
        default ->
            throw new IllegalArgumentException("Unexpected element type: " + element.getClass());
      }
      jsonGen.writeEndObject();
    }
    jsonGen.writeEndArray();
  }

  @Override
  public void serialize(Project project, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(project, jsonGen);
    jsonGen.writeEndObject();
  }
}
