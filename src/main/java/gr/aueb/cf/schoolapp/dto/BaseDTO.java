package gr.aueb.cf.schoolapp.dto;

import java.security.PublicKey;

public class BaseDTO {
    //we will need id in updates not in inserts
    private Integer id;

    public BaseDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
