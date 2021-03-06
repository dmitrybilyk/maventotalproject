package runner.sso.readaccount.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.type.TypeReference;


/**
 * Custom parser for json List of String deserialization and trimming.
 *
 * @module pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de
 * @see "https://wiki.hybris.com"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class CustomJsonListOfStringTrimmerDeserializer extends JsonDeserializer<List<String>>
{
   static final TypeReference<List<String>> LIST_TYPE_REFERENCE = new TypeReference<List<String>>()
   {
   };

   @Override
   public List<String> deserialize(final JsonParser jsonparser,
                                   final DeserializationContext deserializationcontext) throws IOException
   {
      List<String> oldStrList = jsonparser.readValueAs(LIST_TYPE_REFERENCE);
      List<String> newStrList = new ArrayList<>();

      for (final String str : oldStrList)
      {
         if (StringUtils.isNotEmpty(str))
         {
            newStrList.add(str.trim());
         }
      }

      return newStrList;
   }
}
