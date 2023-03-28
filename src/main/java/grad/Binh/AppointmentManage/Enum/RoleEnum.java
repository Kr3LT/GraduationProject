package grad.Binh.AppointmentManage.Enum;

import lombok.Getter;

@Getter
public enum RoleEnum {
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN"),
    PROVIDER("ROLE_PROVIDER");

    private final String value;

    RoleEnum(String value){
        this.value = value;
    }
}
