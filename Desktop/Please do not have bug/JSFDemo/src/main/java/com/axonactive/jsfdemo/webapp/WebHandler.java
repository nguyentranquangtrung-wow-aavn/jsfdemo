package com.axonactive.jsfdemo.webapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject; //CDI

import com.axonactive.jsfdemo.department.DepartmentBOM;
import com.axonactive.jsfdemo.department.DepartmentService;
import com.axonactive.jsfdemo.employee.EmployeeBOM;
import com.axonactive.jsfdemo.employee.EmployeeEntity;
import com.axonactive.jsfdemo.employee.EmployeeService;

@ManagedBean
@ViewScoped
public class WebHandler {

	private EmployeeBOM employee = new EmployeeBOM();
	private DepartmentBOM department = new DepartmentBOM();

	@Inject
	//@EJB
	private EmployeeService employeeService;

	@Inject
	private DepartmentService departmentService;

	private List<EmployeeBOM> employeeList = new ArrayList<>();
	private List<DepartmentBOM> departmentList = new ArrayList<>();

	private List<EmployeeBOM> employeeListGender = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		try {
			employeeListGender = employeeService.toBoms(employeeService.getListEmployeeGender("male"));
			
			employeeList = employeeService.toBoms(employeeService.readAll());
			departmentList = departmentService.toBoms(departmentService.readAll());
			if (departmentList.size() > 0) {
				department = departmentList.get(0);
			}
		} catch (Throwable e) {
			System.out.println("Cause: " + e.getCause());
			System.out.println("Message: " + e.getMessage());
			System.out.println("Class: " + e.getClass());
			System.out.println("StackTrace: " + e.getStackTrace());
			throw e;
		}
	}

	public String addNewEmployee() {
		employee.setDepartment(departmentService.toEntity(department));
		employeeService.save(employeeService.toEntity(employee));
		employeeList = employeeService.toBoms(employeeService.readAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public String editEmployee(EmployeeBOM employeeBOM) {
		employeeService.update(employeeService.toEntity(employeeBOM));
		employeeList = employeeService.toBoms(employeeService.readAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
//	public void editEmployee(EmployeeBOM employeeBOM) {
//		employee = employeeBOM;
//}

//	public String deleteEmployee(int id) {
//		employeeService.remove(id);
//		employeeList = employeeService.toBoms(employeeService.readAll());
//		return "index.xhtml?faces-redirect=true&includeViewParams=true";
//	}
	
	public String deleteEmployee(EmployeeBOM employeeBOM) {
		employeeService.removeEntity(employeeService.toEntity(employeeBOM));
		employeeList = employeeService.toBoms(employeeService.readAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public void changeDepartment(ValueChangeEvent dept) {
		department = departmentService
				.toBom(departmentService.findById(Integer.parseInt(dept.getNewValue().toString())));
	}
	
	public void reverseEmployeeList() {
		Collections.reverse(employeeList);
	}
	
	public String updateEmployee() {
	employee.setDepartment(departmentService.toEntity(department));
	employeeService.update(employeeService.toEntity(employee));
	employeeList = employeeService.toBoms(employeeService.readAll());
	return "index.xhtml?faces-redirect=true&includeViewParams=true";
}
	
	public Boolean isEmployeeExisting() {
		for(EmployeeBOM emp : employeeList) {
			if(emp.getId() == employee.getId()) {
				return true;
			}
		}
		return false;
	}

	public EmployeeBOM getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBOM employee) {
		this.employee = employee;
	}

	public List<EmployeeBOM> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeBOM> employeeList) {
		this.employeeList = employeeList;
	}

	public DepartmentBOM getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBOM department) {
		this.department = department;
	}

	public List<DepartmentBOM> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentBOM> departmentList) {
		this.departmentList = departmentList;
	}
	
	public List<EmployeeBOM> getEmployeeListGender() {
		return employeeListGender;
	}

	public void setEmployeeListGender(List<EmployeeBOM> employeeList) {
		this.employeeListGender = employeeList;
	}

}
