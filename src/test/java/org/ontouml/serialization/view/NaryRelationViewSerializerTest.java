package org.ontouml.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Class;
import org.ontouml.model.NaryRelation;
import org.ontouml.model.view.ClassView;
import org.ontouml.model.view.NaryRelationView;
import org.ontouml.shape.Diamond;
import org.ontouml.shape.Path;

public class NaryRelationViewSerializerTest {
  NaryRelationView naryRelationView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz1 = Class.builder().id("class_1").build();
    Class clazz2 = Class.builder().id("class_2").build();
    Class clazz3 = Class.builder().id("class_3").build();

    ClassView classView1 = ClassView.builder().id("classview_1").isViewOf(clazz1).build();
    ClassView classView2 = ClassView.builder().id("classview_2").isViewOf(clazz2).build();
    ClassView classView3 = ClassView.builder().id("classview_3").isViewOf(clazz3).build();

    Path path1 = Path.builder().id("path_1").build();
    Path path2 = Path.builder().id("path_2").build();
    Path path3 = Path.builder().id("path_3").build();

    Diamond diamond = Diamond.builder().id("diamond_1").build();

    NaryRelation naryRelation = NaryRelation.builder().id("naryrelation_1").build();

    naryRelationView =
        NaryRelationView.builder()
            .id("naryrelationview_1")
            .members(List.of(classView1, classView2, classView3))
            .paths(List.of(path1, path2, path3))
            .diamond(diamond)
            .isViewOf(naryRelation)
            .build();

    node = naryRelationView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("naryrelationview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("naryrelation_1");
  }

  @Test
  void shouldSerializeMembers() throws JsonProcessingException {
    JsonNode membersNode = node.get("members");

    assertThat(membersNode.get(0).textValue()).isEqualTo("classview_1");
    assertThat(membersNode.get(1).textValue()).isEqualTo("classview_2");
    assertThat(membersNode.get(2).textValue()).isEqualTo("classview_3");
  }

  @Test
  void shouldSerializePaths() throws JsonProcessingException {
    JsonNode pathsNode = node.get("paths");

    assertThat(pathsNode.get(0).textValue()).isEqualTo("path_1");
    assertThat(pathsNode.get(1).textValue()).isEqualTo("path_2");
    assertThat(pathsNode.get(2).textValue()).isEqualTo("path_3");
  }
}
