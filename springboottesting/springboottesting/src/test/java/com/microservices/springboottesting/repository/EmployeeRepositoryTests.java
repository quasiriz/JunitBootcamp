package com.microservices.springboottesting.repository;

import com.microservices.springboottesting.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Junit test for save employee operation
    @Test
    @DisplayName("Junit test for save Employee operation")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        //when
        Employee savedEmployee = employeeRepository.save(employee);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeesList() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Smrthi")
                .lastName("Krishnamurthi")
                .email("smrthik@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        //when
        List<Employee> employees = employeeRepository.findAll();

        //then
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Junit test for getEmployeeById")
    public void givenEmployee_whenGetEmployeeById_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        employeeRepository.save(employee);
        //when

        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee).isEqualTo(employee);
    }

    @Test
    @DisplayName("Junit test for findByEmail")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findByEmail(employee.getEmail()).orElse(null);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee).isEqualTo(employee);
    }

    @Test
    @DisplayName("Junit test for update Functionality")
    public void givenEmployee_whenUpdateEmployee_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        savedEmployee.setEmail("quasi.rizvi@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo(savedEmployee.getEmail());
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo(savedEmployee.getFirstName());

    }

    @Test
    @DisplayName("Junit test for delete operation")
    public void givenEmployee_whenDeleteEmployee_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when
        employeeRepository.delete(employee);

        Employee deletedEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        //then
        Assertions.assertThat(deletedEmployee).isNull();
    }

    @Test
    @DisplayName("Junit test for custom query operation")
    public void givenEmployee_whenFindEmployeeByCustomQuery_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findByJPQL(employee.getFirstName(), employee.getLastName());

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Junit test for custom query operation namedParams")
    public void givenEmployee_whenFindEmployeeByCustomQueryNamedParams_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(employee.getFirstName(), employee.getLastName());

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Junit test for native sql for indexed params")
    public void givenEmployee_whenFindEmployeeByNativeQuery_thenEmployee() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findByNativeQuery(
                employee.getFirstName(), employee.getLastName());

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Junit test case for Find By Native Query NamedParameters")
    public void given_when_then() {
        //given
        Employee employee = Employee.builder()
                .firstName("Quasim")
                .lastName("Rizvi")
                .email("quasijs@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findByNativeQueryNamedParams(
                employee.getFirstName(), employee.getLastName());

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
