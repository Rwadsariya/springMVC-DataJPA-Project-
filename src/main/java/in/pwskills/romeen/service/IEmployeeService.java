package in.pwskills.nitin.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.pwskills.nitin.bean.Employee;

public interface IEmployeeService {

	void saveRecord(Employee employee);
	
	Page<Employee> displayAllRecords(Pageable pagable);
	
	void deleteRecord(Integer id);
	
	Employee findEmployee(Integer id);
}
