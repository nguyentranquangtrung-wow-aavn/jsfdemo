package com.axonactive.jsfdemo.employee;

import com.axonactive.jsfdemo.department.DepartmentEntity;

//import com.axonactive.jsfdemo.department.DepartmentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBOM {
	private Integer id;

	private String firstName;

	private String lastName;

	private String gender;

	private String email;

	private DepartmentEntity department;

}
