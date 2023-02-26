
package Services;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Products;

public class ProductManagement extends DataManagement<Products> {

    public static final ProductManagement instance = new ProductManagement();

    public static ProductManagement getInstance() {
        return instance;
    }

    public Products getProductById(String proID) {
        if (proID != null && !proID.isBlank()) {
            for (Products pro : entityList) {
                if (proID.equalsIgnoreCase(pro.getProductID())) {
                    return pro;
                }
            }
        }
        return null;
    }

    public void PrintAll() {
        for (Products products : entityList) {
            System.out.println(products);
        }
    }

    @Override
    protected Products parseEntity(String stringEntity) {
        try {
            Products p = new Products();
            p.parseProduct(stringEntity);
            return p;
        } catch (Exception e) {
            Logger.getLogger(CustomersManagement.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
