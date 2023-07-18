package PojoClasses.ErrorEmployeePOJO;

import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.EmployeeContactPOJO.EmployeeContactRequest;
import Tests.POST.CreateContactsEmployee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

@Setter
@Getter
@Builder
public class ErrorResponse {
    private String id;
    private String description;
    private String details;
    private Date timestamp;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(String id, String description, String details, Date timestamp) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.timestamp = timestamp;
    }

    // ------------------ EMPLOYEE ----------------------------
    public static List<ErrorResponse> createEmployeeErrorList(String url, String token, CreateNewEmployeeRequest requestBody) {
        List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee")
                .then()
                .log().all()
                .extract().jsonPath().getList("", ErrorResponse.class);
        return error;
    }

    public static ErrorResponse createEmployeeError(String url, String token, CreateNewEmployeeRequest requestBody) {
        ErrorResponse error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee")
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return error;
    }

    public static ErrorResponse patchEmployeeError(String url, String token, CreateNewEmployeeRequest requestBody, Integer idEmployee) {
        ErrorResponse error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(url + "/employee/" + idEmployee)
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return error;
    }

    public static ErrorResponse patchEmployeeErrorStr(String url, String token, String requestBody, Integer idEmployee) {
        ErrorResponse error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(url + "/employee/" + idEmployee)
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return error;
    }

    public static List<ErrorResponse> patchEmployeeErrorList(String url, String token, String requestBody, Integer idEmployee) {
        List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(url + "/employee/" + idEmployee)
                .then()
                .log().all()
                .extract().jsonPath().getList("", ErrorResponse.class);
        return error;
    }


  /*  public static List<ErrorResponse> patchEmployeeErrorList(String url, String token, CreateNewEmployeeRequest requestBody, Integer idEmployee) {
        List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(url + "/employee/" + idEmployee)
                .then()
                .log().all()
                .extract().jsonPath().getList("", ErrorResponse.class);
        return error;
    }*/


    //----------------- CONTACTS ----------------------------------------------
    public static ErrorResponse createContactError(String url, String token, EmployeeContactRequest requestBody) {

        ErrorResponse error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee-contact")
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return error;
    }

    public static List<ErrorResponse> createContactErrorList(String url, String token, EmployeeContactRequest requestBody) {

       List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee-contact")
                .then()
                .log().all()
                .extract().jsonPath().getList("",ErrorResponse.class);
        return error;
    }

   /* public ErrorResponse  createEmployeeErrorWorkFormatIdNull (){

        CreateNewEmployeeResponse newEmployee = new CreateNewEmployeeResponse(String url, String token, CreateNewEmployeeRequest requestBody);

        ErrorResponse error = given()
                .header("Authorization", "Bearer "+token)
                .body(newEmployee)
                .when()
                .post(url + "/employee")
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return  error;
    }

    public ErrorResponse  createEmployeeErrorPositionIdNull(String url, String token, String name,
                                              String surname, String middleName, String birthDate, String employmentDate, String dismissalDate,
                                              String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                              Integer employmentTypeId, Integer curriculumVitaeId,
                                              Integer gradeDictId, Integer employeeStatusId,
                                              Integer locationId, Integer mainDepartmentId){

        CreateNewEmployeeResponse newEmployee = new CreateNewEmployeeResponse(name, surname, middleName,
                birthDate, employmentDate, dismissalDate, avatar, comment, fullAddress, mentorId, workFormatId,
                employmentTypeId, curriculumVitaeId,gradeDictId,employeeStatusId,locationId,mainDepartmentId);

        ErrorResponse error = given()
                .header("Authorization", "Bearer "+token)
                .body(newEmployee)
                .when()
                .post(url + "/employee")
                .then()
                .log().all()
                .extract().body().as(ErrorResponse.class);
        return  error;
    }*/

}
