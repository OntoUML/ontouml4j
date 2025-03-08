package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.NaryRelation;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.NaryRelationView;
import org.ontouml.ontouml4j.shape.Diamond;
import org.ontouml.ontouml4j.shape.Path;

public class NaryRelationViewSerializerTest {
  NaryRelationView naryRelationView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Class clazz1 = new Class("class_1");
    Class clazz2 = new Class("class_2");
    Class clazz3 = new Class("class_3");

    ClassView classView1 = new ClassView("classview_1", clazz1);
    ClassView classView2 = new ClassView("classview_2", clazz2);
    ClassView classView3 = new ClassView("classview_3", clazz3);

    Path path1 = new Path("path_1");
    Path path2 = new Path("path_2");
  Path path3 = new Path("path_3");

    Diamond diamond = new Diamond("diamond_1");

    NaryRelation naryRelation = new NaryRelation("naryrelation_1");

    naryRelationView = new
        NaryRelationView("naryrelationview_1", List.of(classView1, classView2, classView3), List.of(path1, path2, path3), diamond, naryRelation);

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
