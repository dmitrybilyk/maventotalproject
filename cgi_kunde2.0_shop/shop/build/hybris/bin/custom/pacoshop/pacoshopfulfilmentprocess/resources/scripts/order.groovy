package scripts

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel
import de.hybris.platform.core.model.order.payment.PaymentInfoModel
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.payment.enums.PaymentTransactionType
import de.hybris.platform.payment.model.PaymentTransactionEntryModel
import de.hybris.platform.servicelayer.search.impl.SearchResultImpl
import de.hybris.platform.core.model.order.OrderModel
import de.hybris.platform.core.model.user.AddressModel
import de.hybris.platform.core.model.order.AbstractOrderEntryModel
/**
 * This Groovy script prints the information sent to SAP SD/MD by Order Fulfilment process
 *
 * @module pacoshoporderfulfilmentprocess
 * @version 1.0v Apr 21, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
// Change this to actual order code after the order is created
orderCode = "00001008"

SearchResultImpl result =
        ctx.getBean('flexibleSearchService').search("SELECT {o.PK} FROM {Order AS o} WHERE {o:code} = " + orderCode);

if (result == null || result.getResult().size() == null)
{
    println("Order with code " + orderCode + " does not exist")
    return;
}

OrderModel order = result.getResult().get(0)
// orderRequestId
println("orderRequestId: " + order.getCode())
printEntries(order)



printAddress("client:", order.getCustomerAddress())
printAddress("invoiceRecipient:", order.getPaymentAddress())
printAddress("consignee:", order.getDeliveryAddress() != null ? order.getDeliveryAddress() : order.getCustomerAddress())

println(" orderOrigin: TODO: clarification in progress")
println(" orderId: TODO: clarification in progress")
println(" studentGraduationDate: TODO: clarification in progress")

println(" milesAndMoreNumber: " + (order.getBonusRecipientAddress() != null ? order.getBonusRecipientAddress().getMilesAndMoreNumber() : null))

println(" bonusPaymentInfo: TODO: work in progress")
printPaymentInfo(order.getBonusRecipientPaymentInfo(), order)

println(" deliveryStartDate: " + order.getDeliveryStartDate())

printPaymentInfo(order.getPaymentInfo(), order) // main payment info

/**
 * Function for printing the order entries
 */
def printEntries(OrderModel order)
{
    println("orderPosition: ")

    if (order.getEntries() == null || order.getEntries().size() == 0)
    {
        println(" Order contains no entries")
    }
    else
    {
        for (AbstractOrderEntryModel oe : order.getEntries())
        {
            println(" orderRequestPositionId: " + oe.getCode())
            println(" orderSize: " + oe.getQuantity())
            printProduct(oe.getProduct())
        }
    }
}

/**
 * Prints product attributes
 * @param p
 */
def printProduct(ProductModel p)
{
    if (p == null)
    {
        println(" Product: ERROR, product not defined")
        return
    }

    println(" productOrigin: " + p.getOfferOrigin())
    println(" productId: " + p.getExternalProductId())
    println(" productOwner: " + (p.getProductOwner() != null ? p.getProductOwner().getCode() : null))

    println(" productClass: " + (p.getProductType() != null && p.getProductType().getProductClass() != null ? p.getProductType().getProductClass().getCode() : null))
    println(" productGroup: " + (p.getProductType() != null && p.getProductType().getProductGroup() != null ? p.getProductType().getProductGroup().getCode() : null))
}

/**
 * Prints payment information
 *
 * @param pe
 * @param order
 */
def printPaymentInfo(PaymentInfoModel pe, OrderModel order)
{
    if (pe != null)
    {
        println("pspMethod: " + pe.getCode())
        if (pe.getPaymentMethod() != null && pe.getPaymentMethod().getPsp() != null)
        {
            if (PacoshopFulfilmentProcessConstants.PAYMENT_WIRECARD.equalsIgnoreCase(pe.getPaymentMethod().getPsp().getCode()))
            {
                println(" pspId: " + PacoshopFulfilmentProcessConstants.PAYMENT_WIRECARD)
            }
            else
            {
                println(" pspId: " + PacoshopFulfilmentProcessConstants.PAYMENT_SAP)
            }
        }
        else
        {
            println (" pspId: not defined")
        }

        PaymentTransactionEntryModel transaction =
                ctx.getBean("paymentExtService").findTransaction(order, PaymentTransactionType.CAPTURE)
        println(" pspTransactionId: " + (transaction != null ? transaction.getRequestToken() : null))

        println (" pspAccount: ")

        if (pe instanceof DebitPaymentInfoModel)
        {
            DebitPaymentInfoModel dpe = (DebitPaymentInfoModel)pe
            println("  accountHolder: " + dpe.getBaOwner())
            println("  accountNumber: " + dpe.getAccountNumber())
            println("  bankCode: " + dpe.getBankIDNumber())
            println("  bankName: " + dpe.getBank())
            println("  iban: " + dpe.getIban())
            println("  swiftBic: " + dpe.getBic())
        }
        else if (pe instanceof  PacoDebitPaymentInfoModel)
        {
            PacoDebitPaymentInfoModel dpe = (PacoDebitPaymentInfoModel)pe
            println("  accountHolder: " + dpe.getFirstName() + " " + dpe.getLastName())
            println("  accountNumber: " + dpe.getAccountNumber())
            println("  bankCode: " + dpe.getBankIdNumber())
            println("  bankName: " + null)
            println("  iban: " + dpe.getIBAN())
            println("  swiftBic: " + dpe.getBIC())
        }
        else
        {
            println("  Account information is not sent to SAP for payment method: " + pe._TYPECODE)
        }
    }
}

/*
 * Prints the address information
 */
def printAddress(String addressType, AddressModel a)
{
    println(addressType)

    if (a != null)
    {
        println(" salutation: " + (a.getTitle() != null ? a.getTitle().getName() : null))
        println(" title: " + (a.getTitle2() != null ? a.getTitle2().getName() : null))
        println(" firstname: " + a.getFirstname())
        println(" lastname: " + a.getLastname())
        println(" addressSuffix: " + a.getLine3())
        println(" streetNumber: " + a.getLine2())
        println(" street: " + a.getLine1())
        println(" postalCode " + a.getPostalcode())
        println(" city: " + a.getTown())
        println(" country: " + (a.getCountry() != null ? a.getCountry().getName() : null))
        println(" email: " + a.getEmail())
        println(" phone: " + a.getPhone1())
        println(" mobile: " + a.getCellphone())
        println(" birthday: " + a.getDateOfBirth())
    }
    else
    {
        println(" Not defined");
    }
}
