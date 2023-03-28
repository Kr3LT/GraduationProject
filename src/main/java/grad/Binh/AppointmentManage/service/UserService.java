package grad.Binh.AppointmentManage.service;

import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.entity.user.customer.CorporateCustomer;
import grad.Binh.AppointmentManage.entity.user.customer.Customer;
import grad.Binh.AppointmentManage.entity.user.customer.RetailCustomer;
import grad.Binh.AppointmentManage.entity.user.provider.Provider;
import grad.Binh.AppointmentManage.model.ChangePasswordForm;
import grad.Binh.AppointmentManage.model.UserForm;

import java.util.Collection;
import java.util.List;

public interface UserService {
    /*
     * User
     * */
    boolean userExists(String userName);

    User getUserById(int userId);

    User getUserByUsername(String userName);

    List<User> getUsersByRoleName(String roleName);

    List<User> getAllUsers();

    void deleteUserById(int userId);

    void updateUserPassword(ChangePasswordForm passwordChangeForm);

    /*
     * Provider
     * */
    Provider getProviderById(int providerId);

    List<Provider> getProvidersWithRetailWorks();

    List<Provider> getProvidersWithCorporateWorks();

    List<Provider> getProvidersByWork(Work work);

    List<Provider> getAllProviders();

    void saveNewProvider(UserForm userForm);

    void updateProviderProfile(UserForm updateData);

    Collection<Role> getRolesForProvider();

    /*
     * Customer
     * */
    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    /*
     * RetailCustomer
     * */
    RetailCustomer getRetailCustomerById(int retailCustomerId);

    void saveNewRetailCustomer(UserForm userForm);

    void updateRetailCustomerProfile(UserForm updateData);

    Collection<Role> getRolesForRetailCustomer();

    /*
     * CorporateCustomer
     * */
    CorporateCustomer getCorporateCustomerById(int corporateCustomerId);

    List<RetailCustomer> getAllRetailCustomers();

    void saveNewCorporateCustomer(UserForm userForm);

    void updateCorporateCustomerProfile(UserForm updateData);

    Collection<Role> getRoleForCorporateCustomers();
}
