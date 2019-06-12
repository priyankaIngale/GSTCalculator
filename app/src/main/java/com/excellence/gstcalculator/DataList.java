package com.excellence.gstcalculator;

/**
 * Created by Intl on 8/2/2017.
 */

class DataList {

    String percentage;
    String selling_amt;

    public DataList() {
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getSelling_amt() {
        return selling_amt;
    }

    public void setSelling_amt(String selling_amt) {
        this.selling_amt = selling_amt;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        this.basicPrice = basicPrice;
    }

    String tax;
    String basicPrice;
}
