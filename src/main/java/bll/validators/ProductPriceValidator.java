package bll.validators;

import model.Product;

/**
 * <p>Clasa care ajuta la validarea unui produs in functie de pretul pe care il are</p>
 * <p>Acesta trebuie sa fie intre 0 si 500</p>
 */
public class ProductPriceValidator implements Validator<Product>{
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 500;

    @Override
    public void validate(Product t) {
        if(t.getPrice() < MIN_PRICE || t.getPrice() > MAX_PRICE) {
            throw new IllegalArgumentException("The Product Price limit is not respected! (0 - 500)");
        }
    }
}
