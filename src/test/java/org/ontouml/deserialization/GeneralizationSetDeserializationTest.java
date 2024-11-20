package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Class;
import org.ontouml.model.Generalization;
import org.ontouml.model.GeneralizationSet;
import org.ontouml.model.Project;
import org.ontouml.utils.ResourceGetter;

class GeneralizationSetDeserializerTest {

  static ResourceGetter resourceGetter;
  ObjectMapper mapper;
  GeneralizationSet gs;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    resourceGetter = new ResourceGetter();
    File jsonFile =
        resourceGetter.getJsonFromDeserialization("generalizationset.allfields.ontouml.json");

    try {
      Project project = mapper.readValue(jsonFile, Project.class);
      Optional<GeneralizationSet> myGeneralizationSet =
          project.getElementById("genset_1", GeneralizationSet.class);
      myGeneralizationSet.ifPresent(myGen -> gs = myGen);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(gs.getId()).isEqualTo("genset_1");
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String json = "{\"id\": \"gs1\", \"type\": \"GeneralizationSet\"}";
    GeneralizationSet gs = mapper.readValue(json, GeneralizationSet.class);
    Truth.assertThat(gs.getId()).isEqualTo("gs1");
  }

  @Test
  void shouldDeserializeIsComplete() {
    assertThat(gs.isComplete()).isTrue();
  }

  @Test
  void shouldDeserializeIsDisjoint() {
    assertThat(gs.isDisjoint()).isTrue();
  }

  @Test
  void shouldDeserializeGeneralizations() {
    Set<Generalization> generalizations = gs.getGeneralizations();

    assertThat(generalizations).hasSize(2);

    Set<String> ids =
        generalizations.stream().map(Generalization::getId).collect(Collectors.toSet());

    assertThat(ids).containsExactly("generalization_1", "generalization_2");
  }

  @Test
  void shouldDeserializeCategorizer() {
    Optional<Class> categorizer = gs.getCategorizer();

    assertThat(categorizer).isPresent();
    assertThat(categorizer.get().getId()).isEqualTo("class_1");
  }
}
