package PojoClasses.EmployeeContactPOJO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeContactRequest {

    private String phone;
    private String telegram;
    private String chatTelegramId;
    private String skype;
    private String email;
    private Integer employeeId;

    public EmployeeContactRequest(String phone, String chatTelegramId, String skype, String email, Integer employeeId) {
        this.phone = phone;
        this.chatTelegramId = chatTelegramId;
        this.skype = skype;
        this.email = email;
        this.employeeId = employeeId;
    }

    public EmployeeContactRequest(String phone, String telegram, String chatTelegramId, String skype, String email, Integer employeeId) {
        this.phone = phone;
        this.telegram = telegram;
        this.chatTelegramId = chatTelegramId;
        this.skype = skype;
        this.email = email;
        this.employeeId = employeeId;
    }



    public EmployeeContactRequest() {super();
    }

  /*  public static EmployeeContactRequest createContact(String phone, String telegram, String chatTelegramId, String skype, String email, Integer employeeId) {
        return  builder()
                .phone(phone)
                .telegram(telegram)
                .chatTelegramId(chatTelegramId)
                .skype(skype)
                .email(email)
                .employeeId(employeeId).build();
    } */

    public static EmployeeContactRequest createContactTelegramNull(String phone, String chatTelegramId, String skype, String email, Integer employeeId) {
        return  builder()
                .phone(phone)
                .chatTelegramId(chatTelegramId)
                .skype(skype)
                .email(email)
                .employeeId(employeeId).build();
    }

}
