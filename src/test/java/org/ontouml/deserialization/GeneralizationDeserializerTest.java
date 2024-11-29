package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
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

  static ObjectMapper mapper;
  static Generalization gen;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile =
        ResourceGetter.getJsonFromDeserialization("generalization.allfields.ontouml.json");

    try {
      Project project = mapper.readValue(jsonFile, Project.class);
      Optional<Generalization> myGeneralization =
          project.getElementById("generalization_1", Generalization.class);
      myGeneralization.ifPresent(myGen -> gen = myGen);

    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("failed to read project");
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(gen.getId()).isEqualTo("generalization_1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(gen.getNameIn("en")).hasValue("My Generalization");
    assertThat(gen.getNameIn("pt")).hasValue("Minha Generalização");
  }

  @Test
  void shouldDeserializeDescription() {
    assertThat(gen.getDescriptionIn("en")).hasValue("My description.");
    assertThat(gen.getDescriptionIn("pt")).hasValue("Minha descrição.");
  }

  @Test
  void shouldDeserializeGeneral() {
    Optional<Classifier<?, ?>> general = gen.getGeneral();

    assertThat(general).isPresent();
    assertThat(general.get().getId()).isEqualTo("class_1");
    assertThat(general.get().getFirstName()).isEmpty();
  }

  @Test
  void shouldDeserializeSpecific() {
    Optional<Classifier<?, ?>> specific = gen.getSpecific();

    assertThat(specific).isPresent();
    assertThat(specific.get().getId()).isEqualTo("class_2");
    assertThat(specific.get().getFirstName()).isEmpty();
  }
}
