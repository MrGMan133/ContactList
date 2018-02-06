package persistency;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Person;

public class PersonDAO implements IDAO<Person, String>{
	
	@SuppressWarnings("unused")
	private EntityTransaction entityTransaction;
	private DAO dao;
	
	EntityManager entityManager = Persistence
			.createEntityManagerFactory("personlist")
			.createEntityManager();
	
	public PersonDAO() {
		this.entityTransaction = this.entityManager.getTransaction();
		this.dao = new DAO(entityManager);
	}
	public void save(Person person) {
		dao.beginTransaction();
		entityManager.persist(person);
		dao.commitTransaction();
	}
	public void close() {
		dao.closeEntityManager();
	}
	public Person find(int id) {
		return entityManager.find(Person.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Person> findAll(){
		return entityManager.createQuery("Select p FROM Person p").getResultList();
	}
	public void update(Person person) {
		dao.beginTransaction();
		entityManager.merge(person);
		dao.commitTransaction();
	}
	public void remove(Person person) {
		dao.beginTransaction();
		entityManager.remove(person);
		dao.commitTransaction();
	}
}
