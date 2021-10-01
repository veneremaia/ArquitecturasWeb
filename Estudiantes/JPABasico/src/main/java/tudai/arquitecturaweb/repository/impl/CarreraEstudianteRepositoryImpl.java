package tudai.arquitecturaweb.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tudai.arquitecturaweb.entity.CarreraEstudiante;
import tudai.arquitecturaweb.repository.interfaces.CarreraEstudianteRepository;

import java.util.List;

public class CarreraEstudianteRepositoryImpl implements CarreraEstudianteRepository{
	
	EntityManager em;
	
	public CarreraEstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public CarreraEstudiante matricularEstudiante(CarreraEstudiante ce) {
		if((Integer)ce.getId() == null) {
			em.persist(ce);	
		} else {
			ce = em.merge(ce);
		}
		return ce;
	}

	@Override
	public List<Object[]> getReporte() {
		TypedQuery<Object[]> informes =  em.createQuery("SELECT EXTRACT(YEAR FROM ce.startDate) AS anio," +
				" c.name AS carrera," +
				" COUNT(ce.startDate) AS inscriptos," +
				" COUNT(ce.finishDate) AS graduados " +
				"FROM CarreraEstudiante ce " +
				"JOIN Carrera c " +
				"ON ce.carrera.id= c.id " +
				"GROUP BY c.id,EXTRACT(YEAR FROM ce.startDate) " +
				"ORDER BY EXTRACT(YEAR FROM ce.startDate), c.name" , Object[].class);
		return informes.getResultList();
	}


}
