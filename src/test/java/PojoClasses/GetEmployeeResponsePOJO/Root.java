package PojoClasses.GetEmployeeResponsePOJO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Root {
    private ArrayList<Content> content;
    private Integer total;

    public Root() {super();
    }
}
