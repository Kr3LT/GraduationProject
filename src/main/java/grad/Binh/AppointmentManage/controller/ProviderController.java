package grad.Binh.AppointmentManage.controller;

import grad.Binh.AppointmentManage.configuration.security.CustomUserDetails;
import grad.Binh.AppointmentManage.entity.WorkingPlan;
import grad.Binh.AppointmentManage.model.ChangePasswordForm;
import grad.Binh.AppointmentManage.model.TimePeriod;
import grad.Binh.AppointmentManage.model.UserForm;
import grad.Binh.AppointmentManage.service.AppointmentService;
import grad.Binh.AppointmentManage.service.UserService;
import grad.Binh.AppointmentManage.service.WorkService;
import grad.Binh.AppointmentManage.service.WorkingPlanService;
import grad.Binh.AppointmentManage.validation.group.CreateProvider;
import grad.Binh.AppointmentManage.validation.group.CreateUser;
import grad.Binh.AppointmentManage.validation.group.UpdateProvider;
import grad.Binh.AppointmentManage.validation.group.UpdateUser;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/providers")
public class ProviderController {
    private final UserService userService;
    private final WorkService workService;
    private final WorkingPlanService workingPlanService;
    private final AppointmentService appointmentService;

    public ProviderController(UserService userService, WorkService workService, WorkingPlanService workingPlanService, AppointmentService appointmentService) {
        this.userService = userService;
        this.workService = workService;
        this.workingPlanService = workingPlanService;
        this.appointmentService = appointmentService;
    }


    @GetMapping("/all")
    public String showAllProviders(Model model) {
        model.addAttribute("providers", userService.getAllProviders());
        return "users/userListProvider";
    }

    @GetMapping("/{id}")
    public String showProviderDetails(@PathVariable("id") int providerId, Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        if (currentUser.getId() == providerId || currentUser.hasRole("ROLE_ADMIN")) {
            if (!model.containsAttribute("user")) {
                model.addAttribute("user", new UserForm(userService.getProviderById(providerId)));
            }
            if (!model.containsAttribute("passwordChange")) {
                model.addAttribute("passwordChange", new ChangePasswordForm(providerId));
            }
            model.addAttribute("account_type", "provider");
            model.addAttribute("formActionProfile", "/providers/update/profile");
            model.addAttribute("formActionPassword", "/providers/update/password");
            model.addAttribute("allWorks", workService.getAllWorks());
            model.addAttribute("numberOfScheduledAppointments", appointmentService.getNumberOfScheduledAppointmentsForUser(providerId));
            model.addAttribute("numberOfCanceledAppointments", appointmentService.getNumberOfCanceledAppointmentsForUser(providerId));
            return "users/userUpdateForm";

        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }
    }

    @PostMapping("/update/profile")
    public String processProviderUpdate(@Validated({UpdateUser.class, UpdateProvider.class}) @ModelAttribute("user") UserForm userUpdateData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", userUpdateData);
            return "redirect:/providers/" + userUpdateData.getId();
        }
        userService.updateProviderProfile(userUpdateData);
        return "redirect:/providers/" + userUpdateData.getId();
    }

    @GetMapping("/new")
    public String showProviderRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) model.addAttribute("user", new UserForm());
        model.addAttribute("account_type", "provider");
        model.addAttribute("registerAction", "/providers/new");
        model.addAttribute("allWorks", workService.getAllWorks());
        return "users/userCreateForm";
    }

    @PostMapping("/new")
    public String processProviderRegistrationForm(@Validated({CreateUser.class, CreateProvider.class}) @ModelAttribute("user") UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", userForm);
            return "redirect:/providers/new";
        }
        userService.saveNewProvider(userForm);
        return "redirect:/providers/all";
    }

    @PostMapping("/delete")
    public String processDeleteProviderRequest(@RequestParam("providerId") int providerId) {
        userService.deleteUserById(providerId);
        return "redirect:/providers/all";
    }

    @GetMapping("/availability")
    public String showProviderAvailability(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        var plan = workingPlanService.getWorkingPlanByProviderId(currentUser.getId());
        System.out.println(plan.getMonday().getWorkingHours().getStart());
        model.addAttribute("plan", workingPlanService.getWorkingPlanByProviderId(currentUser.getId()));
        model.addAttribute("breakModel", new TimePeriod());
        return "users/userProviderAvailable";
    }

    @PostMapping("/availability")
    public String processProviderWorkingPlanUpdate(@ModelAttribute("plan") WorkingPlan plan) {
        workingPlanService.updateWorkingPlan(plan);
        return "redirect:/providers/availability";
    }

    @PostMapping("/availability/breakes/add")
    public String processProviderAddBreak(@ModelAttribute("breakModel") TimePeriod breakToAdd, @RequestParam("planId") int planId, @RequestParam("dayOfWeek") String dayOfWeek) {
        workingPlanService.addBreakToWorkingPlan(breakToAdd, planId, dayOfWeek);
        return "redirect:/providers/availability";
    }

    @PostMapping("/availability/breakes/delete")
    public String processProviderDeleteBreak(@ModelAttribute("breakModel") TimePeriod breakToDelete, @RequestParam("planId") int planId, @RequestParam("dayOfWeek") String dayOfWeek) {
        workingPlanService.deleteBreakFromWorkingPlan(breakToDelete, planId, dayOfWeek);
        return "redirect:/providers/availability";
    }

    @PostMapping("/update/password")
    public String processProviderPasswordUpdate(@Valid @ModelAttribute("passwordChange") ChangePasswordForm passwordChange, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.passwordChange", bindingResult);
            redirectAttributes.addFlashAttribute("passwordChange", passwordChange);
            return "redirect:/providers/" + passwordChange.getId();
        }
        userService.updateUserPassword(passwordChange);
        return "redirect:/providers/" + passwordChange.getId();
    }

}
