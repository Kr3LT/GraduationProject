package grad.Binh.AppointmentManage.entity;

import grad.Binh.AppointmentManage.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "works")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Work extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "duration")
    private int duration;

    @Column(name = "editable")
    private boolean editable;

    @Column(name = "target")
    private String targetCustomer;

    @ManyToMany
    @JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_work"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> providers;
}
