package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Generalization;
import org.ontouml.ontouml4j.model.GeneralizationSet;
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
    Class clazz = new Class("class_1", "MyClass");
    Class clazz2 = new Class("class_2", "MyClass2");

    Generalization gen = new Generalization("generalization_1", clazz, clazz2);
    Generalization gen2 = new Generalization("generalization_2", clazz2, clazz);

    GeneralizationSet genset = new GeneralizationSet("genset_1", null, List.of(gen, gen2));

    ClassView classview1 = new ClassView("classview_1", clazz);
    ClassView classview2 = new ClassView("classview_2", clazz2);

    Path path = new Path("path_1");

    GeneralizationView generalizationView = new GeneralizationView("generalizationview_1", gen, classview1, classview2, path);
    GeneralizationView generalizationView2 = new GeneralizationView("generalizationview_2", gen, classview1, classview2, path);

    generalizationSetView = new GeneralizationSetView("generalizationview_1", genset, new Text("text_1", new Point(10,20), 25, 50), List.of(generalizationView, generalizationView2));

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
