package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.entity.user.customer.CorporateCustomer;
import grad.Binh.AppointmentManage.entity.user.customer.Customer;
import grad.Binh.AppointmentManage.entity.user.customer.RetailCustomer;
import grad.Binh.AppointmentManage.entity.user.provider.Provider;
import grad.Binh.AppointmentManage.model.ChangePasswordForm;
import grad.Binh.AppointmentManage.model.UserForm;
import grad.Binh.AppointmentManage.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean userExists(String userName) {
        return false;
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String userName) {
        return null;
    }

    @Override
    public List<User> getUsersByRoleName(String roleName) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUserById(int userId) {

    }

    @Override
    public void updateUserPassword(ChangePasswordForm passwordChangeForm) {

    }

    @Override
    public Provider getProviderById(int providerId) {
        return null;
    }

    @Override
    public List<Provider> getProvidersWithRetailWorks() {
        return null;
    }

    @Override
    public List<Provider> getProvidersWithCorporateWorks() {
        return null;
    }

    @Override
    public List<Provider> getProvidersByWork(Work work) {
        return null;
    }

    @Override
    public List<Provider> getAllProviders() {
        return null;
    }

    @Override
    public void saveNewProvider(UserForm userForm) {

    }

    @Override
    public void updateProviderProfile(UserForm updateData) {

    }

    @Override
    public Collection<Role> getRolesForProvider() {
        return null;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public RetailCustomer getRetailCustomerById(int retailCustomerId) {
        return null;
    }

    @Override
    public void saveNewRetailCustomer(UserForm userForm) {

    }

    @Override
    public void updateRetailCustomerProfile(UserForm updateData) {

    }

    @Override
    public Collection<Role> getRolesForRetailCustomer() {
        return null;
    }

    @Override
    public CorporateCustomer getCorporateCustomerById(int corporateCustomerId) {
        return null;
    }

    @Override
    public List<RetailCustomer> getAllRetailCustomers() {
        return null;
    }

    @Override
    public void saveNewCorporateCustomer(UserForm userForm) {

    }

    @Override
    public void updateCorporateCustomerProfile(UserForm updateData) {

    }

    @Override
    public Collection<Role> getRoleForCorporateCustomers() {
        return null;
    }
}
