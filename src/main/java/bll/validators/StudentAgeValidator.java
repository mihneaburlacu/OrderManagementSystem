package bll.validators;

import model.Student;

/**
 * <p>Clasa care valideaza varsta unui student (client)</p>
 * <p>Aceasta varsta trebuie sa fie intre 7 si 30 de ani</p>
 */
public class StudentAgeValidator implements Validator<Student> {
	private static final int MIN_AGE = 7;
	private static final int MAX_AGE = 30;

	public void validate(Student t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}

	}

}
