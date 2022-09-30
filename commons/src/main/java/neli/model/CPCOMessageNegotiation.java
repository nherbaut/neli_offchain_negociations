package neli.model;

public class CPCOMessageNegotiation {
    String contentID;
    String owner;
    int price;
    boolean cpAgreed;
    boolean coAgreed;


    public CPCOMessageNegotiation() {
    }

    public CPCOMessageNegotiation(String contentID, String owner, int price, boolean cpAgreed, boolean coAgreed) {
        this.contentID = contentID;
        this.owner = owner;
        this.price = price;
        this.cpAgreed = cpAgreed;
        this.coAgreed = coAgreed;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "COInboundMessage{" +
                "contentID='" + contentID + '\'' +
                ", owner='" + owner + '\'' +
                ", price='" + price + '\'' +
                "}";
    }

    public boolean isCpAgreed() {
        return cpAgreed;
    }

    public void setCpAgreed(boolean cpAgreed) {
        this.cpAgreed = cpAgreed;
    }

    public boolean isCoAgreed() {
        return coAgreed;
    }

    public void setCoAgreed(boolean coAgreed) {
        this.coAgreed = coAgreed;
    }
}
