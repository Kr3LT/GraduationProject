package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Invoice;
import grad.Binh.AppointmentManage.configuration.security.CustomUserDetails;
import grad.Binh.AppointmentManage.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public void createNewInvoice(Invoice invoice) {

    }

    @Override
    public Invoice getInvoiceByAppointmentId(int appointmentId) {
        return null;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public void changeInvoiceStatusToPaid(int invoiceId) {

    }

    @Override
    public void issueInvoicesForConfirmedAppointments() {

    }

    @Override
    public String generateInvoiceNumber() {
        return null;
    }

    @Override
    public File generatePdfForInvoice(int invoiceId) {
        return null;
    }

    @Override
    public boolean isUserAllowedToDownloadInvoice(CustomUserDetails user, Invoice invoice) {
        return false;
    }
}
