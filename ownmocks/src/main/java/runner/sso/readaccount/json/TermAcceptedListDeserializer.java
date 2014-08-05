package runner.sso.readaccount.json;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import runner.sso.readaccount.model.ResponseTermAccepted;

/**
 * Deserializer for the termAccepted fields. Gets from JsonNode with a key from the configuration service.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class TermAcceptedListDeserializer extends JsonDeserializer<List<ResponseTermAccepted>>
{
	private static final String NAME_JSON_FIELD_NAME = "name";
	private static final String TERM_TYPE_JSON_FIELD_NAME = "termsType";
	private static final String TERM_VERSION_ID_JSON_FIELD_NAME = "termsVersionId";
	private static final String VERSION_IDENTIFIER_JSON_FIELD_NAME = "versionIdentifier";
	private static final String CONFIRMED_JSON_FIELD_NAME = "confirmed";
	private static final String REF_ID_JSON_FIELD_NAME = "refId";
	private static final String VERSION_JSON_FIELD_NAME = "version";
	private static final String URL_JSON_FIELD_NAME = "url";

	@Override
	public List<ResponseTermAccepted> deserialize(final JsonParser jsonParser,
																 final DeserializationContext deserializationContext)
			  throws IOException, JsonProcessingException
	{
		JsonNode termListNode = jsonParser.readValueAsTree();
		List<ResponseTermAccepted> termAcceptedList = new ArrayList<>();

		Iterator<JsonNode> iterator = termListNode.getElements();
		while (iterator.hasNext())
		{
			JsonNode termNode = iterator.next();
			ResponseTermAccepted term = new ResponseTermAccepted();

			term.setName(termNode.get(NAME_JSON_FIELD_NAME).getTextValue());
			term.setTermsType(termNode.get(TERM_TYPE_JSON_FIELD_NAME).getTextValue());
			term.setTermsVersionId(termNode.get(TERM_VERSION_ID_JSON_FIELD_NAME).getTextValue());
			term.setVersionIdentifier(termNode.get(VERSION_IDENTIFIER_JSON_FIELD_NAME).getTextValue());
			term.setConfirmed(termNode.get(CONFIRMED_JSON_FIELD_NAME).getBooleanValue());
			term.setRefId(termNode.get(REF_ID_JSON_FIELD_NAME).getTextValue());
			term.setVersion(termNode.get(VERSION_JSON_FIELD_NAME).getIntValue());
			term.setUrl(termNode.get(URL_JSON_FIELD_NAME).getTextValue());

			termAcceptedList.add(term);
		}
		return termAcceptedList;
	}

}
