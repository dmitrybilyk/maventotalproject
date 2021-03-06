package runner.sso.readaccount.model;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import runner.sso.readaccount.json.CustomJsonStringTrimmerDeserializer;
import runner.sso.readaccount.json.DynamicFieldsDeserializer;
import runner.sso.readaccount.json.TermAcceptedListDeserializer;


/**
 * ResponseCustomer for imprort userdata via rest client from SSO.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de
 * @see "https://wiki.hybris.com"
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCustomer implements Serializable
{
   private static final long serialVersionUID = 4073497957695884490L;

   @JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
   private String accountId;

//   @JsonDeserialize(using = DynamicFieldsDeserializer.class)
//   private ResponseDynamicFields dynamicFields;

   @JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
   private String email;

   @JsonDeserialize(using = TermAcceptedListDeserializer.class)
   private List<ResponseTermAccepted> terms;

   /**
    *	empty constructor.
    */
   public ResponseCustomer()
   {
      //
   }

   /**
    *	For json desirialization constructor.
    *
    * @param dynamicFields the dynamicFields
    *
    */
   public ResponseCustomer(final ResponseDynamicFields dynamicFields)
   {
//      this.dynamicFields = dynamicFields;
   }

   /**
    * Gets dynamicFields.
    *
    * @return the dynamicFields
    */
//   public ResponseDynamicFields getDynamicFields()
//   {
//      return dynamicFields;
//   }

   /**
    * Sets dynamicFields.
    *
    * @param dynamicFields the dynamicFields
    */
//   public void setDynamicFields(final ResponseDynamicFields dynamicFields)
//   {
//      this.dynamicFields = dynamicFields;
//   }

   /**
    * Gets accountId.
    *
    * @return the accountId
    */
   public String getAccountId()
   {
      return accountId;
   }

   /**
    * Sets accountId.
    *
    * @param accountId the accountId
    */
   public void setAccountId(final String accountId)
   {
      this.accountId = accountId;
   }

   /**
    * Gets email.
    *
    * @return the email
    */
   public String getEmail()
	{
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Gets termsAccepted values list.
	 *
	 * @return the list of termsAccepted values.
	 */
	public List<ResponseTermAccepted> getTerms()
	{
		return terms;
	}

	/**
	 * Gets termsAccepted values list.
	 *
	 * @param termsAccepted  the list of termsAccepted values.
	 */
	public void setTerms(final List<ResponseTermAccepted> termsAccepted)
	{
		this.terms = termsAccepted;
	}
}
