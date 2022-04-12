package bll.validators;

/**
 * <p>Interfata care ne ajuta sa validam clientii, produsele si comenzile</p>
 * <p>Aceasta interfata este implementata de mai multe clase care vor realiza validarile</p>
 * @param <T> Primeste un obiect de tipul Object ca parametru, pentru a putea fi implementata de orice clasa din Model
 */
public interface Validator<T> {

	public void validate(T t);
}
