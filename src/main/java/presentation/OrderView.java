package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * <p>Clasa pentru realizarea interfetei grafice, care contine fereastra pentru tabela Order</p>
 * <p>Aceasta fereastra este alcatuita dintr-un tabel mare (JTable) care cuprinde toate comenzile deja existente in baza de date</p>
 * <p>Butoane pentru realizarea executiei interogarilor de tipul Insert, Remove si FindAll(Refresh Table)</p>
 * <p>Si diverse TextField-uri si Label-uri pentru prelucrarea datelor</p>
 * <p>Pentru fiecare buton avem metode de tip addListener care ne vor ajuta in Controller pentru simularea aplicatiei</p>
 * <p>Pe langa acestea vom avea si metode care ne vor ajuta sa extragem datele din tabel si din textField-uri sau metode care ne ajuta sa scriem(sa setam) datele acolo</p>
 */
public class OrderView extends JFrame {

    private JPanel contentPane;
    private JTable orderTable;
    private JLabel orderLabel;
    private JButton refreshButton;
    private JLabel InsertLabel;
    private JTable insertTable;
    private JLabel insertCommentTextField;
    private JLabel removeLabel;
    private JTextField textField;
    private JButton insertButton;
    private JButton removeButton;

    /**
     * <p>Constructorul clasei</p>
     */
    public OrderView() {
        setTitle("Order Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 545);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        orderTable = new JTable();
        orderTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {"ID", "ID Produs", "ID Student", "Date", "Amount"},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        orderTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        orderTable.setBounds(10, 10, 510, 245);
        contentPane.add(orderTable);

        orderLabel = new JLabel("Order Table");
        orderLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        orderLabel.setBounds(625, 75, 100, 15);
        contentPane.add(orderLabel);

        refreshButton = new JButton("Refresh Table");
        refreshButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        refreshButton.setBounds(540, 194, 124, 21);
        contentPane.add(refreshButton);

        InsertLabel = new JLabel("Insert:");
        InsertLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        InsertLabel.setBounds(10, 290, 45, 13);
        contentPane.add(InsertLabel);

        insertTable = new JTable();
        insertTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        insertTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertTable.setBounds(65, 290, 455, 13);
        contentPane.add(insertTable);

        insertCommentTextField = new JLabel("Insert the id, produs id, student id, date and the amount for the order");
        insertCommentTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertCommentTextField.setBounds(107, 313, 360, 13);
        contentPane.add(insertCommentTextField);

        JLabel insertCommentSecondTextField = new JLabel("Select produs id and student id from the produs and student tables");
        insertCommentSecondTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertCommentSecondTextField.setBounds(107, 336, 360, 13);
        contentPane.add(insertCommentSecondTextField);

        insertButton = new JButton("Insert");
        insertButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        insertButton.setBounds(540, 287, 124, 21);
        contentPane.add(insertButton);

        removeLabel = new JLabel("Remove:");
        removeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        removeLabel.setBounds(10, 360, 60, 13);
        contentPane.add(removeLabel);

        textField = new JTextField();
        textField.setBounds(65, 359, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel removeCommentLabel = new JLabel("(Insert the order ID)");
        removeCommentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        removeCommentLabel.setBounds(171, 361, 149, 13);
        contentPane.add(removeCommentLabel);

        removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        removeButton.setBounds(540, 357, 124, 21);
        contentPane.add(removeButton);
    }

    /**
     * <p>Metoda pentru butonul remove</p>
     * @param actionListener
     */
    public void addRemoveListener(ActionListener actionListener) {
        this.removeButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul insert</p>
     * @param actionListener
     */
    public void addInsertListener(ActionListener actionListener) {
        this.insertButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul refresh</p>
     * @param actionListener
     */
    public void addRefreshListener(ActionListener actionListener) {
        this.refreshButton.addActionListener(actionListener);
    }

    public void setOrderTableText(Object t, int row, int col) {
        orderTable.getModel().setValueAt(t, row, col);
    }

    public int getInsertTableIDText() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 0).toString());
    }

    public int getInsertTableIDProdusText() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 1).toString());
    }

    public int getInsertTableIDStudentText() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 2).toString());
    }

    public String getInsertTableDateText() {
        return insertTable.getModel().getValueAt(0, 3).toString();
    }

    public int getInsertTableAmountText() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 4).toString());
    }

    public String getRemoveTextField() {
        return textField.getText();
    }

    public void writeMessage(String msg) {
        showMessageDialog(contentPane, msg);
    }
}

