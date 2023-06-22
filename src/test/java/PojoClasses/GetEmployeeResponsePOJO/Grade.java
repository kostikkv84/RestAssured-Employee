package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Grade {
    private Integer id;
    private String value;

    public Grade() {super();
    }

    public Grade(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
