package Tests.PATCH;

import BaseClasses.StringGenerate;
import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.ErrorEmployeePOJO.ErrorResponse;
import Tests.CreateEmployee;
import Tests.POST.CreateEmployeeNameTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import spec.Specifications;

import java.math.BigInteger;

public class PatchParams extends Specifications {

    Integer employeeID;

    //----------- avatar ------------------------------------------------------

    /**
     * Avatar - передается типом Integer
     */
    @Test
    public void patchEmployee_Avatar_Integer() {
        installSpecification(requestSpec(URL), specResponseOK200());
        Integer number = Integer.parseInt(WorkMethods.RandomNumber(5));
        String requestBody = WorkMethods.RequestBodyPatchInt("avatar", number);

        String avatar = CreateNewEmployeeResponse.patchemployeesuccess(URL, token, requestBody, employeeID).getAvatar();
        System.out.println("Аватар изменяется на : " + avatar);
        Assert.assertEquals(avatar, String.valueOf(number), "Аватар в виде числа не получен");
    }

    /**
     * Avatar - очень большое количество символов! Заведен БАГ!
     */
    @Test (description = "BUG - на Avatar нет ограничения на количество символов в аватаре")
    public void patchEmployee_Avatar_BigString() {
        installSpecification(requestSpec(URL), specResponseError400());
        String text = WorkMethods.RandomString(100000);
        String requestBody = WorkMethods.RequestBodyPatchStr("avatar", text);

        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка : " + errorText);
        Assert.assertEquals(errorText, "Max length avatar cant be over 4000 symbols", "Аватар содержит больше допустимого количества символов");
    }

    /**
     * Avatar - значение передается с типом Boolean
     */
    @Test
    public void patchEmployee_Avatar_Boolean() {
        installSpecification(requestSpec(URL), specResponseOK200());

        String requestBody = WorkMethods.RequestBodyPatchBoolean("avatar", true);

        String avatar = CreateNewEmployeeResponse.patchemployeesuccess(URL, token, requestBody, employeeID).getAvatar();
        System.out.println("Аватар изменился на : " + avatar);
        Assert.assertEquals(avatar, "true", "Значение параметра Avatar не изменилось");
    }

    /**
     * Avatar - значение передается с типом Object
     */
    @Test
    public void patchEmployee_Avatar_Object() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = "{\n" + "\"avatar\"" + " : " + "{\"Name\": \"text\"}" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка : " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.String` from Object value"), "Ошибка в параметре аватар при отправке Object не получена");
    }

    /**
     * Avatar - значение передается с типом Array
     */
    @Test
    public void patchEmployee_Avatar_Array() {
        installSpecification(requestSpec(URL), specResponseError400());

        String requestBody = "{\n" + "\"mentorId\"" + " : " + "[{\"Name\": \"text\"}]" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка : " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Array value"), "Ошибка в параметре аватар при отправке Object не получена");
    }

    //----------- MentorId ----------------------------------------------------

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

    /**
     * MentorId - передается типом Boolean
     */
    @Test
    public void patchEmployee_MentorId_Boolean() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchBoolean("mentorId", true);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Boolean value"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом Object
     */
    @Test
    public void patchEmployee_MentorId_Object() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = "{\n" + "\"mentorId\"" + " : " + "{\"Name\": \"text\"}" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Object value"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом Array
     */
    @Test
    public void patchEmployee_MentorId_Array() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = "{\n" + "\"mentorId\"" + " : " + "[{\"Name\": \"text\"}]" + "\n}";
        System.out.println(requestBody);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Array value"), "Текст ошибки не получен или не совпадает");
    }


    // ------------- mainDepartmentId ---------------------
    /**
     * mainDepartmentId - не существует
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
     * mainDepartmentId - равно 0
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
     * mainDepartmentId - большое число
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
     * mainDepartmentId - передается типом String
     */
    @Test
    public void patchEmployee_mainDepartmentId_String() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchStr("mainDepartmentId", WorkMethods.RandomString(5));
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from String"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом Boolean
     */
    @Test
    public void patchEmployee_mainDepartmentId_Boolean() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = WorkMethods.RequestBodyPatchBoolean("mainDepartmentId", true);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Boolean value"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом Object
     */
    @Test
    public void patchEmployee_mainDepartmentId_Object() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = "{\n" + "\"mainDepartmentId\"" + " : " + "{\"Name\": \"text\"}" + "\n}";
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Object value"), "Текст ошибки не получен или не совпадает");
    }

    /**
     * MentorId - передается типом Array
     */
    @Test
    public void patchEmployee_mainDepartmentId_Array() {
        installSpecification(requestSpec(URL), specResponseError400());
        String requestBody = "{\n" + "\"mainDepartmentId\"" + " : " + "[{\"Name\": \"text\"}]" + "\n}";
        System.out.println(requestBody);
        String errorText = ErrorResponse.patchEmployeeErrorStr(URL, token, requestBody, employeeID).getDescription();
        System.out.println("Получена ошибка: " + errorText);
        Assert.assertTrue(errorText.contains("Cannot deserialize value of type `java.lang.Long` from Array value"), "Текст ошибки не получен или не совпадает");
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
