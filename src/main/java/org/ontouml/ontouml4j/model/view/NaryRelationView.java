package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.ontouml.ontouml4j.deserialization.view.NaryRelationViewDeserializer;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.NaryRelationViewSerializer;
import org.ontouml.ontouml4j.shape.Diamond;
import org.ontouml.ontouml4j.shape.Path;

/** A view element that represents the single occurrence of a n-ary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = NaryRelationViewDeserializer.class)
@JsonSerialize(using = NaryRelationViewSerializer.class)
public class NaryRelationView extends View {

  List<String> memberIds = new ArrayList<>();

  /**
   * Identifies the class views (i.e., the members) that are connected by the n-ary relation view in
   * the diagram. This array of member views must be ordered according to the properties of the
   * relation the view represents.
   *
   * <p>Restriction - minimum of 3 members
   */
  private List<View> members = new ArrayList<>();

  /**
   * Identifies the diamond shape that renders the joining of all paths of the n-ary relation in the
   * diagram.
   */
  private Diamond diamond;

  private String diamondId;

  /**
   * Identifies the path shapes that render each path of the n-ary relation view in the diagram.
   * This array of paths must be ordered according to the properties of the relation the view
   * represents.
   */
  private List<Path> paths = new ArrayList<>();

  private List<String> pathIds = new ArrayList<>();

  public NaryRelationView(String id, List<View> views, List<Path> paths, Diamond diamond, ModelElement isViewOf) {
    super(id, isViewOf);
    this.members = views;
    this.paths = paths;
    this.diamond = diamond;
  }

  @Override
  public String getType() {
    return "NaryRelationView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
    memberIds.forEach(
        id -> {
          View member = project.getElementById(id, View.class).orElse(null);
          if (member != null) {
            members.add(member);
          }
        });

    pathIds.forEach(
        id -> {
          Path member = project.getElementById(id, Path.class).orElse(null);
          if (member != null) {
            paths.add(member);
          }
        });

    this.diamond = project.getElementById(diamondId, Diamond.class).orElse(null);
  }
}
