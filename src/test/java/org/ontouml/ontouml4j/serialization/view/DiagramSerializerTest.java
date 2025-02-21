package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.Diagram;
import org.ontouml.ontouml4j.shape.Rectangle;

public class DiagramSerializerTest {

  Project project;
  Diagram diagram;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    project = new Project("project_1", "My Project name");

    diagram = Diagram.builder().id("diagram_1").build();

    project.addDiagram(diagram);

    node = diagram.serialize();
  }

  @Test
  void shouldSerializeId() throws IOException, URISyntaxException {
    String id = node.get("id").asText();
    assertThat(id).isEqualTo("diagram_1");
  }

  @Test
  void shouldSerializeType() throws IOException, URISyntaxException {
    String type = node.get("type").asText();
    assertThat(type).isEqualTo("Diagram");
  }

  @Test
  void shouldSerializeName() throws IOException, URISyntaxException {
    diagram.addName("pt", "Meu Diagrama");
    diagram.addName("en", "My Diagram");

    node = diagram.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).isEqualTo("Meu Diagrama");
    assertThat(nameEn).isEqualTo("My Diagram");
  }

  @Test
  void shouldSerializeDescription() throws IOException, URISyntaxException {
    diagram.addDescription("pt", "Descrição do diagrama");
    diagram.addDescription("en", "Diagram description");

    node = diagram.serialize();

    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt).isEqualTo("Descrição do diagrama");
    assertThat(descriptionEn).isEqualTo("Diagram description");
  }

  @Test
  void shouldSerializeOwner() throws IOException, URISyntaxException {
    Package pkg = Package.builder().id("package_1").name(new MultilingualText("MyPackage")).build();

    diagram.setOwner(pkg);

    node = diagram.serialize();

    String ownerId = node.get("owner").asText();
    assertThat(ownerId).isEqualTo("package_1");
  }

  @Test
  void shouldSerializeViews() throws IOException, URISyntaxException {
    Class clazz = Class.builder().id("class_1").name(new MultilingualText("MyClass")).build();
    ClassView classView =
        ClassView.builder()
            .id("classview_1")
            .rectangle(new Rectangle("rectangle_1", 10, 20))
            .isViewOf(clazz)
            .build();
    diagram.addElement(classView);

    node = diagram.serialize();

    JsonNode viewsNode = node.get("views");

    assertThat(viewsNode.get(0).textValue()).isEqualTo("classview_1");
  }

  @Test
  void shouldSerializeInsideProject() throws IOException, URISyntaxException {
    Class clazz = Class.builder().id("class_1").name(new MultilingualText("MyClass")).build();
    ClassView classView =
        ClassView.builder()
            .id("classview_1")
            .rectangle(new Rectangle("rectangle_1", 10, 20))
            .isViewOf(clazz)
            .build();
    diagram.addElement(classView);

    JsonNode projectNode = project.serialize();

    JsonNode elements = projectNode.get("elements");

    assertThat(elements.get(0).get("id").textValue()).isEqualTo("diagram_1");
  }
}
