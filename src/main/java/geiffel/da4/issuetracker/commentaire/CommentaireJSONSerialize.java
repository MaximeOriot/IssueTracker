package geiffel.da4.issuetracker.commentaire;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class CommentaireJSONSerialize extends JsonSerializer<Commentaire> {
    @Override
    public void serialize(Commentaire comment, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("url", "/commentaires/"+comment.getId());
        gen.writeEndObject();
    }
}
