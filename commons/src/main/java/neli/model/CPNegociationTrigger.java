package neli.model;

public class CPNegociationTrigger {
    String contentID;
    String owner;
    int price;

    public CPNegociationTrigger() {
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) { this.owner = owner; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CPNegociationTrigger(String contentID, String owner, int price) {
        this.contentID = contentID;
        this.owner = owner;
        this.price = price;
    }
}
