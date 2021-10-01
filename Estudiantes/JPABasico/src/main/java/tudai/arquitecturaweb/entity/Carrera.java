package tudai.arquitecturaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="carrera", fetch=FetchType.LAZY)
    private List<CarreraEstudiante> estudiantes;

    public Carrera() {
    }

    public Carrera(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CarreraEstudiante> getEstudiantes() {
        if(estudiantes==null)
            this.createEstudiantes();
        return estudiantes;
    }

    private void createEstudiantes(){
        this.estudiantes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

