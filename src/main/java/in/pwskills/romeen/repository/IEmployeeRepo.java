package in.pwskills.nitin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.pwskills.nitin.bean.Employee;

public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {

	List<Employee> findAllByOrderByLastnameAsc();
}
