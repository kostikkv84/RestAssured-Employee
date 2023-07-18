package PojoClasses.CreateNewEmployeePOJO;

import PojoClasses.EmployeeContactPOJO.EmployeeContactRequest;
import PojoClasses.EmployeeContactPOJO.EmployeeContactResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spec.Specifications;

import static io.restassured.RestAssured.given;

@Setter
@Getter
@Builder
public class CreateNewEmployeeRequest {
    private String name;
    private String surname;
    private String middleName;
    private String birthDate;
    private String employmentDate;
    private String dismissalDate;
    private String avatar;
    private String comment;
    private String fullAddress;
    private Integer mentorId;
    private Integer workFormatId;
    private Integer employmentTypeId;
    private Integer positionId;
    private Integer curriculumVitaeId;
    private Integer gradeDictId;
    private Integer employeeStatusId;
    private Integer locationId;
    private Integer mainDepartmentId;

    public CreateNewEmployeeRequest() {
        super();
    }

    /**
     * конструктор класса
     *
     * @param name
     * @param surname
     * @param middleName
     * @param birthDate
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     */
    public CreateNewEmployeeRequest(String name, String surname, String middleName, String birthDate, String employmentDate, String dismissalDate, String avatar, String comment, String fullAddress, int mentorId, int workFormatId, int employmentTypeId, int positionId, int curriculumVitaeId, int gradeDictId, int employeeStatusId, int locationId, int mainDepartmentId) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;
        this.dismissalDate = dismissalDate;
        this.avatar = avatar;
        this.comment = comment;
        this.fullAddress = fullAddress;
        this.mentorId = mentorId;
        this.workFormatId = workFormatId;
        this.employmentTypeId = employmentTypeId;
        this.positionId = positionId;
        this.curriculumVitaeId = curriculumVitaeId;
        this.gradeDictId = gradeDictId;
        this.employeeStatusId = employeeStatusId;
        this.locationId = locationId;
        this.mainDepartmentId = mainDepartmentId;
    }


  /*  public CreateNewEmployeeRequest setName(String eName) {
        this.name = eName;
        return this;
    }
*/


    /**
     * СОздание сотрудника - полные данные
     *
     * @param eName
     * @param eSurname
     * @param eMiddleName
     * @param eBirthDate
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
    public static CreateNewEmployeeRequest createEmployee(String eName, String eSurname, String eMiddleName, String eBirthDate, String employmentDate, String dismissalDate,
                                                          String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                                          Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                          Integer gradeDictId, Integer employeeStatusId,
                                                          Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
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

    }

    /**
     * Создание сотрудника Name = null
     *
     * @param eSurname
     * @param eMiddleName
     * @param eBirthDate
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
   public static CreateNewEmployeeRequest createEmployeeNameNull(String eSurname, String eMiddleName, String eBirthDate, String employmentDate, String dismissalDate,
                                                                  String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                                                  Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                                  Integer gradeDictId, Integer employeeStatusId,
                                                                  Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
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

    }

    /**
     * СОздание сотрудника - eSurname = null
     *
     * @param eName
     * @param eMiddleName
     * @param eBirthDate
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
    public static CreateNewEmployeeRequest createEmployeeSurnameNull(String eName, String eMiddleName, String eBirthDate, String employmentDate, String dismissalDate,
                                                                     String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                                                     Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                                     Integer gradeDictId, Integer employeeStatusId,
                                                                     Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
                .name(eName)
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

    }

    /**
     * Создание сотрудника - BirthDate  = null
     *
     * @param eName
     * @param eMiddleName
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
    public static CreateNewEmployeeRequest createEmployeeBirthDateNull(String eName, String eSurname, String eMiddleName, String employmentDate, String dismissalDate,
                                                                       String avatar, String comment, String fullAddress, Integer mentorId, Integer workFormatId,
                                                                       Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                                       Integer gradeDictId, Integer employeeStatusId,
                                                                       Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
                .name(eName)
                .surname(eSurname)
                .middleName(eMiddleName)
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

    }

    /**
     * СОздание сотрудника - employmentDate = null
     *
     * @param eName
     * @param eSurname
     * @param eMiddleName
     * @param eBirthDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param workFormatId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
    public static CreateNewEmployeeRequest createEmployeeEmploymentDateNull(String eName, String eSurname, String eMiddleName,
                                                                            String eBirthDate, String dismissalDate,
                                                                            String avatar, String comment, String fullAddress,
                                                                            Integer mentorId, Integer workFormatId,
                                                                            Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                                            Integer gradeDictId, Integer employeeStatusId,
                                                                            Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
                .name(eName)
                .surname(eSurname)
                .middleName(eMiddleName)
                .birthDate(eBirthDate)
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

    }

    /**
     * СОздание сотрудника - workFormatId = null
     *
     * @param eName
     * @param eSurname
     * @param eMiddleName
     * @param eBirthDate
     * @param employmentDate
     * @param dismissalDate
     * @param avatar
     * @param comment
     * @param fullAddress
     * @param mentorId
     * @param employmentTypeId
     * @param positionId
     * @param curriculumVitaeId
     * @param gradeDictId
     * @param employeeStatusId
     * @param locationId
     * @param mainDepartmentId
     * @return
     */
    public static CreateNewEmployeeRequest createEmployeeWorkFormatIdNull(String eName, String eSurname, String eMiddleName, String eBirthDate, String employmentDate, String dismissalDate,
                                                                          String avatar, String comment, String fullAddress, Integer mentorId,
                                                                          Integer employmentTypeId, Integer positionId, Integer curriculumVitaeId,
                                                                          Integer gradeDictId, Integer employeeStatusId,
                                                                          Integer locationId, Integer mainDepartmentId) {
        return CreateNewEmployeeRequest.builder()
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
                .employmentTypeId(employmentTypeId)
                .positionId(positionId)
                //   .workFormatId(workFormatId)
                .curriculumVitaeId(curriculumVitaeId)
                .gradeDictId(gradeDictId)
                .employeeStatusId(employeeStatusId)
                .locationId(locationId)
                .mainDepartmentId(mainDepartmentId).build();

    }



    //------------------------------------------------------------------------------------------------------------------


}
