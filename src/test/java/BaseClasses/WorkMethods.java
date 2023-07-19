package BaseClasses;

import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeRequest;
import PojoClasses.CreateNewEmployeePOJO.CreateNewEmployeeResponse;
import PojoClasses.EmployeeContactPOJO.EmployeeContactResponse;
import PojoClasses.EmployeeStatusPojo.EmployeeStatusResponse;
import PojoClasses.GetEmployeeResponsePOJO.Content;
import PojoClasses.GetEmployeeResponsePOJO.Root;
import org.apache.commons.lang.RandomStringUtils;
import spec.Specifications;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class WorkMethods extends Specifications {

    public static Root getAllEmployee(String url, String token){
        installSpecification(requestSpec(url), specResponseOK200());

        Root employeers = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(url + "/employee")
                .then()
                //.log().all()
                .extract().body().as(Root.class);
       return employeers;
    }

//----------- Генерация данных ------------------------------------------------------------------
    // генерация случайной строки
    public static String RandomString(int n) {

        int length = n;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }

    // генерация случайного числа - String
    public static String RandomNumber(int n) {
        int length = n;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generateNum = RandomStringUtils.random(length, useLetters, useNumbers);

        return generateNum;
    }

    // Генерация числа в диапазоне
    public static int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }



//------------------------------------------------------------------------------------------------
    /**
     * Очистка созданных для теста карточек
     * @param url
     */
    public static void deleteAllExtraEmployee(String url, String token){
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
     * Очистка лишних записей с контактами
     */
    public static void deleteExtraContacts(String url, String token){
        installSpecification(requestSpec(url), specResponseOK200());
        List<EmployeeContactResponse> list = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(url + "/employee-contact")
                .then()
               // .log().all()
                .extract().body().jsonPath().getList("",EmployeeContactResponse.class);

        // Создаем список ID карточек сотрудников
        List<Integer> listID = list.stream().map(EmployeeContactResponse::getId).collect(Collectors.toList());
        List<Integer> listToDelete = new ArrayList<>();

        // для безопасности, создадим отдельный список с ID на удаление сотрудников. Где ID больше 320
        for (int i=0;i<listID.size();i++) {
            if (listID.get(i)>20) {
                listToDelete.add(listID.get(i));
            }
        }

        for (int i=0;i<listToDelete.size();i++) {
            installSpecification(requestSpec(url), specResponseOK204());
            given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete(url + "/employee-contact/" + listToDelete.get(i))
                    .then()
                    .log().all();
            System.out.println("Удален контакт с ID: " + listToDelete.get(i));
        }

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

    public static String RequestBodyPatchStr(String key, String value){
        String requestBody = "{\n" + "    \"" + key + "\"" + " : " + "\"" + value +  "\"" + "\n}";
        return requestBody;
    }

    public static String RequestBodyPatchInt(String key, Integer value){
        String requestBody = "{\n" + "    \"" + key + "\"" + " : " + value + "\n}";
        return requestBody;
    }

    public static String RequestBodyPatchBigInt(String key, BigInteger value){
        String requestBody = "{\n" + "    \"" + key + "\"" + " : " + value + "\n}";
        return requestBody;
    }

    public static String RequestBodyPatchBoolean(String key, Boolean value){
        String requestBody = "{\n" + "    \"" + key + "\"" + " : " + value + "\n}";
        return requestBody;
    }


}
