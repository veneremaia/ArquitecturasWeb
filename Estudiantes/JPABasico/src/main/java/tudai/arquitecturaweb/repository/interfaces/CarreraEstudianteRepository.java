package tudai.arquitecturaweb.repository.interfaces;

import tudai.arquitecturaweb.entity.CarreraEstudiante;
import tudai.arquitecturaweb.entity.Direccion;

import java.util.List;

public interface CarreraEstudianteRepository {
	
	CarreraEstudiante matricularEstudiante(CarreraEstudiante ce);
	List<Object[]> getReporte();
	
}
