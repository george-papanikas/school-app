package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

public class TeacherNotFoundException extends Exception{
    private static final long serialVersionUID = 123456L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + " was not found");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
