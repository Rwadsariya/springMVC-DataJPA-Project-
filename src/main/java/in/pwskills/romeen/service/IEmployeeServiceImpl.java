package in.pwskills.nitin.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import in.pwskills.nitin.bean.Employee;
import in.pwskills.nitin.exception.EmployeeNotFoundException;
import in.pwskills.nitin.repository.IEmployeeRepo;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	IEmployeeRepo repo;

	@Override
	public void saveRecord(Employee employee) {
		repo.save(employee);

	}

	
	@Override
	public void deleteRecord(Integer id) {
		repo.delete(repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee with id:"+id+"not available.")));
	}

	@Override
	public Employee findEmployee(Integer id) {
		return repo.findById(id).get();
	}


	@Override
	public Page<Employee> displayAllRecords(org.springframework.data.domain.Pageable pagable) {
		return repo.findAll(pagable);
	}

}
