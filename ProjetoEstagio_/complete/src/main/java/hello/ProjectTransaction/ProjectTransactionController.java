package hello.ProjectTransaction;

import hello.Enums.Frequency;
import hello.Enums.Genre;
import hello.Pager;
import hello.Project.ProjectService;
import hello.SubType.SubType;
import hello.Type.Type;
import hello.Type.TypeService;
import javafx.scene.media.SubtitleTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

import static hello.Application.*;

@Controller
@RequestMapping(path="/project_transaction")
public class ProjectTransactionController implements WebMvcConfigurer {

    @Autowired
    ProjectTransactionService projectTransactionService;

    @Autowired
    TypeService typeService;

    @Autowired
    ProjectService projectService;


//    @GetMapping("/revenue")
//    public String indexRevenue(Model model) {
//
//        model.addAttribute("listTransactions",projectTransactionService.getProjectsTransactionsByGenre(Genre.REVENUE));
//        return "ProjectTransaction/index";
//    }

    @GetMapping("/revenue")
    public ModelAndView showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam(name="value_filter", required=false) String value,
                                        @RequestParam(name="frequency", required=false) Frequency frequency,
                                        @RequestParam(name="type_id", required=false) Type type,
                                        @RequestParam(name="sub_type_id", required=false) SubType subType,
                                        @RequestParam(name="project_id", required=false) Long projectId,
                                        @RequestParam(name="date_since", required=false) String dateSince,
                                        @RequestParam(name="date_until", required=false) String dateUntil,
                                        @RequestParam(name="value_since", required=false) String valueSince,
                                        @RequestParam(name="value_until", required=false) String valueUntil)

    {
        ModelAndView modelAndView = new ModelAndView("ProjectTransaction/index");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;


       Page<ProjectTransaction> projectsTransactions = projectTransactionService.findAllPageableByGenre(PageRequest.of(evalPage, evalPageSize), value, frequency, type, subType, projectId, dateSince, dateUntil, valueSince, valueUntil, Genre.REVENUE);


        Pager pager = new Pager(projectsTransactions.getTotalPages(), projectsTransactions.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("listEntitys", projectsTransactions);

        modelAndView.addObject("types", typeService.getTypes());
        modelAndView.addObject("projects", projectService.getProjects());


        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
//        modelAndView.addObject("pager", pager);

        return modelAndView;
    }

    @GetMapping("/add_revenue")
    public String addRevenue(Model model) {

        ProjectTransaction revenue = new ProjectTransaction();
        revenue.setGenre(Genre.REVENUE);
        model.addAttribute("transaction", revenue);
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("projects", projectService.getProjects());

        return "ProjectTransaction/add_transaction";
    }

    @PostMapping("/add_transaction")
    public String addRevenue(Model model, @Valid @ModelAttribute("transaction") ProjectTransaction projectTransaction, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.getTypes());
            model.addAttribute("projects", projectService.getProjects());
            return "ProjectTransaction/add_transaction";
        }
        projectTransactionService.addTransaction(projectTransaction);

        return "redirect:/project_transaction/revenue";
    }

    @GetMapping("/edit_transaction")
    public String editTransaction(Model model,@RequestParam("id") Long id) {

        ProjectTransaction revenue = projectTransactionService.getProjectTransaction(id);
        model.addAttribute("transaction", revenue);
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("projects", projectService.getProjects());

        return "ProjectTransaction/edit_transaction";
    }
    @PostMapping("/edit_transaction")
    public String editTransaction(Model model, @Valid @ModelAttribute("transaction") ProjectTransaction projectTransaction, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.getTypes());
            model.addAttribute("projects", projectService.getProjects());
            return "ProjectTransaction/edit_transaction";
        }
        projectTransactionService.editProjectTransaction(projectTransaction);
        return "redirect:/project_transaction/revenue";
    }

    @RequestMapping("/remove_transaction")
    public String removeTransaction(@RequestParam("id") Long id, Model model) {

        ProjectTransaction projectTransaction = projectTransactionService.getProjectTransaction(id);
        model.addAttribute("transaction", projectTransaction);

        return "ProjectTransaction/remove_transaction :: modal";
    }
    @DeleteMapping("/remove_transaction")
    public @ResponseBody String removeTransaction(@RequestParam("id") Long id) {
        projectTransactionService.removeProjectTransaction(id);
        return "redirect:/project_transaction/revenue";
    }

    @RequestMapping("/info_transaction")
    public String infoProject(@Valid @RequestParam("id") Long id, Model model) {

        ProjectTransaction projectTransaction = projectTransactionService.getProjectTransaction(id);

        model.addAttribute("transaction", projectTransaction);
        return "ProjectTransaction/info_transaction";
    }

//    @GetMapping("/cost")
//    public String indexCost(Model model) {
//
//        model.addAttribute("listTransactions",projectTransactionService.getProjectsTransactionsByGenre(Genre.COST));
//        return "ProjectTransaction/index";
//    }

    @GetMapping("/cost")
    public ModelAndView indexCost(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam(name="value_filter", required=false) String value,
                                        @RequestParam(name="frequency", required=false) Frequency frequency,
                                        @RequestParam(name="type_id", required=false) Type type,
                                        @RequestParam(name="sub_type_id", required=false) SubType subType,
                                        @RequestParam(name="project_id", required=false) Long projectId,
                                        @RequestParam(name="date_since", required=false) String dateSince,
                                        @RequestParam(name="date_until", required=false) String dateUntil,
                                        @RequestParam(name="value_since", required=false) String valueSince,
                                        @RequestParam(name="value_until", required=false) String valueUntil)

    {
        ModelAndView modelAndView = new ModelAndView("ProjectTransaction/index");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);


        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;


        Page<ProjectTransaction> projectsTransactions = projectTransactionService.findAllPageableByGenre(PageRequest.of(evalPage, evalPageSize), value, frequency, type, subType, projectId, dateSince, dateUntil, valueSince, valueUntil,Genre.COST);


        Pager pager = new Pager(projectsTransactions.getTotalPages(), projectsTransactions.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("listEntitys", projectsTransactions);

        modelAndView.addObject("types", typeService.getTypes());
        modelAndView.addObject("projects", projectService.getProjects());


        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
//        modelAndView.addObject("pager", pager);

        return modelAndView;
    }

    @GetMapping("/add_cost")
    public String addCost(Model model) {

        ProjectTransaction cost = new ProjectTransaction();
        cost.setGenre(Genre.COST);
        model.addAttribute("transaction", cost);
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("projects", projectService.getProjects());

        return "ProjectTransaction/add_transaction";
    }

}
