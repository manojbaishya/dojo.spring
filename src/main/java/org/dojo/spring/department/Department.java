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
    public Department(Long id, String name, String address, String code) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setCode(code);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long departmentId) { this.id = departmentId; }

    @NotBlank(message = "Department Name cannot be empty.")
    private String name;
    public String getName() { return name; }
    public void setName(String departmentName) { this.name = departmentName; }

    private String address;
    public String getAddress() { return address; }
    public void setAddress(String departmentAddress) { this.address = departmentAddress; }

    @Length(min = 0, max = 50)
    private String code;
    public String getCode() { return code; }
    public void setCode(String departmentCode) { this.code = departmentCode; }

    @Override
    public String toString() {
        return String.format("Department [id=%s, name=%s, address=%s, code=%s]", id, name, address, code);
    }

    public Department(Builder builder) {
        this.setId(builder.id);
        this.setName(builder.name);
        this.setAddress(builder.address);
        this.setCode(builder.code);
    }

    public static Builder builder(Long id) { return new Builder(id); }

    public static class Builder {
        private Long id = null;

        Builder(Long id) { this.id = id; }

        public Department build() { return new Department(this); }

        private String name = null;
        public Builder name(String name) {
            this.name =  name;
            return this;
        }

        private String address = null;
        public Builder address(String address) {
            this.address = address;
            return this;
        }

        private String code = null;
        public Builder code(String code) {
            this.code = code;
            return this;
        }
    }
}
