package org.ontouml.deserialization;

import static com.google.common.truth.Truth8.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Classifier;
import org.ontouml.model.Generalization;
import org.ontouml.model.Project;
import org.ontouml.utils.ResourceGetter;

class GeneralizationDeserializerTest {

  static ResourceGetter resourceGetter;
  static ObjectMapper mapper;
  static Generalization gen;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    resourceGetter = new ResourceGetter();
    File jsonFile =
        resourceGetter.getJsonFromDeserialization("generalization.allfields.ontouml.json");

    try {
      Project project = mapper.readValue(jsonFile, Project.class);
      Optional<Generalization> myGeneralization =
          project.getElementById("generalization_1", Generalization.class);
      myGeneralization.ifPresent(myGen -> gen = myGen);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(gen.getId()).isEqualTo("generalization_1");
  }

  @Test
  void shouldDeserializeName() {
    Truth8.assertThat(gen.getNameIn("en")).hasValue("My Generalization");
    Truth8.assertThat(gen.getNameIn("pt")).hasValue("Minha Generalização");
  }

  @Test
  void shouldDeserializeDescription() {
    Truth8.assertThat(gen.getDescriptionIn("en")).hasValue("My description.");
    Truth8.assertThat(gen.getDescriptionIn("pt")).hasValue("Minha descrição.");
  }

  @Test
  void shouldDeserializeGeneral() {
    Optional<Classifier<?, ?>> general = gen.getGeneral();

    assertThat(general).isPresent();
    Truth.assertThat(general.get().getId()).isEqualTo("class_1");
    Truth8.assertThat(general.get().getFirstName()).isEmpty();
  }

  @Test
  void shouldDeserializeSpecific() {
    Optional<Classifier<?, ?>> specific = gen.getSpecific();

    assertThat(specific).isPresent();
    Truth.assertThat(specific.get().getId()).isEqualTo("class_2");
    Truth8.assertThat(specific.get().getFirstName()).isEmpty();
  }
}
