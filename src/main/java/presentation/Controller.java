package presentation;

import bll.OrderBLL;
import bll.ProductBLL;
import bll.StudentBLL;
import model.Ordert;
import model.Product;
import model.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * <p>Clasa Controller pentru gestionarea interfetei grafice</p>
 * <p>Produce simularea propriu-zisa a aplicatiei noastre</p>
 */
public class Controller {
    private StudentView studentView;
    private ProductView productView;
    private OrderView orderView;
    private StudentBLL studentBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;

    /**
     * Constructor pentru clasa
     * @param studentView
     * @param productView
     * @param orderView
     */
    public Controller(StudentView studentView, ProductView productView, OrderView orderView) {
        this.studentView = studentView;
        this.productView = productView;
        this.orderView = orderView;
        this.studentBLL = new StudentBLL();
        this.productBLL = new ProductBLL();
        this.orderBLL = new OrderBLL();

        setStudentTable(studentView, studentBLL);
        setProductTable(productView, productBLL);
        setOrderTable(orderView, orderBLL);

        this.studentView.addRefreshListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                setStudentTable(studentView, studentBLL);
            }
        });
        this.productView.addRefreshListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProductTable(productView, productBLL);
            }
        });
        this.orderView.addRefreshListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOrderTable(orderView, orderBLL);
            }
        });
        this.studentView.addInsertListener(new ActionListener() {
            int id = 0;
            String name = "";
            String address = "";
            int age = 0;
            String email = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                id = studentView.getInsertTableIDText();
                name = studentView.getInsertTableNameText();
                address = studentView.getInsertTableAddressText();
                age = studentView.getInsertTableAge();
                email = studentView.getInsertTableEmailText();
                Student student = new Student(id, name, address, email, age);
                Student returnStudent = studentBLL.insertStudent(student);
                if(returnStudent == null) {
                    studentView.writeMessage("Cannot insert the student");
                }
                else {
                    studentView.writeMessage("The student was inserted");
                }
            }
        });
        this.studentView.addUpdateListener(new ActionListener() {
            int id = 0;
            String name = "";
            String address = "";
            int age = 0;
            String email = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                id = studentView.getUpdateTableIDText();
                name = studentView.getUpdateTableNameText();
                address = studentView.getUpdateTableAddressText();
                age = studentView.getUpdateTableAge();
                email = studentView.getUpdateTableEmailText();
                Student student = new Student(id, name, address, email, age);
                Student returnStudent = studentBLL.updateStudent(student, id);
                if(returnStudent == null) {
                    studentView.writeMessage("Cannot update the student");
                }
                else {
                    studentView.writeMessage("The student was updated");
                }
            }
        });
        this.studentView.addFindByListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentView.getComboBox() == "ID") {
                    int id = studentView.getFindByTextFieldText();
                    Student student = studentBLL.findStudentById(id);
                    studentView.setFindByResultTextField(student.toString());
                }
                if(studentView.getComboBox() == "Name") {
                    String name = studentView.getFindByTextFieldStringText();
                    Student student = studentBLL.findByName(name);
                    studentView.setFindByResultTextField(student.toString());
                }
                if(studentView.getComboBox() == "Age") {
                    int age = studentView.getFindByTextFieldText();
                    Student student = studentBLL.findByAge(age);
                    studentView.setFindByResultTextField(student.toString());
                }
            }
        });
        this.studentView.addRemoveListener(new ActionListener() {
            int id = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(studentView.getRemoveTextFieldText());
                boolean deleted = false;
                deleted = studentBLL.removeStudent(id);
                if(deleted == true) {
                    studentView.writeMessage("The student was deleted!");
                }
                else {
                    studentView.writeMessage("The student cannot be deleted!");
                }
            }
        });
        this.productView.addInsertListener(new ActionListener() {
            int id = 0;
            String name = "";
            int price = 0;
            int amount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                id = productView.getInsertTableIDText();
                name = productView.getInsertTableNameText();
                price = productView.getInsertTablePrice();
                amount = productView.getInsertTableAmount();
                Product product = new Product(id, name, price, amount);
                Product returnProduct = productBLL.insertProduct(product);
                if(returnProduct == null) {
                    productView.writeMessage("Cannot insert the product");
                }
                else {
                    productView.writeMessage("The product was inserted");
                }
            }
        });
        this.productView.addUpdateListener(new ActionListener() {
            int id = 0;
            String name = "";
            int price = 0;
            int amount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                id = productView.getUpdateTableIDText();
                name = productView.getUpdateTableNameText();
                price = productView.getUpdateTablePrice();
                amount = productView.getUpdateTableAmount();
                Product product = new Product(id, name, price, amount);
                Product returnProduct = productBLL.updateProduct(product, id);
                if(returnProduct == null) {
                    productView.writeMessage("Cannot update the product");
                }
                else {
                    productView.writeMessage("The product was updated");
                }
            }
        });
        this.productView.addFindByListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productView.getComboBox() == "ID") {
                    int id = productView.getFindByTextFieldText();
                    Product product = productBLL.findProductById(id);
                    productView.setFindByResultTextField(product.toString());
                }
                if(productView.getComboBox() == "Name") {
                    String name = productView.getFindByTextFieldStringText();
                    Product product = productBLL.findByName(name);
                    productView.setFindByResultTextField(product.toString());
                }
            }
        });
        this.productView.addRemoveListener(new ActionListener() {
            int id = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(productView.getRemoveTextFieldText());
                boolean deleted = false;
                deleted = productBLL.removeProduct(id);
                if(deleted == true) {
                    productView.writeMessage("The product was deleted!");
                }
                else {
                    productView.writeMessage("The product cannot be deleted!");
                }
            }
        });
        this.orderView.addInsertListener(new ActionListener() {
            int id = 0;
            int id_p = 0;
            int id_s = 0;
            String date = "";
            int amount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                id = orderView.getInsertTableIDText();
                id_p = orderView.getInsertTableIDProdusText();
                id_s = orderView.getInsertTableIDStudentText();
                date = orderView.getInsertTableDateText();
                amount = orderView.getInsertTableAmountText();
                Ordert order = new Ordert(id, id_p, id_s, date, amount);
                Ordert returnOrder = null;
                try {
                    returnOrder = orderBLL.insertOrder(order);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(returnOrder == null) {
                    orderView.writeMessage("Cannot insert the order");
                }
                else {
                    orderView.writeMessage("The order was inserted");
                }
            }
        });
        this.orderView.addRemoveListener(new ActionListener() {
            int id = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(orderView.getRemoveTextField());
                boolean deleted = false;
                deleted = productBLL.removeProduct(id);
                if(deleted == true) {
                    orderView.writeMessage("The order was deleted!");
                }
                else {
                    orderView.writeMessage("The order cannot be deleted!");
                }
            }
        });
    }

    /**
     * <p>Metoda pentru setarea tabelului de studenti din interfata grafica</p>
     * <p>Aici parcurgem element cu element lista de obiecte returnata de findAll si adaugam in tabel</p>
     * @param studentView interfata pentru studenti
     * @param studentBLL
     */
    public void setStudentTable(StudentView studentView, StudentBLL studentBLL) {

        List<Student> list = studentBLL.findAllStudent();
        int length = list.size();

        for(int i = 0; i < length; i++) {
            System.out.println(list.get(i));
            for(int j = 0; j < 5; j++) {
                if(j == 0) {
                    studentView.setStudentTableText(list.get(i).getId(), i+1, j);
                }
                else if(j == 1) {
                    studentView.setStudentTableText(list.get(i).getName(), i+1, j);
                }
                else if(j == 2) {
                    studentView.setStudentTableText(list.get(i).getAddress(), i+1, j);
                }
                else if(j == 3) {
                    studentView.setStudentTableText(list.get(i).getAge(), i+1, j);
                }
                else {
                    studentView.setStudentTableText(list.get(i).getEmail(), i+1, j);
                }
            }
        }
    }

    /**
     * <p>Metoda pentru setarea tabelului de produse din interfata grafica</p>
     * <p>Aici parcurgem element cu element lista de obiecte returnata de findAll si adaugam in tabel</p>
     * @param productView
     * @param productBLL
     */
    public void setProductTable(ProductView productView, ProductBLL productBLL) {
        List<Product> list = productBLL.findAllProducts();
        int length = list.size();

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < 4; j++) {
                if(j == 0) {
                    productView.setProductTableText(list.get(i).getId(), i+1, j);
                }
                else if(j == 1) {
                    productView.setProductTableText(list.get(i).getName(), i+1, j);
                }
                else if(j == 2) {
                    productView.setProductTableText(list.get(i).getPrice(), i+1, j);
                }
                else {
                    productView.setProductTableText(list.get(i).getAmount(), i+1, j);
                }
            }
        }
    }

    /**
     * <p>Metoda pentru setarea tabelului de produse din interfata grafica</p>
     * <p>Aici parcurgem element cu element lista de obiecte returnata de findAll si adaugam in tabel</p>
     * @param orderView
     * @param orderBLL
     */
    public void setOrderTable(OrderView orderView, OrderBLL orderBLL) {
        List<Ordert> list = orderBLL.findAllOrders();
        int length = list.size();

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < 5; j++) {
                if(j == 0) {
                    orderView.setOrderTableText(list.get(i).getId(), i+1, j);
                }
                else if(j == 1) {
                    orderView.setOrderTableText(list.get(i).getId_p(), i+1, j);
                }
                else if(j == 2) {
                    orderView.setOrderTableText(list.get(i).getId_s(), i+1, j);
                }
                else if(j == 3) {
                    orderView.setOrderTableText(list.get(i).getDate(), i+1, j);
                }
                else {
                    orderView.setOrderTableText(list.get(i).getAmount(), i+1, j);
                }
            }
        }
    }
}
