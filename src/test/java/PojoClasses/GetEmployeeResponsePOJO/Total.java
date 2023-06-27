package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Total {
    private Integer total;

    public Total() {super();
    }

    public Total(Integer total) {
        this.total = total;
    }
}
