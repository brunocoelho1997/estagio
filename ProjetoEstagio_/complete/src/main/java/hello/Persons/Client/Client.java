package hello.Persons.Client;

import hello.Persons.Person;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "client")
public class Client extends hello.Entity {

    public static final int MAX_NAME_LENGHT = 30;
    public static final int MAX_REGISTRATIONCODE_LENGHT = 30;
    public static final int MAX_NUMBERPHONE_LENGHT = 12;


    @NotNull
    @Length(min=1, max = MAX_REGISTRATIONCODE_LENGHT)
    @Column(nullable = false, length = MAX_REGISTRATIONCODE_LENGHT)
    private String registrationCode;

    @NotNull
    @Length(min=1, max = MAX_NAME_LENGHT)
    @Column(nullable = false, length = MAX_NAME_LENGHT)
    private String name;

    @NotNull
    @Column(nullable = false, length = MAX_NUMBERPHONE_LENGHT)
    private String numberPhone;

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
