package PojoClasses.EmployeeStatusPojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeStatusResponse {

      private Integer id;
      private String employeeStatus;

    public EmployeeStatusResponse() {super();
    }
}
