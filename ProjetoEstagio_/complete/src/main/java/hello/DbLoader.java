package hello;

import hello.Adress.Adress;
import hello.Client.Client;
import hello.Client.ClientRepository;
import hello.Contact.Contact;
import hello.Contact.Genre;
import hello.Enums.Category;
import hello.Project.Project;
import hello.Project.ProjectClient;
import hello.Project.ProjectRepository;
import hello.SubType.SubType;
import hello.SubType.SubTypeRepository;
import hello.SubType.SubTypeService;
import hello.Type.Type;
import hello.Type.TypeRepository;
import hello.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DbLoader implements CommandLineRunner {

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private SubTypeRepository subTypeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public void run(String... args) throws Exception {

        if(typeRepository.findAll().isEmpty() && subTypeRepository.findAll().isEmpty())
        {
//         create subTypes
            SubType subType1 = new SubType();
            subType1.setName("Base dados");

            SubType subType2 = new SubType();
            subType2.setName("Estruturação");

//      add types
            Type type1 = new Type();
            type1.setCategory(Category.PROJECTS);
            type1.setGenre(Genre.REVENUE);
            type1.setName("Manutenção");
            type1.setSubTypeList(new ArrayList<>());
            type1 = typeRepository.save(type1);

            Type type2 = new Type();
            type2.setCategory(Category.PROJECTS);
            type2.setGenre(Genre.REVENUE);
            type2.setName("Manutenção");
            type2.setSubTypeList(new ArrayList<>());
            type2 = typeRepository.save(type2);

//            associate types with subtypes
            type1.getSubTypeList().add(subType1);
            type1.getSubTypeList().add(subType2);

            type2.getSubTypeList().add(subType1);

            typeRepository.save(type1);
            typeRepository.save(type2);

//            create adress
            Adress adress1 = new Adress();
            adress1.setCity("Coimbra");
            adress1.setZipCode("3220-113");
            adress1.setNumber(23);
            adress1.setAdressName("Avenida Padre Américo");

            Adress adress2 = new Adress();
            adress2.setCity("Lisboa");
            adress2.setZipCode("1220-143");
            adress2.setNumber(61);
            adress2.setAdressName("Rua Ali e Acolá");

            Adress adress3 = new Adress();
            adress3.setCity("Viseu");
            adress3.setZipCode("6220-143");
            adress3.setNumber(12);
            adress3.setAdressName("Rua Aqui E Ali");

            Adress adress4 = new Adress();
            adress4.setCity("Aveiro");
            adress4.setZipCode("1250-143");
            adress4.setNumber(123);
            adress4.setAdressName("Rua XPTO");

            Adress adress5 = new Adress();
            adress5.setCity("Porto");
            adress5.setZipCode("5550-143");
            adress5.setNumber(523);
            adress5.setAdressName("Rua Do Porto");




//            create contacts
            Contact contact1 = new Contact();
            contact1.setEmail("contato1@isec.pt");
            contact1.setNumberPhone("239345542");
            contact1.setName("Contato1");
            contact1.setAdresses(new ArrayList<>());
            contact1.getAdresses().add(adress1);

            Contact contact2 = new Contact();
            contact2.setEmail("contato2@isec.pt");
            contact2.setNumberPhone("239345542");
            contact2.setName("Contato2");
            contact2.setAdresses(new ArrayList<>());
            contact2.getAdresses().add(adress2);


            Contact contact3 = new Contact();
            contact3.setEmail("contato3@isec.pt");
            contact3.setNumberPhone("239345542");
            contact3.setName("Contato3");
            contact3.setAdresses(new ArrayList<>());
            contact3.getAdresses().add(adress3);

//            create clients
            Client client1 = new Client();
            client1.setRegistrationCode("CODEClient1");
            client1.setNumberPhone("234432234");
            client1.setName("Cliente1");
            client1.setAdresses(new ArrayList<>());
            client1.setContacts(new ArrayList<>());
            client1.getAdresses().add(adress4);
            client1.getContacts().add(contact3);

            Client client2 = new Client();
            client2.setRegistrationCode("CODEClient2");
            client2.setNumberPhone("234432234");
            client2.setName("Cliente2");
            client2.setAdresses(new ArrayList<>());
            client2.setContacts(new ArrayList<>());
            client2.getAdresses().add(adress5);
            client2.getContacts().add(contact1);
            client2.getContacts().add(contact2);


            client1 = clientRepository.save(client1);
            client2 = clientRepository.save(client2);


//            create projects

            ProjectClient projectClient1 = new ProjectClient();
            projectClient1.setContact(contact1);
            projectClient1.setClient(client1);

            Project project1 = new Project();
            project1.setProjectClient(projectClient1);
            project1.setDescription("Descrição1");
            project1.setName("Projecto1");

            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate1 = LocalDate.ofEpochDay(randomDay);
            LocalDate randomDate2 = LocalDate.ofEpochDay(randomDay);

            project1.setInitialDate(randomDate1);
            project1.setFinalDate(randomDate2);

            project1.setScope("Scope1");
            project1.setBalance(0);

            projectRepository.save(project1);


        }
    }
}