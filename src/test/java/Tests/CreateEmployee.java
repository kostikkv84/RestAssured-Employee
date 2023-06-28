package Tests;

import BaseClasses.StringGenerate;
import BaseClasses.WorkMethods;

import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import spec.Specifications;

import static io.restassured.RestAssured.given;

public class CreateEmployee extends Specifications {

    /**
     * Создание сотрудника - простой тест
     */
    @Test (priority = 1)
    public void addEmployee() {
        installSpecification(requestSpec(URL), specResponseOK201());
       CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("Test", "Test", "Test", "26.08.1984", "03.03.2023", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2,3,2,2,2,3);

        CreateNewEmployeeResponse createdEmployee = given()
                .header("Authorization", "Bearer "+ Specifications.token)
                .body(bodyRequest)
                .when()
                .post(URL + "/employee")
                .then().log().all()
                .extract().body().as(CreateNewEmployeeResponse.class);
        System.out.println("Создан " + createdEmployee.getId());
        Assert.assertTrue(createdEmployee.getName().contains("Test"), "Сотрудник не добавлен");
    }

    /**
     * Требуется доработка. Успешное создание сотрудника - через билдер и инкапсуляция
     * требуется доработка
     */
    @Test
    @Ignore
    public void addEmployee_builder(){
        CreateNewEmployeeResponse response = new CreateNewEmployeeResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
                .middleName("Test")
                .birthDate("01.05.1989")
                .employmentDate("03.01.2023")
                .dismissalDate("")
                .avatar("http://Avatar")
                .comment("Комментарий ...")
                .fullAddress("Crimea, Yalta")
                .mentorId(2)
                .workFormatId(1)
                .employmentTypeId(1)
                .positionId(1)
                .curriculumVitaeId(2)
                .gradeDictId(2)
                .employeeStatusId(1)
                .locationId(2)
                .mainDepartmentId(2).build();

        String name = response.createEmployeeSuccess(URL, Specifications.token, requestBody).getName();
        System.out.println("Создан: " + name);
        //  Assert.assertEquals(errorText,"Поле fullAddress: если поле не null, то должно быть не пустым");

    }

    //------ Негативные тесты Обязательные поля не указаны - Null ------------------------------------------------------------------


    /**
     * Создание сотрудника - Дата рождения = null
     */
    @Test
    public void createEmployeeBirthDateNull(){
        installSpecification(requestSpec(URL), specResponseError400());
        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest request = new CreateNewEmployeeRequest();
        CreateNewEmployeeRequest requestBody = request.createEmployeeBirthDateNull("Test", "Test", "Test", "01.02.2023",
                "01.02.2015", "Http://url_avatar.jpg", "Some comment about", "Russia, Yalta", 1,
                2, 2,3,1,3,2,1,
                2);
        String errorText = error.createEmployeeError(URL, Specifications.token, requestBody).getDescription();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,"Ошбика добавления или обновления записи в бд");
    }

    /**
     * Создание сотрудника - EmploymentDate = null
     */
    @Test
    public void createEmployeeEmploymentDateNull(){
        installSpecification(requestSpec(URL), specResponseError400());
        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest request = new CreateNewEmployeeRequest();
        CreateNewEmployeeRequest requestBody = request.createEmployeeEmploymentDateNull("Test", "Test", "Test", "01.05.1989",
                "", "Http://url_avatar.jpg", "Its me", "Russia, Yalta", 1,
                2, 2,1,1,2,2,1,
                2);
        String errorText = error.createEmployeeError(URL, Specifications.token, requestBody).getDescription();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,"Ошбика добавления или обновления записи в бд");
    }

    /**
     * Создание сотрудника - WorkFormatId = null
     * Заведен БАГ
     */
    @Test (description = "Error create WorkFormat = Null - Заведен баг - ...")
    public void createEmployeeWorkFormatIdNull_BUG(){
        installSpecification(requestSpec(URL), specResponseError400());
        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest request = new CreateNewEmployeeRequest();
        CreateNewEmployeeRequest requestBody = request.createEmployeeWorkFormatIdNull("Test", "Test", "Test", "01.05.1989",
                "03.03.2023", "", "Http://url_avatar.jpg", "Комментарий ...", "Russia, Yalta",
                2, 2,1, 1,2,2,1,
                2);

        String errorText = error.createEmployeeErrorList(URL, Specifications.token, requestBody).get(0).getDescription();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,"Поле workFormatId: must be greater than 0");
    }

    /**
     * Создание сотрудника - FullAddress = null - через builder
     */
    @Test (description = "Error - create Address = пустое значение - пробел")
    public void createEmployeeAddressNull(){
        installSpecification(requestSpec(URL), specResponseError400());
        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
                .middleName("Test")
                .birthDate("01.05.1989")
                .employmentDate("03.01.2023")
                .dismissalDate("")
                .avatar("http://Avatar")
                .comment("Комментарий ...")
                .fullAddress(" ")
                .mentorId(2)
                .workFormatId(2)
                .employmentTypeId(1)
                .positionId(1)
                .curriculumVitaeId(2)
                .gradeDictId(2)
                .employeeStatusId(1)
                .locationId(2)
                .mainDepartmentId(2).build();

        String errorText = error.createEmployeeErrorList(URL, Specifications.token, requestBody).get(0).getDescription();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,"Поле fullAddress: если поле не null, то должно быть не пустым");
    }

    @Test (description = "Error - create Address = 1001 символов")
    public void createEmployeeAddress1001Symbols(){
        installSpecification(requestSpec(URL), specResponseError400());
        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
                .middleName("Test")
                .birthDate("01.05.1989")
                .employmentDate("03.01.2023")
                .dismissalDate("")
                .avatar("http://Avatar")
                .comment("Комментарий ...")
                .fullAddress(StringGenerate.RandomString(1001))
                .mentorId(2)
                .workFormatId(2)
                .employmentTypeId(1)
                .positionId(1)
                .curriculumVitaeId(2)
                .gradeDictId(2)
                .employeeStatusId(1)
                .locationId(2)
                .mainDepartmentId(2).build();

        String errorText = error.createEmployeeError(URL, Specifications.token, requestBody).getDetails();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,"ERROR: value too long for type character varying(1000)");
    }

    @Test (description = "Success - create Address = 1000 символов")
    public void createEmployeeAddress1000Symbols(){
        installSpecification(requestSpec(URL), specResponseOK201());
        String address = StringGenerate.RandomString(1000);

        CreateNewEmployeeResponse response = new CreateNewEmployeeResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
                .middleName("Test")
                .birthDate("01.05.1989")
                .employmentDate("03.01.2023")
                .dismissalDate("")
                .avatar("http://Avatar")
                .comment("Комментарий ...")
                .fullAddress(address)
                .mentorId(2)
                .workFormatId(2)
                .employmentTypeId(1)
                .positionId(1)
                .curriculumVitaeId(2)
                .gradeDictId(2)
                .employeeStatusId(1)
                .locationId(2)
                .mainDepartmentId(2).build();

        String errorText = response.createEmployeeSuccess(URL, Specifications.token, requestBody).getFullAddress();
        System.out.println("Текст ошибки: " + errorText);
        Assert.assertEquals(errorText,address);
    }






   //-----------------------------------------------------------------------------------
   @AfterClass
   //@Test
   public void deleteEmployeeAfterTests() {
       WorkMethods.deleteEmployee(URL);
   }

    

}
