package walgreens.ecom.batch.framework.common.beans;

import java.util.List;


public class ProductBean {
    
    private String productName = null;
    private String productId = null;
    private String productType = null;
    private String productDeliveryOption = null;
    private String productQuantity = null;
    private String productTotalPrice = null;
    
    public String getProductDeliveryOption() {
        return productDeliveryOption;
    }
    public void setProductDeliveryOption(String productDeliveryOption) {
        this.productDeliveryOption = productDeliveryOption;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductTotalPrice() {
        return productTotalPrice;
    }
    public void setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }
    
}
