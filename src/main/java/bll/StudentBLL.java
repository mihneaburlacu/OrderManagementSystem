package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.StudentAgeValidator;
import bll.validators.Validator;
import dao.StudentDAO;
import model.Student;

/**
 * <p>Clasa pentru studentii (clientii) care vor realiza comenzi din business logic</p>
 */
public class StudentBLL {

	private List<Validator<Student>> validators;
	private StudentDAO studentDAO;

	/**
	 * <p>Constructorul clasei prin care sunt initializati validatorii si creaza studentul(clientul) din pachetul DataAccess</p>
	 */
	public StudentBLL() {
		validators = new ArrayList<Validator<Student>>();
		validators.add(new EmailValidator());
		validators.add(new StudentAgeValidator());

		studentDAO = new StudentDAO();
	}

	/**
	 * <p>Metoda FindStudentById ne ajuta sa gasim un student cu id-ul trimis ca parametru</p>
	 * <p>Aceasta apeleaza functia findById din pachetul DataAccess prin care se executa interogarea necesara</p>
	 * <p>La sfarsit, dupa ce a verificat ca executia instructiunii a fost realizata cu succes, ne returneaza studentul cerut</p>
	 * @param id
	 * @return
	 */
	public Student findStudentById(int id) {
		Student st = studentDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The student with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * <p>Metoda findAllStudent() ne gaseste toti studentii (clientii) din baza noastra de date</p>
	 * <p>Aceasta ii returneaza intr-o lista, obtinuta prin executarea interogarii din DataAccess, doar daca avem clienti la momentul respectiv in baza de date</p>
	 * @return
	 */
	public List<Student> findAllStudent() {
		List<Student> studentList = studentDAO.findAll();

		if(studentList == null) {
			throw new NoSuchElementException("No students in table!");
		}

		return studentList;
	}

	/**
	 * <p>UpdateStudent ne ajuta sa updatam un student cu id-ul primit ca parametru cu valori noi (valorile obiectului de tip Student trimis ca parametru)</p>
	 * <p>Aceasta ne va returna studentul nou updatat, doar daca executarea interogarii s-a realizat cu succes</p>
	 * @param updateStudent
	 * @param id
	 * @return
	 */
	public Student updateStudent(Student updateStudent, int id) {
		Student student = studentDAO.update(updateStudent, id);

		if(student == null) {
			throw new NoSuchElementException("Cannot update the student with ID = " + updateStudent.getId() + " !");
		}

		return student;
	}

	/**
	 * <p>InsertStudent este o metoda care ne ajuta sa introducem studenti (clienti) noi in baza noastra de date</p>
	 * <p>Aceasta va returna studentul nou inserat, sau, daca interogarea din DataAccess nu a functionat, vom returna null (a esuat realizarea inserarii)</p>
	 * @param insertStudent
	 * @return
	 */
	public Student insertStudent(Student insertStudent) {
		Student student = studentDAO.insert(insertStudent);

		if(student == null) {
			throw new NoSuchElementException("Cannot insert the student with ID = " + insertStudent.getId() +" !");
		}

		return student;
	}

	/**
	 * <p>FindByName este o metoda specifica pentru clasa Student, prin care vom gasi un student (client) care are numele la fel cu String-ul trimis ca parametru</p>
	 * <p>Aceasta este realizata prin metoda findByName realizata in clasa Student a pachetului DataAcces, care executa interogarea gasirii dupa nume</p>
	 * @param name
	 * @return
	 */
	public Student findByName(String name) {
		Student student = studentDAO.findByName(name);

		if (student == null) {
			throw new NoSuchElementException("The students with the name =" + name + " were not found!");
		}

		return student;
	}

	/**
	 * <p>FindByName este o metoda specifica pentru clasa Student, prin care vom gasi un student (client) care are varsta egala cu numarul intreg trimis ca parametru</p>
	 * <p>Aceasta este realizata prin metoda findByName realizata in clasa Student a pachetului DataAcces, care executa interogarea gasirii dupa varsta</p>
	 * @param age
	 * @return
	 */
	public Student findByAge(int age) {
		Student student = studentDAO.findByAge(age);

		if (student == null) {
			throw new NoSuchElementException("The students with the name =" + age + " were not found!");
		}

		return student;
	}

	/**
	 * <p>RemoveStudent ne ajuta sa stergem un student din baza noastra de date</p>
	 * <p>Metoda functioneaza doar daca studentul cu id-ul trimis ca parametru este gasit</p>
	 * @param id
	 * @return
	 */
	public boolean removeStudent(int id) {
		boolean removed = studentDAO.remove(id);

		if (removed == false) {
			throw new NoSuchElementException("The students with the id =" + id + " was not found!");
		}

		return removed;
	}
}
