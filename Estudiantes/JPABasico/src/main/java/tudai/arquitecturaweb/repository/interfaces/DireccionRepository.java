package tudai.arquitecturaweb.repository.interfaces;

import tudai.arquitecturaweb.entity.Direccion;

public interface DireccionRepository {

	Direccion saveDireccion(Direccion d);
	Direccion getDireccionById(int id);
	
}
