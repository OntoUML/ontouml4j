package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.*;
import org.ontouml.model.utils.ProjectMetaProperties;

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
    ProjectMetaProperties metaProperties = project.getMetaProperties();

    if (metaProperties == null) {
      return;
    }
    Serializer.writeNullableOjectField("publisher", metaProperties.getPublisher(), jsonGen);
    Serializer.writeNullableArrayField(
        "designedForTasks", metaProperties.getDesignedForTasks(), jsonGen);
    Serializer.writeNullableOjectField("license", metaProperties.getLicense(), jsonGen);
    Serializer.writeNullableArrayField("accessRights", metaProperties.getAccessRights(), jsonGen);
    Serializer.writeNullableArrayField("themes", metaProperties.getThemes(), jsonGen);
    Serializer.writeNullableArrayField("contexts", metaProperties.getContexts(), jsonGen);
    Serializer.writeNullableArrayField("ontologyTypes", metaProperties.getOntologyTypes(), jsonGen);
    Serializer.writeNullableOjectField(
        "representationStyle", metaProperties.getRepresentationStyle(), jsonGen);
    Serializer.writeNullableStringField(
        "namespace",
        metaProperties.getNamespace() == null ? null : metaProperties.getNamespace().toString(),
        jsonGen);

    Serializer.writeNullableArrayField("landingPages", metaProperties.getLandingPages(), jsonGen);
    Serializer.writeNullableArrayField("sources", metaProperties.getSources(), jsonGen);
    Serializer.writeNullableArrayField(
        "bibliographicCitations", metaProperties.getBibliographicCitations(), jsonGen);
    Serializer.writeNullableArrayField("acronyms", metaProperties.getAcronyms(), jsonGen);
    Serializer.writeNullableArrayField("languages", metaProperties.getLanguages(), jsonGen);
    Serializer.writeNullableArrayField("keywords", metaProperties.getKeywords(), jsonGen);
  }

  private static void serializeElements() throws IOException {
    List<ModelElement> elements = project.getElements();
    jsonGen.writeArrayFieldStart("elements");
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
