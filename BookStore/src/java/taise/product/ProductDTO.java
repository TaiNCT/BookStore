/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taise.product;

/**
 *
 * @author ADMIN
 */
public class ProductDTO {

    protected String productID;
    protected String productName;
    protected int productQuantity;
    protected float productUnitPrice;
    protected boolean productStatus;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, int productQuantity, float productUnitPrice, boolean productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productUnitPrice = productUnitPrice;
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(float productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

}
