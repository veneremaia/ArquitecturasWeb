package tudai.arquitecturaweb.repository.impl;

import javax.persistence.EntityManager;

import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.Estudiante;
import tudai.arquitecturaweb.repository.interfaces.CarreraRepository;

import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository{
	
	EntityManager em;
	
	public CarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Carrera saveCarrera(Carrera c) {
		if((Integer)c.getId() == null) {
			em.persist(c);	
		} else {
			c = em.merge(c);
		}
		return c;
	}

	@Override
	public Carrera getCarreraByName(String nombre) {
		Carrera carrera = (Carrera)em.createQuery("SELECT c FROM Carrera c WHERE c.name= :carrera").setParameter("carrera",nombre).getResultList().get(0);
		return carrera;
	}
	@Override

	public List<Carrera> getCarreraOrderByStundents() {
		List<Carrera> carreras = em.createQuery("SELECT c " +
				" FROM Carrera c " +
				" JOIN CarreraEstudiante ce " +
				" ON c.id = ce.carrera.id " +
				" WHERE ce.finishDate IS NULL " +
				" GROUP BY c.id " +
				" ORDER BY COUNT(ce.estudiante)").getResultList();
		return carreras;

	}




}
