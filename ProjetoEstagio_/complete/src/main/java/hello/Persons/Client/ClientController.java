package hello.Persons.Client;


import hello.JsonResponse;
import hello.Persons.Client.Resources.Input.CreateClientDTO;
import hello.Persons.Client.Resources.Input.CreateContactDTO;
import hello.Persons.Client.Resources.Output.InfoClientDTO;
import hello.Persons.Client.Resources.Output.InfoContactPersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path="/client")
public class ClientController implements WebMvcConfigurer{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;


    @GetMapping("/")
    public String index(Model model) {
        /*
        TODO: APLICAR DTO AQUI
         */
        model.addAttribute("listClients", clientService.getAllClients());
        return "Client/clients_index";
    }


    @GetMapping("/add_client")
    public String addClient(Model model) {

        /*
        TODO: sera' q necessita mesmo de mandar o cliente?
         */
        model.addAttribute("clientDTO", new CreateClientDTO());
        return "Client/add_client";
    }


    /*
        TODO: mudar isto do add_submit... meter igual ao de cima... "add_client" e nome do metodo tbm
     */
    @PostMapping("/add_submit")
    public String addSubmit(Model model, @Valid @ModelAttribute("clientDTO") CreateClientDTO clientDTO, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "Client/add_client";
        }

        Client aux = clientService.addClient(clientDTO);

        attributes.addAttribute("client_id", aux.getId());
        return "redirect:/client/add_contact";
    }

    @GetMapping("/add_contact")
    public String addContact(@RequestParam("client_id") Long clientId, Model model) {

        CreateContactDTO createContactDTO = new CreateContactDTO();
        createContactDTO.setClientId(clientId);

        /*
            TODO: verificar se isto e' a melhor maneira... provavelmente n devia devolver lista de contactos.. mas sim um DTO
         */
        List<ContactPerson> listaContactos = clientService.getClient(clientId).getContactPerson();
        createContactDTO.setContacts(listaContactos);

        model.addAttribute("contactDTO", createContactDTO);
        return "Client/Contact/add_contact";
    }

    @RequestMapping(value = "add_contact", method = RequestMethod.POST)
    public String addContact(@Valid @RequestBody CreateContactDTO contactDTO, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
//            model.addAttribute("contactDTO", contactDTO);
//            return "Client/Contact/add_contact_form :: form";
            return "Client/Contact/add_contact";
        }

        clientService.addContact(contactDTO);

//        ModelAndView mav = new ModelAndView("Client/Contact/add_contact_line :: line");
        //ModelAndView mav = new ModelAndView("Client/Contact/add_contact_line :: line");
        //mav.addObject("contactDTO", contactDTO);

        model.addAttribute("contactDTO", contactDTO);

        return "Client/Contact/add_contact_line :: line";
    }

    @RequestMapping("/info_client")
    public String infoClient(@RequestParam("id") Long id, Model model) {

        /*
        TODO: fazer validacoes aqui. Imagina q n existe o id? tem de retornar p um pagina q indicara' isso
         */
        Client c = clientService.getClient(id);

        //convert entity to DTO
        InfoClientDTO infoClientDTO = new InfoClientDTO();
        infoClientDTO.setName(c.getName());
        infoClientDTO.setNumberPhone(c.getNumberPhone());
        infoClientDTO.setRegistrationCode(c.getRegistrationCode());
        infoClientDTO.setId(c.getId());
        infoClientDTO.setContactPerson(c.getContactPerson());
        model.addAttribute("clientDTO", infoClientDTO);
        return "Client/info_client";
    }

    @RequestMapping("/info_contact_client")
    public String infoContactClient(@RequestParam("id_client") Long clientId,@RequestParam("id_contact") Long contactId, Model model) {

        ContactPerson cp = clientService.getContactPerson(clientId, contactId);

        //convert entity to DTO
        InfoContactPersonDTO infoContactDTO = new InfoContactPersonDTO();
        infoContactDTO.setAdress(cp.getAdress());
        infoContactDTO.setEmail(cp.getEmail());
        infoContactDTO.setId(cp.getId());
        infoContactDTO.setClientId(clientId);
        infoContactDTO.setName(cp.getName());
        infoContactDTO.setNumberPhone(cp.getNumberPhone());
        model.addAttribute("contactDTO", infoContactDTO);

        return "Client/Contact/info_contact";
    }



//    @RequestMapping("/editclient")
//    public String editClient(@RequestParam("id") Long id, Model model) {
//
//        Client c = clientService.getClient(id);
//
//        model.addAttribute("client", c);
//        return "Client/edit_client";
//    }
//
//
//    @PostMapping("/edit_submit")
//    public String editSubmit(@Valid Client client, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "edit_client";
//        }
//
//        clientService.editClient(client);
//
//        return "redirect:/client/";
//    }

//    @RequestMapping("/removeclient")
//    public String removeClient(@RequestParam("id") Long id, Model model) {
//
//        Client c = clientRepository.getOne(id);
//
//        model.addAttribute("client", c);
//        return "Client/remove_client";
//    }
//    @RequestMapping("/removeclientid")
//    public String removeClientId(@RequestParam("id") Long id, Model model) {
//
//        Client c = clientService.getClient(id);
//
//        clientService.deleteClient(c);
//        return "redirect:/client/";
//    }

//
//    @GetMapping("/search_submit")
//    public String searchSubmit(@RequestParam(name="value_filter", required=false) String value, Model model) {
//
//        List<Client> clientList;
//
//        if(value.isEmpty()){
//            model.addAttribute("listClients", clientRepository.findAll());
//            return "Client/clients_index";
//        }
//
//        clientList = clientRepository.findByNameContaining(value);
//        if(!clientList.isEmpty()){
//            model.addAttribute("listClients", clientList);
//            return "Client/clients_index";
//        }
//
//        clientList = clientRepository.findByEmail(value);
//        model.addAttribute("listClients", clientList);
//        return "Client/clients_index";
//    }




}
