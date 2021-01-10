package Project.model;

public class Sales {
    int salesID;
    String date;
    String productName;
    String quantity;
    String finalPrice;
    int salesRepID;
    int userID;

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int  getSalesRepID() {
        return salesRepID;
    }

    public void setSalesRepID(int  salesRepID) {
        this.salesRepID = salesRepID;
    }

    public int  getUserID() {
        return userID;
    }

    public void setUserID(int  userID) {
        this.userID = userID;
    }
}
