package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.ontouml.view.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

class RectangleDeserializerTest {

  static ObjectMapper mapper = new ObjectMapper();
  static Rectangle rectangle;
  static String json =
      "{\n"
          + "  \"id\": \"r1\",\n"
          + "  \"type\": \"Rectangle\",\n"
          + "  \"x\": 406,\n"
          + "  \"y\": 285,\n"
          + "  \"width\": 84,\n"
          + "  \"height\": 40\n"
          + "}";

  @BeforeAll
  static void beforeAll() throws IOException {
    rectangle = mapper.readValue(json, Rectangle.class);
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String json = "{\"id\": \"1\", \"type\": \"Rectangle\"}";
    Rectangle rectangle = mapper.readValue(json, Rectangle.class);

    Truth.assertThat(rectangle.getId()).isEqualTo("1");
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(rectangle.getId()).isEqualTo("r1");
  }

  @Test
  void shouldDeserializeX() {
    assertThat(rectangle.getX()).isEqualTo(406);
  }

  @Test
  void shouldDeserializeY() {
    assertThat(rectangle.getY()).isEqualTo(285);
  }

  @Test
  void shouldDeserializeWidth() {
    assertThat(rectangle.getWidth()).isEqualTo(84);
  }

  @Test
  void shouldDeserializeHeight() {
    assertThat(rectangle.getHeight()).isEqualTo(40);
  }
}
