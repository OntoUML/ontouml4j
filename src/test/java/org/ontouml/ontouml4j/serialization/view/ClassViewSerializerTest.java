package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.shape.Rectangle;

public class ClassViewSerializerTest {
  static ClassView classView;
  JsonNode node;

  @BeforeAll
  static void beforeAll() throws IOException, ParseException, URISyntaxException {}

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz = Class.builder().id("class_1").name(new MultilingualText("MyClass")).build();
    classView =
        ClassView.builder()
            .id("classview_1")
            .rectangle(new Rectangle("rectangle_1", 10, 20))
            .isViewOf(clazz)
            .build();

    node = classView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("classview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("class_1");
  }

  @Test
  void shouldSerializeRectangle() throws JsonProcessingException {
    String rectangle = node.get("rectangle").textValue();

    assertThat(rectangle).isEqualTo("rectangle_1");
  }
}
