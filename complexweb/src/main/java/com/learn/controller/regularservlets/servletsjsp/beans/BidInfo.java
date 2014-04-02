package com.learn.controller.regularservlets.servletsjsp.beans;


import com.learn.controller.regularservlets.servletsjsp.ServletUtilities;

/** Bean that represents information about a bid at
 *  an auction site. Used to demonstrate redisplay of forms
 *  that have incomplete data.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class BidInfo {
  private String itemID = "";
  private String itemName = "";
  private String bidderName = "";
  private String emailAddress = "";
  private double bidPrice = 0;
  private boolean autoIncrement = false;

  public String getItemName() {
    return(itemName);
  }

  public void setItemName(String itemName) {
    this.itemName = ServletUtilities.filter(itemName);
  }

  public String getItemID() {
    return(itemID);
  }

  public void setItemID(String itemID) {
    this.itemID = ServletUtilities.filter(itemID);
  }
  
  public String getBidderName() {
    return(bidderName);
  }

  public void setBidderName(String bidderName) {
    this.bidderName =  ServletUtilities.filter(bidderName);
  }

  public String getEmailAddress() {
    return(emailAddress);
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = ServletUtilities.filter(emailAddress);
  }

  public double getBidPrice() {
    return(bidPrice);
  }

  public void setBidPrice(double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public boolean isAutoIncrement() {
    return(autoIncrement);
  }

  public void setAutoIncrement(boolean autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  /** Has all the required data been entered? Everything except
      autoIncrement must be specified explicitly (autoIncrement
      defaults to false).
  */

  public boolean isComplete() {
    return(hasValue(getItemID()) &&
           hasValue(getItemName()) &&
           hasValue(getBidderName()) &&
           hasValue(getEmailAddress()) &&
           (getBidPrice() > 0));
  }

  /** Has any of the data been entered? */

  public boolean isPartlyComplete() {
    boolean flag =
           (hasValue(getItemID()) ||
           hasValue(getItemName()) ||
           hasValue(getBidderName()) ||
           hasValue(getEmailAddress()) ||
           (getBidPrice() > 0) ||
           isAutoIncrement());
    return(flag);
  }

  private boolean hasValue(String val) {
    return((val != null) && (!val.equals("")));
  }
}
