package util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Used to serialize Java.util.Date, which is not a common JSON type, so we have
 * to create a custom serialize method;.
 *
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 */
@Component
public class JSONSerializer extends JsonSerializer<Date> {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeString(format.format(value));
	}
}