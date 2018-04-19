package hello.Client;

import hello.Adress.Adress;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "client")
public class Client extends hello.Entity {

    public static final int MAX_NAME_LENGHT = 30;
    public static final int MAX_REGISTRATIONCODE_LENGHT = 30;
    public static final int MAX_NUMBERPHONE_LENGHT = 12;


    @NotNull
    @Length(min = 1, max = MAX_REGISTRATIONCODE_LENGHT)
    @Column(nullable = false, length = MAX_REGISTRATIONCODE_LENGHT)
    private String registrationCode;

    @NotNull
    @Length(min = 1, max = MAX_NAME_LENGHT)
    @Column(nullable = false, length = MAX_NAME_LENGHT)
    private String name;

    @NotNull
    @Length(min = 1, max = MAX_NUMBERPHONE_LENGHT)
    @Column(nullable = false, length = MAX_NUMBERPHONE_LENGHT)
    private String numberPhone;

//    @NotNull
//    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Client_Contact"), nullable = false)
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Contact> contacts;


    @ElementCollection
    @CollectionTable(foreignKey = @ForeignKey(name = "FK_Client_Adress"))
    private List<Adress> adress;

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

    public List<Adress> getAdress() {
        return adress;
    }

    public void setAdress(List<Adress> adress) {
        this.adress = adress;
    }
}