package grad.Binh.AppointmentManage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends BaseEntity{
    @Column(name = "number")
    private String number;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private double totalAmount;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "issued")
    private LocalDateTime issued;

    @OneToMany(mappedBy = "invoice")
    private List<Appointment> appointments;

    public Invoice(String number, String status, LocalDateTime issued, List<Appointment> appointments2) {
        this.number = number;
        this.status = status;
        this.issued = issued;
        this.appointments = new ArrayList<>();
        for (Appointment a : appointments2) {
            this.appointments.add(a);
            a.setInvoice(this);
            totalAmount += a.getWork().getPrice();
        }
    }

}
