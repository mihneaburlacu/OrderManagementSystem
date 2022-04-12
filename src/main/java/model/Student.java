package model;

/**
 * <p>Clasa pentru studenti (clientii) care pot comanda produse</p>
 */
public class Student {
	private int id;
	private String name;
	private String address;
	private String email;
	private int age;

	/**
	 * <p>Constructor fara argumente</p>
	 */
	public Student() {
		super();
	}

	/**
	 * <p>Constructor cu argumente in care sunt setate valorile primite</p>
	 * @param id id-ul studentului(clientului)
	 * @param name numele studentului
	 * @param address adresa studentului
	 * @param email adresa de email a studentului
	 * @param age varsta studentului
	 */
	public Student(int id, String name, String address, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	/**
	 * <p>Constructor fara atribut-ul id</p>
	 * @param name numele studentului
	 * @param address adresa studentului
	 * @param email adresa de email a studentului
	 * @param age varsta studentului
	 */
	public Student(String name, String address, String email, int age) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	/**
	 * <p>Metoda de tip getter pentru atributul Id</p>
	 * @return returneaza id-ul studentului
	 */
	public int getId() {
		return id;
	}

	/**
	 * <p>Metoda de tip setter pentru atributul Id</p>
	 * @param id seteaza id-ul studentului cu cel primit ca parametru
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p>Metoda de tip getter pentru atributul name</p>
	 * @return returneaza numele studentului
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>Metoda de tip setter pentru atributul nume</p>
	 * @param name seteaza numele studentului cu cel primit ca parametru
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>Metoda de tip getter pentru atributul address</p>
	 * @return returneaza adresa studentului
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <p>Metoda de tip setter pentru atributul address</p>
	 * @param address seteaza adresa studentului cu cea primit ca parametru
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <p>Metoda de tip getter pentru atributul age</p>
	 * @return returneaza varsta studentului
	 */
	public int getAge() {
		return age;
	}

	/**
	 * <p>Metoda de tip setter pentru atributul age</p>
	 * @param age seteaza varsta studentului cu cea primit ca parametru
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * <p>Metoda de tip getter pentru atributul email</p>
	 * @return returneaza id-ul studentului
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <p>Metoda de tip setter pentru atributul email</p>
	 * @param email seteaza adresa de email a studentului cu cea primit ca parametru
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p>Metoda toString() pentru a afisa fiecare student(client)</p>
	 * @return Returneaza un String cu datele studentului
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
				+ "]";
	}

}
