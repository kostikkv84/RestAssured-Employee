package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeStatus {
    private Integer id;
    private String employeeStatus;

    public EmployeeStatus() {
        super();
    }

    public EmployeeStatus(Integer id, String employeeStatus) {
        this.id = id;
        this.employeeStatus = employeeStatus;
    }
}
