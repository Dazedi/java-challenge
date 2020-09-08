package jp.co.axa.apidemo.services;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void test_retrieveEmployees() {

        when(employeeRepository.findAll()).thenReturn(getAllEmployees());
        List<Employee> employees = employeeRepository.findAll();

        assertThat(employees.get(0).getId(), is(1L));
        assertThat(employees.get(0).getName(), is("Bob"));
        assertThat(employees.get(0).getSalary(), is(1000));
        assertThat(employees.get(0).getDepartment(), is("Test"));

        assertThat(employees.get(1).getId(), is(2L));
        assertThat(employees.get(1).getName(), is("Dylan"));
        assertThat(employees.get(1).getSalary(), is(3333));
        assertThat(employees.get(1).getDepartment(), is("Test"));
    }

    @Test
    public void test_getEmployee() {

        when(employeeRepository.findById(100L)).thenReturn(Optional.ofNullable(getEmployeeId100()));
        Optional<Employee> employee = employeeRepository.findById(100L);

        assertThat(employee.get().getId(), is(100L));
        assertThat(employee.get().getSalary(), is(1000));
        assertThat(employee.get().getName(), is("Bob"));
        assertThat(employee.get().getDepartment(), is("Test"));
    }

    @Test
    public void test_saveEmployee() {

        Employee create = new Employee("Bob", 1000, "Test");
        Employee created = new Employee("Bob", 1000, "Test");
        created.setId(1L);

        when(employeeRepository.save(any(Employee.class))).thenReturn(created);
        Employee employee = employeeRepository.save(create);
        assertThat(employee.getId(), is(1L));
        assertThat(employee.getSalary(), is(1000));
        assertThat(employee.getName(), is("Bob"));
        assertThat(employee.getDepartment(), is("Test"));
    }

    @Test
    public void test_deleteEmployee(){
        // employeeRepository.deleteById(employeeId);
    }

    @Test
    public void test_updateEmployee() {
        // employeeRepository.save(employee);
    }

    public Employee getEmployeeId100() {
        Employee employee = new Employee("Bob", 1000, "Test");
        employee.setId(100L);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Bob", 1000, "Test");
        employee1.setId(1L);
        Employee employee2 = new Employee("Dylan", 3333, "Test");
        employee2.setId(2L);
        employees.add(employee1);
        employees.add(employee2);
        return employees;
    }
}
