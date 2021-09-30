package tudai.arqui.test;

import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.CarreraEstudiante;
import tudai.arquitecturaweb.entity.Direccion;
import tudai.arquitecturaweb.entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Consultas {

    static EntityManager em;

    public static void main(String[] args) throws IOException {
        //El nombre 'Example'  se cargó en el xml persistence-unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        em = emf.createEntityManager();
        // iniciar una transaccion para empezar a guardar datos
        em.getTransaction().begin();


        //Alta direccion
        /*Direccion direccion = new Direccion(18,"TANDIL","9 DE JULIO 558");
        em.persist(direccion);
        // Alta estudiante
        Estudiante estudiante = new Estudiante(23, "PAULA","GARCIA", 18, "femenino",36555252,direccion,1212);
        em.persist(estudiante);*/
        Estudiante estudiante = (Estudiante) em.createQuery("SELECT e FROM Estudiante e WHERE e.id= :estudiante").setParameter("estudiante",23).getResultList().get(0);

        // Matricular el estudiante a una carrera
        Carrera carrera = (Carrera) em.createQuery("SELECT c FROM Carrera c WHERE e.name=:nombreCarrera").setParameter("nombreCarrera","Ingenieria de sistemas").getResultList().get(0);
        CarreraEstudiante ce = new CarreraEstudiante()


        //commiteamos la transaccion
        em.getTransaction().commit();
        //cerramos el entity manager & factory
        em.close();
        emf.close();

    }

}
