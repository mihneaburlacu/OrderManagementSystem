package bll.validators;

import bll.ProductBLL;
import model.Ordert;

/**
 * <p>Clasa care valideaza pentru o comanda (order) daca exista produsul cerut</p>
 */
public class ExistingProduct implements Validator<Ordert>{
    private static final String errorMessage = "Student does not exist!";
    private final ProductBLL productBLL = new ProductBLL();

    @Override
    public void validate(Ordert order) {
        if(productBLL.findProductById(order.getId_p()) == null) {
            throw new IllegalArgumentException((errorMessage));
        }
    }
}
