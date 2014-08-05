package runner.sso.readaccount.json;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;


/**
 * Custom parser for json date deserialization. We should receive date in ISO 8601.
 *
 * @module pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de
 * @see "https://wiki.hybris.com"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class CustomJsonStringTrimmerDeserializer extends JsonDeserializer<String>
{
	@Override
	public String deserialize(final JsonParser jsonparser,
									final DeserializationContext deserializationcontext) throws IOException
	{
		return jsonparser.getText().trim();
	}
}
