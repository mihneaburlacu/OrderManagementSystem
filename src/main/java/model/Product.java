package model;

import java.beans.Transient;

/**
 * <p>Clasa pentru produsele care pot fi comandate</p>
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int amount;

    /**
     * <p>Constructor fara argumente</p>
     */
    public Product() {
        super();
    }

    /**
     * <p>Constructor cu argumente in care sunt setate valorile primite</p>
     * @param id id-ul produsului
     * @param name numele produsului
     * @param price pretul produsului
     * @param amount cantitatea
     */
    public Product(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    /**
     * <p>Constructor fara atribut-ul id</p>
     * @param name
     * @param price
     * @param amount
     */
    public Product(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_p) {
        this.id = id_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Metoda care decrementeaza cantitatea unui produs cu valoarea primita ca parametru
     * @param used valoarea ceruta la comanda de catre client
     * @return returneaza noua cantitate a produsului
     */
    public int decrementAmount(int used) {
        this.amount = this.amount - used;
        return this.amount;
    }

    /**
     * <p>Metoda toString() pentru a afisa produsele</p>
     * @return Returneaza un String cu datele studentului
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}

