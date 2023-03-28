package grad.Binh.AppointmentManage.configuration.security;

import grad.Binh.AppointmentManage.service.AppointmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AppointmentService appointmentService;

    public CustomAuthenticationSuccessHandler(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        if(currentUser.hasRole("ROLE_ADMIN")){
            appointmentService.updateAllAppointmentsStatuses();
        }else {
            appointmentService.updateUserAppointmentsStatuses(currentUser.getId());
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
