package Tests.POST;

import BaseClasses.StringGenerate;
import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import spec.Specifications;

public class CreateEmployeeNameTests extends Specifications {

    //------------------Name --------------------------

    /**
     * Создание сотрудника - Имя сотрудника = null
     */
    @Test
    public void createEmployeeNameNull() {
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployeeNameNull("TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 3, 1, 3, 2, 1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "Поле name: must not be blank");
    }

    /**
     * Создание сотрудника - Имя сотрудника = blank (" ")
     */
    @Test
    public void createEmployeeNameBlank() {
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployee(" ", "TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 3, 1, 3, 2, 1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "Поле name: must not be blank");
    }

    /**
     * Создание сотрудника - Имя сотрудника = 255
     */
    @Test
    public void createEmployeeName255() {
        installSpecification(requestSpec(URL), specResponseOK201());
        String name = StringGenerate.RandomString(255);

        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployee(name, "TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 3, 1, 3, 2, 1,
                2);

        CreateNewEmployeeResponse response = CreateNewEmployeeResponse.createEmployeeSuccess(URL, token, requestBody);
        Assert.assertEquals(response.getName(), name, "Имя не совпадает");
    }

    /**
     * Создание сотрудника - Имя сотрудника = 256
     */
    @Test
    public void createEmployeeName256() {
        installSpecification(requestSpec(URL), specResponseError400());
        String name = StringGenerate.RandomString(256);

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee(name, "Test", "Test", "26.08.1984", "03.03.2023", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);

        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDetails();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "ERROR: value too long for type character varying(255)", "Текст ошибки не получен или не совпадает");
    }
    //-----------------Surname ------------------------

    /**
     * Создание сотрудника - Фамилия сотрудника = null
     */
    @Test
    public void createEmployeeSurnameNull() {
        installSpecification(requestSpec(URL), specResponseError400());

        ErrorResponse error = new ErrorResponse();
        CreateNewEmployeeRequest request = new CreateNewEmployeeRequest();
        CreateNewEmployeeRequest requestBody = request.createEmployeeSurnameNull("TEst", "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 1, 1, 2, 2, 1,
                2);
        String errorText = error.createEmployeeErrorList(URL, token, requestBody).get(0).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "Поле surname: must not be blank");
    }


    /**
     * Создание сотрудника - Фамилия сотрудника = 255
     */
    @Test
    public void createEmployeeSurename255() {
        installSpecification(requestSpec(URL), specResponseOK201());
        String surename = StringGenerate.RandomString(255);

        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployee("TEstName", surename, "Test", "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 3, 1, 3, 2, 1,
                2);

        CreateNewEmployeeResponse response = CreateNewEmployeeResponse.createEmployeeSuccess(URL, token, requestBody);
        Assert.assertEquals(response.getSurname(), surename, "Фамилия не совпадает");
    }

    /**
     * Создание сотрудника - Фамилия сотрудника = 256
     */
    @Test
    public void createEmployeeSurename256() {
        installSpecification(requestSpec(URL), specResponseError400());
        String surename = StringGenerate.RandomString(256);

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", surename, "Test", "26.08.1984", "03.03.2023", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);

        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDetails();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "ERROR: value too long for type character varying(255)", "Текст ошибки не получен или не совпадает");
    }

    //----------------- MiddleName

    /**
     * Создание сотрудника - Фамилия сотрудника = null
     */
    @Test
    public void createEmployeeMiddleNameNull() {
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
        Assert.assertEquals(name, "Test");
    }


    @Test
    public void createEmployeeMiddleNameEmpty() {
        installSpecification(requestSpec(URL), specResponseOK201());

        CreateNewEmployeeResponse response = new CreateNewEmployeeResponse();

        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name("Test")
                .surname("Test")
                .middleName(" ")
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
        Assert.assertEquals(name, "Test");
    }

    /**
     * Создание сотрудника - Фамилия сотрудника = 255
     */
    @Test
    public void createEmployeeMiddleName255() {
        installSpecification(requestSpec(URL), specResponseOK201());
        String middleName = StringGenerate.RandomString(255);

        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.createEmployee("TEstName1", "surename", middleName, "01.05.1990", "01.05.1990",
                "", "Http://url_avatar.jpg", "Some comment", "Russia, Yalta", 2,
                2, 2, 3, 1, 3, 2, 1,
                2);

        CreateNewEmployeeResponse response = CreateNewEmployeeResponse.createEmployeeSuccess(URL, token, requestBody);
        Assert.assertEquals(response.getMiddleName(), middleName, "Отчество не совпадает");
    }

    /**
     * Создание сотрудника - Фамилия сотрудника = 256
     */
    @Test
    public void createEmployeeMiddleName256() {
        installSpecification(requestSpec(URL), specResponseError400());
        String middleName = StringGenerate.RandomString(256);

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", middleName, "Test", "26.08.1984", "03.03.2023", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);

        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDetails();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "ERROR: value too long for type character varying(255)", "Текст ошибки не получен или не совпадает");
    }



    //------------------------------------------
    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        WorkMethods.deleteAllExtraEmployee(URL, token);
    }

}
