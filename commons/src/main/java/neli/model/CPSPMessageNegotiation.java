package neli.model;

public class CPSPMessageNegotiation {
    String contentID;
    String owner;
    double price;
    boolean spAgreed;
    CPCOMessageNegotiation cpcoMessageNegotiation;

    public CPSPMessageNegotiation() {
    }

    public CPCOMessageNegotiation getCpcoMessageNegotiation() {
        return cpcoMessageNegotiation;
    }

    public void setCpcoMessageNegotiation(CPCOMessageNegotiation cpcoMessageNegotiation) {
        this.cpcoMessageNegotiation = cpcoMessageNegotiation;
    }



    public CPSPMessageNegotiation(String contentID, String owner, double price) {
        this.contentID = contentID;
        this.owner = owner;
        this.price = price;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSpAgreed() {
        return spAgreed;
    }

    public void setSpAgreed(boolean spAgreed) {
        this.spAgreed = spAgreed;
    }

    public boolean isCpAgreed() {
        return cpAgreed;
    }

    public void setCpAgreed(boolean cpAgreed) {
        this.cpAgreed = cpAgreed;
    }

    boolean cpAgreed;
}
