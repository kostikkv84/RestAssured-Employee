package Tests.PATCH;

import BaseClasses.GetDate;
import BaseClasses.WorkMethods;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import spec.Specifications;

public class PatchEmployee_Success_Admin extends Specifications {

    private Integer employeeId;

    /**
     * Изменение имени
     */
    @Test
    public void changeNamePatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("name", "PatchName");
        // записываем изменненный параметр
        String name = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getName();
        System.out.println("Имя сотрудника заменено на: " + name);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(name, "PatchName", "Изменяемое имя не совпадает");
    }

    /**
     * Изменение фамилии
     */
    @Test
    public void changeSurnamePatch() {
        installSpecification(requestSpec(URL), specResponseOK200()); // проверяем статус код
                 //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("surname", "PatchSurname");
                 // записываем изменненный параметр
        String surname = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getSurname();
        System.out.println("Фамилия заменена на: " + surname);
                 // сравниваем результат с ожидаемым.
        Assert.assertEquals(surname, "PatchSurname", "Изменяемая фамилия не совпадает с ожидаемой!");
    }

    /**
     * Изменение отчества
     */
    @Test
    public void changeMiddleNamePatch() {
        installSpecification(requestSpec(URL), specResponseOK200()); // проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("middleName", "PatchMiddleName");
        // записываем изменненный параметр
        String middleName = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getMiddleName();
        System.out.println("Фамилия заменена на: " + middleName);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(middleName, "PatchMiddleName", "Изменяемое отчество не совпадает с ожидаемым!");
    }

    /**
     * Изменение даты рождения
     */
    @Test
    public void changeBirthDatePatch() {
        installSpecification(requestSpec(URL), specResponseOK200()); // проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("birthDate", "07.07.1997");
        // записываем изменненный параметр
        String birthDate = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getBirthDate();
        System.out.println("Дата рождения заменена на: " + birthDate);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(birthDate, "07.07.1997", "Измененная дата не совпадает с ожидаемой!");
    }

    /**
     * Изменение даты приема на работу
     */
    @Test
    public void changeEmploymentDatePatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("employmentDate", GetDate.dateToday());
        // записываем изменненный параметр
        String emplData = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getEmploymentDate();
        System.out.println("Дата приема на работу заменена на: " + emplData);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(emplData, GetDate.dateToday(), "Изменяемая дата приема на работу не совпадает");
    }

    /**
     * Изменение даты увольнения
     */
    @Test
    public void changeDismissalDatePatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        String date = GetDate.dayPlusRandom(10);
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("dismissalDate", date);
        // записываем изменненный параметр
        String dismData = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getDismissalDate();
        System.out.println("Дата увольнения изменена на: " + dismData);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(dismData, date, "Изменяемая дата увольнения не совпадает");
    }

    /**
     * Изменение ссылки на аватар
     */
    @Test
    public void changeAvatarPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("avatar", "http://ava-Patch.jpg");
        // записываем изменненный параметр
        String avatar = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getAvatar();
        System.out.println("Новая ссылка на аватар: " + avatar);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(avatar, "http://ava-Patch.jpg", "Ссылка на аватар не изменилась");
    }

    /**
     * Изменение комментария
     */
    @Test
    public void changeCommentPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("comment", "New comment - Patch");
     // записываем изменненный параметр
        String comment = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getComment();
        System.out.println("Комментарий изменен на: " + comment);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(comment, "New comment - Patch", "Изменяемый комментарий не совпадает");
    }

    /**
     * Изменение адреса
     */
    @Test
    public void changeFullAddressPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchStr("fullAddress", "Krasnodar, Belorechensk, 305479");
        // записываем изменненный параметр
        String fullData = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getFullAddress();
        System.out.println("Адрес изменен на: " + fullData);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(fullData, "Krasnodar, Belorechensk, 305479", "Изменяемый адрес не совпадает");
    }

    /**
     * Изменение ментора по Id
     */
    @Test
    public void changeMentorIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("mentorId", 2);
        System.out.println(requestBody);
        // записываем изменненный параметр
        Integer mentorId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getMentor().getId();
        System.out.println("Id ментора изменено на: " + mentorId);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(mentorId, 2, "MentorId не изменился");
    }

    /**
     * Изменение формата работы по Id
     */
    @Test
    public void changeWorkFormatIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("workFormatId", 2);
          // записываем изменненный параметр
        Integer formatId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getWorkFormat().getId();
        String formatValue = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getWorkFormat().getValue();

        System.out.println("Id формата работы изменено на: " + formatId);
        System.out.println("Value формата работы изменено на: " + formatValue);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(formatId, 2, "WorkFormatId не изменился");
        Assert.assertEquals(formatValue, "Удалённо", "WorkFormatValue не изменился");
    }

    /**
     * Изменение типа должности по Id
     */
    @Test
    public void changeEmploymentTypeIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("employmentTypeId", 2);
     // записываем изменненный параметр
        Integer emplTypeId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getEmploymentType().getEmploymentTypeId();
        System.out.println("Id формата работы изменено на: " + emplTypeId);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(emplTypeId, 2, "EmploymentTYpeId не изменился");
    }

    /**
     * Изменение типа позиции по Id
     */
    @Test
    public void changePositionIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("positionId", 2);
        // записываем изменненный параметр
        Integer positionId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getPosition().getId();
        String valueId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getPosition().getValue();
        System.out.println("Id позиции изменено на: " + positionId);
        System.out.println("Value позиции изменено на: " + valueId);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(positionId, 2, "PositionId не изменился");
        Assert.assertEquals(valueId, "Аналитик", "PositionId не изменился");
    }

    /**
     * Изменение биографии по Id
     */
    @Test
    public void changeCurriculumVitaeIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("curriculumVitaeId", 2);
        // записываем изменненный параметр
        Integer vitaeId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getCurriculumVitaeId();
        System.out.println("Id биографии изменено на: " + vitaeId);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(vitaeId, 2, "Id биографии не изменился");
    }

    /**
     * Изменение грейда по Id
     */
    @Test
    public void changeGradeIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("gradeDictId", 2);
        // записываем изменненный параметр
        Integer gradeId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getGrade().getId();
        String valueId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getGrade().getValue();
        System.out.println("Id грейда изменено на: " + gradeId);
        System.out.println("Value грейда изменено на: " + valueId);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(gradeId, 2, "Id грейда не изменился");
        Assert.assertEquals(valueId, "Junior", "Value грейда не изменился");
    }

    /**
     * Изменение должности по Id
     */
    @Test
    public void changeEmployeeStatusIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
            //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("employeeStatusId", 2);
            // записываем изменненный параметр
        Integer emplStatusId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getEmployeeStatus().getId();
        String emplStatusValue = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getEmployeeStatus().getEmployeeStatus();
        System.out.println("Id должности на: " + emplStatusId);
        System.out.println("Value должности на: " + emplStatusValue);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(emplStatusId, 2, "Id должности не изменился");
        Assert.assertEquals(emplStatusValue, "Внутренний стажер", "Value должности не изменился");
    }

    /**
     * Изменение локации по Id
     */
    @Test
    public void changeLocationIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
           //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("locationId", 2);
           // записываем изменненный параметр
        Integer locationId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getLocation().getId();
        String locationRegion = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getLocation().getRegion();
        System.out.println("Id региона на: " + locationId);
        System.out.println("Регион на: " + locationRegion);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(locationId, 2, "Id региона не изменилось");
        Assert.assertEquals(locationRegion, "Ульяновская область", "Значение региона не изменилось");
    }

    /**
     * Изменение локации по Id
     */
    @Test
    public void changeDepartmentIdPatch() {
        installSpecification(requestSpec(URL), specResponseOK200());// проверяем статус код
        //создаем тело запроса
        String requestBody = WorkMethods.RequestBodyPatchInt("mainDepartmentId", 2);
        // записываем изменненный параметр
        Integer departmentId = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getMainDepartment().getId();
        String departmentValue = CreateNewEmployeeResponse.patchemployeesuccess(URL, Specifications.token, requestBody, employeeId).getMainDepartment().getName();
        System.out.println("Id департамента на: " + departmentId);
        System.out.println("Значение департамента на: " + departmentValue);
        // сравниваем результат с ожидаемым.
        Assert.assertEquals(departmentId, 2, "Id департамента не изменилось");
        Assert.assertEquals(departmentValue, "Frontend", "Значение департамента не изменилось");
    }

//--------------------------------------------------------------------------------------------------------------

    /**
     * Настройки для тестов:
     * СОздание записи для Patch тестов
     * Удаление записи - постусловие
     */
    @BeforeClass
    public void createEmployee() {
        installSpecification(requestSpec(URL), specResponseOK201());
        CreateNewEmployeeResponse response = WorkMethods.createEmployee(URL, "Test", "Test", "Test", "26.08.1984", "03.03.2023", "", "http://ava.jpg", "Its me", "Crimea, Yalta, 298600", 1, 1, 1, 1, 1, 1, 1, 1, 1);
        employeeId = response.getId();
    }

    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        WorkMethods.deleteEmployeeOnId(URL, employeeId);
    }


}
