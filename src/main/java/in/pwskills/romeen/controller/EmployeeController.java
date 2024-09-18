package in.pwskills.nitin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.pwskills.nitin.bean.Employee;
import in.pwskills.nitin.service.IEmployeeService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService service;

	/**
	 * 				Inputting the form to the user 
	 * 1. Display a Form 
	 * 2. METHOD : GET 
	 * 3. ACTION :/showForm
	 * 4. R.T : employee-form 
	 * 5. INPUT : Model
	 */
	
	@GetMapping(value = "/showForm")
	public String showForm(Model model) {
		Employee employee = new Employee();
		
		model.addAttribute("employee",employee);
		return "/employee/employee-form";
	}
	
	/***
	 * 					Saving the object 
	 * 1. Save the Object collected from the user 
	 * 2. METHOD : POST
	 * 3. ACTION : /save 
	 * 4. R.T : list-employees 
	 * 5. INPUT : Employee employee @ModelAttribute
	 */
	
	@PostMapping(value = "/save")
	public String saveEmployeeData(
			@ModelAttribute
			Employee employee) {
		
			service.saveRecord(employee);
		return "redirect:/employee/list";	 // request dispatching..	
	}
	
	/***
	 * 				Retrieve all records and display 
	 * 1. GET ALL RECORDS FROM DB 
	 * 2. METHOD : GET
	 * 3. ACTION : /list 
	 * 4. R.T : list-employees 
	 * 5. INPUT : Model
	 */
	@GetMapping(value = "/list")
	public String displayEmployeeList(Model model,
			@PageableDefault(page = 0,size = 3)
				Pageable Pageable
			) {
		Page<Employee> page = service.displayAllRecords(Pageable);
		
		model.addAttribute("employees",page.getContent());
		model.addAttribute("page",page);
		
		return "/employee/list-employees";
	}
	
	/****
	 * 				Delete the Employee based on id
	 *  1. Collect ID from the user
	 *  2. METHOD : POST
	 *  3. ACTION : /delete
	 *  4. R.T    : list-employees
	 *  5. INPUT  : ID @RequestParam
	 */
	@PostMapping("/delete")
	public String deleteEmployee(
			@RequestParam("empId")
			Integer eid
			) {
		
		service.deleteRecord(eid);
		
		return "redirect:/employee/list";
	}
	
	/****
	 * 				Display the Employee Data on a form based on id
	 *  1. Collect ID from the user
	 *  2. METHOD : POST
	 *  3. ACTION : /showFormForUpdate
	 *  4. R.T    : employee-form
	 *  5. INPUT  : Model, ID @RequestParam
	 */
	@PostMapping(value = "/showFormForUpdate")
	public String updateEmployeeDetails(
			@RequestParam("empId")
			Integer eid, Model model
			) {
		Employee employee = service.findEmployee(eid);
		model.addAttribute("employee",employee);
		return "/employee/employee-form";
	}
}
