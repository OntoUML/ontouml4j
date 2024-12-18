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
import org.ontouml.model.view.PackageView;
import org.ontouml.utils.ResourceGetter;

public class PackageViewDeserializerTest {

  ObjectMapper mapper;
  Project project;
  PackageView pkgView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = ResourceGetter.getJsonFromDeserialization("packageview.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<PackageView> optionalPackageView =
          project.getElementById("packageview_1", PackageView.class);

      optionalPackageView.ifPresent(value -> pkgView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(pkgView.getId()).isEqualTo("packageview_1");
  }

  @Test
  void shouldDeserializeModelElement() {
    assertThat(pkgView.getIsViewOf().getId()).isEqualTo("package_1");
  }

  @Test
  void shouldDeserializeWidth() {
    assertThat(pkgView.getRectangle().getWidth()).isEqualTo(95);
  }

  @Test
  void shouldDeserializeHeight() {
    assertThat(pkgView.getRectangle().getHeight()).isEqualTo(40);
  }

  @Test
  void shouldDeserializeShape() {
    Truth.assertThat(pkgView.getRectangle().getId()).isEqualTo("rectangle_1");
  }

  @Test
  void shouldDeserializeShapeX() {
    assertThat(pkgView.getRectangle().getX()).isEqualTo(813);
  }

  @Test
  void shouldDeserializeShapeY() {
    assertThat(pkgView.getRectangle().getY()).isEqualTo(20);
  }

  @Test
  void shouldDeserializeShapeWidth() {
    assertThat(pkgView.getRectangle().getWidth()).isEqualTo(95);
  }

  @Test
  void shouldDeserializeShapeHeight() {
    assertThat(pkgView.getRectangle().getHeight()).isEqualTo(40);
  }
}
