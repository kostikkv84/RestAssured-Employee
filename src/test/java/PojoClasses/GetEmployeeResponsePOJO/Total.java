package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Total {
    private String total;

    public Total() {super();
    }

    public Total(String total) {
        this.total = total;
    }
}
