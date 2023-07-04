package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkFormat {
    private Integer id;
    private String value;

    public WorkFormat() {
        super();
    }

    public WorkFormat(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
