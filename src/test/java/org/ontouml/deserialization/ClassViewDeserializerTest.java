package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.model.view.ClassView;
import org.ontouml.shape.RectangularShape;
import org.ontouml.utils.ResourceGetter;

public class ClassViewDeserializerTest {

  ObjectMapper mapper = new ObjectMapper();
  ClassView view;
  RectangularShape shape;

  @BeforeEach
  void beforeEach() throws IOException {
    File jsonFile = ResourceGetter.getJsonFromDeserialization("classview.minimum.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<ClassView> classView = project.getElementById("classview_1", ClassView.class);
    classView.ifPresent(aView -> view = aView);
    shape = view.getRectangle();
  }

  @Test
  void shouldDeserializeId() {
    assertThat(view.getId()).isEqualTo("classview_1");
  }

  @Test
  void shouldDeserializeModelElement() {
    assertThat(view.getIsViewOf().getId()).isEqualTo("class_1");
  }

  @Test
  void shouldDeserializeWidth() {
    assertThat(view.getRectangle().getWidth()).isEqualTo(95);
  }

  @Test
  void shouldDeserializeHeight() {
    assertThat(view.getRectangle().getHeight()).isEqualTo(40);
  }

  @Test
  void shouldDeserializeShape() {
    Truth.assertThat(shape.getId()).isEqualTo("rectangle_1");
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
