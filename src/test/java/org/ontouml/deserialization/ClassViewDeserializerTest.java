package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.ontouml.view.ClassView;
import org.ontouml.view.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

class ClassViewDeserializerTest {

  static ObjectMapper mapper = new ObjectMapper();
  static String json =
      "{\n"
          + "  \"id\": \"cv1\",\n"
          + "  \"type\": \"ClassView\",\n"
          + "  \"modelElement\": {\n"
          + "    \"id\": \"cl1\",\n"
          + "    \"type\": \"Class\"\n"
          + "  },\n"
          + "  \"shape\": {\n"
          + "    \"id\": \"re1\",\n"
          + "    \"type\": \"Rectangle\",\n"
          + "    \"x\": 813,\n"
          + "    \"y\": 72,\n"
          + "    \"width\": 95,\n"
          + "    \"height\": 40\n"
          + "  }\n"
          + "}";

  static ClassView view;
  static Rectangle shape;

  @BeforeAll
  static void beforeAll() throws IOException {
    view = mapper.readValue(json, ClassView.class);
    shape = view.getShape();
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String json = "{\"id\": \"1\", \"type\": \"ClassView\"}";
    ClassView view = mapper.readValue(json, ClassView.class);

    Truth.assertThat(view.getId()).isEqualTo("1");
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(view.getId()).isEqualTo("cv1");
  }

  @Test
  void shouldDeserializeModelElement() {
    Truth.assertThat(view.getModelElement().getId()).isEqualTo("cl1");
  }

  @Test
  void shouldDeserializeX() {
    assertThat(view.getX()).isEqualTo(813);
  }

  @Test
  void shouldDeserializeY() {
    assertThat(view.getY()).isEqualTo(72);
  }

  @Test
  void shouldDeserializeWidth() {
    assertThat(view.getWidth()).isEqualTo(95);
  }

  @Test
  void shouldDeserializeHeight() {
    assertThat(view.getHeight()).isEqualTo(40);
  }

  @Test
  void shouldDeserializeShape() {
    Truth.assertThat(shape.getId()).isEqualTo("re1");
  }

  @Test
  void shouldDeserializeShapeX() {
    assertThat(shape.getX()).isEqualTo(813);
  }

  @Test
  void shouldDeserializeShapeY() {
    assertThat(shape.getY()).isEqualTo(72);
  }

  @Test
  void shouldDeserializeShapeWidth() {
    assertThat(shape.getWidth()).isEqualTo(95);
  }

  @Test
  void shouldDeserializeShapeHeight() {
    assertThat(shape.getHeight()).isEqualTo(40);
  }
}
