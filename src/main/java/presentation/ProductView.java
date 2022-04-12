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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * <p>Clasa pentru realizarea interfetei grafice, care contine fereastra pentru tabela Product (produs)</p>
 * <p>Aceasta fereastra este alcatuita dintr-un tabel mare (JTable) care cuprinde toate comenzile deja existente in baza de date</p>
 * <p>Butoane pentru realizarea executiei interogarilor de tipul Insert, Remove, update, FindBy si FindAll(Refresh Table)</p>
 * <p>Si diverse TextField-uri si Label-uri pentru prelucrarea datelor</p>
 * <p>Pentru fiecare buton avem metode de tip addListener care ne vor ajuta in Controller pentru simularea aplicatiei</p>
 * <p>Pe langa acestea vom avea si metode care ne vor ajuta sa extragem datele din tabel si din textField-uri sau metode care ne ajuta sa scriem(sa setam) datele acolo</p>
 */
public class ProductView extends JFrame {

    private JPanel contentPane;
    private JTable productTable;
    private JLabel updateLabel;
    private JTable updateTable;
    private JLabel insertLabel;
    private JButton updateButton;
    private JTable insertTable;
    private JLabel removeLabel;
    private JButton removeButton;
    private JTextField removeTextField;
    private JLabel idRemoveTextField;
    private JLabel findByLabel;
    private JTextField findByTextField;
    private JComboBox findByComboBox;
    private JLabel productLable;
    private JButton insertButton;
    private JButton findByButton;
    private JButton refreshButton;
    private JLabel findByIntroduceLabel;
    private JTextField findByResultTextField;

    /**
     * <p>Constructor pentru clasa care ne creeaza fereastra</p>
     */
    public ProductView() {
        setTitle("Product Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 545);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        productTable = new JTable();
        productTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {"ID", "Name", "Price", "Amount"},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column"
                }
        ));
        productTable.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        productTable.setBounds(10, 10, 550, 220);
        contentPane.add(productTable);

        updateLabel = new JLabel("Update:");
        updateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        updateLabel.setBounds(10, 240, 53, 22);
        contentPane.add(updateLabel);

        updateTable = new JTable();
        updateTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column"
                }
        ));
        updateTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        updateTable.setBounds(80, 245, 526, 16);
        contentPane.add(updateTable);

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        updateButton.setBounds(645, 245, 85, 20);
        contentPane.add(updateButton);

        insertLabel = new JLabel("Insert:");
        insertLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        insertLabel.setBounds(10, 290, 55, 22);
        contentPane.add(insertLabel);

        insertTable = new JTable();
        insertTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column", "New column"
                }
        ));
        insertTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertTable.setBounds(80, 295, 526, 16);
        contentPane.add(insertTable);

        insertButton = new JButton("Insert");
        insertButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        insertButton.setBounds(645, 292, 85, 20);
        contentPane.add(insertButton);

        removeLabel = new JLabel("Remove:");
        removeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        removeLabel.setBounds(10, 340, 55, 22);
        contentPane.add(removeLabel);

        removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        removeButton.setBounds(645, 340, 85, 20);
        contentPane.add(removeButton);

        removeTextField = new JTextField();
        removeTextField.setBounds(80, 340, 106, 19);
        contentPane.add(removeTextField);
        removeTextField.setColumns(10);

        idRemoveTextField = new JLabel("(Insert only the ID for the product)");
        idRemoveTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        idRemoveTextField.setBounds(220, 340, 175, 22);
        contentPane.add(idRemoveTextField);

        findByLabel = new JLabel("Find By:");
        findByLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        findByLabel.setBounds(10, 390, 55, 22);
        contentPane.add(findByLabel);

        findByTextField = new JTextField();
        findByTextField.setColumns(10);
        findByTextField.setBounds(80, 390, 106, 19);
        contentPane.add(findByTextField);

        findByIntroduceLabel = new JLabel("(Insert the id or name for the product)");
        findByIntroduceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        findByIntroduceLabel.setBounds(220, 390, 205, 22);
        contentPane.add(findByIntroduceLabel);

        findByComboBox = new JComboBox();
        findByComboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name"}));
        findByComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        findByComboBox.setBounds(509, 390, 97, 21);
        contentPane.add(findByComboBox);

        findByButton = new JButton("Find By");
        findByButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        findByButton.setBounds(645, 390, 85, 20);
        contentPane.add(findByButton);

        productLable = new JLabel("Product Table");
        productLable.setFont(new Font("Times New Roman", Font.BOLD, 18));
        productLable.setBounds(600, 75, 130, 13);
        contentPane.add(productLable);

        refreshButton = new JButton("Refresh Table");
        refreshButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        refreshButton.setBounds(600, 180, 130, 20);
        contentPane.add(refreshButton);

        findByResultTextField = new JTextField();
        findByResultTextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        findByResultTextField.setBounds(150, 420, 410, 22);
        contentPane.add(findByResultTextField);
        findByResultTextField.setColumns(10);
    }

    /**
     * <p>Metoda pentru butonul Update</p>
     * @param actionListener
     */
    public void addUpdateListener(ActionListener actionListener) {
        this.updateButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul Remove</p>
     * @param actionListener
     */
    public void addRemoveListener(ActionListener actionListener) {
        this.removeButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul Insert</p>
     * @param actionListener
     */
    public void addInsertListener(ActionListener actionListener) {
        this.insertButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul FindBy</p>
     * @param actionListener
     */
    public void addFindByListener(ActionListener actionListener) {
        this.findByButton.addActionListener(actionListener);
    }

    /**
     * <p>Metoda pentru butonul Refresh</p>
     * @param actionListener
     */
    public void addRefreshListener(ActionListener actionListener) {
        this.refreshButton.addActionListener(actionListener);
    }

    public int getUpdateTableIDText() {
        return Integer.parseInt(updateTable.getModel().getValueAt(0, 0).toString());
    }

    public String getUpdateTableNameText() {
        return updateTable.getModel().getValueAt(0, 1).toString();
    }

    public int getUpdateTablePrice() {
        return Integer.parseInt(updateTable.getModel().getValueAt(0, 2).toString());
    }

    public int getUpdateTableAmount() {
        return Integer.parseInt(updateTable.getModel().getValueAt(0, 3).toString());
    }

    public int getInsertTableIDText() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 0).toString());
    }

    public String getInsertTableNameText() {
        return insertTable.getModel().getValueAt(0, 1).toString();
    }

    public int getInsertTablePrice() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 2).toString());
    }

    public int getInsertTableAmount() {
        return Integer.parseInt(insertTable.getModel().getValueAt(0, 3).toString());
    }

    public String getRemoveTextFieldText() {
        return removeTextField.getText();
    }

    public String getFindByTextFieldStringText() {
        return findByTextField.getText();
    }

    public int getFindByTextFieldText() {
        return Integer.parseInt(findByTextField.getText());
    }

    public String getComboBox() {
        return (String)findByComboBox.getSelectedItem();
    }

    public void setProductTableText(Object t, int row, int col) {
        productTable.getModel().setValueAt(t, row, col);
    }

    public void setFindByResultTextField(String msg) {
        findByResultTextField.setText(msg);
    }

    public void writeMessage(String msg) {
        showMessageDialog(contentPane, msg);
    }

}
