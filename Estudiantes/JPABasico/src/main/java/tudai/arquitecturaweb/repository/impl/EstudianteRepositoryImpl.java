package tudai.arquitecturaweb.repository.impl;

import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.Estudiante;
import tudai.arquitecturaweb.repository.interfaces.EstudianteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository{
	
    EntityManager em;
    
	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Estudiante saveEstudiante(Estudiante e) {
		if((Integer)e.getId() == null) {
			em.persist(e);	
		} else {
			e = em.merge(e);
		}
		return e;
	}

	@Override
	public Estudiante getEstudianteById(int id) {
		Estudiante e = (Estudiante)em.createQuery("SELECT e from Estudiante e where e.id =:id").setParameter("id", id).getResultList().get(0);
		return e;
	}

	@Override
	public List<Estudiante> getEstudiantesOrderByAge() {
		List<Estudiante> estudiantes = em.createQuery("SELECT e from Estudiante e order by e.age").getResultList();
		return estudiantes;

	}

	@Override
	public Estudiante getEstudianteByStudentNumber(int number) {
		Estudiante e = (Estudiante)em.createQuery("SELECT e from Estudiante e where e.studentNumber =:studentNumber").setParameter("studentNumber", number).getResultList().get(0);
		return e;
	}

	@Override
	public List<Estudiante> getEstudianteByGenero(String genero) {
		int generoId = getGeneroById(genero);
		List<Estudiante> estudiantes = em.createQuery("SELECT e from Estudiante e where e.gender =:gender").setParameter("gender", generoId).getResultList();

		return estudiantes;	
		}

	@Override
	public List<Estudiante> getEstudiantesByCarreraAndCity(Carrera carrera, String ciudad) {
		List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e " +
				"JOIN CarreraEstudiante ce " +
				"ON e.studentNumber = ce.estudiante.studentNumber " +
				"JOIN Carrera c " +
				"ON ce.carrera.id = c.id " +
				"WHERE e.domicilio.ciudad " +
				"LIKE :ciudad " +
				"AND c.name LIKE :carrera")
				.setParameter("ciudad",ciudad)
				.setParameter("carrera",carrera.getName())
				.getResultList();
		return estudiantes;
	}

	private int getGeneroById(String genero){
		return (genero).equalsIgnoreCase("femenino")? 1 : 2;
		}


}
	
	
	
