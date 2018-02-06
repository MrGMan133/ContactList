package persistency;

import java.io.Serializable;

public interface IDAO<T, Id extends Serializable> {
	public void save(T entity);
	public void remove(T entity);
	public Object find(int id);
	public void update(T entity);
}
