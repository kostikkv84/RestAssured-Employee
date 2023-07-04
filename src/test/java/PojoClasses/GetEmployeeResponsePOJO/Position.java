package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private Integer id;
    private String value;

    public Position() {
        super();
    }

    public Position(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
