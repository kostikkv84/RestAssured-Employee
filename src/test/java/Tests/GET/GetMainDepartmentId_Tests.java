package Tests.GET;

import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetMainDepartmentId_Tests extends Specifications {


    /**
     * Получение сотрудников по ID департамента - mainDepartmentId = 2
     */
    @Test
    public void getEmployeeListOnMainDepartmentId() {
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("mainDepartmentId", "2")
                .when()
                .get(URL + "/employee/filter")
                .then().log().all()
                .extract().body().as(Root.class);
        response.getContent().stream().forEach(x -> Assert.assertEquals(x.getMainDepartment().getId(), 2, "Не совпадает ожидаемый ID епартамента в записи сотрудников"));
        //Assert.assertEquals(response.getContent().get(0).getId(), 2, "СОтрудник с указанным ID не найден!");
    }

    /**
     * Получение сотрудников по ID департамента - mainDepartmentId = 0
     */
    @Test
    public void getEmployeeListOnMainDepartmentId_0() {
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("mainDepartmentId", "0")
                .when()
                .get(URL + "/employee/filter")
                .then().log().all()
                .extract().body().jsonPath().getList("", ErrorResponse.class);
        //  response.getContent().stream().forEach(x -> Assert.assertEquals(x.getMainDepartment().getId(),2, "Не совпадает ожидаемый ID епартамента в записи сотрудников"));
        Assert.assertEquals(error.get(0).getDescription(), "Поле mainDepartmentId - must be greater than 0", "Ожидаемая ошибка не получена");
    }

    /**
     * Получение сотрудников по ID департамента - mainDepartmentId = String
     */
    @Test
    public void getEmployeeListOnMainDepartmentId_String() {
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("mainDepartmentId", "text")
                .when()
                .get(URL + "/employee/filter")
                .then().log().all()
                .extract().body().jsonPath().getList("", ErrorResponse.class);
        //  response.getContent().stream().forEach(x -> Assert.assertEquals(x.getMainDepartment().getId(),2, "Не совпадает ожидаемый ID епартамента в записи сотрудников"));
        Assert.assertTrue(error.get(0).getDescription().contains("NumberFormatException: For input string:"), "Ожидаемая ошибка не получена!");
    }


}
