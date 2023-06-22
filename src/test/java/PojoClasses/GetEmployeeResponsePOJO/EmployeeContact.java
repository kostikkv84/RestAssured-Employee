package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeContact {
    private Integer id;
    private String phone;
    private String telegram;
    private String email;

    public EmployeeContact() {super();
    }

    public EmployeeContact(Integer id, String phone, String telegram, String email) {
        this.id = id;
        this.phone = phone;
        this.telegram = telegram;
        this.email = email;
    }
}
