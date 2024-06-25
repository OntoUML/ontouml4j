package org.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.deserialization.PackageViewDeserializer;
import org.ontouml.model.Package;
import org.ontouml.serialization.PackageViewSerializer;

@JsonSerialize(using = PackageViewSerializer.class)
@JsonDeserialize(using = PackageViewDeserializer.class)
public class PackageView extends NodeView<Package, Rectangle> {
  public PackageView(String id, Package pkg) {
    super(id, pkg);
  }

  public PackageView(Package clazz) {
    this(null, clazz);
  }

  public PackageView() {
    this(null, null);
  }

  @Override
  public String getType() {
    return "PackageView";
  }

  @Override
  Rectangle createShape() {
    return new Rectangle();
  }
}
