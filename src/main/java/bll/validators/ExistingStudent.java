package bll.validators;

import bll.StudentBLL;
import model.Ordert;

/**
 * <p>Clasa care valideaza pentru o comanda (order) daca exista clientul(studentul) cerut</p>
 */
public class ExistingStudent implements Validator<Ordert>{
    private static final String errorMessage = "Student does not exist!";
    private final StudentBLL studentBLL = new StudentBLL();

    @Override
    public void validate(Ordert order) {
        if(studentBLL.findStudentById(order.getId_p()) == null) {
            throw new IllegalArgumentException((errorMessage));
        }
    }
}
