package org.ontouml.deserialization.view;

import org.ontouml.deserialization.*;

// public class AnchorViewDeserializer extends JsonDeserializer<AnchorView> {
//
//  @Override
//  public AnchorView deserialize(JsonParser parser, DeserializationContext ctxt)
//      throws IOException, JacksonException {
//    ObjectCodec codec = parser.getCodec();
//    JsonNode root = parser.readValueAsTree();
//
//    AnchorView view = new AnchorView();
//    ViewDeserializer.deserialize(view, root, codec);
//
//    String isViewOf = DeserializerUtils.deserializeNullableStringField(root, "isViewOf");
//    anchor.setNoteId(noteId);
//
//    return relation;
//  }
// }
