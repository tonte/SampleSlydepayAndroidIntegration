package slydepay.com.tonte.sampleslydepayintegration;

/**
 * Created by Tonte on 6/15/17.
 */

public class Request {
    private String emailOrMobile;
    private String merchantKey;
    private double amount;
    private String orderCode;


    public Request(String emailOrMobile, String merchantKey, double amount, String orderCode) {
        this.emailOrMobile = emailOrMobile;
        this.merchantKey = merchantKey;
        this.amount = amount;
        this.orderCode = orderCode;
    }

    public String getEmailOrMobile() {
        return emailOrMobile;
    }

    public void setEmailOrMobile(String emailOrMobile) {
        this.emailOrMobile = emailOrMobile;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}

