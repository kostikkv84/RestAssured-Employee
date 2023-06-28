package PojoClasses.CreateNewEmployeePOJO;

import PojoClasses.GetEmployeeResponsePOJO.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;

@Setter
@Getter
public class CreateNewEmployeeResponse {
    private int id;
    private String name;
    private String surname;
    private String middleName;
    private String birthDate;
    private String employmentDate;
    private String dismissalDate;
    private String avatar;
    private String comment;
    private String fullAddress;
    private Mentor mentor;
    private WorkFormat workFormat;
    private Position position;
    private int curriculumVitaeId;
    private Grade grade;
    private EmployeeStatus employeeStatus;
    private Location location;
    private MainDepartment mainDepartment;
    private EmploymentType employmentType;

    public CreateNewEmployeeResponse() {super();
    }

    public CreateNewEmployeeResponse(int id, String name, String surname, String middleName, String birthDate, String employmentDate, String dismissalDate, String avatar, String comment, String fullAddress, Mentor mentor, WorkFormat workFormat, Position position, int curriculumVitaeId, Grade grade, EmployeeStatus employeeStatus, Location location, MainDepartment mainDepartment, EmploymentType employmentType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;
        this.avatar = avatar;
        this.comment = comment;
        this.fullAddress = fullAddress;
        this.mentor = mentor;
        this.workFormat = workFormat;
        this.position = position;
        this.curriculumVitaeId = curriculumVitaeId;
        this.grade = grade;
        this.employeeStatus = employeeStatus;
        this.location = location;
        this.mainDepartment = mainDepartment;
        this.employmentType = employmentType;
    }


    /**
     * Отправка запроса на создание сотрудника
     */
    public static CreateNewEmployeeResponse createEmployeeSuccess(String url, String token, CreateNewEmployeeRequest requestBody) {

        CreateNewEmployeeResponse createdEmployee = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee")
                .then().log().all()
                .extract().body().as(CreateNewEmployeeResponse.class);
        System.out.println("Создан " + createdEmployee.getId());
        return createdEmployee;
    }

    /**
     * Отправка запроса на создание сотрудника
     */
    public static CreateNewEmployeeResponse patchEmployeeSuccess(String url, String token, CreateNewEmployeeRequest requestBody, Integer id) {

        CreateNewEmployeeResponse patchEmployee = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(url + "/employee/"+id)
                .then()
                .log().all()
                .extract().body().as(CreateNewEmployeeResponse.class);
        return patchEmployee;
    }


}
