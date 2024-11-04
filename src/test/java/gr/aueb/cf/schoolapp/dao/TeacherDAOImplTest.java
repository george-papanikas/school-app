package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOImplTest {

    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    void setUp() throws TeacherDAOException {
        createDummyTeachers();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Bob");
        teacher.setLastname("Marley");
        teacherDAO.insert(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname("Marley");
        assertEquals(1, teachers.size());
    }

    @Test
    void update() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setFirstname("Anna2");
        teacher.setLastname("Kefala2");
        teacherDAO.update(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname(teacher.getLastname());
        assertEquals(teachers.get(0).getFirstname(), "Anna2");
    }

    @Test
    void delete() throws TeacherDAOException {
        int id = 1;
        teacherDAO.delete(id);

        Teacher teacher = teacherDAO.getById(1);

        assertNull(teacher);
    }

    @Test
    void getByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getByLastname("Kape");
        assertEquals(2, teachers.size());
    }

    @Test
    void getById() throws TeacherDAOException {
        int id = 4;
        Teacher teacher = teacherDAO.getById(id);
        assertNotNull(teacher);
        assertEquals("Kapetidis", teacher.getLastname());
    }

    public static void createDummyTeachers() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Athanasios");
        teacher.setLastname("Androutsos");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Kefala");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Makis");
        teacher.setLastname("Kapetis");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("John");
        teacher.setLastname("Kapetidis");
        teacherDAO.insert(teacher);
    }
}