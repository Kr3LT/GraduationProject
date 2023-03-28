package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.service.JwtTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    @Override
    public String generateAppointmentRejectionToken(Appointment appointment) {
        return null;
    }

    @Override
    public String generateAcceptRejectionToken(Appointment appointment) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public int getAppointmentIdFromToken(String token) {
        return 0;
    }

    @Override
    public int getCustomerIdFromToken(String token) {
        return 0;
    }

    @Override
    public int getProviderIdFromToken(String token) {
        return 0;
    }

    @Override
    public Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return null;
    }
}
