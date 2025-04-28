package org.dojo.spring.department.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.dojo.spring.department.Department;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    public Transaction() { }
    public Transaction(Long id, BigDecimal amount, Department department) {
        this.id = id;
        this.amount = amount;
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")  // Specify the foreign key column
    private Department department;
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction txn)) return false;
        return Objects.equals(getId(), txn.getId()) && Objects.equals(getAmount(), txn.getAmount()) && Objects.equals(getDepartment().getId(), txn.getDepartment().getId());
    }
    @Override
    public int hashCode() { return Objects.hash(getId(), getAmount(), getDepartment().getId()); }

    @Override
    public String toString() { return "Transaction{id=%d, amount=%s, department=%s}".formatted(id, amount, department.toString()); }

}
