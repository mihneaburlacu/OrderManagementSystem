package bll;

import bll.validators.ExistingProduct;
import bll.validators.ExistingStudent;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Ordert;
import model.Product;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p>Clasa pentru comenzi din business logic</p>
 */
public class OrderBLL {
    private List<Validator<Ordert>> validators;
    private OrderDAO orderDAO;
    private ProductBLL productBLL;
    private StudentBLL studentBLL;

    /**
     * <p>Constructorul clasei prin care sunt initializati validatorii, comanda din pachetul DataAccess si clientul si produsul</p>
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<Ordert>>();
        validators.add(new ExistingStudent());
        validators.add(new ExistingProduct());

        orderDAO = new OrderDAO();
        productBLL = new ProductBLL();
        studentBLL = new StudentBLL();
    }

    /**
     * <p>Metoda pentru implementarea interogarii insert</p>
     * <p>Aceasta ne va introduce comenzile in baza de date si va reinitializa cantitatea produsului cerut in comanda</p>
     * <p>La sfarsit aceasta va crea nota de plata intr-un fisier text, in care ne va oferii detalii despre comanda</p>
     * @param order
     * @return
     * @throws Exception
     */
    public Ordert insertOrder(Ordert order) throws Exception {
        for(Validator<Ordert> v : validators) {
            v.validate(order);
        }

        productBLL.decrementAmount(order.getId_p(), order.getAmount());
        Product product = productBLL.findProductById(order.getId_p());
        if(product.getAmount() < order.getAmount()) {
            throw new Exception("The amount of the order was greater than the amount of the product");
        }

        productBLL.updateProduct(new Product(product.getId(), product.getName(), product.getPrice(), product.decrementAmount(order.getAmount())), order.getId_p());

        if(productBLL.findProductById(order.getId_p()).getAmount() == 0){
            productBLL.removeProduct(order.getId_p());
        }

        Ordert orderRet = orderDAO.insertOrderTable(order);
        if(orderRet == null) {
            throw new NoSuchElementException("Cannot insert the bill with the id = " + order.getId());
        }
        createBill(orderRet);

        return orderRet;
    }

    /**
     * <p>Metoda care ne creeaza fisierul text pentru afisarea notei comenzii</p>
     * <p>In aceasta metoda se adauga pe rand detalii legate de produs si despre comanda si sunt afisate in fisier cu ajutorul unui obiect StringBuilder</p>
     * @param order
     */
    public void createBill(Ordert order) {
        StringBuilder sb = new StringBuilder("");
        sb.append("Order number " + order.getId() + "\n");
        sb.append("ID Student: " + order.getId_s() + "\n");
        sb.append("ID Product: " + order.getId_p() + "\n");
        Product product = productBLL.findProductById(order.getId_p());
        int price = product.getPrice() * order.getAmount();
        sb.append("Product: " + product.getName() + "\n");
        sb.append("Price: " + price + "\n");
        sb.append("Amount: " + order.getAmount() + "\n");
        sb.append("Date: " + order.getDate() + "\n");

        try {
            FileWriter fW = new FileWriter("file.txt", true);
            fW.write(sb.toString());
            fW.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * <p>Metoda care implementeaza interogarea de gasirea unei comenzi dupa un id</p>
     * <p>Aceasta va returna comanda cu id-ul respectiv trimis ca parametru</p>
     * @param id
     * @return
     */
    public Ordert findOrderById(int id) {
        Ordert od = orderDAO.findById(id);

        if(od == null) {
            throw new NoSuchElementException("Cannot find the order with id = " + id);
        }

        return od;
    }

    /**
     * <p>Metoda care ne returneaza toate comenzile pe care le avem in baza de date</p>
     * @return
     */
    public List<Ordert> findAllOrders() {
        //List<Order> listOrder = null;
        List<Ordert> listOrder = orderDAO.findAll();

        if(listOrder == null) {
            throw new NoSuchElementException("No orders in table!");
        }

        return listOrder;
    }

    /**
     * <p>Metoda care ne sterge o comanda din baza de date</p>
     * @param id
     * @return
     */
    public boolean removeOrder(int id) {
        boolean removed = false;

        removed = orderDAO.remove(id);

        return removed;
    }

}
