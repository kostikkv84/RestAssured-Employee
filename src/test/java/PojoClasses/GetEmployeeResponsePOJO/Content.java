package PojoClasses.GetEmployeeResponsePOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String birthDate;
    private String employmentDate;
    private MainDepartment mainDepartment;
    private Position position;
    private Grade grade;
    private EmployeeStatus employeeStatus;
    private WorkFormat workFormat;
    private String employmentType;
    private Salary salary;
    private String city;
    private EmployeeContact employeeContact;

    public Content() {super();
    }

    public Content(Integer id, String name, String surname, String middleName, String birthDate, String employmentDate, MainDepartment mainDepartment, Position position, Grade grade, EmployeeStatus employeeStatus, WorkFormat workFormat, String employmentType, Salary salary, String city, EmployeeContact employeeContact) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;
        this.mainDepartment = mainDepartment;
        this.position = position;
        this.grade = grade;
        this.employeeStatus = employeeStatus;
        this.workFormat = workFormat;
        this.employmentType = employmentType;
        this.salary = salary;
        this.city = city;
        this.employeeContact = employeeContact;
    }
}
