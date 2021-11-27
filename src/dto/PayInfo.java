package dto;

import java.util.Map;

/**
 * @author tackedev
 */
public class PayInfo {

    private Map<Product, Integer> buyedProducts;
    private int refundAmount;

    public PayInfo(Map<Product, Integer> buyedProducts, int refundAmount) {
        this.buyedProducts = buyedProducts;
        this.refundAmount = refundAmount;
    }

    public Map<Product, Integer> getBuyedProducts() {
        return buyedProducts;
    }

    public void setBuyedProducts(Map<Product, Integer> buyedProducts) {
        this.buyedProducts = buyedProducts;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }
}
