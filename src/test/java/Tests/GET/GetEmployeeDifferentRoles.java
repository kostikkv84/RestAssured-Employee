package Tests.GET;

import BaseClasses.WorkMethods;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.Specifications;

import static io.restassured.RestAssured.given;

public class GetEmployeeDifferentRoles extends Specifications {

    /**
     * Получение списка сотрудников - auth User
     */
    @Test
    public void getAllEmployeeUser() {
        installSpecification(requestSpec(URL), specResponseError403());
        given()
                .header("Authorization", "Bearer " + Specifications.tokenUser)
                .when()
                .get(URL + "/employee")
                .then().log().all();
    }

    @Test
    public void getAllEmployeeKadry() {
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response = WorkMethods.getAllEmployee(URL, tokenKadry);
        Assert.assertTrue(response.getTotal() > 100);
    }

    @Test
    public void getAllEmployeeBuhgalter() {
        installSpecification(requestSpec(URL), specResponseOK200());
        Root response = WorkMethods.getAllEmployee(URL, tokenBuhgalter);
        Assert.assertTrue(response.getTotal() > 100);
    }


}
