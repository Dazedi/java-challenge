package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    protected Employee() {}
    public Employee(String name, Integer salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotEmpty(message = "Employee name can not be empty")
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @NotNull
    @Min(0)
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @NotEmpty(message = "Employee department can not be empty")
    @Column(name="DEPARTMENT")
    private String department;

    @Override
    public String toString() {
        return String.format("Name: %s, Salary: %s, Department: %s", name, salary, department);
    }
}
