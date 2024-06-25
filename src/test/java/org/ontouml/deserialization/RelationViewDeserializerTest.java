package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.ontouml.view.Path;
import org.ontouml.view.Point;
import org.ontouml.view.RelationView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

class RelationViewDeserializerTest {

  static ObjectMapper mapper = new ObjectMapper();
  static String json =
      "{\n"
          + "  \"id\": \"rv1\",\n"
          + "  \"type\": \"RelationView\",\n"
          + "  \"modelElement\": { \"id\": \"re1\", \"type\": \"Relation\" },\n"
          + "  \"shape\": {\n"
          + "    \"id\": \"pa1\",\n"
          + "    \"type\": \"Path\",\n"
          + "    \"points\": [\n"
          + "      { \"x\": 642, \"y\": 92 },\n"
          + "      { \"x\": 812, \"y\": 93 }\n"
          + "    ]\n"
          + "  },\n"
          + "  \"source\": { \"id\": \"cv1\", \"type\": \"ClassView\" },\n"
          + "  \"target\": { \"id\": \"cv2\", \"type\": \"ClassView\" }\n"
          + "}";

  static RelationView view;
  static Path shape;

  @BeforeAll
  static void beforeAll() throws IOException {
    view = mapper.readValue(json, RelationView.class);
    shape = view.getShape();
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String json = "{\"id\": \"1\", \"type\": \"RelationView\"}";
    RelationView view = mapper.readValue(json, RelationView.class);

    Truth.assertThat(view.getId()).isEqualTo("1");
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(view.getId()).isEqualTo("rv1");
  }

  @Test
  void shouldDeserializeModelElement() {
    Truth.assertThat(view.getModelElement().getId()).isEqualTo("re1");
  }

  @Test
  void shouldDeserializeSource() {
    Truth.assertThat(view.getSource().getId()).isEqualTo("cv1");
  }

  @Test
  void shouldDeserializeTarget() {
    Truth.assertThat(view.getTarget().getId()).isEqualTo("cv2");
  }

  @Test
  void shouldDeserializeShape() {
    Truth.assertThat(shape.getId()).isEqualTo("pa1");
  }

  @Test
  void shouldDeserializePoints() {
    assertThat(shape.getPoints()).hasSize(2);
  }

  @Test
  void shouldDeserializePointsData() {
    Point firstPoint = shape.getPoints().get(0);
    assertThat(firstPoint.getX()).isEqualTo(642);
    assertThat(firstPoint.getY()).isEqualTo(92);

    Point secondPoint = shape.getPoints().get(1);
    assertThat(secondPoint.getX()).isEqualTo(812);
    assertThat(secondPoint.getY()).isEqualTo(93);
  }
}
