package Tests.PATCH;

import BaseClasses.GetDate;
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


// -------- Перепроверить и переделать тесты!
public class PatchEmployeeDateTests extends Specifications {

    Integer employeeID;

    // ------------- DIsmissalDate ---------------------
    /**
     * Дата увольнения - ранее даты приема на работу
     */
    @Test (description = "Заведен BUG - Дата увольнения - ранее даты приема на работу")
    public void createEmployeeDismissalDateInPast() {
        installSpecification(requestSpec(URL), specResponseError400());

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "26.08.2000", "26.08.2023", "30.05.2023", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.patchEmployeeError(URL, token, bodyRequest, employeeID).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу число
     * BUG -
     */
    @Test (description = "BUG - дата увольнения - число, заведен баг, так как нет ошибки")
    public void createEmployeeEmploymentDateNumber() {
        installSpecification(requestSpec(URL), specResponseError400());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "26.08.2000", GetDate.dateToday(), GetDate.dateYesterday(), "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу String
     */
    @Test (description = "BUG - дата увольнения - String-text, заведен баг, так как нет ошибки")
    public void createEmployeeEmploymentDateText() {
        installSpecification(requestSpec(URL), specResponseError400());
        String text = StringGenerate.RandomString(5);
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "26.08.2000", GetDate.dateToday(), text, "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertEquals(errorText, "Text '" + text + "' could not be parsed at index 0", "Текст ошибки не получен или не совпадает");
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


