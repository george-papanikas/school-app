package gr.aueb.cf.schoolapp.dao.exceptions;

public class TeacherDAOException extends Exception {
    private static final long serialVersionUID = 123456L; //exceptions made by us are serializable

    public TeacherDAOException(String s) {
        super(s);
    }
}