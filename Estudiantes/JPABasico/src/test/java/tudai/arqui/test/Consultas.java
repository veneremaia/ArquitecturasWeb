package tudai.arqui.test;

import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.CarreraEstudiante;
import tudai.arquitecturaweb.entity.Direccion;
import tudai.arquitecturaweb.entity.Estudiante;
import tudai.arquitecturaweb.repository.impl.CarreraEstudianteRepositoryImpl;
import tudai.arquitecturaweb.repository.impl.CarreraRepositoryImpl;
import tudai.arquitecturaweb.repository.impl.DireccionRepositoryImpl;
import tudai.arquitecturaweb.repository.impl.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;
import java.util.List;

public class Consultas {

    static EntityManager em;

    public static void main(String[] args) throws IOException {
        //El nombre 'Example'  se cargó en el xml persistence-unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        em = emf.createEntityManager();
        // iniciar una transaccion
        em.getTransaction().begin();

        DireccionRepositoryImpl dRI = new DireccionRepositoryImpl(em);
        CarreraEstudianteRepositoryImpl CER = new CarreraEstudianteRepositoryImpl(em);
        EstudianteRepositoryImpl ERP = new EstudianteRepositoryImpl(em);
        CarreraRepositoryImpl CRI = new CarreraRepositoryImpl(em);

        // A) Dar de alta un estudiante con una direccion nueva

        //Alta direccion
        Direccion tandil = new Direccion(25, "Tandil", "Sarmiento 755");
        tandil = dRI.saveDireccion(tandil);
        Estudiante juan = new Estudiante(25, "juan", "perez", 28, "masculino", 40, tandil, 333);
        ERP.saveEstudiante(juan);

        // B) Matricular un estudiante
        // Nos traemos de la base la carrera con el siguiente nombre
        Carrera carrera= CRI.getCarreraByName("Ingenieria de sistemas");
        java.sql.Timestamp t = new java.sql.Timestamp(System.currentTimeMillis());
        // Matriculamos el estudiante con fecha de comienzo de hoy, y sin fecha de fin ya que no es egresado.
        CarreraEstudiante ce = new CarreraEstudiante(juan, carrera, t, null);
        CER.matricularEstudiante(ce);

        // C) Obtenemos los estudiantes ordenados por edad
        System.out.println("ESTUDIANTES ORDENADOS POR EDAD");
        List<Estudiante> estudiantes= ERP.getEstudiantesOrderByAge();
        estudiantes.forEach(p->System.out.println(p));
        // D) Obtenemos un estudiante por su nro de libreta
        System.out.println("ESTUDIANTE CON LIBRETA ESPECIFICA");
        Estudiante aux= ERP.getEstudianteByStudentNumber(2030);
        System.out.println(aux);
        // E) Obtenemos todos los estudiantes con genero femenino
        System.out.println("ESTUDIANTES CON GENERO ESPECIFICO");
        List<Estudiante> estudianteGenero= ERP.getEstudianteByGenero("femenino");
        estudianteGenero.forEach(p->System.out.println(p));

        // F) Obtenemos todas las carreras con inscriptos ordenados por cantidad de alumnos
        System.out.println("CARRERAS INSCRIPTOS ORDENADOS POR CANTIDAD DE ESTUDIANTES");
        List<Carrera> carreras= CRI.getCarreraOrderByStundents();
        carreras.forEach(c->System.out.println(c));
        // G) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        System.out.println("ESTUDIANTES INSCRIPTOS A CARRERA ESPECIFICA Y QUE PERTENEZCAN A CIUDAD ESPECIFICA");
        //List<Estudiante> estudiantesByCarrera= ERP.getEstudiantesByCarreraAndCity(carrera,"TANDIL");
        //estudiantesByCarrera.forEach(e->System.out.println(e));

        // 3) Reporte de carreras con informacion de inscriptos y egresados por año
        System.out.println("REPORTE");
        List<Object[]> reporte= CER.getReporte();
        reporte.forEach(e->System.out.println("Año: "+e[0]+", Carrera: "+e[1]+", cant. inscriptos: " +
                e[2]+", cant. egresados: "+e[3]));
        //commiteamos la transaccion
        em.getTransaction().commit();
        //cerramos el entity manager & factory
        em.close();
        emf.close();

    }

}
