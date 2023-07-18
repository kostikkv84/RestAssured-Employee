package Tests.PATCH;

import BaseClasses.StringGenerate;
import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import spec.Specifications;

import java.math.BigInteger;

public class PatchParams extends Specifications {

    Integer employeeID;

    // ------------- MentorId ---------------------
    /**
     * MentorId - не существует
     */
    @Test
    public void patchEmployee_mainDepartmentId_NotExist() {
        installSpecification(requestSpec(URL), specResponseError404());

        String requestBody = WorkMethods.RequestBodyPatchInt("mainDepartmentId", Integer.parseInt(WorkMethods.RandomNumber(4)));
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Department not found, id: "), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - равно 0
     */
    @Test
    public void patchEmployee_mainDepartmentId_0() {
        installSpecification(requestSpec(URL), specResponseError404());
        String requestBody = WorkMethods.RequestBodyPatchInt("mainDepartmentId", 0);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertEquals(errorText,"Department not found, id: 0", "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - большое число
     */
    @Test
    public void patchEmployee_mainDepartmentId_BigNumber() {
        installSpecification(requestSpec(URL), specResponseError400());

        BigInteger number = new BigInteger("45646589879865413258");
        String requestBody = WorkMethods.RequestBodyPatchBigInt("mainDepartmentId", number);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();

        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("out of range of long"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом String
     */
    @Test
    public void patchEmployee_mainDepartmentId_String() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchStr("mainDepartmentId", WorkMethods.RandomString(5));
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from String"), "Текст ошибки не получен или не совпадает");
    }

    //----------- MainDepartmentId ----------------------------------------------------

    /**
     * MentorId - не существует
     */
    @Test
    public void patchEmployee_MentorId_NotExist() {
        installSpecification(requestSpec(URL), specResponseError404());
        String requestBody = WorkMethods.RequestBodyPatchInt("mentorId", 777);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Работник с id 777 не найден"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - равно 0
     */
    @Test
    public void patchEmployee_MentorId_0() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchInt("mentorId", 0);
        String errorText = ErrorResponse.patchEmployeeErrorList(URL, token, requestBody, employeeID).get(0).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertEquals(errorText,"Поле mentorId: must be greater than 0", "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - большое число
     */
    @Test
    public void patchEmployee_MentorId_BigNumber() {
        installSpecification(requestSpec(URL), specResponseError400());

        BigInteger number = new BigInteger("45646589879865413258");
        String requestBody = WorkMethods.RequestBodyPatchBigInt("mentorId", number);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();

        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("out of range of long"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом String
     */
    @Test
    public void patchEmployee_MentorId_String() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchStr("mentorId", WorkMethods.RandomString(5));
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from String"), "Текст ошибки не получен или не совпадает");
    }


    //----------- постусловия ---------- очистка БД от тестовых данных-----------------
    @BeforeClass
    public void createEmployeeForTests() {
        installSpecification(requestSpec(URL), specResponseOK201());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestEmployee", "TestEmployee", "TestEmployee", "26.08.1988", "03.03.2021", "", "http://ava_contact.jpg", "Its me_ contact", "Crimea, Yalta, 298600 - Contact", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        CreateNewEmployeeResponse createdEmployee = CreateNewEmployeeResponse.createEmployeeSuccess(URL, token, bodyRequest);
        employeeID = createdEmployee.getId();
        System.out.println("Создан сотрудник с ID: " + employeeID);
    }

    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        WorkMethods.deleteAllExtraEmployee(URL, token);
    }
}
