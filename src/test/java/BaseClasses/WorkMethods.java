package BaseClasses;

import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.EmployeeStatusPojo.EmployeeStatusResponse;
import PojoClasses.GetEmployeeResponsePOJO.Content;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import spec.Specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class WorkMethods extends Specifications {
    public String RandomString(int n) {

        int length = n;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    } // генерация случайной строки


    /**
     * Очистка созданных для теста карточек
     * @param url
     */
    public static void deleteAllExtraEmployee(String url){
        // ПОлучение всех карточек сотрудников
        Integer count = 0;
        installSpecification(requestSpec(url), specResponseOK200());
        Root list = given().header("Authorization", "Bearer "+token)
                .when()
                .get(url + "/employee")
                .then()
                //.log().all()
                .extract().body().as(Root.class);
        System.out.println(list.getContent().size());

        // Создаем список ID карточек сотрудников
        List<Integer> listID = list.getContent().stream().map(Content::getId).collect(Collectors.toList());
        List<Integer> listToDelete = new ArrayList<>();

        // для безопасности, создадим отдельный список с ID на удаление сотрудников. Где ID больше 320
        for (int i=0;i<listID.size();i++) {
            if (listID.get(i)>320) {
                listToDelete.add(listID.get(i));
            }
            //    System.out.println(listToDelete);
        }

        // List<Integer> listToDelete = listID.get().stream().forEach(x -> listID.stream().collect(Collectors.toList());
        //--- удаляем по списку из списка на удаление.

        for (int i=0;i<listToDelete.size();i++){
            installSpecification(requestSpec(url), specResponseOK204());

            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer "+token)
                    .when()
                    .delete(url+"/employee/" + listToDelete.get(i))
                    .then()
                    .extract().response();
            System.out.println("Удален: " + listToDelete.get(i));

        }


        // проверяем количество записей, что их 6
    /*    installSpecification(requestSpec(URL), specResponseOK200());
        List<VacationType> listAfterDelete = given().header("Authorization", "Bearer "+token)
                .when()
                .get(URL + "/vacationType")
                .then()
                //.then().log().all()
                .extract().jsonPath().getList("",VacationType.class);
        List<Integer> idTypesAfterDelete = list.stream().map(VacationType::getId).collect(Collectors.toList());
        System.out.println("ID отпусков после удаления: " + idTypesAfterDelete);
        Assert.assertEquals(listAfterDelete.size(),6);
*/
    }

    public static void deleteEmployeeOnId(String url, Integer id){
        installSpecification(requestSpec(url), specResponseOK204());

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete(url+"/employee/" + id)
                .then()
                .extract();
    }

    /**
     * СОздание сотрудника для тестов
     */
    public static CreateNewEmployeeResponse createEmployee(String url, String eName, String eSurname, String eMiddleName, String eBirthDate, String employmentDate, String dismissalDate,
                                                           String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                                           Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                           Integer gradeDictId, Integer employeeStatusId,
                                                           Integer locationId, Integer mainDepartmentId){
        installSpecification(requestSpec(url),specResponseOK201());
        CreateNewEmployeeRequest requestBody = CreateNewEmployeeRequest.builder()
                .name(eName)
                .surname(eSurname)
                .middleName(eMiddleName)
                .birthDate(eBirthDate)
                .employmentDate(employmentDate)
                .dismissalDate(dismissalDate)
                .avatar(avatar)
                .comment(comment)
                .fullAddress(fullAddress)
                .mentorId(mentorId)
                .workFormatId(workFormatId)
                .employmentTypeId(employmentTypeId)
                .positionId(positionId)
                .curriculumVitaeId(curriculumVitaeId)
                .gradeDictId(gradeDictId)
                .employeeStatusId(employeeStatusId)
                .locationId(locationId)
                .mainDepartmentId(mainDepartmentId).build();

        CreateNewEmployeeResponse response = given()
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(url + "/employee")
                .then()
                //.log().all()
                .extract().body().as(CreateNewEmployeeResponse.class);
        System.out.println("Создан сотрудрик с ID: " + response.getId());
        return response;
    }


    public static void deleteExtraEmployeeStatus(String url){
        List<EmployeeStatusResponse> response = given()
                .header("Authorization", "Bearer " + Specifications.token)
                .when()
                .get(url + "/employee-status")
                .then()//.log().all()
                .extract().body().jsonPath().getList("", EmployeeStatusResponse.class);

        // Достаем все ID-шники
        List<Integer> listID = response.stream().map(EmployeeStatusResponse::getId).collect(Collectors.toList());
        //   List<Integer> listToDelete = new ArrayList<>();
        // Выбираем, те, что больше 10 в список
        List<Integer> listToDelete = listID.stream().filter(p -> p > 10).collect(Collectors.toList());
        // List<EmployeeStatusResponse> list = response.stream().filter(p -> p.getId() > 10).collect(Collectors.toList());

        /**
         * Удаляем все лишние записи статусов сотрудников.
         */
        for (Integer i=0; i<listToDelete.size(); i++) {
            installSpecification(requestSpec(url), specResponseOK204());

            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer "+token)
                    .when()
                    .delete(url+"/employee-status/" + listToDelete.get(i))
                    .then()
                    .extract().response();
            System.out.println("Удален: " + listToDelete.get(i));
        }

    }

}
