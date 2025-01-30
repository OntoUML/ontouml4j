package org.ontouml.ontouml4j.deserialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.view.NaryRelationView;
import org.ontouml.ontouml4j.model.view.View;
import org.ontouml.ontouml4j.shape.Diamond;
import org.ontouml.ontouml4j.shape.Path;
import org.ontouml.ontouml4j.utils.ResourceGetter;

public class NaryRelationViewDeserializationTest {

  ObjectMapper mapper;
  Project project;
  NaryRelationView relationView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    File jsonFile =
        ResourceGetter.getJsonFromDeserialization("naryRelationView.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<NaryRelationView> optionalRelationView =
          project.getElementById("naryview_1", NaryRelationView.class);

      optionalRelationView.ifPresent(value -> relationView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(relationView.getId()).isEqualTo("naryview_1");
  }

  @Test
  void shouldDeserializeType() {
    assertThat(relationView.getType()).isEqualTo("NaryRelationView");
  }

  @Test
  void shouldDeserializeMembers() {
    List<View> members = relationView.getMembers();

    assertThat(members.size()).isEqualTo(3);
    assertThat(members.get(0).getId()).isEqualTo("classview_1");
    assertThat(members.get(1).getId()).isEqualTo("classview_2");
    assertThat(members.get(2).getId()).isEqualTo("classview_3");
  }

  @Test
  void shouldDeserializePaths() {
    List<Path> paths = relationView.getPaths();

    assertThat(paths.size()).isEqualTo(3);
    assertThat(paths.get(0).getId()).isEqualTo("path_1");
    assertThat(paths.get(1).getId()).isEqualTo("path_2");
    assertThat(paths.get(2).getId()).isEqualTo("path_3");
  }

  @Test
  void shouldDeserializeDiamond() {
    Diamond diamond = relationView.getDiamond();

    assertThat(diamond.getId()).isEqualTo("diamond_1");
    assertThat(diamond.getX()).isEqualTo(59);
    assertThat(diamond.getY()).isEqualTo(254);
    assertThat(diamond.getWidth()).isEqualTo(50);
    assertThat(diamond.getHeight()).isEqualTo(60);
  }

  //  @Test
  //  void shouldDeserializeSourceView() {
  //    assertThat(relationView.getSourceView().getId()).isEqualTo("classview_1");
  //  }
  //
  //  @Test
  //  void shouldDeserializeTargetView() {
  //    assertThat(relationView.getTargetView().getId()).isEqualTo("classview_2");
  //  }
  //
  //  @Test
  //  void shouldDeserializePath() {
  //    assertThat(relationView.getPath().getId()).isEqualTo("path_1");
  //    assertThat(relationView.getPath().getPoints().size()).isEqualTo(2);
  //  }
}
