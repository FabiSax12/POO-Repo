package Models;

import java.util.UUID;

public class Person {
    private String id;
    private String firstName;
    private String lastname;

    public Person(String firstName, String lastname) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String middleName) {
        this.lastname = middleName;
    }
}
