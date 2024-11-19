package org.ontouml.model.view;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Diamond;
import org.ontouml.shape.Path;

/** A view element that represents the single occurrence of a n-ary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class NaryRelationView extends View {

  /**
   * Identifies the class views (i.e., the members) that are connected by the n-ary relation view in
   * the diagram. This array of member views must be ordered according to the properties of the
   * relation the view represents.
   *
   * <p>Restriction - minimum of 3 members
   */
  @Builder.Default
  private List<View> members = new ArrayList<>();

  /**
   * Identifies the diamond shape that renders the joining of all paths of the n-ary relation in the
   * diagram.
   */
  private Diamond diamond;

  /**
   * Identifies the path shapes that render each path of the n-ary relation view in the diagram.
   * This array of paths must be ordered according to the properties of the relation the view
   * represents.
   */
  private List<Path> paths;

  @Override
  public String getType() {
    return "NaryRelationView";
  }
}
