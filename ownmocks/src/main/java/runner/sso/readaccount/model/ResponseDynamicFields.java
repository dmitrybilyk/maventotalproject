package runner.sso.readaccount.model;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import runner.util.ShopConfigurationService;


/**
 * ResponseCustomer part for import userdata via rest client from SSO.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http://www.symmetrics.de
 * @see "https://wiki.hybris.com"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDynamicFields implements Serializable
{
   private static final long serialVersionUID = -9136236972774517060L;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_TITLE)
   private String title;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_FIRSTNAME)
   private String firstName;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_LASTNAME)
   private String lastName;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_STREET)
   private String street;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_STREET_NUMBER)
   private String streetNumber;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_POSTAL_CODE)
   private String postalCode;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_CITY)
   private String city;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_COUNTRY)
   private String country;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_PREFIX_HOME)
   private String phonePrefixHome;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_NUMBER_HOME)
   private String phoneNumberHome;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_EXTENSION_HOME)
   private String phoneExtensionHome;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_PREFIX_BUSINESS)
   private String phonePrefixBusiness;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_NUMBER_BUSINESS)
   private String phoneNumberBusiness;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_EXTENSION_BUSINESS)
   private String phoneExtensionBusiness;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_MOBILE_PREFIX)
   private String mobilePrefix;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_MOBILE_NUMBER)
   private String mobileNumber;

   @FieldIdentity(key = ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_DATE_OF_BIRTH)
   private String dateOfBirth;

   private transient Map<String, Field> fieldsRelations = new HashMap<>();

   /**
    * Create dynamic fields response object.
    */
//   public ResponseDynamicFields()
//   {
//      final ShopConfigurationService config = Registry.getApplicationContext().getBean(ShopConfigurationService.class);
//      final Field[] fields = this.getClass().getDeclaredFields();
//
//      for (final Field field : fields)
//      {
//         if (field.isAnnotationPresent(FieldIdentity.class))
//         {
//            final FieldIdentity fieldIdentity = field.getAnnotation(FieldIdentity.class);
//            final String fieldKey = config.getServiceConfiguration().getString(fieldIdentity.key());
//            fieldsRelations.put(fieldKey, field);
//         }
//      }
//   }

   /**
    * Gets title.
    *
    * @return the title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Sets title.
    *
    * @param title
    *            the title
    */
   public void setTitle(final String title)
    {
        this.title = title;
    }

    /**
     * Gets firstName.
     * 
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets firstName.
     * 
     * @param firstName
     *            the firstName
     */
    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets lastName.
     * 
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets lastName.
     * 
     * @param lastName
     *            the lastName
     */
    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets street.
     * 
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Sets street.
     * 
     * @param street
     *            the street
     */
    public void setStreet(final String street)
    {
        this.street = street;
    }

    /**
     * Gets postalCode.
     * 
     * @return the postalCode
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Sets postalCode.
     * 
     * @param postalCode
     *            the postalCode
     */
    public void setPostalCode(final String postalCode)
    {
        this.postalCode = postalCode;
    }

    /**
     * Gets city.
     * 
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets city.
     * 
     * @param city
     *            the city
     */
    public void setCity(final String city)
    {
        this.city = city;
    }

    /**
     * Gets country.
     * 
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

	/**
	 *
	 * @return phonePrefixHome
	 */
	public String getPhonePrefixHome()
	{
		return phonePrefixHome;
	}

	/**
	 *
	 * @return phoneNumberHome
	 */
	public String getPhoneNumberHome()
	{
		return phoneNumberHome;
	}

	/**
	 *
	 * @return phoneExtensionHome
	 */
	public String getPhoneExtensionHome()
	{
		return phoneExtensionHome;
	}

	/**
	 *
	 * @return phonePrefixBusiness
	 */
	public String getPhonePrefixBusiness()
	{
		return phonePrefixBusiness;
	}

	/**
	 *
	 * @return phoneNumberBusiness
	 */
	public String getPhoneNumberBusiness()
	{
		return phoneNumberBusiness;
	}

	/**
	 *
	 * @return phoneExtensionBusiness
	 */
	public String getPhoneExtensionBusiness()
	{
		return phoneExtensionBusiness;
	}

	/**
	 *
	 * @return mobilePrefix
	 */
	public String getMobilePrefix()
	{
		return mobilePrefix;
	}

	/**
	 *
	 * @return mobileNumber
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 *
	 * @return streetNumber
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
     * Gets dateOfBirth.
     * 
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Sets dateOfBirth.
     * 
     * @param dateOfBirth
     *            the dateOfBirth
     */
    public void setDateOfBirth(final String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Initialize a transient field at deserialization.
     * 
     * @return Object.
     */
    private Object readResolve()
    {
        fieldsRelations = new HashMap<>();
        return this;
    }

    /**
     * Set data to this object.
     * 
     * @param key
     *            Data key.
     * @param value
     *            Data value.
     * @throws IllegalAccessException
     *             Missing or inaccessible field.
     */
    public void setData(final String key, final Object value) throws IllegalAccessException
    {
        if (key != null)
        {
            final Field field = fieldsRelations.get(key);
            if (field == null)
            {
                throw new IllegalAccessException(String.format("Missing field key for Customer Dynamic Fields at SSO: %s", key));
            }

            field.set(this, value);
        }
    }

    /**
     * Field configuration relation.
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface FieldIdentity
    {
        /**
         * Configuration key.
         */
        String key() default "";
    }
}
