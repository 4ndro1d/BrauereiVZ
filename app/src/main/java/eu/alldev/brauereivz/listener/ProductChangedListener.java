package eu.alldev.brauereivz.listener;

import java.util.List;

import eu.alldev.brauereivz.model.Product;

public interface ProductChangedListener {

    void productsChanged(List<Product> entities);
}
