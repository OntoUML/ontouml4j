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
    Class clazz = new Class("class_1", new MultilingualText("MyClass"));
    Class clazz2 = new Class("class_2", new MultilingualText("MyClass"));

    Generalization gen = new Generalization("generalization_1", clazz, clazz2);

    ClassView classview1 = new ClassView("classview_1",clazz);
    ClassView classview2 = new ClassView("classview_2", clazz2);

    Path path = new Path("path_1");

    generalizationView = new GeneralizationView("generalizationview_1", gen, classview1, classview2, path);

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
