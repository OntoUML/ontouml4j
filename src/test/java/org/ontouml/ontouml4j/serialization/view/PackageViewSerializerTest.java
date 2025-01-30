package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.view.PackageView;
import org.ontouml.ontouml4j.shape.Rectangle;

public class PackageViewSerializerTest {
  static PackageView pkgView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Package pkg = Package.builder().id("package_1").name(new MultilingualText("MyClass")).build();

    pkgView =
        PackageView.builder()
            .id("packageview_1")
            .isViewOf(pkg)
            .rectangle(new Rectangle("rectangle_1", 10, 20))
            .build();

    node = pkgView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("packageview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("package_1");
  }

  @Test
  void shouldSerializeRectangle() throws JsonProcessingException {
    JsonNode rectangle = node.get("rectangle");
    String id = rectangle.get("id").asText();
    int width = rectangle.get("width").asInt();
    int height = rectangle.get("height").asInt();

    assertThat(id).isEqualTo("rectangle_1");
    assertThat(width).isEqualTo(10);
    assertThat(height).isEqualTo(20);
  }
}
