package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth8;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.ontouml.model.MultilingualText;

public class MultilingualTextDeserializerTest {
  @Test
  void deserializeObject() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    String json = "{\"en\":\"My Project\", \"pt\": \"Meu Projeto\" }";
    MultilingualText element = mapper.readValue(json, MultilingualText.class);

    assertThat(element.getLanguages()).containsExactly("en", "pt");
    Truth8.assertThat(element.getText("en")).hasValue("My Project");
    Truth8.assertThat(element.getText("pt")).hasValue("Meu Projeto");
  }

  @Test
  void deserializeString() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    String json = "\"My Project\"";
    MultilingualText element = mapper.readValue(json, MultilingualText.class);

    assertThat(element.getLanguages()).containsExactly("en");
    Truth8.assertThat(element.getText("en")).hasValue("My Project");
  }

  @Test
  void deserializeNull() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    String json = "null";
    MultilingualText element = mapper.readValue(json, MultilingualText.class);

    assertThat(element.getLanguages()).isEmpty();
    Truth8.assertThat(element.getText("en")).isEmpty();
  }
}
