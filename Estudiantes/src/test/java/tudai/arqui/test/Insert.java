package tudai.arqui.test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tudai.arquitecturaweb.entity.Carrera;
import tudai.arquitecturaweb.entity.CarreraEstudiante;
import tudai.arquitecturaweb.entity.Direccion;
import tudai.arquitecturaweb.entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Insert {

    public static void main(String[] args) throws IOException {
        //El nombre 'Example'  se cargó en el xml persistence-unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        // iniciar una transaccion para empezar a guardar datos
        em.getTransaction().begin();

        // Lectura de archivos csv y inserts en las respectivas tablas
         CSVParser parser;
        parser = readFile("direcciones");
        for (CSVRecord row : parser) {
            em.persist(new Direccion(Integer.parseInt(row.get("id")),row.get("ciudad"), row.get("calle")));

        }
        parser = readFile("carreras");
        for (CSVRecord row : parser) {
            em.persist(new Carrera(Integer.parseInt(row.get("id")),row.get("name")));
        }

        parser = readFile("estudiantes");
        for (CSVRecord row : parser) {
            int direccion = Integer.parseInt(row.get("direccion"));
            Direccion direccions = (Direccion) em.createQuery("SELECT d FROM Direccion d WHERE d.id= :direccion").setParameter("direccion",direccion).getResultList().get(0);
            em.persist(new Estudiante(Integer.parseInt(row.get("id")),row.get("name"), row.get("lastName"), Integer.parseInt(row.get("age")),
                    row.get("gender"), Integer.parseInt(row.get("identificationNumber")),
                    direccions, Integer.parseInt(row.get("studentNumber"))));
        }

        parser = readFile("estudiantes-carreras");
        for (CSVRecord row : parser) {
            int carreraId = Integer.parseInt(row.get("carreraId"));
            int estudianteId = Integer.parseInt(row.get("estudianteId"));
            Carrera carrera = (Carrera) em.createQuery("SELECT c FROM Carrera c WHERE c.id= :carrera").setParameter("carrera",carreraId).getResultList().get(0);
            Estudiante estudiante = (Estudiante) em.createQuery("SELECT e FROM Estudiante e WHERE e.id= :estudiante").setParameter("estudiante",estudianteId).getResultList().get(0);
            Timestamp startDate = getDate(row.get("start_date"));
            Timestamp finishDate = (row.get("finish_date").equals("")) ? null : getDate(row.get("finish_date"));
            CarreraEstudiante ce = new CarreraEstudiante(estudiante,carrera,startDate,finishDate);
            em.persist(ce);
        }
        //commiteamos la transaccion
        em.getTransaction().commit();
        //cerramos el entity manager & factory
        em.close();
        emf.close();
    }

    public static CSVParser readFile(String fileName) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(System.getProperty("user.dir") + "\\csv\\" + fileName + ".csv"));
        return parser;
    }

    public static Timestamp getDate(String date) {
        Calendar calendar = Calendar.getInstance();
        String fecha[] = date.split("/");
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(fecha[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(fecha[1]));
        calendar.set(Calendar.YEAR,Integer.parseInt(fecha[2]));
        Date now = calendar.getTime();
        return new Timestamp(now.getTime());
    }
}
