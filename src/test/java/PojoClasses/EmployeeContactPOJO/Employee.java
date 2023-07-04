package PojoClasses.EmployeeContactPOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private int id;
    private String name;
    private String surname;
    private String middleName;

    public Employee(int id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public Employee() {
    }
}
