package Tests.POST;

import PojoClasses.GetEmployeeResponsePOJO.Content;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class CreateEmployeeNameTests extends Specifications {

    //------------------Name --------------------------
    /**
     * Создание сотрудника - Имя сотрудника = null
     */
    @Test
    public void createEmployeeNameNull(){
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployeeNameNull("TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2,3,1,3,2,1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Поле name: must not be blank");
    }

    /**
     * Создание сотрудника - Имя сотрудника = blank (" ")
     */
    @Test
    public void createEmployeeNameBlank(){
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployee(" ","TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2,3,1,3,2,1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Поле name: must not be blank");
    }

    //-----------------Surname ------------------------
    /**
     * Создание сотрудника - Фамилия сотрудника = null
     */
    @Test
    public void createEmployeeSurnameNull(){
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest request = new CreateNewEmployeeRequest();
        CreateNewEmployeeRequest requestBody = request.createEmployeeSurnameNull("TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2,1,1,2,2,1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText,"Поле surname: must not be blank");
    }

    //----------------- MiddleName
    /**
     * Создание сотрудника - Фамилия сотрудника = null
     */
    @Test
    public void createEmployeeMiddleNameNull(){
        installSpecification(requestSpec(URL), specResponseOK201());

        CreateNewEmployeeResponse response = new CreateNewEmployeeResponse();

        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
            //    .middleName("Test")
                .birthDate("01.05.1989")
                .employmentDate("03.01.2023")
                .dismissalDate("")
                .avatar("http://Avatar")
                .comment("Комментарий ...")
                .fullAddress("address")
                .mentorId(2)
                .workFormatId(2)
                .employmentTypeId(1)
                .positionId(1)
                .curriculumVitaeId(2)
                .gradeDictId(2)
                .employeeStatusId(1)
                .locationId(2)
                .mainDepartmentId(2).build();

        String name = response.createEmployeeSuccess(URL, token, requestBody).getName();
        System.out.println(name);
        Assert.assertEquals(name,"Test");
    }

    //------------------------------------------
    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        Specifications.deleteEmployee(URL);
    }

}
