package it.unibz.inf.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.unibz.inf.ontouml.OntoumlElement;
import it.unibz.inf.ontouml.deserialization.GeneralizationViewDeserializer;
import it.unibz.inf.ontouml.model.Generalization;
import it.unibz.inf.ontouml.serialization.GeneralizationViewSerializer;

import java.util.ArrayList;
import java.util.List;
//
@JsonSerialize(using = GeneralizationViewSerializer.class)
@JsonDeserialize(using = GeneralizationViewDeserializer.class)
public class GeneralizationView extends ConnectorView<Generalization> {

  public GeneralizationView(String id, Generalization generalization) {
    super(id, generalization);
  }

  public GeneralizationView(Generalization generalization) {
    this(null, generalization);
  }

  public GeneralizationView() {
    this(null, null);
  }

  @Override
  public List<OntoumlElement> getContents() {
    return new ArrayList<>();
  }

  @Override
  public String getType() {
    return "GeneralizationView";
  }
}
