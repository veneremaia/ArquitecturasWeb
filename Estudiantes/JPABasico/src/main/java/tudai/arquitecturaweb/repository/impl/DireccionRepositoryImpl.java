package tudai.arquitecturaweb.repository.impl;

import javax.persistence.EntityManager;

import tudai.arquitecturaweb.entity.Direccion;
import tudai.arquitecturaweb.repository.interfaces.DireccionRepository;

public class DireccionRepositoryImpl implements DireccionRepository{
	
	EntityManager em;

	public DireccionRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Direccion saveDireccion(Direccion d) {
		if((Integer)d.getId() == null) {
			em.persist(d);	
		} else {
			d = em.merge(d);
		}
		return d;
	}

	@Override
	public Direccion getDireccionById(int id) {
		Direccion d = (Direccion)em.createQuery("SELECT d from Direccion d where d.id =:id").setParameter("id", id).getResultList().get(0);
		return d;
	}
	
	
	
}
