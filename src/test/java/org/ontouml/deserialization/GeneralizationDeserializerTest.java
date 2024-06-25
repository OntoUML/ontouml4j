package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import org.ontouml.model.Classifier;
import org.ontouml.model.Generalization;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static com.google.common.truth.Truth8.assertThat;
import static com.google.common.truth.Truth.assertThat;

class GeneralizationDeserializerTest {

  static String json =
      "{\n"
          + "  \"id\" : \"g1\",\n"
          + "  \"type\" : \"Generalization\",\n"
          + "  \"name\" : {\n"
          + "    \"en\" : \"My Generalization\",\n"
          + "    \"pt\" : \"Minha Generalization\"\n"
          + "  },\n"
          + "  \"description\" : {\n"
          + "    \"pt\" : \"Minha descrição.\",\n"
          + "    \"en\" : \"My description.\"\n"
          + "  },\n"
          + "  \"general\" : {\n"
          + "    \"id\" : \"c1\",\n"
          + "    \"type\" : \"Class\"\n"
          + "  },\n"
          + "  \"specific\" : {\n"
          + "    \"id\" : \"c2\",\n"
          + "    \"type\" : \"Class\"\n"
          + "  }\n"
          + "}";

  static ObjectMapper mapper;
  static Generalization gen;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    gen = mapper.readValue(json, Generalization.class);
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(gen.getId()).isEqualTo("g1");
  }

  @Test
  void shouldDeserializeName() {
    Truth8.assertThat(gen.getNameIn("en")).hasValue("My Generalization");
    Truth8.assertThat(gen.getNameIn("pt")).hasValue("Minha Generalization");
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
    Truth.assertThat(general.get().getId()).isEqualTo("c1");
    Truth8.assertThat(general.get().getFirstName()).isEmpty();
  }

  @Test
  void shouldDeserializeSpecific() {
    Optional<Classifier<?, ?>> specific = gen.getSpecific();

    assertThat(specific).isPresent();
    Truth.assertThat(specific.get().getId()).isEqualTo("c2");
    Truth8.assertThat(specific.get().getFirstName()).isEmpty();
  }
}
