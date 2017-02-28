package eu.alldev.brauereivz.model;

public class Relation {
    private Long producer;
    private Long product;

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getProducer() {
        return producer;
    }

    public void setProducer(Long producer) {
        this.producer = producer;
    }
}
