package hello.Project;

import hello.Client.ClientService;
import hello.Contact.Contact;
import hello.CostCenter.CostCenterService;
import hello.Project.Resources.ChartResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/project")
public class ProjectController implements WebMvcConfigurer {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CostCenterService costCenterService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listProjects", projectService.getProjects());
        return "Project/index";
    }

    @GetMapping("/add_project")
    public String addProject(Model model) {

        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("listClients", clientService.getClients());
        model.addAttribute("listCostsCenter", costCenterService.getCostsCenter());

        /*
        TODO: para validacao das datas na backend uma pessoa teria de criar a propria anotacao...
        https://stackoverflow.com/questions/1700295/java-bean-validation-jsr303-constraints-involving-relationship-between-several
         */

        return "Project/add_project";
    }

    @PostMapping("/add_project")
    public String addProject(Model model, @Valid @ModelAttribute("project") Project project, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listClients", clientService.getClients());
            model.addAttribute("listCostsCenter", costCenterService.getCostsCenter());
            return "Project/add_project";
        }
        projectService.addProject(project);

        model.addAttribute("listProjects", projectService.getProjects());

        return "redirect:/project/";
    }

    @GetMapping("/edit_project")
    public String editProject(Model model,@RequestParam("id") Long id) {
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("listClients", clientService.getClients());
        model.addAttribute("listCostsCenter", costCenterService.getCostsCenter());

        return "Project/edit_project";
    }
    @PostMapping("/edit_project")
    public String editProject(Model model, @Valid @ModelAttribute("project") Project project, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listClients", clientService.getClients());
            model.addAttribute("listCostsCenter", costCenterService.getCostsCenter());
            return "Project/edit_project";
        }
        projectService.editProject(project);

        model.addAttribute("listProjects", projectService.getProjects());

        return "redirect:/project/";
    }

    @RequestMapping("/remove_project")
    public String removeProject(@RequestParam("id") Long id, Model model) {

        Project project = projectService.getProject(id);
        model.addAttribute("project", project);

        return "Project/remove_project :: modal";
    }
    @DeleteMapping("/remove_project")
    public @ResponseBody String removeProject(@RequestParam("id") Long id) {
        projectService.removeProject(id);
        return "redirect:/client/";
    }

    @RequestMapping("/info_project")
    public String infoProject(@RequestParam("id") Long id, Model model) {

        Project project= projectService.getProject(id);
        model.addAttribute("project", project);
        return "Project/info_project";
    }


    @GetMapping("/get_chart_resource")
    @ResponseBody
    public ChartResource getChartResource(@RequestParam("id") Long id, Model model) {

        ChartResource chartResource = projectService.getStatistic(id);
        return chartResource;
    }



}
