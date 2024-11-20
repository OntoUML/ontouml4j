package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Anchor;
import org.ontouml.model.Project;
import org.ontouml.utils.ResourceGetter;

public class AnchorDeserializerTest {
  static ObjectMapper mapper;
  static ResourceGetter resourceGetter;
  static Anchor anchor;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    resourceGetter = new ResourceGetter();

    File jsonFile = resourceGetter.getJsonFromDeserialization("anchor.allfields.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<Anchor> anchor1 = project.getElementById("anchor_1", Anchor.class);
    anchor1.ifPresent(aAnchor -> anchor = aAnchor);
  }

  @Test
  void shouldDeserializeId() {
    assertThat(anchor.getId()).isEqualTo("anchor_1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(anchor.getFirstName().isPresent() ? anchor.getFirstName().get() : null)
        .isEqualTo("My Anchor");
  }

  @Test
  void shouldDeserializeNote() {
    assertThat(anchor.getNote()).isNotNull();
    assertThat(anchor.getNote().getId()).isEqualTo("note_1");
  }

  @Test
  void shouldDeserializeElement() {
    assertThat(anchor.getElement()).isNotNull();
    assertThat(anchor.getElement().getId()).isEqualTo("class_1");
  }
}
