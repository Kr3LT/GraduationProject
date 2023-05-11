package grad.Binh.AppointmentManage.controller;

import com.google.common.collect.Lists;
import grad.Binh.AppointmentManage.Enum.RoleEnum;
import grad.Binh.AppointmentManage.configuration.security.CustomUserDetails;
import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.model.AppointmentRegisterForm;
import grad.Binh.AppointmentManage.service.AppointmentService;
import grad.Binh.AppointmentManage.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AjaxController {
    private final AppointmentService appointmentService;
    private final NotificationService notificationService;

    public AjaxController(AppointmentService appointmentService, NotificationService notificationService) {
        this.appointmentService = appointmentService;
        this.notificationService = notificationService;
    }
    @GetMapping("/user/{userId}/appointments")
    public List<Appointment> getAppointmentsForUser(@PathVariable("userId") int userId, @AuthenticationPrincipal CustomUserDetails currentUser){
        if(currentUser.hasRole(RoleEnum.CUSTOMER.getValue()))
            return appointmentService.getAppointmentByCustomerId(currentUser.getId());
        else if (currentUser.hasRole("ROLE_PROVIDER")){
            return appointmentService.getAppointmentByProviderId(currentUser.getId());
        } else if (currentUser.hasRole("ROLE_ADMIN")) {
            return appointmentService.getAllAppointments();
        }
        return Lists.newArrayList();
    }
    @GetMapping("/availableHours/{providerId}/{workId}/{date}")
    public List<AppointmentRegisterForm> getAvailableHour(@PathVariable("providerId") int providerId,
                                                          @PathVariable("workId") int workId,
                                                          @PathVariable("date") String date,
                                                          @AuthenticationPrincipal CustomUserDetails currentUser){
        LocalDate localDate = LocalDate.parse(date);
        return appointmentService.getAvailableHours(providerId, currentUser.getId(), workId, localDate)
                .stream()
                .map(timePeriod -> AppointmentRegisterForm.builder()
                                .workId(workId)
                                .providerId(providerId)
                                .start(timePeriod.getStart().atDate(localDate))
                                .end(timePeriod.getEnd().atDate(localDate))
                                .build())
                .toList();
    }
    @GetMapping("/user/notifications")
    public int getUnreadNotificationsCount(@AuthenticationPrincipal CustomUserDetails currentUser){
        return notificationService.getUnreadNotifications(currentUser.getId()).size();
    }
}
