package tudai.arquitecturaweb.repository.interfaces;

import tudai.arquitecturaweb.entity.Carrera;

import java.util.List;

public interface CarreraRepository {

	Carrera saveCarrera(Carrera c);
	
	Carrera getCarreraByName(String nombre);

	List<Carrera> getCarreraOrderByStundents();
	
}
