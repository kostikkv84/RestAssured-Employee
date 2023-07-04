package Tests.GET;

import PojoClasses.EmployeeContactPOJO.EmployeeContactResponse;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetContactEmployee extends Specifications {


    @Test
    public void getAllContacts(){
        installSpecification(requestSpec(URL), specResponseOK200());
        List<EmployeeContactResponse> response = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(URL + "/employee-contact")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("",EmployeeContactResponse.class);
        Assert.assertTrue(response.size()>1);
    }

}
