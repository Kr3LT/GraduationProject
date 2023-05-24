package grad.Binh.AppointmentManage.controller;

import grad.Binh.AppointmentManage.configuration.security.CustomUserDetails;
import grad.Binh.AppointmentManage.entity.user.customer.Customer;
import grad.Binh.AppointmentManage.model.ChangePasswordForm;
import grad.Binh.AppointmentManage.model.UserForm;
import grad.Binh.AppointmentManage.service.AppointmentService;
import grad.Binh.AppointmentManage.service.UserService;
import grad.Binh.AppointmentManage.validation.group.CreateCorporateCustomer;
import grad.Binh.AppointmentManage.validation.group.CreateUser;
import grad.Binh.AppointmentManage.validation.group.UpdateCorporateCustomer;
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
@RequestMapping("/customers")
public class CustomerController {
    private final UserService userService;
    private final AppointmentService appointmentService;

    private final String USER_CREATE_FORM = "users/userCreateForm";

    private final String ACCOUNT_TYPE = "account_type";


    public CustomerController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    public String showAllCustomers(Model model) {
        model.addAttribute("customers", userService.getAllCustomers());
        return "users/userListCustomer";
    }

    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable int id, Model model) {
        Customer customer = userService.getCustomerById(id);
        if (customer.hasRole("ROLE_CUSTOMER_CORPORATE")) {
            if (!model.containsAttribute("user")) {
                model.addAttribute("user", new UserForm(userService.getCorporateCustomerById(id)));
            }
            model.addAttribute(ACCOUNT_TYPE, "customer_corporate");
            model.addAttribute("formActionProfile", "/customers/corporate/update/profile");
        } else if (customer.hasRole("ROLE_CUSTOMER_RETAIL")) {
            if (!model.containsAttribute("user")) {
                model.addAttribute("user", new UserForm(userService.getRetailCustomerById(id)));
            }
            model.addAttribute(ACCOUNT_TYPE, "customer_retail");
            model.addAttribute("formActionProfile", "/customers/retail/update/profile");
        }
        if (!model.containsAttribute("passwordChange")) {
            model.addAttribute("passwordChange", new ChangePasswordForm(id));
        }
        model.addAttribute("formActionPassword", "/customers/update/password");
        model.addAttribute("numberOfScheduledAppointments", appointmentService.getNumberOfScheduledAppointmentsForUser(id));
        model.addAttribute("numberOfCanceledAppointments", appointmentService.getNumberOfCanceledAppointmentsForUser(id));
        return "users/userUpdateForm";
    }

    @PostMapping("/corporate/update/profile")
    public String processCorporateCustomerProfileUpdate(@Validated({UpdateUser.class, UpdateCorporateCustomer.class}) @ModelAttribute("user") UserForm user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/customers/" + user.getId();
        }
        userService.updateCorporateCustomerProfile(user);
        return "redirect:/customers/" + user.getId();
    }

    @PostMapping("/retail/update/profile")
    public String processRetailCustomerProfileUpdate(@Validated({UpdateUser.class}) @ModelAttribute("user") UserForm user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/customers/" + user.getId();
        }
        userService.updateRetailCustomerProfile(user);
        return "redirect:/customers/" + user.getId();
    }


    @GetMapping("/new/{customer_type}")
    public String showCustomerRegistrationForm(@PathVariable("customer_type") String customerType, Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        if (currentUser != null) {
            return "redirect:/";
        }
        if (customerType.equals("corporate")) {
            model.addAttribute(ACCOUNT_TYPE, "customer_corporate");
            model.addAttribute("registerAction", "/customers/new/corporate");
        } else if (customerType.equals("retail")) {
            model.addAttribute(ACCOUNT_TYPE, "customer_retail");
            model.addAttribute("registerAction", "/customers/new/retail");
        } else {
            throw new RuntimeException();
        }
        model.addAttribute("user", new UserForm());
        return "users/userCreateForm";
    }


    @PostMapping("/new/retail")
    public String processReatilCustomerRegistration(@Validated({CreateUser.class}) @ModelAttribute("user") UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateModel(model, userForm, "customer_retail", "/customers/new/retail", null);
            return USER_CREATE_FORM;
        }
        userService.saveNewRetailCustomer(userForm);
        model.addAttribute("createdUserName", userForm.getUserName());
        return "users/userLogin";
    }

    @PostMapping("/new/corporate")
    public String processCorporateCustomerRegistration(@Validated({CreateUser.class, CreateCorporateCustomer.class}) @ModelAttribute("user") UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateModel(model, userForm, "customer_corporate", "/customers/new/corporate", null);
            return USER_CREATE_FORM;
        }
        userService.saveNewCorporateCustomer(userForm);
        model.addAttribute("createdUserName", userForm.getUserName());
        return "users/userLogin";
    }


    @PostMapping("/update/password")
    public String processCustomerPasswordUpdate(@Valid @ModelAttribute("passwordChange") ChangePasswordForm passwordChange, BindingResult bindingResult, @AuthenticationPrincipal CustomUserDetails currentUser, RedirectAttributes redirectAttributes) {
       if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.passwordChange", bindingResult);
            redirectAttributes.addFlashAttribute("passwordChange", passwordChange);
            return "redirect:/customers/" + currentUser.getId();
        }
        userService.updateUserPassword(passwordChange);
        return "redirect:/customers/" + currentUser.getId();
    }

    @PostMapping("/delete")
    public String processDeleteCustomerRequest(@RequestParam("customerId") int customerId) {
        userService.deleteUserById(customerId);
        return "redirect:/customers/all";
    }

    public Model populateModel(Model model, UserForm user, String account_type, String action, String error) {
        model.addAttribute("user", user);
        model.addAttribute(ACCOUNT_TYPE, account_type);
        model.addAttribute("registerAction", action);
        model.addAttribute("registrationError", error);
        return model;
    }
}
