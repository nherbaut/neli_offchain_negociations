package neli.model;

public class CPNegociationTrigger {
    String contentId;
    double price;

    public CPNegociationTrigger() {
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CPNegociationTrigger(String contentId, double price) {
        this.contentId = contentId;
        this.price = price;
    }
}
