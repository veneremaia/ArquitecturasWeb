package tudai.arquitecturaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Direccion {

    // Para que genere el id automaticamente
    @Id
    private int id;
    @Column
    private String ciudad;
    @Column
    private String calle;
    @OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)
    private List<Estudiante> habitantes;

    public Direccion() {
    }

    //Este constructor es sin id porque pusimos que se genere automaticamente
    public Direccion(int id,String ciudad, String calle) {
        this.id = id;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    public int getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", calle='" + calle + '\'' +
                '}';
    }
}
