package it.unibz.inf.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibz.inf.ontouml.model.Class;
import it.unibz.inf.ontouml.model.Generalization;
import it.unibz.inf.ontouml.model.GeneralizationSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class GeneralizationSetDeserializerTest {

  String json =
      "{\n"
          + "  \"id\" : \"gs1\",\n"
          + "  \"name\" : null,\n"
          + "  \"description\" : null,\n"
          + "  \"type\" : \"GeneralizationSet\",\n"
          + "  \"propertyAssignments\" : null,\n"
          + "  \"isDisjoint\" : true,\n"
          + "  \"isComplete\" : true,\n"
          + "  \"categorizer\" : {\n"
          + "    \"id\" : \"c1\",\n"
          + "    \"type\" : \"Class\"\n"
          + "  },"
          + "  \"generalizations\" : [ {\n"
          + "    \"id\" : \"g1\",\n"
          + "    \"type\" : \"Generalization\"\n"
          + "  }, {\n"
          + "    \"id\" : \"g2\",\n"
          + "    \"type\" : \"Generalization\"\n"
          + "  } ]\n"
          + "} ]";

  ObjectMapper mapper;
  GeneralizationSet gs;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    gs = mapper.readValue(json, GeneralizationSet.class);
  }

  @Test
  void shouldDeserializeId() {
    assertThat(gs.getId()).isEqualTo("gs1");
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String json = "{\"id\": \"gs1\", \"type\": \"GeneralizationSet\"}";
    GeneralizationSet gs = mapper.readValue(json, GeneralizationSet.class);
    assertThat(gs.getId()).isEqualTo("gs1");
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

    assertThat(ids).containsExactly("g1", "g2");
  }

  @Test
  void shouldDeserializeCategorizer() {
    Optional<Class> categorizer = gs.getCategorizer();

    assertThat(categorizer).isPresent();
    assertThat(categorizer.get().getId()).isEqualTo("c1");
  }
}
