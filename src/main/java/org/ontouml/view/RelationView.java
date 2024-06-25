package org.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.OntoumlElement;
import org.ontouml.deserialization.RelationViewDeserializer;
import org.ontouml.model.Relation;
import org.ontouml.serialization.RelationViewSerializer;

import java.util.List;

@JsonSerialize(using = RelationViewSerializer.class)
@JsonDeserialize(using = RelationViewDeserializer.class)
public class RelationView extends ConnectorView<Relation> {

    private Text nameText = new Text();
    private Text stereotypeText = new Text();
    private Text sourceRoleText = new Text();
    private Text sourceCardinalityText = new Text();
    private Text targetRoleText = new Text();
    private Text targetCardinalityText = new Text();

  public RelationView(String id, Relation relation) {
    super(id, relation);
  }

  public RelationView(Relation relation) {
    this(null, relation);
  }

  public RelationView() {
    super(null, null);
  }

  @Override
  public List<OntoumlElement> getContents() {
    List<OntoumlElement> contents = super.getContents();

        contents.addAll(
            List.of(
                nameText,
                stereotypeText,
                sourceRoleText,
                sourceCardinalityText,
                targetRoleText,
                targetCardinalityText));

    return contents;
  }

  @Override
  public String getType() {
    return "RelationView";
  }
}
