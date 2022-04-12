package start;

import java.util.List;
import java.util.logging.Logger;

import bll.OrderBLL;
import model.Ordert;
import presentation.Controller;
import presentation.OrderView;
import presentation.ProductView;
import presentation.StudentView;

/**
 * <p>Clasa pentru pornirea simularii aplicatiei</p>
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	/**
	 * <p>Metoda main in care creeam obiecte de tipul StudentView, ProductView, OrderView si Controller</p>
	 * <p>Executia simularii incepe prin constructorul Controller-ului</p>
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		StudentView studentView = new StudentView();
		ProductView productView = new ProductView();
		OrderView orderView = new OrderView();
		Controller controller = new Controller(studentView, productView, orderView);
		studentView.setVisible(true);
		productView.setVisible(true);
		orderView.setVisible(true);
	}
}
