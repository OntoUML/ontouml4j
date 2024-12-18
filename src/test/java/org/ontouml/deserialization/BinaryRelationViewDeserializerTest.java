package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;
import org.ontouml.model.view.BinaryRelationView;
import org.ontouml.utils.ResourceGetter;

public class BinaryRelationViewDeserializerTest {

  ObjectMapper mapper;
  Project project;
  BinaryRelationView relationView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile =
        ResourceGetter.getJsonFromDeserialization("binaryRelationView.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<BinaryRelationView> optionalRelationView =
          project.getElementById("binaryview_1", BinaryRelationView.class);

      optionalRelationView.ifPresent(value -> relationView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(relationView.getId()).isEqualTo("binaryview_1");
  }

  @Test
  void shouldDeserializeType() {
    assertThat(relationView.getType()).isEqualTo("BinaryRelationView");
  }

  @Test
  void shouldDeserializeSourceView() {
    assertThat(relationView.getSourceView().getId()).isEqualTo("classview_1");
  }

  @Test
  void shouldDeserializeTargetView() {
    assertThat(relationView.getTargetView().getId()).isEqualTo("classview_2");
  }

  @Test
  void shouldDeserializePath() {
    assertThat(relationView.getPath().getId()).isEqualTo("path_1");
    assertThat(relationView.getPath().getPoints().size()).isEqualTo(2);
  }
}
