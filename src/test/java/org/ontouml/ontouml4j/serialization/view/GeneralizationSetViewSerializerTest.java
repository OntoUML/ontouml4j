package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Generalization;
import org.ontouml.ontouml4j.model.GeneralizationSet;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.GeneralizationSetView;
import org.ontouml.ontouml4j.model.view.GeneralizationView;
import org.ontouml.ontouml4j.shape.Path;
import org.ontouml.ontouml4j.shape.Point;
import org.ontouml.ontouml4j.shape.Text;

public class GeneralizationSetViewSerializerTest {
  GeneralizationSetView generalizationSetView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz = Class.builder().id("class_1").name(new MultilingualText("MyClass")).build();
    Class clazz2 = Class.builder().id("class_2").name(new MultilingualText("MyClass")).build();

    Generalization gen =
        Generalization.builder().id("generalization_1").general(clazz).specific(clazz2).build();
    Generalization gen2 =
        Generalization.builder().id("generalization_2").general(clazz2).specific(clazz).build();

    GeneralizationSet genset =
        GeneralizationSet.builder().id("genset_1").generalizations(Set.of(gen, gen2)).build();

    ClassView classview1 = ClassView.builder().id("classview_1").isViewOf(clazz).build();
    ClassView classview2 = ClassView.builder().id("classview_2").isViewOf(clazz2).build();

    Path path = Path.builder().id("path_1").build();

    GeneralizationView generalizationView =
        GeneralizationView.builder()
            .id("generalizationview_1")
            .isViewOf(gen)
            .sourceView(classview1)
            .targetView(classview2)
            .path(path)
            .build();
    GeneralizationView generalizationView2 =
        GeneralizationView.builder()
            .id("generalizationview_2")
            .isViewOf(gen)
            .sourceView(classview1)
            .targetView(classview2)
            .path(path)
            .build();

    generalizationSetView =
        GeneralizationSetView.builder()
            .id("generalizationview_1")
            .isViewOf(genset)
            .text(
                Text.builder().id("text_1").topLeft(new Point(10, 20)).width(25).height(50).build())
            .generalizations(List.of(generalizationView, generalizationView2))
            .build();

    node = generalizationSetView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("generalizationview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("genset_1");
  }

  @Test
  void shouldSerializeGeneralizations() throws JsonProcessingException {
    JsonNode sourceNode = node.get("generalizations");

    assertThat(sourceNode.get(0).textValue()).isEqualTo("generalizationview_1");
    assertThat(sourceNode.get(1).textValue()).isEqualTo("generalizationview_2");
  }

  @Test
  void shouldSerializeText() throws JsonProcessingException {
    String text = node.get("text").textValue();
    assertThat(text).isEqualTo("text_1");
  }
}
