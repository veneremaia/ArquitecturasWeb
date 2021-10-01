package tudai.arquitecturaweb.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CarreraEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne 
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;
    @Column(nullable = false, name="start_date")
    private Timestamp startDate;
    @Column(name="finish_date")
    private Timestamp finishDate;

    public CarreraEstudiante() {
    }

    public CarreraEstudiante(Estudiante e, Carrera c, Timestamp startDate, Timestamp finishDate) {
        this.estudiante = e;
        this.carrera = c;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiantes) {
        this.estudiante = estudiantes;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "CarreraEstudiante{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}

