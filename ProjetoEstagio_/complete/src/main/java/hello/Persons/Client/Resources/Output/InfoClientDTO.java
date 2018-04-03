package hello.Persons.Client.Resources.Output;


import hello.Persons.Client.ContactPerson;

import java.util.List;

public class InfoClientDTO {


    /*
        Dados do cliente e todos os seus contactos tambem!
     */

    private Long id;
    private String registrationCode;
    private String name;
    private String numberPhone;
    private List<ContactPerson> contactPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ContactPerson> getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(List<ContactPerson> contactPerson) {
        this.contactPerson = contactPerson;
    }
}
