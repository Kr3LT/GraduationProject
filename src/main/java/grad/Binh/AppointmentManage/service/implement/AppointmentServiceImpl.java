package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.entity.ChatMessage;
import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.model.TimePeriod;
import grad.Binh.AppointmentManage.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public void createNewAppointment(int workId, int providerId, int customerId, LocalDateTime start) {

    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void updateUserAppointmentsStatuses(int userId) {

    }

    @Override
    public void updateAllAppointmentsStatuses() {

    }

    @Override
    public void updateAppointmentsStatusesWithExpiredExchangeRequest() {

    }

    @Override
    public void deleteAppointmentById(int appointmentId) {

    }

    @Override
    public Appointment getAppointmentByIdWithAuthorization(int id) {
        return null;
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByProviderId(int providerId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByProviderAtDay(int providerId, LocalDate day) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByCustomerAtDay(int providerId, LocalDate day) {
        return null;
    }

    @Override
    public List<Appointment> getConfirmedAppointmentsByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Appointment> getCanceledAppointmentsByCustomerIdForCurrentMonth(int userId) {
        return null;
    }

    @Override
    public List<TimePeriod> getAvailableHours(int providerId, int customerId, int workId, LocalDate date) {
        return null;
    }

    @Override
    public List<TimePeriod> calculateAvailableHours(List<TimePeriod> availableTimePeroids, Work work) {
        return null;
    }

    @Override
    public List<TimePeriod> excludeAppointmentsFromTimePeroids(List<TimePeriod> peroids, List<Appointment> appointments) {
        return null;
    }

    @Override
    public String getCancelNotAllowedReason(int userId, int appointmentId) {
        return null;
    }

    @Override
    public void cancelUserAppointmentById(int appointmentId, int userId) {

    }

    @Override
    public boolean isCustomerAllowedToRejectAppointment(int customerId, int appointmentId) {
        return false;
    }

    @Override
    public boolean requestAppointmentRejection(int appointmentId, int customerId) {
        return false;
    }

    @Override
    public boolean requestAppointmentRejection(String token) {
        return false;
    }

    @Override
    public boolean isProviderAllowedToAcceptRejection(int providerId, int appointmentId) {
        return false;
    }

    @Override
    public boolean acceptRejection(int appointmentId, int providerId) {
        return false;
    }

    @Override
    public boolean acceptRejection(String token) {
        return false;
    }

    @Override
    public void addMessageToAppointmentChat(int appointmentId, int authorId, ChatMessage chatMessage) {

    }

    @Override
    public int getNumberOfCanceledAppointmentsForUser(int userId) {
        return 0;
    }

    @Override
    public int getNumberOfScheduledAppointmentsForUser(int userId) {
        return 0;
    }

    @Override
    public boolean isAvailable(int workId, int providerId, int customerId, LocalDateTime start) {
        return false;
    }
}
