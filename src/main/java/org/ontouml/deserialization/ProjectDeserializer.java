package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.MultilingualText;
import org.ontouml.Project;
import org.ontouml.model.Resource;
import org.ontouml.view.Diagram;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class ProjectDeserializer extends JsonDeserializer<Project> {

  @Override
  public Project deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    System.out.println("Deserializing project...");

    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Project project = new Project();

    ElementDeserializer.deserialize(project, root, codec);
    deserializeMetaProperties(codec, project, root);

    // TODO: Change Model to root elements
//    Package model = DeserializerUtils.deserializeObjectField(root, "model", Package.class, codec);
//    project.setModel(model);
//
    List<Diagram> diagrams = DeserializerUtils.deserializeArrayField(root, "diagrams", Diagram.class, codec);
    project.setDiagrams(diagrams);

    try {
      ReferenceResolver.resolveReferences(project);
    } catch (Exception e) {
      throw new JsonParseException(parser, "Cannot deserialize project", e);
    }

    return project;
  }

  public void deserializeMetaProperties(ObjectCodec codec, Project project, JsonNode root) throws IOException {
    JsonNode keywordsNode = root.get("keywords");
    if (keywordsNode != null) {
      List<MultilingualText> keywords = keywordsNode.traverse(codec).readValueAs(new TypeReference<List<MultilingualText>>() {
      });
      project.getMetaProperties().setKeywords(keywords);
      System.out.println("Deserialized Keywords: " + keywords);
    }

    JsonNode publisherNode = root.get("publisher");
    if (publisherNode != null) {
      Resource publisher = publisherNode.traverse(codec).readValueAs(Resource.class);
      project.getMetaProperties().setPublisher(publisher);
      System.out.println("Deserialized publisher: " + publisher);
    }

    JsonNode designedForTasksNode = root.get("designedForTasks");
    if (designedForTasksNode != null) {
      List<Resource> designedForTasks = designedForTasksNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {
      });
      project.getMetaProperties().setDesignedForTasks(designedForTasks);
      System.out.println("Deserialized designedForTasks: " + designedForTasks);
    }

    JsonNode licenseNode = root.get("license");
    if (licenseNode != null) {
      Resource license = licenseNode.traverse(codec).readValueAs(Resource.class);
      project.getMetaProperties().setLicense(license);
      System.out.println("Deserialized license: " + license);
    }

    JsonNode accessRightsNode = root.get("accessRights");
    if (accessRightsNode != null) {
      List<Resource> accessRights = accessRightsNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {
      });
      project.getMetaProperties().setAccessRights(accessRights);
      System.out.println("Deserialized accessRights: " + accessRights);
    }

    JsonNode themesNode = root.get("themes");
    if (themesNode != null) {
      List<Resource> themes = themesNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {
      });
      project.getMetaProperties().setThemes(themes);
      System.out.println("Deserialized themes: " + themes);
    }

    JsonNode contextsNode = root.get("contexts");
    if (contextsNode != null) {
      List<Resource> contexts = contextsNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {
      });
      project.getMetaProperties().setContexts(contexts);
      System.out.println("Deserialized contexts: " + contexts);
    }

    JsonNode ontologyTypesNode = root.get("ontologyTypes");
    if (ontologyTypesNode != null) {
      List<Resource> ontologyTypes = ontologyTypesNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {
      });
      project.getMetaProperties().setOntologyTypes(ontologyTypes);
      System.out.println("Deserialized ontologyTypes: " + ontologyTypes);
    }

    JsonNode representationStyleNode = root.get("representationStyle");
    if (representationStyleNode != null) {
      Resource representationStyle = representationStyleNode.traverse(codec).readValueAs(Resource.class);
      project.getMetaProperties().setRepresentationStyle(representationStyle);
      System.out.println("Deserialized representationStyle: " + representationStyle);
    }

    JsonNode namespaceNode = root.get("namespace");
    if (namespaceNode != null) {
      URI namespace = namespaceNode.traverse(codec).readValueAs(URI.class);
      project.getMetaProperties().setNamespace(namespace);
      System.out.println("Deserialized namespace: " + namespace);
    }

    JsonNode landingPagesNode = root.get("landingPages");
    if (landingPagesNode != null) {
      List<URI> landingPages = landingPagesNode.traverse(codec).readValueAs(new TypeReference<List<URI>>() {
      });
      project.getMetaProperties().setLandingPages(landingPages);
      System.out.println("Deserialized landingPages: " + landingPages);
    }

    JsonNode sourcesNode = root.get("sources");
    if (sourcesNode != null) {
      List<URI> sources = sourcesNode.traverse(codec).readValueAs(new TypeReference<List<URI>>() {
      });
      project.getMetaProperties().setSources(sources);
      System.out.println("Deserialized sources: " + sources);
    }

    JsonNode bibliographicCitationsNode = root.get("bibliographicCitations");
    if (bibliographicCitationsNode != null) {
      List<MultilingualText> citations = bibliographicCitationsNode.traverse(codec).readValueAs(new TypeReference<List<MultilingualText>>() {
      });
      project.getMetaProperties().setBibliographicCitations(citations);
      System.out.println("Deserialized bibliographicCitations: " + citations);
    }

    JsonNode acronymsNode = root.get("acronyms");
    if (acronymsNode != null) {
      List<String> acronyms = acronymsNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {
      });
      project.getMetaProperties().setAcronyms(acronyms);
      System.out.println("Deserialized acronyms: " + acronyms);
    }

    JsonNode languagesNode = root.get("languages");
    if (languagesNode != null) {
      List<String> languages = languagesNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {
      });
      project.getMetaProperties().setLanguages(languages);
      System.out.println("Deserialized languages: " + languages);
    }
  }
}
