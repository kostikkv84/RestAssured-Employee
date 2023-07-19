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
    public void createEmployee_DismissalDate_In_Past() {
        installSpecification(requestSpec(URL), specResponseError400());

            // дата увольнения ранее даты приема на работу.
        String requestBody = WorkMethods.RequestBodyPatchStr("dismissalDate", "01.01.2022");
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();

        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу число
     * BUG -
     */
    @Test (description = "BUG - дата увольнения - число, заведен баг, так как нет ошибки")
    public void createEmployee_DismissalDate_Number() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = WorkMethods.RequestBodyPatchInt("dismissalDate", 123);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();

        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("добавления или обновления записи в бд"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу String
     */
    @Test
    public void createEmployee_DismissalDate_String() {
        installSpecification(requestSpec(URL), specResponseError400());
        String text = StringGenerate.RandomString(5);

        String requestBody = WorkMethods.RequestBodyPatchStr("dismissalDate", text);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();

        System.out.println(errorText);
        Assert.assertEquals(errorText, "Text '" + text + "' could not be parsed at index 0", "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу Boolean
     */
    @Test
    public void createEmployee_DismissalDate_Boolean() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = WorkMethods.RequestBodyPatchBoolean("dismissalDate", false);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("Expected array or string."), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу Object
     */
    @Test
    public void createEmployee_DismissalDate_Object() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = "{\n" + "\"dismissalDate\"" + " : " + "{\"Name\": \"text\"}" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.time.LocalDate` from Object value"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * Дата увольнения на работу Array
     */
    @Test
    public void createEmployee_DismissalDate_Array() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = "{\n" + "\"dismissalDate\"" + " : " + "[{\"Name\": \"text\"}]" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println(errorText);
        Assert.assertTrue(errorText.contains("Unexpected token (START_OBJECT) within Array"), "Текст ошибки не получен или не совпадает");
    }





    //----------- постусловия ---------- очистка БД от тестовых данных-----------------

    @BeforeClass
    public void createEmployeeForTests() {
        installSpecification(requestSpec(URL), specResponseOK201());
        CreateNewEmployeeRequest bodyRequest = CreateNewEmployeeRequest.createEmployee("TestEmployee", "TestEmployee", "TestEmployee", "26.08.1988", "01.01.2023", "", "http://ava_contact.jpg", "Its me_ contact", "Crimea, Yalta, 298600 - Contact", 1, 2, 2, 2, 3, 2, 2, 2, 3);
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


