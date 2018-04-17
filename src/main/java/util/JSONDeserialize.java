package util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * Used to serialize java.time.LocalDate, which is not a common JSON type, so we have
 * to create a custom Deserialize method;.
 *
 * @author Ngo Thi Ngoc Sang (ngosangoc@gmail.com)
 */
public class JSONDeserialize  extends JsonDeserializer<Date> {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 
	@Override
	public Date deserialize(JsonParser jpar, DeserializationContext descon) throws IOException, JsonProcessingException {
		
		String date = jpar.getText();
		
		Date dateSer = null;
		
		try {
			dateSer = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return dateSer;
	}
}
