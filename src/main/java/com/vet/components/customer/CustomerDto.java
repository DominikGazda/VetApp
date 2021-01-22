package com.vet.components.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class CustomerDto {

    private Long idCustomer;

    @Pattern(regexp = "^[0-9]{4}?$", message = "{com.vet.components.customer.CustomerDto.ID.Pattern}")
    @NotNull(message = "{com.vet.components.customer.CustomerDto.ID.NotNull}")
    private String ID;

    @Pattern(regexp = "^[0-9]{4}?$", message = "{com.vet.components.customer.CustomerDto.PIN.Pattern}")
    @NotNull(message = "{com.vet.components.customer.CustomerDto.PIN.NotNull}")
    private String PIN;

    @NotBlank(message = "{com.vet.components.customer.CustomerDto.firstName.NotBlank}")
    private String firstName;

    @NotBlank(message = "{com.vet.components.customer.CustomerDto.lastName.NotBlank}")
    private String lastName;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(idCustomer, that.idCustomer) &&
                Objects.equals(ID, that.ID) &&
                Objects.equals(PIN, that.PIN) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, ID, PIN, firstName, lastName);
    }
}
