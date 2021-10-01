package tudai.arquitecturaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private int age;

    @Column()
    private int gender;

    @Column(nullable = false, name = "identification_number")
    private int identificationNumber;
    // varios estudiantes en un domicilio. No es posible un estudiante en varios domicilios
    @ManyToOne
    private Direccion domicilio;

    @Column(nullable = false, name = "student_number")
    private int studentNumber;

    @OneToMany(mappedBy="estudiante", fetch=FetchType.LAZY)
    private List<CarreraEstudiante> carreras;

    public Estudiante() {
    }

    public Estudiante(int id,String name, String lastName, int age, String gender, int identificationNumber, Direccion domicilio, int studentNumber) {
        this.id  = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = getGenderId(gender);
        this.identificationNumber = identificationNumber;
        this.domicilio = domicilio;
        this.studentNumber = studentNumber;
        this.carreras = new ArrayList<>();
    }
    
    public int getId() {
		return id;
	}

	private int getGenderId(String gender){
        return (gender.equalsIgnoreCase("femenino")) ? 1: 2;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public List<CarreraEstudiante> getCarreras() {
        return carreras;
    }

    public void addCarrera(CarreraEstudiante carrera) {
        this.carreras.add(carrera);
    }

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", gender="
				+ gender + ", identificationNumber=" + identificationNumber +
				", studentNumber=" + studentNumber + "]";
	}



	
    
    
}
