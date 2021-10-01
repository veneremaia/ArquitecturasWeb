package tudai.arquitecturaweb.repository.interfaces;

import java.util.List;

import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.Estudiante;

public interface EstudianteRepository {

	Estudiante saveEstudiante(Estudiante e);
	Estudiante getEstudianteById(int id);
	List<Estudiante> getEstudiantesOrderByAge();
	Estudiante getEstudianteByStudentNumber(int number);
	List<Estudiante> getEstudianteByGenero(String genero);
	List<Estudiante> getEstudiantesByCarreraAndCity(Carrera carrera, String ciudad);
	
	
}
