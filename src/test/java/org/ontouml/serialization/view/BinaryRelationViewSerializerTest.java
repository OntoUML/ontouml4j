package org.ontouml.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.BinaryRelation;
import org.ontouml.model.Class;
import org.ontouml.model.view.BinaryRelationView;
import org.ontouml.model.view.ClassView;
import org.ontouml.shape.Path;

public class BinaryRelationViewSerializerTest {
  BinaryRelationView binaryRelationView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz1 = Class.builder().id("class_1").build();
    Class clazz2 = Class.builder().id("class_2").build();

    ClassView classView1 = ClassView.builder().id("classview_1").isViewOf(clazz1).build();
    ClassView classView2 = ClassView.builder().id("classview_2").isViewOf(clazz2).build();

    BinaryRelation binaryRelation = BinaryRelation.builder().id("binaryrelation_1").build();

    Path path = Path.builder().id("path_1").build();

    binaryRelationView =
        BinaryRelationView.builder()
            .id("binaryrelationview_1")
            .sourceView(classView1)
            .targetView(classView2)
            .path(path)
            .isViewOf(binaryRelation)
            .build();

    node = binaryRelationView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("binaryrelationview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("binaryrelation_1");
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
