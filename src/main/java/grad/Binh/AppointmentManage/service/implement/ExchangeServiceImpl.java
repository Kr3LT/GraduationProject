package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.entity.AppointmentStatus;
import grad.Binh.AppointmentManage.entity.ExchangeRequest;
import grad.Binh.AppointmentManage.entity.ExchangeStatus;
import grad.Binh.AppointmentManage.entity.user.customer.Customer;
import grad.Binh.AppointmentManage.repository.AppointmentRepository;
import grad.Binh.AppointmentManage.repository.ExchangeRequestRepository;
import grad.Binh.AppointmentManage.service.ExchangeService;
import grad.Binh.AppointmentManage.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final AppointmentRepository appointmentRepository;
    private final NotificationService notificationService;
    private final ExchangeRequestRepository exchangeRequestRepository;

    public ExchangeServiceImpl(AppointmentRepository appointmentRepository, NotificationService notificationService, ExchangeRequestRepository exchangeRequestRepository) {
        this.appointmentRepository = appointmentRepository;
        this.notificationService = notificationService;
        this.exchangeRequestRepository = exchangeRequestRepository;
    }

    @Override
    public boolean checkIfEligibleForExchange(int userId, int appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        return appointment.getStart().minusHours(24)
                .isAfter(LocalDateTime.now())
                && appointment.getStatus().equals(AppointmentStatus.SCHEDULED)
                && appointment.getCustomer().getId() == userId;
    }

    @Override
    public List<Appointment> getEligibleAppointmentsForExchange(int appointmentId) {
        Appointment appointmentToExchange = appointmentRepository.findById(appointmentId).orElseThrow();
        return appointmentRepository.getEligibleAppointmentsForExchange(LocalDateTime.now().plusHours(24), appointmentToExchange.getCustomer().getId(), appointmentToExchange.getProvider().getId(), appointmentToExchange.getWork().getId());
    }

    @Override
    public boolean checkIfExchangeIsPossible(int oldAppointmentId, int newAppointmentId, int userId) {
        Appointment oldAppointment = appointmentRepository.findById(oldAppointmentId).orElseThrow();
        Appointment newAppointment = appointmentRepository.findById(newAppointmentId).orElseThrow();
        if (oldAppointment.getCustomer().getId() == userId) {
            return oldAppointment.getWork().getId().equals(newAppointment.getWork().getId())
                    && oldAppointment.getProvider().getId().equals(newAppointment.getProvider().getId())
                    && oldAppointment.getStart().minusHours(24).isAfter(LocalDateTime.now())
                    && newAppointment.getStart().minusHours(24).isAfter(LocalDateTime.now());
        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }

    }

    @Override
    public boolean acceptExchange(int exchangeId, int userId) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeId).orElseThrow();
        Appointment requestor = exchangeRequest.getRequestor();
        Appointment requested = exchangeRequest.getRequested();
        Customer tempCustomer = requestor.getCustomer();
        requestor.setStatus(AppointmentStatus.SCHEDULED);
        exchangeRequest.setStatus(ExchangeStatus.ACCEPTED);
        requestor.setCustomer(requested.getCustomer());
        requested.setCustomer(tempCustomer);
        exchangeRequestRepository.save(exchangeRequest);
        appointmentRepository.save(requested);
        appointmentRepository.save(requestor);
        notificationService.newExchangeAcceptedNotification(exchangeRequest, true);
        return true;
    }

    @Override
    public boolean rejectExchange(int exchangeId, int userId) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(exchangeId).orElseThrow();
        Appointment requestor = exchangeRequest.getRequestor();
        exchangeRequest.setStatus(ExchangeStatus.REJECTED);
        requestor.setStatus(AppointmentStatus.SCHEDULED);
        exchangeRequestRepository.save(exchangeRequest);
        appointmentRepository.save(requestor);
        notificationService.newExchangeRejectedNotification(exchangeRequest, true);
        return true;
    }

    @Override
    public boolean requestExchange(int oldAppointmentId, int newAppointmentId, int userId) {
        if (checkIfExchangeIsPossible(oldAppointmentId, newAppointmentId, userId)) {
            Appointment oldAppointment = appointmentRepository.findById(oldAppointmentId).orElseThrow();
            Appointment newAppointment = appointmentRepository.findById(newAppointmentId).orElseThrow();
            oldAppointment.setStatus(AppointmentStatus.EXCHANGE_REQUESTED);
            appointmentRepository.save(oldAppointment);
            ExchangeRequest exchangeRequest = new ExchangeRequest(ExchangeStatus.PENDING, newAppointment,  oldAppointment);
            exchangeRequestRepository.save(exchangeRequest);
            notificationService.newExchangeRequestedNotification(oldAppointment, newAppointment, true);
            return true;
        }
        return false;
    }
}
