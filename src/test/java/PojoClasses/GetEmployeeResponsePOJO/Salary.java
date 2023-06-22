package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Setter
@Getter
@JsonIgnoreProperties (ignoreUnknown = true)
public class Salary {
    private Integer id;
    private double salary;
    private String startDate;
    private boolean isPlanned;
    private String comment;

    public Salary() {super();
    }

    public Salary(Integer id, double salary, String startDate, boolean isPlanned, String comment) {
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.isPlanned = isPlanned;
        this.comment = comment;
    }
}
