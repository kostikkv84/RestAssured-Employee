package PojoClasses.CreateNewEmployeePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor {
    private int id;
    private String name;
    private String surname;
    private String middleName;

    public Mentor() {super();
    }

    public Mentor(int id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }
}
