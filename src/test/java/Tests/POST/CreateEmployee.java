package Tests.POST;

import BaseClasses.WorkMethods;
import org.testng.annotations.AfterClass;
import spec.Specifications;

public class CreateEmployee extends Specifications {
    Integer employeeID;




    //---- Очистка сотрудников после тестов ------------------------
    @AfterClass
    //@Test
    public void deleteEmployeeAfterTests() {
        WorkMethods.deleteAllExtraEmployee(URL, token);
    }
}
