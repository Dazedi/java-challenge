package jp.co.axa.apidemo.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;


class PostData {
    public String name;
    public int salary;
    public String department;
}

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
// @WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    private MockMvc mvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test_getEmployees() throws Exception {
        when(employeeService.retrieveEmployees()).thenReturn(getEmployeeList());

        mvc.perform(get("/api/v1/employees"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("Bob"))
            .andExpect(jsonPath("$[0].salary").value(12000))
            .andExpect(jsonPath("$[0].department").value("Building management"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].name").value("Dylan"))
            .andExpect(jsonPath("$[1].salary").value(58000))
            .andExpect(jsonPath("$[1].department").value("Financial department"))
            .andDo(print());
    }

    @Test
    public void save_employee_ok() throws Exception {        
        PostData data = new PostData();
        data.department = "IT";
        data.name = "Tommi";
        data.salary = 1337;

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(getNewEmployee(data));

        mvc.perform(post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(data))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Tommi"))
            .andExpect(jsonPath("$.department").value("IT"))
            .andExpect(jsonPath("$.salary").value(1337))
            .andDo(print());
    }

    @Test
    public void save_employee_ng() throws Exception {        
        PostData data = new PostData();
        data.name = "Tommi";

        mvc.perform(post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(data))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void save_employee_ng2() throws Exception {        
        PostData data = new PostData();

        mvc.perform(post("/api/v1/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(data))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void get_employee_ok() throws Exception {
        when(employeeService.getEmployee(1L)).thenReturn(getEmployeeOfId1());

        mvc.perform(get("/api/v1/employees/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Bob"))
            .andExpect(jsonPath("$.department").value("Test"))
            .andExpect(jsonPath("$.salary").value(1337))
            .andDo(print());
    }

    @Test
    public void get_employee_ng() throws Exception {
        when(employeeService.getEmployee(2L)).thenReturn(null);

        mvc.perform(get("/api/v1/employees/{id}", 2L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist())
            .andDo(print());
    }

    public Employee getNewEmployee(PostData data) {
        Employee dude = new Employee(data.name, data.salary, data.department);
        dude.setId(1L);
        return dude;
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        Employee bob = new Employee("Bob", 12000, "Building management");
        bob.setId(1L);
        
        Employee dylan = new Employee("Dylan", 58000, "Financial department");
        dylan.setId(2L);
        

        employees.add(bob);
        employees.add(dylan);

        return employees;
    }

    public Employee getEmployeeOfId1() {
        Employee bob = new Employee("Bob", 1337, "Test");
        bob.setId(1L);
        return bob;
    }
}
