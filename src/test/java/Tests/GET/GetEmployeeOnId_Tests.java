package Tests.GET;

import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import PojoClasses.GetEmployeeResponsePOJO.Total;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetEmployeeOnId_Tests extends Specifications {

    /**
     * Успешное получение сотрудника - ID = 2
     */
    @Test
    public void getEmployeeOnID_Exist(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("id","2")
                .when()
                .get(URL + "/employee/filter")
                .then()//.log().all()
                .extract().body().as(Root.class);

      //  response.getContent().stream().forEach(x -> Assert.assertTrue(x.get().contains(x.getId().toString())));
        Assert.assertEquals(response.getContent().get(0).getId(), 2, "СОтрудник с указанным ID не найден!");
    }

    /**
     * Успешное получение сотрудника - ID - нет в БД
     * Ответ с количеством 0
     */
    @Test
    public void getEmployeeOnID_notExist(){
        installSpecification(requestSpec(URL), specResponseOK200());
        Total response =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("id","2122546")
                .when()
                .get(URL + "/employee/filter")
                .then().log().all()
                .extract().body().as(Total.class);

        //  response.getContent().stream().forEach(x -> Assert.assertTrue(x.get().contains(x.getId().toString())));
        Assert.assertEquals(response.getTotal(), 0, "Сотрудник найден! Что не соответствует ожидаемому результату!");
    }

    /**
     * Ошибка получения сотрудника - если ID = 0
     */
    @Test
    public void getEmployeeOnID_0(){
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("id","0")
                .when()
                .get(URL + "/employee/filter")
                .then()//.log().all()
                .extract().body().jsonPath().getList("",ErrorResponse.class);
        //  response.getContent().stream().forEach(x -> Assert.assertTrue(x.get().contains(x.getId().toString())));
        Assert.assertEquals(error.get(0).getDescription(), "Поле id - must be greater than 0", "Ожидаемая ошибка не получена!");
    }

    /**
     * Ошибка получения сотрудника - если ID = String
     */
    @Test
    public void getEmployeeOnID_String(){
        installSpecification(requestSpec(URL), specResponseError400());
        List<ErrorResponse> error =given()
                .header("Authorization", "Bearer " + Specifications.token)
                .param("id","Text")
                .when()
                .get(URL + "/employee/filter")
                .then()//.log().all()
                .extract().body().jsonPath().getList("",ErrorResponse.class);
        //  response.getContent().stream().forEach(x -> Assert.assertTrue(x.get().contains(x.getId().toString())));
        Assert.assertTrue(error.get(0).getDescription().contains("exception is java.lang.NumberFormatException: For input string:"), "Ожидаемая ошибка не получена!");
    }

}
