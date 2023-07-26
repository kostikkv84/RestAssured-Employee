package Tests.GET;

import BaseClasses.WorkMethods;
import PojoClasses.EmployeeStatusPojo.EmployeeStatusResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEmployeeStatus_Tests extends Specifications {

    /**
     * Получение всех статусов сотрудников из справочника - ADMIN:
     */
    @Test
    public void getAllEmployeeStatusTypes_admin() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<EmployeeStatusResponse> response = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(URL + "/employee-status")
                .then().log().all()
                .extract().body().jsonPath().getList("", EmployeeStatusResponse.class);

        Assert.assertEquals(response.size(), 11, "Киличество типов статусов сотрудника больше или меньше 10!");
    }

    /**
     * Получение всех статусов сотрудников из справочника - USER:
     */
    @Test
    public void getAllEmployeeStatusTypes_user() {
        installSpecification(requestSpec(URL), specResponseOK200());
        List<EmployeeStatusResponse> response = given()
                .header("Authorization", "Bearer " + Specifications.tokenUser)
                .when()
                .get(URL + "/employee-status")
                .then().log().all()
                .extract().body().jsonPath().getList("", EmployeeStatusResponse.class);

        Assert.assertEquals(response.size(), 11, "Киличество типов статусов сотрудника больше или меньше 11!");
    }

    /**
     * Успешное получение одного статуса сотрников по ID статуса
     */
    @Test
    public void getEmployeeStatusOnID_Exist() {
        installSpecification(requestSpec(URL), specResponseOK200());
        EmployeeStatusResponse response = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(URL + "/employee-status/4")
                .then()//.log().all()
                .extract().body().as(EmployeeStatusResponse.class);

        //  response.getContent().stream().forEach(x -> Assert.assertTrue(x.get().contains(x.getId().toString())));
        Assert.assertEquals(response.getId(), 4, "Статус с указанным ID не найден!");
        Assert.assertEquals(response.getEmployeeStatus(), "ИС: в процессе", "Наименование статуса не получено!");
    }

    //--------------------------------------------------------------
    @AfterClass
    public void deleteExtraEmployeeStatus() {
        WorkMethods.deleteExtraEmployeeStatus(URL);
    }
}
