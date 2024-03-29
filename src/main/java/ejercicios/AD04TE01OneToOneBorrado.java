package ejercicios;

import entidades.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class AD04TE01OneToOneBorrado {

	/**
	 * 4. OneToOne unidireccional entre entidades Student y Tuition (matrícula).
	 * Borra un Tuition y su Student asociado
	 */
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AD04");
		EntityManager entityManager = factory.createEntityManager();
		
		try {			
		
			System.out.println("Borrando un nuevo Student y en cascada su Tuition asociado");
			
			int student_id = 7;
			
			Student tempStudent = entityManager.find(Student.class, student_id);
			// comienza la transacción
			entityManager.getTransaction().begin();
		
			// borra el Student y con CascadeType.ALL termina borrando su Tuition
			entityManager.remove(tempStudent);
					
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
	
}




