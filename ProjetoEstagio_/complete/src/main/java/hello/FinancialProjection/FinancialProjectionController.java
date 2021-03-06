package hello.FinancialProjection;

import hello.Client.ClientService;
import hello.Currency.CurrencyService;
import hello.Employee.EmployeeService;
import hello.Enums.Genre;
import hello.FinancialProjection.Resources.FinancialProjection;
import hello.FinancialProjection.Resources.FinancialProjectionValidated;
import hello.GeneralTransaction.GeneralTransaction;
import hello.Pager;
import hello.Project.ProjectService;
import hello.SubType.SubTypeService;
import hello.Supplier.SupplierService;
import hello.Transaction.Transaction;
import hello.Transaction.TransactionService;
import hello.Type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

import static hello.Application.*;

@Controller
@RequestMapping(path="/financial_projection")

public class FinancialProjectionController {

    @Autowired
    FinancialProjectionService financialProjectionService;
    @Autowired
    TypeService typeService;
    @Autowired
    SubTypeService subTypeService;
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ClientService clientService;
    @Autowired
    SupplierService supplierService;

    @Autowired
    CurrencyService currencyService;


    @GetMapping("/costs")
    public ModelAndView costsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam(name="value_filter", required=false) String value,
                                        @RequestParam(name="frequency", required=false) String frequency,
                                        @RequestParam(name="type_value", required=false) String typeValue,
                                        @RequestParam(name="subtype_value", required=false) String subTypeValue,
                                        @RequestParam(name="date_since", required=false) String dateSince,
                                        @RequestParam(name="date_until", required=false) String dateUntil,
                                        @RequestParam(name="value_since", required=false) String valueSince,
                                        @RequestParam(name="value_until", required=false) String valueUntil,
                                  @RequestParam(name="switch_deleted_entities", required=false) Boolean deletedEntities)

    {
        ModelAndView modelAndView = new ModelAndView("FinancialProjection/index");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Transaction> transactions = financialProjectionService.findAllPageableByGenre(PageRequest.of(evalPage, evalPageSize), value, frequency, typeValue, subTypeValue, dateSince, dateUntil, valueSince, valueUntil, deletedEntities, Genre.COST, false);

        Pager pager = new Pager(transactions.getTotalPages(), transactions.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("listEntities", transactions);

        //        ISTO E' APENAS PARA QUANDO NAO TEM A APRESENTAR SUBTYPES DE TYPES
        modelAndView.addObject("types", typeService.getDistinctTypes());
        modelAndView.addObject("subTypes", subTypeService.getDistinctSubTypesActived());

        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("value_filter", value);
        modelAndView.addObject("frequency", frequency);
        modelAndView.addObject("type_value", typeValue);
        modelAndView.addObject("subtype_value", subTypeValue);
        modelAndView.addObject("date_since", dateSince);
        modelAndView.addObject("date_until", dateUntil);
        modelAndView.addObject("value_since", valueSince);
        modelAndView.addObject("value_until", valueUntil);
        modelAndView.addObject("switch_deleted_entities", deletedEntities);
        modelAndView.addObject("currency", currencyService.getCurrentCurrencySelected());
        return modelAndView;
    }

    @GetMapping("/transaction_executed")
    public String executedTransaction(Model model, @RequestParam("id") Long id) {

        Transaction transaction = financialProjectionService.getTransaction(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("projects", projectService.getProjects());

        return "FinancialProjection/executed_transaction";
    }
    @PostMapping("/transaction_executed")
    public String executedTransaction(Model model, @Valid @ModelAttribute("financialProjectionAproved") FinancialProjectionValidated financialProjectionValidated, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "FinancialProjection/executed_transaction";
        }

        financialProjectionService.aproveTransaction(financialProjectionValidated);

        if(financialProjectionValidated.getGenre().equals(Genre.REVENUE))
            return "redirect:/financial_projection/revenues";
        else
            return "redirect:/financial_projection/costs";

    }



    @GetMapping("/revenues")
    public ModelAndView revenuesPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam(name="value_filter", required=false) String value,
                                        @RequestParam(name="frequency", required=false) String frequency,
                                        @RequestParam(name="type_value", required=false) String typeValue,
                                        @RequestParam(name="subtype_value", required=false) String subTypeValue,
                                        @RequestParam(name="date_since", required=false) String dateSince,
                                        @RequestParam(name="date_until", required=false) String dateUntil,
                                        @RequestParam(name="value_since", required=false) String valueSince,
                                        @RequestParam(name="value_until", required=false) String valueUntil,
                                     @RequestParam(name="switch_deleted_entities", required=false) Boolean deletedEntities)

    {
        ModelAndView modelAndView = new ModelAndView("FinancialProjection/index");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Transaction> transactions = financialProjectionService.findAllPageableByGenre(PageRequest.of(evalPage, evalPageSize), value, frequency, typeValue, subTypeValue, dateSince, dateUntil, valueSince, valueUntil, deletedEntities, Genre.REVENUE, false);

        Pager pager = new Pager(transactions.getTotalPages(), transactions.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("listEntities", transactions);

        //        ISTO E' APENAS PARA QUANDO NAO TEM A APRESENTAR SUBTYPES DE TYPES
        modelAndView.addObject("types", typeService.getDistinctTypes());
        modelAndView.addObject("subTypes", subTypeService.getDistinctSubTypesActived());

        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("value_filter", value);
        modelAndView.addObject("frequency", frequency);
        modelAndView.addObject("type_value", typeValue);
        modelAndView.addObject("subtype_value", subTypeValue);
        modelAndView.addObject("date_since", dateSince);
        modelAndView.addObject("date_until", dateUntil);
        modelAndView.addObject("value_since", valueSince);
        modelAndView.addObject("value_until", valueUntil);
        modelAndView.addObject("switch_deleted_entities", deletedEntities);
        modelAndView.addObject("currency", currencyService.getCurrentCurrencySelected());
        return modelAndView;
    }

    @GetMapping("/add_cost")
    public String addCost(Model model) {

        FinancialProjection transaction = new FinancialProjection(); //e' apenas um resource... apenas para ser preenchido e dps e' tratado
        transaction.setCurrency(currencyService.getCurrentCurrencySelected());
        transaction.setGenre(Genre.COST);
        model.addAttribute("transaction", transaction);
        model.addAttribute("types", typeService.getDistinctTypesActivedAndManuallyCreated());
        model.addAttribute("projects", projectService.getProjectsActived());
        model.addAttribute("employees", employeeService.getActivedEmployees());
        model.addAttribute("suppliers", supplierService.getActivedSuppliers());
        model.addAttribute("clients", clientService.getClientsActived());


        return "FinancialProjection/add_transaction";
    }

    @GetMapping("/add_revenue")
    public String addRevenue(Model model) {

        FinancialProjection transaction = new FinancialProjection(); //e' apenas um resource... apenas para ser preenchido e dps e' tratado
        transaction.setCurrency(currencyService.getCurrentCurrencySelected());
        transaction.setGenre(Genre.REVENUE);
        model.addAttribute("transaction", transaction);
        model.addAttribute("types", typeService.getDistinctTypesActivedAndManuallyCreated());
        model.addAttribute("projects", projectService.getProjectsActived());
        model.addAttribute("employees", employeeService.getActivedEmployees());
        model.addAttribute("suppliers", supplierService.getActivedSuppliers());
        model.addAttribute("clients", clientService.getClientsActived());


        return "FinancialProjection/add_transaction";
    }

    @PostMapping("/add_transaction")
    public String addRevenue(Model model, @Valid @ModelAttribute("transaction") FinancialProjection transaction, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.getDistinctTypesActivedAndManuallyCreated());
            return "GeneralTransaction/add_transaction";
        }
        financialProjectionService.addTransaction(transaction);

        if(transaction.getGenre().equals(Genre.REVENUE))
            return "redirect:/financial_projection/revenues";
        else
            return "redirect:/financial_projection/costs";

    }

    @GetMapping("/edit_transaction")
    public String editTransaction(Model model,@RequestParam("id") Long id) {

        FinancialProjection transaction = financialProjectionService.getFinancialProjection(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("types", typeService.getDistinctTypesActivedAndManuallyCreated());
        model.addAttribute("projects", projectService.getProjectsActived());
        model.addAttribute("employees", employeeService.getActivedEmployees());
        model.addAttribute("suppliers", supplierService.getActivedSuppliers());
        model.addAttribute("clients", clientService.getClientsActived());


        return "FinancialProjection/edit_transaction";


    }
    @PostMapping("/edit_transaction")
    public String editTransaction(Model model, @Valid @ModelAttribute("transaction") FinancialProjection transaction, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("transaction", transaction);
            model.addAttribute("types", typeService.getDistinctTypesActivedAndManuallyCreated());
            model.addAttribute("projects", projectService.getProjectsActived());
            model.addAttribute("employees", employeeService.getActivedEmployees());
            model.addAttribute("suppliers", supplierService.getActivedSuppliers());
            model.addAttribute("clients", clientService.getClientsActived());
            return "ProjectTransaction/edit_transaction";
        }
        financialProjectionService.editTransaction(transaction);

        if(transaction.getGenre().equals(Genre.REVENUE))
            return "redirect:/financial_projection/revenues";
        else
            return "redirect:/financial_projection/costs";

    }

    @GetMapping("/info_transaction")
    public String infoTransaction(Model model, @RequestParam("id") Long id) {

        Transaction transaction = financialProjectionService.getTransaction(id);
        model.addAttribute("transaction", transaction);
        return "FinancialProjection/info_transaction";
    }


    @RequestMapping("/remove_transaction")
    public String removeTransaction(@RequestParam("id") Long id, Model model) {

        Transaction transaction = financialProjectionService.getTransaction(id);
        model.addAttribute("transaction", transaction);

        return "FinancialProjection/remove_transaction :: modal";
    }
    @DeleteMapping("/remove_transaction")
    public @ResponseBody String removeTransaction(@RequestParam("id") Long id) {
        financialProjectionService.removeTransaction(id);
        return "redirect:/financial_projection/costs";
    }

    @RequestMapping("/recovery_transaction")
    public String recoveryTransaction(@RequestParam("id") Long id, Model model) {

        Transaction transaction = financialProjectionService.getTransaction(id);
        model.addAttribute("transaction", transaction);

        return "FinancialProjection/recovery_transaction :: modal";
    }
    @PostMapping("/recovery_transaction")
    public @ResponseBody String recoveryTransaction(@RequestParam("id") Long id) {
        financialProjectionService.recoveryTransaction(id);
        return "redirect:/financial_projection/";
    }
}
