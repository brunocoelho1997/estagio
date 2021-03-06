package hello;

import hello.Adress.Adress;
import hello.Client.Client;
import hello.Client.ClientRepository;
import hello.ComissionTransaction.ComissionTransaction;
import hello.ComissionTransaction.ComissionTransactionRepository;
import hello.Contact.Contact;
import hello.CostCenter.CostCenter;
import hello.CostCenter.CostCenterRepository;
import hello.Currency.Currency;
import hello.Currency.CurrencyRepository;
import hello.Employee.Employee;
import hello.Employee.EmployeeRepository;
import hello.EmployeeTransaction.EmployeeTransaction;
import hello.EmployeeTransaction.EmployeeTransactionRepository;
import hello.Enums.Frequency;
import hello.Enums.Genre;
import hello.GeneralTransaction.GeneralTransaction;
import hello.GeneralTransaction.GeneralTransactionRepository;
import hello.PostContact.PostContact;
import hello.PostContact.PostContactRepository;
import hello.PostEmployee.PostEmployee;
import hello.PostEmployee.PostEmployeeRepository;
import hello.Project.Project;
import hello.Project.ProjectRepository;
import hello.ProjectTransaction.ProjectTransaction;
import hello.ProjectTransaction.ProjectTransactionRepository;
import hello.SaleTransaction.SaleTransaction;
import hello.SaleTransaction.SaleTransactionRepository;
import hello.SheetTransaction.Resources.HoursPerProject;
import hello.SheetTransaction.SheetTransaction;
import hello.SheetTransaction.SheetTransactionRepository;
import hello.SubType.SubType;
import hello.SubType.SubTypeRepository;
import hello.Supplier.Resources.TextContact;
import hello.Supplier.Supplier;
import hello.Supplier.SupplierRepository;
import hello.SupplierTransaction.SupplierTransaction;
import hello.SupplierTransaction.SupplierTransactionRepository;
import hello.Type.Type;
import hello.Type.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
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

    @Autowired
    private ProjectTransactionRepository projectTransactionRepository;

    @Autowired
    private PostContactRepository postContactRepository;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private PostEmployeeRepository postEmployeeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EmployeeTransactionRepository employeeTransactionRepository;

    @Autowired
    private SaleTransactionRepository saleTransactionRepository;

    @Autowired
    private GeneralTransactionRepository generalTransactionRepository;

    @Autowired
    private SheetTransactionRepository sheetTransactionRepository;

    @Autowired
    private SupplierTransactionRepository supplierTransactionRepository;

    @Autowired
    private ComissionTransactionRepository comissionTransactionRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

        @Override
    public void run(String... args) throws Exception {

        if(typeRepository.findAll().isEmpty() && subTypeRepository.findAll().isEmpty())
        {
//         create subTypes
            SubType subType1 = new SubType();
            subType1.setName("Base dados");

            SubType subType2 = new SubType();
            subType2.setName("Estruturação");

            SubType subType3 = new SubType();
            subType3.setName("Base dados");

            SubType subType4 = new SubType();
            subType4.setName("Back-End");

            SubType subType5 = new SubType();
            subType5.setName("Front-End");

            SubType subType6 = new SubType();
            subType6.setName("SEGURO");

            subTypeRepository.save(subType1);
            subTypeRepository.save(subType2);
            subTypeRepository.save(subType3);
            subTypeRepository.save(subType4);
            subTypeRepository.save(subType5);
            subTypeRepository.save(subType6);




//      add types
            Type type1 = new Type();
            type1.setName("MANUTENÇÃO");
            type1.setManuallyCreated(true);
            type1 = typeRepository.save(type1);

            Type type2 = new Type();
            type2.setName("DESENVOLVIMENTO");
            type2.setManuallyCreated(true);
            type2 = typeRepository.save(type2);

            Type type3 = new Type();
            type3.setName("SALÁRIO");
            type3.setManuallyCreated(true);
            type3 = typeRepository.save(type3);


            Type type5 = new Type();
            type5.setName("INFRA");
            type5.setManuallyCreated(true);
            type5 = typeRepository.save(type5);

            Type type6 = new Type();
            type6.setName("ENCARGOS");
            type6.setManuallyCreated(true);
            type6 = typeRepository.save(type6);


            Type type7 = new Type();
            type7.setName("BENEFICIOS");
            type7.setManuallyCreated(true);
            type7 = typeRepository.save(type7);


            Type type = new Type();
            type.setName("RETIRADA");
            type.setManuallyCreated(true);
            typeRepository.save(type);

            type = new Type();
            type.setName("IMPOSTOS");
            type.setManuallyCreated(true);
            typeRepository.save(type);

            type = new Type();
            type.setName("OUTROS");
            type.setManuallyCreated(true);
            typeRepository.save(type);

            type = new Type();
            type.setName("COMISSÃO");
            type.setManuallyCreated(true);
            typeRepository.save(type);

            type = new Type();
            type.setName("LICENÇA");
            type.setManuallyCreated(true);
            typeRepository.save(type);




//            associate types with subtypes
            subType1.setType(type1);
            subType2.setType(type2);
            subType3.setType(type2);

            subType4.setType(type2);
            subType5.setType(type2);

            subType6.setType(type7);



            subTypeRepository.save(subType1);
            subTypeRepository.save(subType2);
            subTypeRepository.save(subType3);
            subTypeRepository.save(subType4);
            subTypeRepository.save(subType5);
            subTypeRepository.save(subType6);




            typeRepository.save(type1);
            typeRepository.save(type2);
            typeRepository.save(type3);
            typeRepository.save(type5);
            typeRepository.save(type6);
            typeRepository.save(type7);




//            create adress
            Adress adress1 = new Adress();
            adress1.setCity("Coimbra");
            adress1.setZipCode("3220-113");
            adress1.setNumber(23);
            adress1.setAdressName("Avenida Padre Américo");

            Adress adress2 = new Adress();
            adress2.setCity("Lisboa");
            adress2.setZipCode("1120-143");
            adress2.setNumber(61);
            adress2.setAdressName("Rua Ali e Acolá");

            Adress adress3 = new Adress();
            adress3.setCity("Viseu");
            adress3.setZipCode("6220-143");
            adress3.setNumber(11);
            adress3.setAdressName("Rua Aqui E Ali");

            Adress adress4 = new Adress();
            adress4.setCity("Aveiro");
            adress4.setZipCode("1150-143");
            adress4.setNumber(113);
            adress4.setAdressName("Rua XPTO");

            Adress adress5 = new Adress();
            adress5.setCity("Porto");
            adress5.setZipCode("5550-143");
            adress5.setNumber(523);
            adress5.setAdressName("Rua Do Porto");



//            create posts of Contacts

            PostContact postContact1 = new PostContact();
            postContact1.setName("Revendedor");
            PostContact postContact2 = new PostContact();
            postContact2.setName("Gestor de Projetos");
            PostContact postContact3 = new PostContact();
            postContact3.setName("Gerente");

            postContact1 = postContactRepository.save(postContact1);
            postContact2 = postContactRepository.save(postContact2);
            postContact3 = postContactRepository.save(postContact3);



//            create contacts
            Contact contact1 = new Contact();
            contact1.setEmail("contato1@isec.pt");
            contact1.setNumberPhone("239345542");
            contact1.setName("Contato1");
            contact1.setAdresses(new ArrayList<>());
            contact1.getAdresses().add(adress1);
            contact1.setPostContact(postContact1);

            Contact contact2 = new Contact();
            contact2.setEmail("contato2@isec.pt");
            contact2.setNumberPhone("239345542");
            contact2.setName("Contato2");
            contact2.setAdresses(new ArrayList<>());
            contact2.getAdresses().add(adress2);
            contact2.setPostContact(postContact2);


            Contact contact3 = new Contact();
            contact3.setEmail("contato3@isec.pt");
            contact3.setNumberPhone("239345542");
            contact3.setName("Contato3");
            contact3.setAdresses(new ArrayList<>());
            contact3.getAdresses().add(adress3);
            contact3.setPostContact(postContact3);

            Contact contact4 = new Contact();
            contact4.setEmail("contato4@isec.pt");
            contact4.setNumberPhone("239345542");
            contact4.setName("Contato4");
            contact4.setAdresses(new ArrayList<>());
            contact4.getAdresses().add(adress3);
            contact4.setPostContact(postContact3);


            /*
            -
            -
            -
            -
            -
            CLIENTS
            -
            -
             */
            Client client1 = new Client();
            client1.setRegistrationCode("CODESicat");
            client1.setNumberPhone("234432234");
            client1.setName("SICAT");
            client1.setAdresses(new ArrayList<>());
            client1.setContacts(new ArrayList<>());
            client1.getAdresses().add(adress4);
            client1.getContacts().add(contact3);

            Client client2 = new Client();
            client2.setRegistrationCode("CODESiger");
            client2.setNumberPhone("234432234");
            client2.setName("SIGER");
            client2.setAdresses(new ArrayList<>());
            client2.setContacts(new ArrayList<>());
            client2.getAdresses().add(adress5);
            client2.getContacts().add(contact1);
            client2.getContacts().add(contact2);

            Client client3 = new Client();
            client3.setRegistrationCode("CODEKALL");
            client3.setNumberPhone("234432234");
            client3.setName("KALL");
            client3.setAdresses(new ArrayList<>());
            client3.setContacts(new ArrayList<>());
            client3.getAdresses().add(adress2);
            client3.getContacts().add(contact4);


            client1 = clientRepository.save(client1);
            client2 = clientRepository.save(client2);
            client3 = clientRepository.save(client3);


//            create Cost Centers

            CostCenter costCenter1 = new CostCenter();
            costCenter1.setName("Centro de Custos 1");
            costCenter1.setDescription("Descrição");
            CostCenter costCenter2 = new CostCenter();
            costCenter2.setName("Centro de Custos 2");
            costCenter2.setDescription("Descrição");

            costCenter1 = costCenterRepository.save(costCenter1);
            costCenter2 = costCenterRepository.save(costCenter2);


            /*
            -
            -
            -
            -
            -
            PROJECTS
            -
            -
            -
             */

            Project project1 = new Project();
            project1.setClient(client1);
            project1.setContact(contact1);
            project1.setDescription("Descrição1");
            project1.setName("COMPSIS");
            


            project1.setInitialDate(LocalDate.of(2017,getRandomNumberInRange(1,12),getRandomNumberInRange(1,25)));
            project1.setFinalDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            project1.setScope("Scope1");
            project1.setCostCenter(costCenter1);

            project1 = projectRepository.save(project1);

            Project project2 = new Project();
            project2.setClient(client2);
            project2.setContact(contact2);
            project2.setDescription("Descrição1");
            project2.setName("IMAGEM");


            project2.setInitialDate(LocalDate.of(2017,getRandomNumberInRange(1,12),getRandomNumberInRange(1,25)));
            project2.setFinalDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            project2.setScope("Scope1");
            project2.setCostCenter(costCenter1);

            project2 = projectRepository.save(project2);


            Currency brlCurrency = new Currency();
            brlCurrency.setName("BRL");
            brlCurrency.setSymbol("R$");
            brlCurrency.setSelected(true);
            currencyRepository.save(brlCurrency);


            Type type14 = new Type();
            type14.setName("MANUTENÇÃO");
            typeRepository.save(type14);
            type14.setSubTypeList(new ArrayList<>());
            type14.getSubTypeList().add(subType4);
            typeRepository.save(type14);

            ProjectTransaction projectTransaction1 = new ProjectTransaction();
            projectTransaction1.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction1.setName("Manutenção Serviço XPTO");
            projectTransaction1.setFrequency(Frequency.DAILY);
            projectTransaction1.setValue((float)25850.3);
            projectTransaction1.setProject(project1);
            projectTransaction1.setGenre(Genre.REVENUE);
            projectTransaction1.setType(type14);
            projectTransaction1.setExecuted(true);
            projectTransaction1.setCurrency(brlCurrency);


            Type type15 = new Type();
            type15.setName("DESENVOLVIMENTO");
            typeRepository.save(type15);

            ProjectTransaction projectTransaction2 = new ProjectTransaction();
            projectTransaction2.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction2.setName("Aluguer de base de dados 10GB.");
            projectTransaction2.setFrequency(Frequency.MONTHLY);
            projectTransaction2.setValue((float)2135.3);
            projectTransaction2.setProject(project1);
            projectTransaction2.setGenre(Genre.COST);
            projectTransaction2.setType(type15);
            projectTransaction2.setExecuted(true);
            projectTransaction2.setCurrency(brlCurrency);

            Type type16 = new Type();
            type16.setName("MANUTENÇÃO");
            typeRepository.save(type16);
            type14.setSubTypeList(new ArrayList<>());
            type14.getSubTypeList().add(subType5);
            typeRepository.save(type14);

            ProjectTransaction projectTransaction3 = new ProjectTransaction();
            projectTransaction3.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction3.setName("Correção de Erros");
            projectTransaction3.setDescription("Correção de alguns erros - Projeto ainda em garantia.");
            projectTransaction3.setFrequency(Frequency.DAILY);
            projectTransaction3.setValue((float)3500.1);
            projectTransaction3.setProject(project2);
            projectTransaction3.setGenre(Genre.COST);
            projectTransaction3.setType(type16);
            projectTransaction3.setExecuted(true);
            projectTransaction3.setCurrency(brlCurrency);

//            projectTransaction3.getType().setSubType(subType1);

            Type type17 = new Type();
            type17.setName("DESENVOLVIMENTO");
            typeRepository.save(type17);
            type17.setSubTypeList(new ArrayList<>());
            type17.getSubTypeList().add(subType4);
            typeRepository.save(type17);

            ProjectTransaction projectTransaction4 = new ProjectTransaction();
            projectTransaction4.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction4.setName("APP Android XPTO.");
            projectTransaction4.setFrequency(Frequency.DAILY);
            projectTransaction4.setValue((float)9461.9);
            projectTransaction4.setProject(project2);
            projectTransaction4.setGenre(Genre.REVENUE);
            projectTransaction4.setType(type17);
            projectTransaction4.setExecuted(true);
            projectTransaction4.setCurrency(brlCurrency);

//            projectTransaction4.getType().setSubType(subType2);

            Type type18 = new Type();
            type18.setName("DESENVOLVIMENTO");
            typeRepository.save(type18);
            type18.setSubTypeList(new ArrayList<>());
            type18.getSubTypeList().add(subType3);
            type18.getSubTypeList().add(subType2);
            typeRepository.save(type18);

            ProjectTransaction projectTransaction5 = new ProjectTransaction();
            projectTransaction5.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction5.setName("REST Service");
            projectTransaction5.setFrequency(Frequency.DAILY);
            projectTransaction5.setValue((float)15000.2);
            projectTransaction5.setProject(project1);
            projectTransaction5.setGenre(Genre.REVENUE);
            projectTransaction5.setType(type18);
            projectTransaction5.setExecuted(true);
            projectTransaction5.setCurrency(brlCurrency);

//            projectTransaction5.getType().setSubType(subType2);


            Type type19 = new Type();
            type19.setName("DESENVOLVIMENTO");
            typeRepository.save(type19);
            type19.setSubTypeList(new ArrayList<>());
            type19.getSubTypeList().add(subType2);
            typeRepository.save(type19);

            ProjectTransaction projectTransaction6 = new ProjectTransaction();
            projectTransaction6.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction6.setName("Alteração Estrutura");
            projectTransaction6.setDescription("Alteração base do projeto. Tinha problemas de eficiência.");
            projectTransaction6.setFrequency(Frequency.DAILY);
            projectTransaction6.setValue((float)6811.3);
            projectTransaction6.setProject(project2);
            projectTransaction6.setGenre(Genre.REVENUE);
            projectTransaction6.setType(type19);
            projectTransaction6.setExecuted(true);
            projectTransaction6.setCurrency(brlCurrency);



            type = new Type();
            type.setName("MANUTENÇÃO");
            typeRepository.save(type);
            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType1);
            typeRepository.save(type);

            ProjectTransaction projectTransaction7 = new ProjectTransaction();
            projectTransaction7.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            projectTransaction7.setName("Alteração na base de dados");
            projectTransaction7.setDescription("Pequena alteração na base de dados. Convenções estavam incorretas.");
            projectTransaction7.setFrequency(Frequency.DAILY);
            projectTransaction7.setValue((float)1153.2);
            projectTransaction7.setProject(project1);
            projectTransaction7.setGenre(Genre.COST);
            projectTransaction7.setType(type);
            projectTransaction7.setExecuted(true);
            projectTransaction7.setCurrency(brlCurrency);


            projectTransactionRepository.save(projectTransaction1);
            projectTransactionRepository.save(projectTransaction2);
            projectTransactionRepository.save(projectTransaction3);
            projectTransactionRepository.save(projectTransaction4);
            projectTransactionRepository.save(projectTransaction5);
            projectTransactionRepository.save(projectTransaction6);
            projectTransactionRepository.save(projectTransaction7);





            /*
            -
            -
            -
            EMPLOYEES
            -
            -
            -
             */
            PostEmployee postEmployee1 = new PostEmployee();
            postEmployee1.setName("Programador - back-end");
            PostEmployee postEmployee2 = new PostEmployee();
            postEmployee2.setName("Gerente");
            PostEmployee postEmployee3 = new PostEmployee();
            postEmployee3.setName("Programador - front-end");

            postEmployeeRepository.save(postEmployee1);
            postEmployeeRepository.save(postEmployee2);
            postEmployeeRepository.save(postEmployee3);

//            add employee
            Employee employee1 = new Employee();

            Adress adress6 = new Adress();
            adress6.setCity("Faro");
            adress6.setZipCode("5550-143");
            adress6.setNumber(3);
            adress6.setAdressName("Rua De Faro");

            employee1.setAdresses(new ArrayList<>());
            employee1.getAdresses().add(adress6);
            employee1.setNumberPhone("939898345");
            employee1.setEmail("nakasato@isec.pt");
            employee1.setName("NAKASATO");
            employee1.setPostEmployee(postEmployee1);

            Employee employee2 = new Employee();
            employee2.setPostEmployee(postEmployee3);

            Adress adress7 = new Adress();
            adress7.setCity("Portimão");
            adress7.setZipCode("2550-143");
            adress7.setNumber(300);
            adress7.setAdressName("Rua De Portimão");

            employee2.setAdresses(new ArrayList<>());
            employee2.getAdresses().add(adress7);
            employee2.setNumberPhone("939898345");
            employee2.setEmail("caio@isec.pt");
            employee2.setName("CAIO");

            Employee employee3 = new Employee();
            employee3.setPostEmployee(postEmployee3);

            Adress adress = new Adress();
            adress7.setCity("Leiria");
            adress7.setZipCode("2550-143");
            adress7.setNumber(300);
            adress7.setAdressName("Rua De Leiria");

            employee3.setAdresses(new ArrayList<>());
            employee3.getAdresses().add(adress7);
            employee3.setNumberPhone("939898345");
            employee3.setEmail("naresh@isec.pt");
            employee3.setName("NARESH");

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);
            employeeRepository.save(employee3);



            /*
            -
            -
            -
            SUPPLIERS
            -
            -
            -
             */
            Supplier supplier1 = new Supplier();

            Adress adress8 = new Adress();
            adress8.setCity("Faro");
            adress8.setZipCode("5550-143");
            adress8.setNumber(3);
            adress8.setAdressName("Rua De Faro");

            TextContact textContact1 = new TextContact();
            textContact1.setContact("911345654");

            TextContact textContact2 = new TextContact();
            textContact2.setContact("google@isec.pt");


            supplier1.setName("GOOGLE");

            supplier1.setContacts(new ArrayList<>());
            supplier1.getContacts().add(textContact1);
            supplier1.getContacts().add(textContact2);

            supplier1.setAdresses(new ArrayList<>());
            supplier1.getAdresses().add(adress8);


//another
            Adress adress9 = new Adress();
            adress9.setCity("Óbidos");
            adress9.setZipCode("5550-143");
            adress9.setNumber(3);
            adress9.setAdressName("Rua De Faro");

            TextContact textContact3 = new TextContact();
            textContact3.setContact("234432345");

            Supplier supplier2 = new Supplier();
            supplier2.setAdresses(new ArrayList<>());
            supplier2.getAdresses().add(adress9);
            supplier2.setName("ATLASSIAN");
            supplier2.setContacts(new ArrayList<>());
            supplier2.getContacts().add(textContact3);



            supplier1.setContacts(new ArrayList<>());
            supplier1.getContacts().add(textContact1);
            supplier1.getContacts().add(textContact2);

            supplier1.setAdresses(new ArrayList<>());
            supplier1.getAdresses().add(adress8);



            Supplier supplier3 = new Supplier();
            supplier3.setAdresses(new ArrayList<>());
            supplier3.getAdresses().add(adress9);
            supplier3.setName("SUBMARINO");
            supplier3.setContacts(new ArrayList<>());
            supplier3.getContacts().add(textContact3);


            Supplier supplier4 = new Supplier();
            supplier4.setAdresses(new ArrayList<>());
            supplier4.getAdresses().add(adress9);
            supplier4.setName("VIVO");
            supplier4.setContacts(new ArrayList<>());
            supplier4.getContacts().add(textContact1);


            supplierRepository.save(supplier2);
            supplierRepository.save(supplier1);
            supplierRepository.save(supplier3);
            supplierRepository.save(supplier4);

//            about empployee transactiosn










            /*
            OU SEJa:
            tipos tem uma lista de subtipos....
            subtipos tem a identificacao dos tipos...

            quando criamos subtipos definimos logo qual o seu tipo...
            só criamos um tipo quando criarmos uma transacao e aí definimos os tipos que queremos...

            (DIA 2) - para a gestão de tipos e afins... criamos tipos so com nome... com a lista de subtipos vazia...
            e caso estes tipos tenham subtipos criamos os seus subtipomos e identificamos os tipos nestes.

            quando for para criar uma transacao:
            X -mandamos todos os nomes possiveis de tipos (apenas nomes, getDistinctTypeNames() )
            Y -quando for para definir o seu subtipo vamos buscar todos os nomes do subtipos (apenas q tenham aquele tipo)

            por fim:
            criar um novo tipo com nome X e a lista de subtipos selecionados

             */

            type = new Type();
            type.setName("RETIRADA");
            typeRepository.save(type7);
            type.setSubTypeList(new ArrayList<>());
            typeRepository.save(type);

            EmployeeTransaction employeeTransaction1 = new EmployeeTransaction();
            employeeTransaction1.setDate(LocalDate.of(2017,getRandomNumberInRange(8,12),getRandomNumberInRange(1,25)));
            employeeTransaction1.setName("RETIRADA XPTO");
            employeeTransaction1.setFrequency(Frequency.DAILY);
            employeeTransaction1.setValue((float)244.1);
            employeeTransaction1.setEmployee(employee1);
            employeeTransaction1.setGenre(Genre.COST);
            employeeTransaction1.setType(type);
            employeeTransaction1.setExecuted(true);
            employeeTransaction1.setCurrency(brlCurrency);

            type = new Type();
            type.setName("BENEFICIOS");
            typeRepository.save(type);


            EmployeeTransaction employeeTransaction2 = new EmployeeTransaction();
            employeeTransaction2.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            employeeTransaction2.setName("CONVÊNIO");
            employeeTransaction2.setFrequency(Frequency.DAILY);
            employeeTransaction2.setValue((float)1104.3);
            employeeTransaction2.setEmployee(employee1);
            employeeTransaction2.setGenre(Genre.COST);
            employeeTransaction2.setType(type);
            employeeTransaction2.setExecuted(true);
            employeeTransaction2.setCurrency(brlCurrency);

//            employeeTransaction2.getType().setSubType(subType1);

            employeeTransactionRepository.save(employeeTransaction1);
            employeeTransactionRepository.save(employeeTransaction2);

            type = new Type();
            type.setName("BENEFICIOS");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType6);
            typeRepository.save(type);

            EmployeeTransaction employeeTransaction3 = new EmployeeTransaction();
            employeeTransaction3.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            employeeTransaction3.setName("SEGURO POR MOTIVO X");
            employeeTransaction3.setDescription("SEGURO POR MOTIVO X. CAIO se magoou.");
            employeeTransaction3.setFrequency(Frequency.DAILY);
            employeeTransaction3.setValue((float)18.3);
            employeeTransaction3.setEmployee(employee2);
            employeeTransaction3.setGenre(Genre.COST);
            employeeTransaction3.setType(type);
            employeeTransaction3.setExecuted(true);
            employeeTransaction3.setCurrency(brlCurrency);
            employeeTransactionRepository.save(employeeTransaction3);


//            type = new Type();
//            type.setName("Desenvolvimento");
//            type.setCategory(Category.PROJECTS);
//            typeRepository.save(type);
//
//            type.setSubTypeList(new ArrayList<>());
//            type.getSubTypeList().add(subType2);
//            type.getSubTypeList().add(subType3);
//            typeRepository.save(type);
//
//            EmployeeTransaction employeeTransaction6 = new EmployeeTransaction();
//            employeeTransaction6.setDate(date3);
//            employeeTransaction6.setName("Despesa de Funcionário 6");
//            employeeTransaction6.setFrequency(Frequency.DAILY);
//            employeeTransaction6.setValue((float)28.3);
//            employeeTransaction6.setEmployee(employee2);
//            employeeTransaction6.setGenre(Genre.COST);
//            employeeTransaction6.setType(type);
//            employeeTransaction6.setExecuted(true);
//            employeeTransaction6.setCurrency(brlCurrency);
//            employeeTransactionRepository.save(employeeTransaction6);


            Type type20 = new Type();
            type20.setName("LICENÇA");
            typeRepository.save(type20);

//            about sale transactiosn
            SaleTransaction saleTransaction1 = new SaleTransaction();
            saleTransaction1.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            saleTransaction1.setName("Licença mensal");
            saleTransaction1.setFrequency(Frequency.MONTHLY);
            saleTransaction1.setValue((float)120.3);
            saleTransaction1.setGenre(Genre.REVENUE);
            saleTransaction1.setType(type20);
            saleTransaction1.setExecuted(true);
            saleTransaction1.setCurrency(brlCurrency);


            Type type21 = new Type();
            type21.setName("MANUTENÇÃO");
            typeRepository.save(type21);


            SaleTransaction saleTransaction2 = new SaleTransaction();
            saleTransaction2.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            saleTransaction2.setName("Manutenção em software");
            saleTransaction2.setFrequency(Frequency.MONTHLY);
            saleTransaction2.setValue((float)110.3);
            saleTransaction2.setGenre(Genre.REVENUE);
            saleTransaction2.setType(type21);
            saleTransaction2.setExecuted(true);
            saleTransaction2.setCurrency(brlCurrency);

//            saleTransaction2.getType().setSubType(subType2);


//            Type type22 = new Type();
//            type22.setName("DESENVOLVIMENTO");
//            type22.setCategory(Category.PROJECTS);
//            typeRepository.save(type22);
//
//            type22.setSubTypeList(new ArrayList<>());
//            type22.getSubTypeList().add(subType2);
//            typeRepository.save(type22);
//
//            SaleTransaction saleTransaction3 = new SaleTransaction();
//            saleTransaction3.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
//            saleTransaction3.setName("Receita de Venda 3");
//            saleTransaction3.setFrequency(Frequency.DAILY);
//            saleTransaction3.setValue((float)10.1);
//            saleTransaction3.setGenre(Genre.REVENUE);
//            saleTransaction3.setType(type22);
//            saleTransaction3.setExecuted(true);
//            saleTransaction3.setCurrency(brlCurrency);


            saleTransactionRepository.save(saleTransaction1);
            saleTransactionRepository.save(saleTransaction2);
//            saleTransactionRepository.save(saleTransaction3);



            type = new Type();
            type.setName("IMPOSTOS");
            typeRepository.save(type);

//            about general transactiosn
            GeneralTransaction generalTransaction1 = new GeneralTransaction();
            generalTransaction1.setDate(LocalDate.of(2017,getRandomNumberInRange(8,12),getRandomNumberInRange(1,25)));
            generalTransaction1.setName("ISS SP PDQ");
            generalTransaction1.setFrequency(Frequency.DAILY);
            generalTransaction1.setValue((float)1096.3);
            generalTransaction1.setGenre(Genre.COST);
            generalTransaction1.setType(type);
            generalTransaction1.setExecuted(true);
            generalTransaction1.setCurrency(brlCurrency);


            type = new Type();
            type.setName("OUTROS");
            typeRepository.save(type);

            GeneralTransaction generalTransaction2 = new GeneralTransaction();
            generalTransaction2.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            generalTransaction2.setName("CHURRASCO");
            generalTransaction2.setFrequency(Frequency.MONTHLY);
            generalTransaction2.setValue((float)110.3);
            generalTransaction2.setGenre(Genre.COST);
            generalTransaction2.setType(type);
            generalTransaction2.setExecuted(true);
            generalTransaction2.setCurrency(brlCurrency);

//            generalTransaction2.getType().setSubType(subType2);

            type = new Type();
            type.setName("IMPOSTOS");
            typeRepository.save(type);

            GeneralTransaction generalTransaction3 = new GeneralTransaction();
            generalTransaction3.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            generalTransaction3.setName("DAS MURALIS");
            generalTransaction3.setFrequency(Frequency.DAILY);
            generalTransaction3.setValue((float)2381.1);
            generalTransaction3.setGenre(Genre.COST);
            generalTransaction3.setType(type);
            generalTransaction3.setExecuted(true);
            generalTransaction3.setCurrency(brlCurrency);

            generalTransactionRepository.save(generalTransaction1);
            generalTransactionRepository.save(generalTransaction2);
            generalTransactionRepository.save(generalTransaction3);

            //sheet transactions...


            type = new Type();
            type.setName("ENCARGOS");
            typeRepository.save(type);

            SheetTransaction sheetTransaction1 = new SheetTransaction();
            sheetTransaction1.setDate(LocalDate.of(2017,getRandomNumberInRange(8,12),getRandomNumberInRange(1,25)));
            sheetTransaction1.setName("SINDICATO");
            sheetTransaction1.setFrequency(Frequency.DAILY);
            sheetTransaction1.setValue((float)87.1);
            sheetTransaction1.setGenre(Genre.COST);
            sheetTransaction1.setType(type);
            sheetTransaction1.setEmployee(employee1);
            sheetTransaction1.setExecuted(true);
            sheetTransaction1.setCurrency(brlCurrency);


            type = new Type();
            type.setName("ENCARGOS");
            typeRepository.save(type);


            SheetTransaction sheetTransaction2 = new SheetTransaction();
            sheetTransaction2.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            sheetTransaction2.setName("IR");
            sheetTransaction2.setFrequency(Frequency.DAILY);
            sheetTransaction2.setValue((float)30.1);
            sheetTransaction2.setGenre(Genre.COST);
            sheetTransaction2.setType(type);
            sheetTransaction2.setEmployee(employee2);
            sheetTransaction2.setExecuted(true);
            sheetTransaction2.setCurrency(brlCurrency);

//            sheetTransaction2.getType().setSubType(subType1);


            type = new Type();
            type.setName("SALÁRIO");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType2);
            type.getSubTypeList().add(subType3);
            typeRepository.save(type);

            SheetTransaction sheetTransaction3 = new SheetTransaction();
            sheetTransaction3.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            sheetTransaction3.setName("SALÁRIO DE NAKASATO");
            sheetTransaction3.setFrequency(Frequency.DAILY);
            sheetTransaction3.setValue((float)1010.1);
            sheetTransaction3.setGenre(Genre.COST);
            sheetTransaction3.setType(type);
//            sheetTransaction3.getType().setSubType(subType1);
            sheetTransaction3.setEmployee(employee1);
            sheetTransaction3.setExecuted(true);
            sheetTransaction3.setCurrency(brlCurrency);

            sheetTransaction3.setHoursPerProjectList(new ArrayList<>());

            HoursPerProject hoursPerProject1 = new HoursPerProject();
            hoursPerProject1.setDuration((float)2.3);
            hoursPerProject1.setProject(project1);
            sheetTransaction3.getHoursPerProjectList().add(hoursPerProject1);




            /*
            -
            -
            -
            -
            FINANCIAL PROJECTION
            -
            -
            -
             */
            type = new Type();
            type.setName("SALÁRIO");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            typeRepository.save(type);




            SheetTransaction sheetTransaction4 = new SheetTransaction();
            sheetTransaction4.setDate(LocalDate.now().plusDays(5));
            sheetTransaction4.setName("CAIO");
            sheetTransaction4.setFrequency(Frequency.MONTHLY);
            sheetTransaction4.setValue((float)3300.1);
            sheetTransaction4.setGenre(Genre.COST);
            sheetTransaction4.setType(type);
            sheetTransaction4.setExecuted(false);
            sheetTransaction4.setCurrency(brlCurrency);
            sheetTransaction4.setInstallments(10);
            sheetTransaction4.setEmployee(employee2);
            sheetTransaction4.setHoursPerProjectList(new ArrayList<>());

            HoursPerProject hoursPerProject2 = new HoursPerProject();
            hoursPerProject2.setDuration((float)5.5);
            hoursPerProject2.setProject(project1);
            sheetTransaction4.getHoursPerProjectList().add(hoursPerProject2);

            sheetTransactionRepository.save(sheetTransaction4);


            type = new Type();
            type.setName("SALÁRIO");
            typeRepository.save(type);

            SheetTransaction sheetTransaction5 = new SheetTransaction();
            sheetTransaction5.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));

            sheetTransaction5.setName("Premio Outubro NASAKO");
            sheetTransaction5.setFrequency(Frequency.SEMESTER);
            sheetTransaction5.setValue((float)211.1);
            sheetTransaction5.setGenre(Genre.COST);
            sheetTransaction5.setType(type);
            sheetTransaction5.setExecuted(false);
            sheetTransaction5.setCurrency(brlCurrency);
            sheetTransaction5.setInstallments(1);
            sheetTransaction5.setEmployee(employee1);


            sheetTransactionRepository.save(sheetTransaction1);
            sheetTransactionRepository.save(sheetTransaction2);
            sheetTransactionRepository.save(sheetTransaction3);
            sheetTransactionRepository.save(sheetTransaction5);



            type = new Type();
            type.setName("IMPOSTO");
            typeRepository.save(type);

            GeneralTransaction generalTransaction4 = new GeneralTransaction();
            generalTransaction4.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            generalTransaction4.setName("ISSP SP PDQ");
            generalTransaction4.setFrequency(Frequency.MONTHLY);
            generalTransaction4.setValue((float)1069.1);
            generalTransaction4.setGenre(Genre.COST);
            generalTransaction4.setType(type);
            generalTransaction4.setExecuted(false);
            generalTransaction4.setCurrency(brlCurrency);

            Type type9 = new Type();
            type9.setName("DESENVOLVIMENTO");
            typeRepository.save(type9);
            type9.setSubTypeList(new ArrayList<>());
            type9.getSubTypeList().add(subType1);
            type9.getSubTypeList().add(subType2);
            typeRepository.save(type9);

            generalTransactionRepository.save(generalTransaction4);


            Type type10 = new Type();
            type10.setName("BENEFICIOS");
            typeRepository.save(type10);
            type10.setSubTypeList(new ArrayList<>());
            typeRepository.save(type10);

            EmployeeTransaction employeeTransaction4 = new EmployeeTransaction();
            employeeTransaction4.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            employeeTransaction4.setName("SPOTIFY");
            employeeTransaction4.setFrequency(Frequency.DAILY);
            employeeTransaction4.setValue((float)68.3);
            employeeTransaction4.setEmployee(employee1);
            employeeTransaction4.setGenre(Genre.COST);
            employeeTransaction4.setType(type10);
            employeeTransaction4.setExecuted(false);
            employeeTransaction4.setCurrency(brlCurrency);


//            Type type11 = new Type();
//            type11.setName("Lazer");
//            type11.setCategory(Category.PROJECTS);
//            typeRepository.save(type11);
//            type11.setSubTypeList(new ArrayList<>());
//            typeRepository.save(type11);
//
//            EmployeeTransaction employeeTransaction5 = new EmployeeTransaction();
//            employeeTransaction5.setDate(date3);
//            employeeTransaction5.setName("SPOTIFY");
//            employeeTransaction5.setFrequency(Frequency.DAILY);
//            employeeTransaction5.setValue((float)82.3);
//            employeeTransaction5.setEmployee(employee2);
//            employeeTransaction5.setGenre(Genre.COST);
//            employeeTransaction5.setType(type11);
//            employeeTransaction5.setExecuted(false);
//            employeeTransaction5.setInstallments(5);
//            employeeTransaction5.setCurrency(brlCurrency);

            employeeTransactionRepository.save(employeeTransaction4);
//            employeeTransactionRepository.save(employeeTransaction5);


            Type type11 = new Type();
            type11.setName("DESENVOLVIMENTO");
            typeRepository.save(type11);
            type11.setSubTypeList(new ArrayList<>());
            type11.getSubTypeList().add(subType1);
            typeRepository.save(type11);

            ProjectTransaction projectTransaction9 = new ProjectTransaction();
            projectTransaction9.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            projectTransaction9.setName("Aluguer Base Dados");
            projectTransaction9.setFrequency(Frequency.DAILY);
            projectTransaction9.setValue((float)2424.3);
            projectTransaction9.setProject(project1);
            projectTransaction9.setGenre(Genre.COST);
            projectTransaction9.setType(type11);
            projectTransaction9.setExecuted(false);
            projectTransaction9.setCurrency(brlCurrency);

            type = new Type();
            type.setName("MANUTENÇÃO");
            typeRepository.save(type);

            ProjectTransaction projectTransaction8 = new ProjectTransaction();
            projectTransaction8.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            projectTransaction8.setName("Futura manutenção - Garantia");
            projectTransaction8.setFrequency(Frequency.DAILY);
            projectTransaction8.setValue((float)110.1);
            projectTransaction8.setGenre(Genre.COST);
            projectTransaction8.setType(type);
            projectTransaction8.setExecuted(false);
            projectTransaction8.setCurrency(brlCurrency);
            projectTransaction8.setInstallments(1);
            projectTransaction8.setProject(project1);

            projectTransactionRepository.save(projectTransaction9);
            projectTransactionRepository.save(projectTransaction8);


            type = new Type();
            type.setName("INFRA");
            typeRepository.save(type);

            SupplierTransaction supplierTransaction = new SupplierTransaction();
            supplierTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            supplierTransaction.setName("Serviço E-mail");
            supplierTransaction.setFrequency(Frequency.DAILY);
            supplierTransaction.setValue((float)528.3);
            supplierTransaction.setSupplier(supplier1);
            supplierTransaction.setGenre(Genre.COST);
            supplierTransaction.setType(type);
            supplierTransaction.setExecuted(false);
            supplierTransaction.setCurrency(brlCurrency);
            supplierTransactionRepository.save(supplierTransaction);


            type = new Type();
            type.setName("COMISSÃO");
            typeRepository.save(type);



            ComissionTransaction comissionTransaction= new ComissionTransaction();
            comissionTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            comissionTransaction.setName("PARA KALL-COMPSIS");
            comissionTransaction.setFrequency(Frequency.DAILY);
            comissionTransaction.setValue((float)2025.4);
            comissionTransaction.setClient(client3);
            comissionTransaction.setProject(project1);
            comissionTransaction.setGenre(Genre.COST);
            comissionTransaction.setType(type);
            comissionTransaction.setExecuted(false);
            comissionTransaction.setCurrency(brlCurrency);
            comissionTransactionRepository.save(comissionTransaction);

            type = new Type();
            type.setName("COMISSÃO");
            typeRepository.save(type);
            comissionTransactionRepository.save(comissionTransaction);


            type = new Type();
            type.setName("DESENVOLVIMENTO");
            typeRepository.save(type);

//            about sale transactiosn
            SaleTransaction saleTransaction = new SaleTransaction();
            saleTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            saleTransaction.setName("Licença Software");
            saleTransaction.setFrequency(Frequency.DAILY);
            saleTransaction.setValue((float)5002.3);
            saleTransaction.setGenre(Genre.REVENUE);
            saleTransaction.setType(type);
            saleTransaction.setExecuted(false);
            saleTransaction.setInstallments(3);
            saleTransaction.setCurrency(brlCurrency);
            saleTransactionRepository.save(saleTransaction);

            type = new Type();
            type.setName("DESENVOLVIMENTO");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType2);
            type.getSubTypeList().add(subType3);
            typeRepository.save(type);

            ProjectTransaction projectTransaction = new ProjectTransaction();
            projectTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(LocalDate.now().getMonthValue(), 12),getRandomNumberInRange(1,25)));
            projectTransaction.setName("Pagamento de desenvolvimento");
            projectTransaction.setFrequency(Frequency.DAILY);
            projectTransaction.setValue((float)2424.3);
            projectTransaction.setProject(project1);
            projectTransaction.setGenre(Genre.REVENUE);
            projectTransaction.setInstallments(5);
            projectTransaction.setType(type);
            projectTransaction.setExecuted(false);
            projectTransaction.setCurrency(brlCurrency);
            projectTransactionRepository.save(projectTransaction);


            type = new Type();
            type.setName("DESENVOLVIMENTO");
            typeRepository.save(type);

            projectTransaction = new ProjectTransaction();
            projectTransaction.setDate(LocalDate.now().plusDays(2));
            projectTransaction.setName("Desenvolvimento");
            projectTransaction.setFrequency(Frequency.DAILY);
            projectTransaction.setValue((float)1124.3);
            projectTransaction.setProject(project2);
            projectTransaction.setGenre(Genre.REVENUE);
            projectTransaction.setInstallments(2);
            projectTransaction.setType(type);
            projectTransaction.setExecuted(false);
            projectTransaction.setCurrency(brlCurrency);
            projectTransactionRepository.save(projectTransaction);
            /*
            -
            -
            -
            -
            SUPPLIER TRANSACTIONS
             */


            type = new Type();
            type.setName("INVESTIMENTO");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType2);
            typeRepository.save(type);

            supplierTransaction = new SupplierTransaction();
            supplierTransaction.setDate(LocalDate.of(2017,getRandomNumberInRange(5,12),getRandomNumberInRange(1,25)));
            supplierTransaction.setName("HD E CASE ZÉ");
            supplierTransaction.setFrequency(Frequency.DAILY);
            supplierTransaction.setValue((float)285.32);
            supplierTransaction.setSupplier(supplier3);
            supplierTransaction.setGenre(Genre.COST);
            supplierTransaction.setType(type);
            supplierTransaction.setExecuted(true);
            supplierTransaction.setCurrency(brlCurrency);
            supplierTransactionRepository.save(supplierTransaction);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType2);
            typeRepository.save(type);

            supplierTransaction = new SupplierTransaction();
            supplierTransaction.setDate(LocalDate.of(2017,getRandomNumberInRange(5,12),getRandomNumberInRange(1,25)));
            supplierTransaction.setName("HD E CASE CAIO");
            supplierTransaction.setFrequency(Frequency.DAILY);
            supplierTransaction.setValue((float)285.32);
            supplierTransaction.setSupplier(supplier3);
            supplierTransaction.setGenre(Genre.COST);
            supplierTransaction.setType(type);
            supplierTransaction.setExecuted(true);
            supplierTransaction.setCurrency(brlCurrency);
            supplierTransactionRepository.save(supplierTransaction);


            type = new Type();
            type.setName("INVESTIMENTO");
            typeRepository.save(type);

            type.setSubTypeList(new ArrayList<>());
            type.getSubTypeList().add(subType2);
            type.getSubTypeList().add(subType3);
            typeRepository.save(type);

            supplierTransaction = new SupplierTransaction();
            supplierTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            supplierTransaction.setName("LIXEIRA COZINHA");
            supplierTransaction.setFrequency(Frequency.DAILY);
            supplierTransaction.setValue((float)148.3);
            supplierTransaction.setSupplier(supplier3);
            supplierTransaction.setGenre(Genre.COST);
            supplierTransaction.setType(type);
            supplierTransaction.setExecuted(true);
            supplierTransaction.setCurrency(brlCurrency);
            supplierTransactionRepository.save(supplierTransaction);

            type = new Type();
            type.setName("INFRA");
            typeRepository.save(type);

            supplierTransaction = new SupplierTransaction();
            supplierTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            supplierTransaction.setName("TELEFONE");
            supplierTransaction.setFrequency(Frequency.MONTHLY);
            supplierTransaction.setValue((float)82.3);
            supplierTransaction.setSupplier(supplier4);
            supplierTransaction.setGenre(Genre.COST);
            supplierTransaction.setType(type);
            supplierTransaction.setExecuted(true);
            supplierTransaction.setCurrency(brlCurrency);
            supplierTransactionRepository.save(supplierTransaction);



            /*
            -
            -
            -

            COMISSION

            -
             -
             -
             -
             -
             */


            type = new Type();
            type.setName("COMISSÃO");
            typeRepository.save(type);

            comissionTransaction= new ComissionTransaction();
            comissionTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            comissionTransaction.setName("PARA KALL-COMPSIS");
            comissionTransaction.setFrequency(Frequency.DAILY);
            comissionTransaction.setValue((float)2025.4);
            comissionTransaction.setClient(client3);
            comissionTransaction.setProject(project1);
            comissionTransaction.setGenre(Genre.COST);
            comissionTransaction.setType(type);
            comissionTransaction.setExecuted(true);
            comissionTransaction.setCurrency(brlCurrency);
            comissionTransactionRepository.save(comissionTransaction);

            type = new Type();
            type.setName("COMISSÃO");
            typeRepository.save(type);

            
            comissionTransaction= new ComissionTransaction();
            comissionTransaction.setDate(LocalDate.of(2018,getRandomNumberInRange(1,LocalDate.now().getMonthValue()),getRandomNumberInRange(1,25)));
            comissionTransaction.setName("PARA KALL-IMAGEM");
            comissionTransaction.setFrequency(Frequency.DAILY);
            comissionTransaction.setValue((float)1971.25);
            comissionTransaction.setClient(client3);
            comissionTransaction.setProject(project2);
            comissionTransaction.setGenre(Genre.COST);
            comissionTransaction.setType(type);
            comissionTransaction.setExecuted(true);
            comissionTransaction.setCurrency(brlCurrency);
            comissionTransactionRepository.save(comissionTransaction);
        }
    }
}