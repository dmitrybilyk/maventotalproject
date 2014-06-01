/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at May 31, 2014 10:18:23 PM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package com.learn.velocity.com.learn.test;


import java.util.Date;

/**
 * Generated model class for type Address first defined at extension core.
 */
@SuppressWarnings("all")
public class AddressModel

{

    private TitleEnum title;

    public void setTitle(TitleEnum title) {
        this.title = title;
    }

    /**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "Address";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.streetnumber</code> attribute defined at extension <code>core</code>. */
	public static final String STREETNUMBER = "streetnumber";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.company</code> attribute defined at extension <code>core</code>. */
	public static final String COMPANY = "company";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.title</code> attribute defined at extension <code>core</code>. */
	public static final String TITLE = "title";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.postalcode</code> attribute defined at extension <code>core</code>. */
	public static final String POSTALCODE = "postalcode";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.building</code> attribute defined at extension <code>core</code>. */
	public static final String BUILDING = "building";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.remarks</code> attribute defined at extension <code>catalog</code>. */
	public static final String REMARKS = "remarks";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.ssoCustomerId</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String SSOCUSTOMERID = "ssoCustomerId";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.appartment</code> attribute defined at extension <code>core</code>. */
	public static final String APPARTMENT = "appartment";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.cellphone</code> attribute defined at extension <code>core</code>. */
	public static final String CELLPHONE = "cellphone";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.phone2</code> attribute defined at extension <code>core</code>. */
	public static final String PHONE2 = "phone2";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.milesAndMoreNumber</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String MILESANDMORENUMBER = "milesAndMoreNumber";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.url</code> attribute defined at extension <code>catalog</code>. */
	public static final String URL = "url";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.country</code> attribute defined at extension <code>core</code>. */
	public static final String COUNTRY = "country";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.lastname</code> attribute defined at extension <code>core</code>. */
	public static final String LASTNAME = "lastname";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.contactAddress</code> attribute defined at extension <code>catalog</code>. */
	public static final String CONTACTADDRESS = "contactAddress";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.pobox</code> attribute defined at extension <code>core</code>. */
	public static final String POBOX = "pobox";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.phone1</code> attribute defined at extension <code>core</code>. */
	public static final String PHONE1 = "phone1";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.lastUpdate</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String LASTUPDATE = "lastUpdate";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.dateOfBirth</code> attribute defined at extension <code>core</code>. */
	public static final String DATEOFBIRTH = "dateOfBirth";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.region</code> attribute defined at extension <code>core</code>. */
	public static final String REGION = "region";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.title2</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String TITLE2 = "title2";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.fax</code> attribute defined at extension <code>core</code>. */
	public static final String FAX = "fax";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.publicKey</code> attribute defined at extension <code>catalog</code>. */
	public static final String PUBLICKEY = "publicKey";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.department</code> attribute defined at extension <code>core</code>. */
	public static final String DEPARTMENT = "department";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.original</code> attribute defined at extension <code>core</code>. */
	public static final String ORIGINAL = "original";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.shippingAddress</code> attribute defined at extension <code>catalog</code>. */
	public static final String SHIPPINGADDRESS = "shippingAddress";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.paymentAddresss2CartToOrderCronJob</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String PAYMENTADDRESSS2CARTTOORDERCRONJOB = "paymentAddresss2CartToOrderCronJob";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.unloadingAddress</code> attribute defined at extension <code>catalog</code>. */
	public static final String UNLOADINGADDRESS = "unloadingAddress";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.district</code> attribute defined at extension <code>core</code>. */
	public static final String DISTRICT = "district";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.billingAddress</code> attribute defined at extension <code>catalog</code>. */
	public static final String BILLINGADDRESS = "billingAddress";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.middlename2</code> attribute defined at extension <code>core</code>. */
	public static final String MIDDLENAME2 = "middlename2";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.town</code> attribute defined at extension <code>core</code>. */
	public static final String TOWN = "town";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.middlename</code> attribute defined at extension <code>core</code>. */
	public static final String MIDDLENAME = "middlename";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.gender</code> attribute defined at extension <code>core</code>. */
	public static final String GENDER = "gender";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.typeQualifier</code> attribute defined at extension <code>catalog</code>. */
	public static final String TYPEQUALIFIER = "typeQualifier";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.businessPartnerId</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String BUSINESSPARTNERID = "businessPartnerId";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.line2</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String LINE2 = "line2";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.visibleInAddressBook</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String VISIBLEINADDRESSBOOK = "visibleInAddressBook";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.line1</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String LINE1 = "line1";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.deliveryAddresss2CartToOrderCronJob</code> attribute defined at extension <code>basecommerce</code>. */
	public static final String DELIVERYADDRESSS2CARTTOORDERCRONJOB = "deliveryAddresss2CartToOrderCronJob";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.firstname</code> attribute defined at extension <code>core</code>. */
	public static final String FIRSTNAME = "firstname";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.email</code> attribute defined at extension <code>core</code>. */
	public static final String EMAIL = "email";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.roleInCompany</code> attribute defined at extension <code>pacoshopcore</code>. */
	public static final String ROLEINCOMPANY = "roleInCompany";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.duplicate</code> attribute defined at extension <code>core</code>. */
	public static final String DUPLICATE = "duplicate";
	
	/** <i>Generated constant</i> - Attribute key of <code>Address.streetname</code> attribute defined at extension <code>core</code>. */
	public static final String STREETNAME = "streetname";
	
	
	/** <i>Generated variable</i> - Variable of <code>Address.streetnumber</code> attribute defined at extension <code>core</code>. */
	private String _streetnumber;
	
	/** <i>Generated variable</i> - Variable of <code>Address.company</code> attribute defined at extension <code>core</code>. */
	private String _company;

	/** <i>Generated variable</i> - Variable of <code>Address.postalcode</code> attribute defined at extension <code>core</code>. */
	private String _postalcode;
	
	/** <i>Generated variable</i> - Variable of <code>Address.building</code> attribute defined at extension <code>core</code>. */
	private String _building;
	
	/** <i>Generated variable</i> - Variable of <code>Address.remarks</code> attribute defined at extension <code>catalog</code>. */
	private String _remarks;
	
	/** <i>Generated variable</i> - Variable of <code>Address.ssoCustomerId</code> attribute defined at extension <code>pacoshopcore</code>. */
	private String _ssoCustomerId;
	
	/** <i>Generated variable</i> - Variable of <code>Address.appartment</code> attribute defined at extension <code>core</code>. */
	private String _appartment;
	
	/** <i>Generated variable</i> - Variable of <code>Address.cellphone</code> attribute defined at extension <code>core</code>. */
	private String _cellphone;
	
	/** <i>Generated variable</i> - Variable of <code>Address.phone2</code> attribute defined at extension <code>core</code>. */
	private String _phone2;
	
	/** <i>Generated variable</i> - Variable of <code>Address.milesAndMoreNumber</code> attribute defined at extension <code>pacoshopcore</code>. */
	private String _milesAndMoreNumber;
	
	/** <i>Generated variable</i> - Variable of <code>Address.url</code> attribute defined at extension <code>catalog</code>. */
	private String _url;
	
	/** <i>Generated variable</i> - Variable of <code>Address.lastname</code> attribute defined at extension <code>core</code>. */
	private String lastname;
	
	/** <i>Generated variable</i> - Variable of <code>Address.contactAddress</code> attribute defined at extension <code>catalog</code>. */
	private Boolean _contactAddress;
	
	/** <i>Generated variable</i> - Variable of <code>Address.pobox</code> attribute defined at extension <code>core</code>. */
	private String _pobox;
	
	/** <i>Generated variable</i> - Variable of <code>Address.phone1</code> attribute defined at extension <code>core</code>. */
	private String _phone1;
	
	/** <i>Generated variable</i> - Variable of <code>Address.lastUpdate</code> attribute defined at extension <code>pacoshopcore</code>. */
	private Date _lastUpdate;
	
	/** <i>Generated variable</i> - Variable of <code>Address.dateOfBirth</code> attribute defined at extension <code>core</code>. */
	private Date _dateOfBirth;
	
	/** <i>Generated variable</i> - Variable of <code>Address.title2</code> attribute defined at extension <code>pacoshopcore</code>. */
	private String _title2;
	
	/** <i>Generated variable</i> - Variable of <code>Address.fax</code> attribute defined at extension <code>core</code>. */
	private String _fax;
	
	/** <i>Generated variable</i> - Variable of <code>Address.publicKey</code> attribute defined at extension <code>catalog</code>. */
	private String _publicKey;
	
	/** <i>Generated variable</i> - Variable of <code>Address.department</code> attribute defined at extension <code>core</code>. */
	private String _department;
	
	/** <i>Generated variable</i> - Variable of <code>Address.original</code> attribute defined at extension <code>core</code>. */
	private AddressModel _original;
	
	/** <i>Generated variable</i> - Variable of <code>Address.shippingAddress</code> attribute defined at extension <code>catalog</code>. */
	private Boolean _shippingAddress;

	/** <i>Generated variable</i> - Variable of <code>Address.unloadingAddress</code> attribute defined at extension <code>catalog</code>. */
	private Boolean _unloadingAddress;
	
	/** <i>Generated variable</i> - Variable of <code>Address.district</code> attribute defined at extension <code>core</code>. */
	private String _district;
	
	/** <i>Generated variable</i> - Variable of <code>Address.billingAddress</code> attribute defined at extension <code>catalog</code>. */
	private Boolean _billingAddress;
	
	/** <i>Generated variable</i> - Variable of <code>Address.middlename2</code> attribute defined at extension <code>core</code>. */
	private String _middlename2;
	
	/** <i>Generated variable</i> - Variable of <code>Address.town</code> attribute defined at extension <code>core</code>. */
	private String _town;
	
	/** <i>Generated variable</i> - Variable of <code>Address.middlename</code> attribute defined at extension <code>core</code>. */
	private String _middlename;

	/** <i>Generated variable</i> - Variable of <code>Address.typeQualifier</code> attribute defined at extension <code>catalog</code>. */
	private String _typeQualifier;
	
	/** <i>Generated variable</i> - Variable of <code>Address.businessPartnerId</code> attribute defined at extension <code>pacoshopcore</code>. */
	private String _businessPartnerId;
	
	/** <i>Generated variable</i> - Variable of <code>Address.line2</code> attribute defined at extension <code>basecommerce</code>. */
	private String _line2;
	
	/** <i>Generated variable</i> - Variable of <code>Address.visibleInAddressBook</code> attribute defined at extension <code>commerceservices</code>. */
	private Boolean _visibleInAddressBook;
	
	/** <i>Generated variable</i> - Variable of <code>Address.line1</code> attribute defined at extension <code>basecommerce</code>. */
	private String _line1;
	
	/** <i>Generated variable</i> - Variable of <code>Address.firstname</code> attribute defined at extension <code>core</code>. */
	private String firstname;
	
	/** <i>Generated variable</i> - Variable of <code>Address.email</code> attribute defined at extension <code>core</code>. */
	private String _email;
	
	/** <i>Generated variable</i> - Variable of <code>Address.roleInCompany</code> attribute defined at extension <code>pacoshopcore</code>. */
	private String _roleInCompany;
	
	/** <i>Generated variable</i> - Variable of <code>Address.duplicate</code> attribute defined at extension <code>core</code>. */
	private Boolean _duplicate;
	
	/** <i>Generated variable</i> - Variable of <code>Address.streetname</code> attribute defined at extension <code>core</code>. */
	private String _streetname;
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public AddressModel()
	{
		super();
	}


    public static String getTypecode() {
        return _TYPECODE;
    }

    public static String getStreetnumber() {
        return STREETNUMBER;
    }

    public static String getCompany() {
        return COMPANY;
    }

    public static String getTitle() {
        return TITLE;
    }

    public static String getPostalcode() {
        return POSTALCODE;
    }

    public static String getBuilding() {
        return BUILDING;
    }

    public static String getRemarks() {
        return REMARKS;
    }

    public static String getSsocustomerid() {
        return SSOCUSTOMERID;
    }

    public static String getAppartment() {
        return APPARTMENT;
    }

    public static String getCellphone() {
        return CELLPHONE;
    }

    public static String getPhone2() {
        return PHONE2;
    }

    public static String getMilesandmorenumber() {
        return MILESANDMORENUMBER;
    }

    public static String getUrl() {
        return URL;
    }

    public static String getCountry() {
        return COUNTRY;
    }

    public static String getLastname() {
        return LASTNAME;
    }

    public static String getContactaddress() {
        return CONTACTADDRESS;
    }

    public static String getPobox() {
        return POBOX;
    }

    public static String getPhone1() {
        return PHONE1;
    }

    public static String getLastupdate() {
        return LASTUPDATE;
    }

    public static String getDateofbirth() {
        return DATEOFBIRTH;
    }

    public static String getRegion() {
        return REGION;
    }

    public static String getTitle2() {
        return TITLE2;
    }

    public static String getFax() {
        return FAX;
    }

    public static String getPublickey() {
        return PUBLICKEY;
    }

    public static String getDepartment() {
        return DEPARTMENT;
    }

    public static String getOriginal() {
        return ORIGINAL;
    }

    public static String getShippingaddress() {
        return SHIPPINGADDRESS;
    }

    public static String getPaymentaddresss2carttoordercronjob() {
        return PAYMENTADDRESSS2CARTTOORDERCRONJOB;
    }

    public static String getUnloadingaddress() {
        return UNLOADINGADDRESS;
    }

    public static String getDistrict() {
        return DISTRICT;
    }

    public static String getBillingaddress() {
        return BILLINGADDRESS;
    }

    public static String getMiddlename2() {
        return MIDDLENAME2;
    }

    public static String getTown() {
        return TOWN;
    }

    public static String getMiddlename() {
        return MIDDLENAME;
    }

    public static String getGender() {
        return GENDER;
    }

    public static String getTypequalifier() {
        return TYPEQUALIFIER;
    }

    public static String getBusinesspartnerid() {
        return BUSINESSPARTNERID;
    }

    public static String getLine2() {
        return LINE2;
    }

    public static String getVisibleinaddressbook() {
        return VISIBLEINADDRESSBOOK;
    }

    public static String getLine1() {
        return LINE1;
    }

    public static String getDeliveryaddresss2carttoordercronjob() {
        return DELIVERYADDRESSS2CARTTOORDERCRONJOB;
    }

    public static String getEmail() {
        return EMAIL;
    }

    public static String getRoleincompany() {
        return ROLEINCOMPANY;
    }

    public static String getDuplicate() {
        return DUPLICATE;
    }

    public static String getStreetname() {
        return STREETNAME;
    }

    public String get_streetnumber() {
        return _streetnumber;
    }

    public void set_streetnumber(String _streetnumber) {
        this._streetnumber = _streetnumber;
    }

    public String get_company() {
        return _company;
    }

    public void set_company(String _company) {
        this._company = _company;
    }

    public String get_postalcode() {
        return _postalcode;
    }

    public void set_postalcode(String _postalcode) {
        this._postalcode = _postalcode;
    }

    public String get_building() {
        return _building;
    }

    public void set_building(String _building) {
        this._building = _building;
    }

    public String get_remarks() {
        return _remarks;
    }

    public void set_remarks(String _remarks) {
        this._remarks = _remarks;
    }

    public String get_ssoCustomerId() {
        return _ssoCustomerId;
    }

    public void set_ssoCustomerId(String _ssoCustomerId) {
        this._ssoCustomerId = _ssoCustomerId;
    }

    public String get_appartment() {
        return _appartment;
    }

    public void set_appartment(String _appartment) {
        this._appartment = _appartment;
    }

    public String get_cellphone() {
        return _cellphone;
    }

    public void set_cellphone(String _cellphone) {
        this._cellphone = _cellphone;
    }

    public String get_phone2() {
        return _phone2;
    }

    public void set_phone2(String _phone2) {
        this._phone2 = _phone2;
    }

    public String get_milesAndMoreNumber() {
        return _milesAndMoreNumber;
    }

    public void set_milesAndMoreNumber(String _milesAndMoreNumber) {
        this._milesAndMoreNumber = _milesAndMoreNumber;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean get_contactAddress() {
        return _contactAddress;
    }

    public void set_contactAddress(Boolean _contactAddress) {
        this._contactAddress = _contactAddress;
    }

    public String get_pobox() {
        return _pobox;
    }

    public void set_pobox(String _pobox) {
        this._pobox = _pobox;
    }

    public String get_phone1() {
        return _phone1;
    }

    public void set_phone1(String _phone1) {
        this._phone1 = _phone1;
    }

    public Date get_lastUpdate() {
        return _lastUpdate;
    }

    public void set_lastUpdate(Date _lastUpdate) {
        this._lastUpdate = _lastUpdate;
    }

    public Date get_dateOfBirth() {
        return _dateOfBirth;
    }

    public void set_dateOfBirth(Date _dateOfBirth) {
        this._dateOfBirth = _dateOfBirth;
    }

    public String get_title2() {
        return _title2;
    }

    public void set_title2(String _title2) {
        this._title2 = _title2;
    }

    public String get_fax() {
        return _fax;
    }

    public void set_fax(String _fax) {
        this._fax = _fax;
    }

    public String get_publicKey() {
        return _publicKey;
    }

    public void set_publicKey(String _publicKey) {
        this._publicKey = _publicKey;
    }

    public String get_department() {
        return _department;
    }

    public void set_department(String _department) {
        this._department = _department;
    }

    public AddressModel get_original() {
        return _original;
    }

    public void set_original(AddressModel _original) {
        this._original = _original;
    }

    public Boolean get_shippingAddress() {
        return _shippingAddress;
    }

    public void set_shippingAddress(Boolean _shippingAddress) {
        this._shippingAddress = _shippingAddress;
    }

    public Boolean get_unloadingAddress() {
        return _unloadingAddress;
    }

    public void set_unloadingAddress(Boolean _unloadingAddress) {
        this._unloadingAddress = _unloadingAddress;
    }

    public String get_district() {
        return _district;
    }

    public void set_district(String _district) {
        this._district = _district;
    }

    public Boolean get_billingAddress() {
        return _billingAddress;
    }

    public void set_billingAddress(Boolean _billingAddress) {
        this._billingAddress = _billingAddress;
    }

    public String get_middlename2() {
        return _middlename2;
    }

    public void set_middlename2(String _middlename2) {
        this._middlename2 = _middlename2;
    }

    public String get_town() {
        return _town;
    }

    public void set_town(String _town) {
        this._town = _town;
    }

    public String get_middlename() {
        return _middlename;
    }

    public void set_middlename(String _middlename) {
        this._middlename = _middlename;
    }

    public String get_typeQualifier() {
        return _typeQualifier;
    }

    public void set_typeQualifier(String _typeQualifier) {
        this._typeQualifier = _typeQualifier;
    }

    public String get_businessPartnerId() {
        return _businessPartnerId;
    }

    public void set_businessPartnerId(String _businessPartnerId) {
        this._businessPartnerId = _businessPartnerId;
    }

    public String get_line2() {
        return _line2;
    }

    public void set_line2(String _line2) {
        this._line2 = _line2;
    }

    public Boolean get_visibleInAddressBook() {
        return _visibleInAddressBook;
    }

    public void set_visibleInAddressBook(Boolean _visibleInAddressBook) {
        this._visibleInAddressBook = _visibleInAddressBook;
    }

    public String get_line1() {
        return _line1;
    }

    public void set_line1(String _line1) {
        this._line1 = _line1;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_roleInCompany() {
        return _roleInCompany;
    }

    public void set_roleInCompany(String _roleInCompany) {
        this._roleInCompany = _roleInCompany;
    }

    public Boolean get_duplicate() {
        return _duplicate;
    }

    public void set_duplicate(Boolean _duplicate) {
        this._duplicate = _duplicate;
    }

    public String get_streetname() {
        return _streetname;
    }

    public void set_streetname(String _streetname) {
        this._streetname = _streetname;
    }
}
