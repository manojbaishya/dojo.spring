package org.dojo.spring.department;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "departments")
public class Department {
    public Department() { }
    public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode) {
        this.setDepartmentId(departmentId);
        this.setDepartmentName(departmentName);
        this.setDepartmentAddress(departmentAddress);
        this.setDepartmentCode(departmentCode);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    @NotBlank(message = "Department Name cannot be empty.")
    private String departmentName;
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    private String departmentAddress;
    public String getDepartmentAddress() { return departmentAddress; }
    public void setDepartmentAddress(String departmentAddress) { this.departmentAddress = departmentAddress; }

    @Length(min = 0, max = 50)
    private String departmentCode;
    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    @Override
    public String toString() {
        return String.format("Department [departmentId=%s, departmentName=%s, departmentAddress=%s, departmentCode=%s]", departmentId, departmentName, departmentAddress, departmentCode);
    }

    public Department(Builder builder) {
        this.setDepartmentId(builder.departmentId);
        this.setDepartmentName(builder.departmentName);
        this.setDepartmentAddress(builder.departmentAddress);
        this.setDepartmentCode(builder.departmentCode);
    }

    public static Builder builder(Long departmentId) { return new Builder(departmentId); }

    public static class Builder {
        private Long departmentId = null;

        Builder(Long departmentId) { this.departmentId = departmentId; }

        public Department build() { return new Department(this); }

        private String departmentName = null;
        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        private String departmentAddress = null;
        public Builder departmentAddress(String departmentAddress) {
            this.departmentAddress = departmentAddress;
            return this;
        }

        private String departmentCode = null;
        public Builder departmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
            return this;
        }
    }
}
