package PojoClasses.EmployeeContactPOJO;


import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

@Getter
@Setter
public class EmployeeContactResponse {

    private int id;
    private String phone;
    private String telegram;
    private String chatTelegramId;
    private String skype;
    private String email;
    private Employee employee;

    public EmployeeContactResponse(int id, String phone, String telegram, String chatTelegramId, String skype, String email, Employee employee) {
        this.id = id;
        this.phone = phone;
        this.telegram = telegram;
        this.chatTelegramId = chatTelegramId;
        this.skype = skype;
        this.email = email;
        this.employee = employee;
    }


    public EmployeeContactResponse() {super();
    }

    public static EmployeeContactResponse createContacts(String url, String token, EmployeeContactRequest requestBody){
        EmployeeContactResponse response = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee-contact")
                .then()
                .log().all()
                .extract().body().as(EmployeeContactResponse.class);
        return response;
    }

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
                .extract().body().jsonPath().getList("", ErrorResponse.class);
        return error;
    }

    /**
     * Тест Json Schema
     */
    public static void addContactSchemaTest(String url, String token, EmployeeContactRequest requestBody) {


    }
}
