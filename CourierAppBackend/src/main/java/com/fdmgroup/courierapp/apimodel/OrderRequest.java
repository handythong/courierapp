package com.fdmgroup.courierapp.apimodel;

public class OrderRequest {
    /*
    private String cardNumber;
    private String expiryDate;
    private String nameOnCard;
    private String securityCode;
    */
    private String fromAddress;
    private String fromCompanyName;
    private String fromFullName;
    private String fromEmail;
    private String fromPhone;
    private String height;
    private String length;
    private String parcelType;
    private String toAddress;
    private String toCompanyName;
    private String toFullName;
    private String toEmail;
    private String toPhone;
    private String weight;
    private String width;

    public OrderRequest() { }

    public OrderRequest(
            String width,
            String weight,
            String toPhone,
            String toEmail,
            String toFullName,
            String toCompanyName,
            String toAddress,
            String parcelType,
            String length,
            String height,
            String fromPhone,
            String fromEmail,
            String fromFullName,
            String fromCompanyName,
            String fromAddress
            /*
            String expiryDate,
            String cardNumber,
            String securityCode,
            String nameOnCard,
            */
    ) {
        this.width = width;
        this.weight = weight;
        this.toPhone = toPhone;
        this.toEmail = toEmail;
        this.toFullName = toFullName;
        this.toCompanyName = toCompanyName;
        this.toAddress = toAddress;
        this.parcelType = parcelType;
        this.length = length;
        this.height = height;
        this.fromPhone = fromPhone;
        this.fromEmail = fromEmail;
        this.fromFullName = fromFullName;
        this.fromCompanyName = fromCompanyName;
        this.fromAddress = fromAddress;
        /*
        this.expiryDate = expiryDate;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.nameOnCard = nameOnCard;
         */
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromCompanyName() {
        return fromCompanyName;
    }

    public void setFromCompanyName(String fromCompanyName) {
        this.fromCompanyName = fromCompanyName;
    }

    public String getFromFullName() {
        return fromFullName;
    }

    public void setFromFullName(String fromFullName) {
        this.fromFullName = fromFullName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToCompanyName() {
        return toCompanyName;
    }

    public void setToCompanyName(String toCompanyName) {
        this.toCompanyName = toCompanyName;
    }

    public String getToFullName() {
        return toFullName;
    }

    public void setToFullName(String toFullName) {
        this.toFullName = toFullName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    /*
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
     */

    @Override
    public String toString() {
        return "OrderRequest{" +
                "fromAddress='" + fromAddress + '\'' +
                ", fromCompanyName='" + fromCompanyName + '\'' +
                ", fromContactName='" + fromFullName + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", fromPhone='" + fromPhone + '\'' +
                ", height='" + height + '\'' +
                ", length='" + length + '\'' +
                ", parcelType='" + parcelType + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", toCompanyName='" + toCompanyName + '\'' +
                ", toContactName='" + toFullName + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", toPhone='" + toPhone + '\'' +
                ", weight='" + weight + '\'' +
                ", width='" + width + '\'' +
                /*
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", securityCode='" + securityCode + '\'' +
                 */
                '}';
    }
}
