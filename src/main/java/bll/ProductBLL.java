package bll;

import bll.validators.ProductPriceValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p>Clasa pentru produse din business logic</p>
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * <p>Constructorul clasei prin care sunt initializati validatorii si creaza produsul din pachetul DataAccess</p>
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductPriceValidator());

        productDAO = new ProductDAO();
    }

    /**
     * <p>Metoda care ne ajuta sa gasim un produs cu id-ul trimis ca parametru</p>
     * @param id
     * @return
     */
    public Product findProductById(int id) {
        Product pd = productDAO.findById(id);
        if (pd == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return pd;
    }

    /**
     * <p>Metoda care ne returneaza o lista cu toate produsele din baza de date</p>
     * <p>Aceasta apeleaza functia care executa interogarea findAll() din DataAccess</p>
     * @return
     */
    public List<Product> findAllProducts() {
        List<Product> productList = productDAO.findAll();

        if(productList == null) {
            throw new NoSuchElementException("No products in table!");
        }

        return productList;
    }

    /**
     * <p>Metoda care ne ajuta sa updatam cu valori noi un produs din baza noastra de date</p>
     * <p>Aceasta apeleaza functia care executa interogarea update() din DataAccess</p>
     * @param updateProduct
     * @param id
     * @return
     */
    public Product updateProduct(Product updateProduct, int id) {
        Product pd = productDAO.update(updateProduct, id);

        if(pd == null) {
            throw new NoSuchElementException("Cannot update the product with ID = " + updateProduct.getId() + " !");
        }

        return pd;
    }

    /**
     * <p>Metoda care ne ajuta sa inseram produse noi in baza de date</p>
     * <p>Aceasta foloseste functia insert din DataAccess care executa interogarea</p>
     * @param insertProduct
     * @return
     */
    public Product insertProduct(Product insertProduct) {
        for(Validator<Product> v : validators) {
            v.validate(insertProduct);
        }

        Product pd = productDAO.insert(insertProduct);

        if(pd == null) {
            throw new NoSuchElementException("Cannot insert the product with ID = " + insertProduct.getId() +" !");
        }

        return pd;
    }

    /**
     * <p>Metoda care realizeaza stergerea unui produs din baza de date in functie id-ul pe care il trimitem ca parametru</p>
     * <p>Aceasta foloseste functia remove(id) din DataAccess care executa interogarea de stergere</p>
     * @param id
     * @return
     */
    public boolean removeProduct(int id) {
        boolean removed = false;
        removed = productDAO.remove(id);

        if(removed == false) {
            throw new NoSuchElementException("The product with the id = " + id + "was not found");
        }

        return removed;
    }

    /**
     * <p>FindByName este o metoda particulara pentru clasa Product, care gaseste un produs cu numele trimis ca parametru</p>
     * <p>Aceasta foloseste functia care executa interogarea creata in clasa ProductDAO din pachetul DataAccess si retuneaza produsul gasit</p>
     * @param name
     * @return
     */
    public Product findByName(String name) {
        Product pd = productDAO.findByName(name);

        if(pd == null) {
            throw new NoSuchElementException("The product with the name = " + name + " was not found!");
        }

        return pd;
    }

    public int getPrice(int id) {
        return productDAO.findById(id).getPrice();
    }

    /**
     * <p>DecrementAmount este o metoda care ne ajuta pentru realizarea unei noi comenzi</p>
     * <p>Aceasta gaseste produsul cu id-ul trimis ca parametru si apeleaza apoi functia decrementAmount din pachetul Model (clasa Product) pentru a returna noua cantitate a produsului respectiv</p>
     * @param id
     * @param need
     */
    public void decrementAmount(int id, int need) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        product.decrementAmount(need);
    }

}
