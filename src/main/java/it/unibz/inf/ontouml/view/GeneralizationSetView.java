package it.unibz.inf.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.unibz.inf.ontouml.deserialization.GeneralizationSetViewDeserializer;
import it.unibz.inf.ontouml.model.GeneralizationSet;
import it.unibz.inf.ontouml.serialization.GeneralizationSetViewSerializer;

@JsonSerialize(using = GeneralizationSetViewSerializer.class)
@JsonDeserialize(using = GeneralizationSetViewDeserializer.class)
public class GeneralizationSetView extends NodeView<GeneralizationSet, Text> {

  public GeneralizationSetView(String id, GeneralizationSet genSet) {
    super(id, genSet);
  }

  public GeneralizationSetView(GeneralizationSet genSet) {
    this(null, genSet);
  }

  public GeneralizationSetView() {
    this(null, null);
  }

  @Override
  public String getType() {
    return "GeneralizationSetView";
  }

  @Override
  Text createShape() {
    return new Text("");
  }
}
