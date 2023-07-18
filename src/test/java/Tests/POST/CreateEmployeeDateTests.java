package Tests.POST;

import BaseClasses.GetDate;
import BaseClasses.StringGenerate;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.Specifications;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEmployeeDateTests extends Specifications {

    //-------------- BirthDate ---------------------------------------

    /**
     * Дата рождения позже даты приема на работу
     */
    @Test
    public void createEmployeeBirthDateInFuture() {
        installSpecification(requestSpec(URL), specResponseError400());

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", GetDate.dateTomorrow(), GetDate.dateToday(), "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeErrorList(URL, token, bodyRequest).get(0).getDescription();
        System.out.println(errorText + " BirthDate is: " + GetDate.dateTomorrow() + " Employment date is: " + GetDate.dateToday());
        Assert.assertEquals(errorText, "Поле birthDate: must be a past date", "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата рождения Null
     */
    @Test
    public void createEmployeeBirthDateNull() {
        installSpecification(requestSpec(URL), specResponseError400());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "", GetDate.dateToday(), "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата рождения Integer
     * BUG -
     */
    @Test (description = "BUG - дата рождения Integer, заведен баг так как нет ошибки")
    public void createEmployeeBirthDateNumber() {
        installSpecification(requestSpec(URL), specResponseError400());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "123", GetDate.dateToday(), "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    //------------- Employment Date ----------------------------

    /**
     * Дата принятия на работу - Null
     */
    @Test
    public void createEmployeeEmploymentDateNull() {
        installSpecification(requestSpec(URL), specResponseError400());

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "26.08.2000", "", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата принятия на работу число
     * BUG -
     */
    @Test (description = "BUG - принятия на работу - число, заведен баг, так как нет ошибки")
    public void createEmployeeEmploymentDateNumber() {
        installSpecification(requestSpec(URL), specResponseError400());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "123", GetDate.dateToday(), "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    //-------------- dismissalDate -----------------------------------

    /**
     * Дата увольнения - не указывается при создании карточки сотрудника
     */
    @Test (description = "BUG - Дата увольнения - не указывается при создании карточки сотрудника, заведен баг, так как нет ошибки")
    public void createEmployeeDismissalDateExist() {
        installSpecification(requestSpec(URL), specResponseError400());

        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestName", "NewSurename", "Test", "26.08.2000", "26.08.2023", "30.08.2023", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        String errorText = ErrorResponse.createEmployeeError(URL, token, bodyRequest).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }



}
