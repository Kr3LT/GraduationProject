package grad.Binh.AppointmentManage.entity.user;

import grad.Binh.AppointmentManage.entity.BaseEntity;
import grad.Binh.AppointmentManage.entity.Notification;
import grad.Binh.AppointmentManage.model.UserForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(UserForm newUserForm, String encryptedPassword, Collection<Role> roles) {
        this.setUserName(newUserForm.getUserName());
        this.setFirstName(newUserForm.getFirstName());
        this.setLastName(newUserForm.getLastName());
        this.setEmail(newUserForm.getEmail());
        this.setCity(newUserForm.getCity());
        this.setStreet(newUserForm.getStreet());
        this.setPostcode(newUserForm.getPostcode());
        this.setMobile(newUserForm.getMobile());
        this.password = encryptedPassword;
        this.roles = roles;
    }

    public void update(UserForm updateData) {
        this.setEmail(updateData.getEmail());
        this.setFirstName(updateData.getFirstName());
        this.setLastName(updateData.getLastName());
        this.setMobile(updateData.getMobile());
        this.setCity(updateData.getCity());
        this.setStreet(updateData.getStreet());
        this.setPostcode(updateData.getPostcode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }

    public boolean hasRole(String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}
