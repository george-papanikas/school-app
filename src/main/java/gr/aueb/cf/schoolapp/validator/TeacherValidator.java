package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.HashMap;
import java.util.Map;

//utility class
public class TeacherValidator {


    private TeacherValidator() {}

    public static Map<String, String> validate(TeacherInsertDTO dto) {
        Map<String, String> errors = new HashMap<>(); //local variable
        // initialized every time validate is used so as list is every time empty
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }

        return errors;
    }
}
