package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Override
    public boolean checkIfEligibleForExchange(int userId, int appointmentId) {
        return false;
    }

    @Override
    public List<Appointment> getEligibleAppointmentsForExchange(int appointmentId) {
        return null;
    }

    @Override
    public boolean checkIfExchangeIsPossible(int oldAppointmentId, int newAppointmentId, int userId) {
        return false;
    }

    @Override
    public boolean acceptExchange(int exchangeId, int userId) {
        return false;
    }

    @Override
    public boolean rejectExchange(int exchangeId, int userId) {
        return false;
    }

    @Override
    public boolean requestExchange(int oldAppointmentId, int newAppointmentId, int userId) {
        return false;
    }
}
