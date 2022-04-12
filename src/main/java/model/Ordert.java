package model;

/**
 * <p>Clasa pentru realizarea comenzilor unor produse de catre studenti(clienti)</p>
 */
public class Ordert {
    private int id;
    private int id_s;
    private int id_p;
    private String date;
    private int amount;

    /**
     * <p>Constructor fara argumente</p>
     */
    public Ordert() {
        super();
    }

    /**
     * <p>Constructor cu argumente in care sunt setate valorile primite</p>
     * @param idOrder id-ul comenzii
     * @param idStudent id-ul studentului(clientului) care realizeaza comanda
     * @param idProduct id-ul produsului comandat
     * @param date data la care se realizeaza comanda
     * @param amount cantitatea ceruta la comanda
     */
    public Ordert(int idOrder, int idStudent, int idProduct, String date, int amount) {
        this.id = idOrder;
        this.id_p = idProduct;
        this.id_s = idStudent;
        this.date = date;
        this.amount = amount;
    }

    /**
     * <p>Constructor fara atribut-ul id al comenzii</p>
     * @param idStudent
     * @param idProduct
     * @param date
     * @param amount
     */
    public Ordert(int idStudent, int idProduct, String date, int amount) {
        this.id_p = idProduct;
        this.id_s = idStudent;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_o) {
        this.id = id_o;
    }

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int a) {
        this.amount = a;
    }


    /**
     * <p>Metoda toString() pentru a afisa comenzile</p>
     * @return Returneaza un String cu datele studentului
     */
    public String toString() {
        return "Order " + this.id + ": student " +  this.id_s + ", product " + this.id_p + " " + this.date + ", " + this.amount;
    }

}

