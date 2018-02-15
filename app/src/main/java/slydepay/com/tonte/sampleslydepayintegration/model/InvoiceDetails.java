package slydepay.com.tonte.sampleslydepayintegration.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tonte on 6/15/17.
 */


public class InvoiceDetails {

    @SerializedName("orderCode")
    @Expose
    private String orderCode;
    @SerializedName("paymentCode")
    @Expose
    private String paymentCode;
    @SerializedName("payToken")
    @Expose
    private String payToken;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("qrCodeUrl")
    @Expose
    private String qrCodeUrl;
    @SerializedName("fullDiscountAmount")
    @Expose
    private Integer fullDiscountAmount;
    @SerializedName("discounts")
    @Expose
    private List<Object> discounts = null;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPayToken() {
        return payToken;
    }

    public void setPayToken(String payToken) {
        this.payToken = payToken;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Integer getFullDiscountAmount() {
        return fullDiscountAmount;
    }

    public void setFullDiscountAmount(Integer fullDiscountAmount) {
        this.fullDiscountAmount = fullDiscountAmount;
    }

    public List<Object> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Object> discounts) {
        this.discounts = discounts;
    }

}
