package slydepay.com.tonte.sampleslydepayintegration.model;

/**
 * Created by Tonte on 2/13/18.
 */

public class PayOption {
    String name = "";
    String shortName = "";
    String maximumAmount = "";
    String active = "";
    String reason = "";
    String logourl = "";

    public PayOption(String name, String shortName, String maximumAmount, String active, String reason, String logourl) {
        this.name = name;
        this.shortName = shortName;
        this.maximumAmount = maximumAmount;
        this.active = active;
        this.reason = reason;
        this.logourl = logourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(String maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }
}
