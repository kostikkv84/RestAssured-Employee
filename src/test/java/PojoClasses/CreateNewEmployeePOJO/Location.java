package PojoClasses.CreateNewEmployeePOJO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
    private int id;
    private String region;
    private String city;

    public Location() {super();
    }

}
