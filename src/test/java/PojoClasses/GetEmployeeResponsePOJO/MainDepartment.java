package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDepartment {
    private Integer id;
    private String name;

    public MainDepartment() {
        super();
    }

    public MainDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
