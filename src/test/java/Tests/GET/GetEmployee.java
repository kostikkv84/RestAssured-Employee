package Tests.GET;

import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEmployee extends Specifications {

    /**
     * Получение списка всех сотрудников, auth Админ
     */
    @Test
    public void getAllEmployeeSuccessAdmin(){
        installSpecification(requestSpec(URL), specResponseOK200());
        SoftAssert softAssert = new SoftAssert();
        Root employeers = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(URL + "/employee")
                .then()
                //.log().all()
                .extract().body().as(Root.class);
        // ---- проверка количества возвращенных в ответе сотрудников
        Integer quantity = employeers.getTotal();
        Assert.assertEquals(employeers.getContent().size(),quantity, "Количество полученных записей не совпадает с значениев параметре Total");
        // проверка через SoftAssert
        employeers.getContent().stream().forEach(x -> softAssert.assertTrue(x.getMainDepartment().getId().equals(1)));

       // softAssert.assertTrue(employeers.getContent().get(0).getName());

    }

    /**
     * Получение списка сотрудников - auth User
     */
    @Test
    public void getAllEmployeeUser(){
         installSpecification(requestSpec(URL), specResponseError403());
        given()
                .header("Authorization", "Bearer " + Specifications.tokenUser)
                .when()
                .get(URL + "/employee")
                .then().log().all();
    }

    /**
     * Проверка схемы Employee - полученного запросом Get
     */
    @Test
    public void getEmployeeCheckJsonSchema() {
        installSpecification(requestSpec(URL), specResponseOK200());
        RestAssured.given().header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(URL + "/employee/2")
                .then().log().all()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("employeeSchema.json"));
    }

    //-------------- Size - tests ----------------------------------------
    /**
     * Получение сиписка из 5 первых сотрудников - size = 5
     */
    @Test
    public void getAllEmployeeSizeTest(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Root employeers =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","5")
                .when()
                .get(URL + "/employee")
                .then().log().all()
                .extract().body().as(Root.class);
        Assert.assertEquals(employeers.getContent().size(),5, "Количество запрошенных данных сотрудников не совпадает с ожидаемым.");

    }

    /**
     * Получение списка сотрудников - size = 0
     */
    @Test
    public void getAllEmployeeSize0(){
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","0")
                .when()
                .get(URL + "/employee")
                .then().log().all()
                .extract().body().jsonPath().getList("", ErrorResponse.class);
        Assert.assertEquals(error.get(0).getDescription(),"Поле size - Значение должно быть меньше 500 и больше 1", "Ожидаемая ошибка не получена.");
    }

    /**
     * Получение списка сотрудников - size = 1
     */
    @Test
    public void getAllEmployeeSize1(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","1")
                .when()
                .get(URL + "/employee")
                .then()//.log().all()
                .extract().body().as(Root.class);

        Assert.assertEquals(response.getContent().size(), 1, "Не совпадает ожидаемое количество запрошенных данных по сотрудникам");
    }

    /**
     * Получение списка сотрудников - size = 500
     */
    @Test
    public void getAllEmployeeSize500(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","500")
                .when()
                .get(URL + "/employee")
                .then()//.log().all()
                .extract().body().as(Root.class);
        Integer count = response.getTotal();

        Assert.assertEquals(response.getContent().size(), count, "Количество найденных сотрудников не совпадает с общим значением в параметре Total");
    }

    /**
     * Получение списка сотрудников - size = 501
     */
    @Test
    public void getAllEmployeeSize501(){
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","501")
                .when()
                .get(URL + "/employee")
                .then().log().all()
                .extract().body().jsonPath().getList("", ErrorResponse.class);
        Assert.assertEquals(error.get(0).getDescription(),"Поле size - Значение должно быть меньше 500 и больше 1", "Ожидаемая ошибка не получена.");
    }

    //---------- Page - tests ------------------------------------

    /**
     * Проверка параметра Page.
     */
    @Test
    public void getAllEmployeePageTest(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Root employeers =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("size","5")
                .param("page","1")
                .when()
                .get(URL + "/employee")
                .then().log().all()
                .extract().body().as(Root.class);
        // ------ проверка, id первой записи второй страницы
        Assert.assertTrue(employeers.getContent().get(0).getId()>5, "Первая запись второй страницы поиска не больше 5");

    }

}
