package persistency;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DAO{
	
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	public void beginTransaction() {
		try {
			this.entityTransaction = this.entityManager.getTransaction();
			entityTransaction.begin();
		}catch(IllegalStateException e){
			e.printStackTrace();
			rollbackTransaction();
		}
	}
	public void commitTransaction() {
		try {
			entityTransaction.commit();
		}catch(IllegalStateException e){
			rollbackTransaction();
		}
	}
	public void closeEntityManager() {
		entityManager.close();
	}
	private void rollbackTransaction() {
		try {
			entityTransaction.rollback();
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	
}