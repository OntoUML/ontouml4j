package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Generalization;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.GeneralizationView;
import org.ontouml.ontouml4j.shape.Path;

public class GeneralizationViewSerializerTest {
  GeneralizationView generalizationView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz = Class.builder().id("class_1").name(new MultilingualText("MyClass")).build();
    Class clazz2 = Class.builder().id("class_2").name(new MultilingualText("MyClass")).build();

    Generalization gen =
        Generalization.builder().id("generalization_1").general(clazz).specific(clazz2).build();

    ClassView classview1 = ClassView.builder().id("classview_1").isViewOf(clazz).build();
    ClassView classview2 = ClassView.builder().id("classview_2").isViewOf(clazz2).build();

    Path path = Path.builder().id("path_1").build();

    generalizationView =
        GeneralizationView.builder()
            .id("generalizationview_1")
            .isViewOf(gen)
            .sourceView(classview1)
            .targetView(classview2)
            .path(path)
            .build();

    node = generalizationView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("generalizationview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("generalization_1");
  }

  @Test
  void shouldSerializeSourceView() throws JsonProcessingException {
    JsonNode sourceNode = node.get("sourceView");

    assertThat(sourceNode.textValue()).isEqualTo("classview_1");
  }

  @Test
  void shouldSerializeTargetView() throws JsonProcessingException {
    JsonNode sourceNode = node.get("targetView");

    assertThat(sourceNode.textValue()).isEqualTo("classview_2");
  }

  @Test
  void shouldSerializePath() throws JsonProcessingException {
    JsonNode pathsNode = node.get("path");

    assertThat(pathsNode.textValue()).isEqualTo("path_1");
  }
}
