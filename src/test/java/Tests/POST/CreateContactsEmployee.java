package Tests.POST;

import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.EmployeeContactPOJO.EmployeeContactRequest;
import PojoClasses.EmployeeContactPOJO.EmployeeContactResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateContactsEmployee extends Specifications {
    Integer employeeID;
    Integer contactID;
    final String number = WorkMethods.RandomNumber(11);

    /**
     * Успешное создание контактов сотрудника.
     */
    @Test
    public void newContact() {
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseOK201());
        // создаем тело запроса
        EmployeeContactRequest requestBody = new EmployeeContactRequest(number, "Test_new",
                "Test_new", "Test_new", "Test_new@test.ru", employeeID);
        // выполняем запрос на создание записи с контактами
        EmployeeContactResponse response = EmployeeContactResponse.createContacts(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertEquals(response.getPhone(), number, "Телефон контакта не совпадает, проверка создания контактов не прошла");
    }

    /**
     * Проверка схемы ответа, при создании нового контакта
     * Записываем ID сонтакта для последующего удаления
     */
    @Test
    public void testSchema() {
        installSpecification(requestSpec(URL), specResponseOK201());
        EmployeeContactRequest requestBody = new EmployeeContactRequest(WorkMethods.RandomNumber(11), "Test_Schema",
                "Test_Schema", "Test_Schema", "Test_Schema@test.ru", employeeID);
        //EmployeeContactResponse.addContactSchemaTest(URL, token, requestBody);
        EmployeeContactResponse contact = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .body(requestBody)
                .when()
                .post(URL + "/employee-contact")
                .then()
                .log().all()
                .assertThat()
                .body("id", Matchers.notNullValue())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("contactsSchema.json"))
                .extract().body().as(EmployeeContactResponse.class);
        contactID = contact.getId();

        System.out.println("ПОлучен СОНТАКТ с ID: " + contactID);

    }

    //-------------- Phone - Обработка ошибок создания контактов  -------------------------------

    /**
     * Phone - String error
     */
    @Test
    public void newContactPhoneString() {
        String phone = WorkMethods.RandomString(11);
        String telegram101 = WorkMethods.RandomString(101);
        String randomStr = WorkMethods.RandomString(15);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseError400());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram101)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(113).build();
        // выполняем запрос на создание записи с контактами
        ErrorResponse error = ErrorResponse.createContactError(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertTrue(error.getDescription().contains("Поле phone: Не верный номер телефона"), "Ожидаемая ошибка не получена");
    }

    /**
     * BUG - Phone - Double - дубль, уже существует в другой записи
     * http://jira.lan:8080/browse/IC-423
     */
    @Test (description = "Error create Contact = Phone - Double - Заведен баг, так как дубль на телефон не отрабатывает!")
    public void newContactPhoneDouble() {
        String phone = WorkMethods.RandomString(11);
        String telegram = WorkMethods.RandomString(50);
        String randomStr = WorkMethods.RandomString(15);
        // создаем тело запроса через бидлдер для первого запроса
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(113).build();
        // первый запрос
     //   EmployeeContactResponse response =
                EmployeeContactResponse.createContacts(URL, token, requestBody);
        // тело второго запроса
        EmployeeContactRequest requestBody2 = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram + "1")
                .chatTelegramId(randomStr + "1")
                .skype(randomStr + "1")
                .email(randomStr + "1" + "@email.ru")
                .employeeId(114).build();
        installSpecification(requestSpec(URL), specResponseError400());
        // второй запрос с телефоном дублем
        ErrorResponse error = ErrorResponse.createContactError(URL, token, requestBody2);
        // проверяем ответ.
        Assert.assertTrue(error.getDescription().contains("Поле phone: Не верный номер телефона"), "Ожидаемая ошибка не получена");
    }

    /**
     * Phone - Null error
     * Заведен баг! - http://jira.lan:8080/browse/IC-424
     */
    @Test (description = "Error create Contact = Phone - Null - Заведен баг, так как создается запись без телефона!")
    public void newContactPhoneNull() {
        //String phone = WorkMethods.RandomNumber(11);
        String telegram = WorkMethods.RandomString(12);
        String randomStr = WorkMethods.RandomString(15);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseError400());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
               // .phone(phone)
                .telegram(telegram)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(114).build();
        // выполняем запрос на создание записи с контактами
        ErrorResponse error = ErrorResponse.createContactError(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertTrue(error.getDescription().contains("Поле phone: Не может быть пустым!"), "Ожидаемая ошибка не получена");
    }

    /**
     * Phone - 16 num error
     * Заведен баг! - http://jira.lan:8080/browse/IC-425
     */
    @Test (description = "Error create Contact = Phone - 16 num - Заведен баг, так как поле Телефон не принимает 16 цифр (согласно ТЗ)!")
    public void newContactPhone16() {
        String phone = WorkMethods.RandomNumber(16);
        String telegram = WorkMethods.RandomString(12);
        String randomStr = WorkMethods.RandomString(15);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseOK201());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(114).build();
        // выполняем запрос на создание записи с контактами
        EmployeeContactResponse response = EmployeeContactResponse.createContacts(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertEquals(response.getPhone(), phone, "Не принят телефон состоящий из 16 цифр");
    }

    /**
     * Phone - 17 num error
     */
    @Test
    public void newContactPhone17() {
        String phone = WorkMethods.RandomNumber(17);
        String telegram = WorkMethods.RandomString(12);
        String randomStr = WorkMethods.RandomString(15);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseError400());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(114).build();
        // выполняем запрос на создание записи с контактами
        List<ErrorResponse> error = ErrorResponse.createContactErrorList(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertTrue(error.get(0).getDescription().contains("Поле phone: Не верный номер телефона"), "Ожидаемая ошибка не получена");
    }

    /**
     * Не верный номер телефона - String
     */
    @Test
    public void errorPhoneString(){
        installSpecification(requestSpec(URL), specResponseError400());
        EmployeeContactRequest requestBody = new EmployeeContactRequest(WorkMethods.RandomString(5), "Test_phoneString",
                "Test_phoneString", "Test_phoneString", "Test_phoneString@test.ru", 60);
        List<ErrorResponse> error = EmployeeContactResponse.createContactErrorList(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertEquals(error.get(0).getDescription(), "Поле phone: Не верный номер телефона", "Телефон верный+" +
                "");

    }


//---------- Telegram - Обработка ошибок создания контактов  -------------------------------

    /**
     * BUG - Telegram - Double - дубль, уже существует в другой записи
     */
    @Test //(description = "Error create Contact = Phone - Double - Заведен баг, так как дубль на телефон не отрабатывает!")
    public void newContactTelegramDouble() {
        String phone = WorkMethods.RandomString(10);
        String telegram = WorkMethods.RandomString(50);
        String randomStr = WorkMethods.RandomString(15);
        // создаем тело запроса через бидлдер для первого запроса
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone+"5")
                .telegram(telegram)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(115).build();
        // первый запрос
        //   EmployeeContactResponse response =
        EmployeeContactResponse.createContacts(URL, token, requestBody);
        // тело второго запроса
        EmployeeContactRequest requestBody2 = EmployeeContactRequest.builder()
                .phone(phone+"7")
                .telegram(telegram)
                .chatTelegramId(randomStr + "1")
                .skype(randomStr + "1")
                .email(randomStr + "1" + "@email.ru")
                .employeeId(116).build();
        installSpecification(requestSpec(URL), specResponseError400());
        // второй запрос с телефоном дублем
        ErrorResponse error = ErrorResponse.createContactError(URL, token, requestBody2);
        // проверяем ответ.
        Assert.assertTrue(error.getDescription().contains("Поле phone: Не верный номер телефона"), "Ожидаемая ошибка не получена");
    }

    /**
     * Telegram = Null. Успешное создание контактов сотрудника.
     */
    @Test
    public void newContactTelegramNull() {
        String phone = WorkMethods.RandomNumber(11);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseOK201());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .chatTelegramId("124we")
                .skype("ewrwer")
                .email("email@email.ru")
                .employeeId(112).build();
        // выполняем запрос на создание записи с контактами
        EmployeeContactResponse response = EmployeeContactResponse.createContacts(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertEquals(response.getPhone(), phone, "Телефон контакта не совпадает, проверка создания контактов не прошла");
    }


    /**
     * Telegram - 101 символ
     */
    @Test
    public void newContactTelegram101Symbols() {
        String phone = WorkMethods.RandomNumber(11);
        String telegram101 = WorkMethods.RandomString(101);
        String randomStr = WorkMethods.RandomString(15);
        // проверяем статус код
        installSpecification(requestSpec(URL), specResponseError400());
        // создаем тело запроса через бидлдер
        EmployeeContactRequest requestBody = EmployeeContactRequest.builder()
                .phone(phone)
                .telegram(telegram101)
                .chatTelegramId(randomStr)
                .skype(randomStr)
                .email(randomStr + "@email.ru")
                .employeeId(113).build();
        // выполняем запрос на создание записи с контактами
        ErrorResponse error = ErrorResponse.createContactError(URL, token, requestBody);
        // проверяем ответ.
        Assert.assertTrue(error.getDescription().contains("добавления или обновления записи в бд"), "Ожидаемая ошибка не получена");
    }



    //-----------------------------------------------------------------------------------
    @BeforeClass
    public void createEmployeeForTests() {
        installSpecification(requestSpec(URL), specResponseOK201());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestContact", "TestContact", "TestContact", "26.08.1988", "03.03.2021", "", "http://ava_contact.jpg", "Its me_ contact", "Crimea, Yalta, 298600 - Contact", 1, 2, 2, 2, 3, 2, 2, 2, 3);
        CreateNewEmployeeResponse createdEmployee = CreateNewEmployeeResponse.createEmployeeSuccess(URL, token, bodyRequest);
        employeeID = createdEmployee.getId();
        System.out.println("Создан сотрудник с ID: " + employeeID);
    }



    @AfterClass
    public void cleanExtraContacts() {
        WorkMethods.deleteContactOnId(URL, token, contactID);
      //  WorkMethods.deleteExtraContacts(URL, token);
    }

    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        WorkMethods.deleteAllExtraEmployee(URL, token);
    }


}
