package runner.sso.readaccount.model;

import java.io.Serializable;


/**
 * Accepted terms response handler.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 16, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http://www.symmetrics.de
 * @see "https://wiki.hybris.com"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class ResponseTermAccepted implements Serializable
{
	private static final long serialVersionUID = -6295165183168088322L;

	private String name;

	private String termsType;

	private String termsVersionId;

	private String versionIdentifier;

	private Boolean confirmed;

	private String refId;

	private Integer version;

	private String url;

	/**
	 * Get name.
	 *
	 * @return Name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set name.
	 * @param name Name.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Get terms type.
	 * @return Terms type.
	 */
	public String getTermsType()
	{
		return termsType;
	}

	/**
	 * Set terms type.
	 *
	 * @param termsType Terms type.
	 */
	public void setTermsType(final String termsType)
	{
		this.termsType = termsType;
	}

	/**
	 * Terms version id.
	 *
	 * @return Version id.
	 */
	public String getTermsVersionId()
	{
		return termsVersionId;
	}

	/**
	 * Set terms version id.
	 *
	 * @param termsVersionId Version id.
	 */
	public void setTermsVersionId(final String termsVersionId)
	{
		this.termsVersionId = termsVersionId;
	}

	/**
	 * Get version id.
	 * @return Version identifier.
	 */
	public String getVersionIdentifier()
	{
		return versionIdentifier;
	}

	/**
	 * Set version id.
	 *
	 * @param versionIdentifier Set version identifier.
	 */
	public void setVersionIdentifier(final String versionIdentifier)
	{
		this.versionIdentifier = versionIdentifier;
	}

	/**
	 * Is confirmed.
	 *
	 * @return Confirmed flag.
	 */
	public Boolean isConfirmed()
	{
		return confirmed;
	}

	/**
	 * Set confirmed flag.
	 *
	 * @param confirmed Confirmed flag.
	 */
	public void setConfirmed(final Boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	/**
	 * Get reference id.
	 *
	 * @return Reference id.
	 */
	public String getRefId()
	{
		return refId;
	}

	/**
	 * Set reference id.
	 *
	 * @param termsId Reference id.
	 */
	public void setRefId(final String termsId)
	{
		this.refId = termsId;
	}

	/**
	 * Get version value.
	 *
	 * @return version numeric value of the current version of the Term object.
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Set version value.
	 *
	 * @param version numeric value of the current version of the Term object.
	 */
	public void setVersion(final Integer version)
	{
		this.version = version;
	}

	/**
	 * Get the url for the link inside of Opt-in term checkbox label.
	 *
	 * @return url of Opt-in terms document or details page.
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Set the url for the link inside of Opt-in term checkbox label.
	 *
	 * @param url of Opt-in terms document or details page.
	 */
	public void setUrl(final String url)
	{
		this.url = url;
	}

}
