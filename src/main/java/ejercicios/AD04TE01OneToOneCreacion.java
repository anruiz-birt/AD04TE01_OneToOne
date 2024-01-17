package ejercicios;

import java.time.LocalDate;

import entidades.Address;
import entidades.Student;
import entidades.Tuition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AD04TE01OneToOneCreacion {

	/**
	 * 1. OneToOne unidireccional entre entidades Student y Tuition (matrícula)
	 */
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AD04");
		EntityManager entityManager = factory.createEntityManager();
		
		try {			
			// crea un objeto Student
			System.out.println("Creando un nuevo objeto Student con su dirección y matrícula (tuition)");
			Student student = createStudent();
			Tuition tuition = new Tuition();
			tuition.setFee(4000.00);
			student.setTuition(tuition);					
			// comienza la transacción
			entityManager.getTransaction().begin();
			
			// guarda el objeto Student
			System.out.println("Guardando el estudiante...");
		
			//guarda el Student y con CascadeType.ALL guarda también el Tuition
			entityManager.persist(student);
						
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			factory.close();
		}
	}
	private static Student createStudent() {
		Student tempStudent = new Student();
		Address tempAddress = new Address();
		
		tempStudent.setFirstName("Ane");
		tempStudent.setLastName("Ruiz");
		tempStudent.setEmail("anruiz@birt.eus");
		tempStudent.getPhones().add("666778899");
		tempStudent.getPhones().add("677990011");
		tempStudent.setBirthdate(LocalDate.parse("1981-01-01"));
		tempAddress.setAddressLine1("Pajares 34");
		tempAddress.setAddressLine2("1A");
		tempAddress.setCity("Santurtzi");
		tempAddress.setZipCode("48980");
		tempStudent.setAddress(tempAddress);
		return tempStudent;		
	}
}






