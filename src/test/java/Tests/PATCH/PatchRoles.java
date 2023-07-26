package Tests.PATCH;

import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import spec.Specifications;

/**
 * Проверка измения записи под разными пользователями
 */
public class PatchRoles extends Specifications {

    Integer employeeID;

    /**
     * Изменение параметра - USER
     */
    @Test
    public void changeEmployeeUser() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("comment", "New comment - Patch");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenUser, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
    }

    /**
     * Изменение параметра - KADRY
     */
    @Test
    public void changeEmployeeKadry() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("avatar", "http://avatar.jpg");
        // Выполняем запрос на изменение записи.
        String avatar = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.tokenKadry, requestBody, employeeID).getAvatar();
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(avatar, "http://avatar.jpg", "Аватар не изменился");
    }

    /**
     * Изменение параметра - Buhgalter
     */
    @Test
    public void changeEmployeeBuh() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("name", "New_Name");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenBuhgalter, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
    }

    /**
     * Изменение параметра - Sales
     */
    @Test
    public void changeEmployeeSales() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("avatar", "New_Ava");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenSales, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
    }

    /**
     * Изменение параметра - RN
     */
    @Test
    public void changeEmployeeRN() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("surname", "New_Surname");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenRN, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
    }

    /**
     * Изменение параметра - Account
     */
    @Test
    public void changeEmployeeAccount() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("fullAddress", "Address_new");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenAccount, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
    }

    /**
     * Изменение параметра - Top
     */
    @Test
    public void changeEmployee() {
        installSpecification(requestSpec(URL), specResponseError403());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("birthDate", "04.05.2000");
        // получаем проверку на пустое тело ответа при 403 коде.
        Boolean result = CreateNewEmployeeResponse.postEmployeeSuccess403(URL, Specifications.tokenTop, requestBody, employeeID);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(result, true, "Тело ответа не пустое");
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
