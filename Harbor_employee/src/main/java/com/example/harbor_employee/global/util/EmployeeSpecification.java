package com.example.harbor_employee.global.util;

import com.example.harbor_employee.Employee.domain.Department;
import com.example.harbor_employee.Employee.domain.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class EmployeeSpecification {
    /**
     * @description name like 조건문 생성
     * @param name {String} 조건값
     * @return {Specification<Employee>} Specification 객체
     */
    public static Specification<Employee> likeName(final String name) {
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(name)) return null;
            else return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + name + "%"),
                    criteriaBuilder.like(root.get("name"), "%" + name),
                    criteriaBuilder.like(root.get("name"), name + "%")
            );
        };
    }
    /**
     * @description employeeId like 조건문 생성
     * @param employeeId {String} 조건값
     * @return {Specification<Employee>} Specification 객체
     */
    public static Specification<Employee> likeEmployeeId(final String employeeId) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(employeeId)) return null;
            else return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("employeeId"), "%" + employeeId + "%"),
                        criteriaBuilder.like(root.get("employeeId"), "%" + employeeId),
                        criteriaBuilder.like(root.get("employeeId"), employeeId + "%")
            );
        };
    }
    /**
     * @description departmentCode equal 조건문 생성
     * @param departmentCode {String} 조건값
     * @return {Specification<Employee>} Specification 객체
     */
    public static Specification<Employee> equalDepartment(final String departmentCode) {
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(departmentCode)) return null;
            else {
                return criteriaBuilder.equal(root.get("departmentCode"), departmentCode);
            }
        };
    }
    /**
     * @description teamCode equal 조건문 생성
     * @param teamCode {String} 조건값
     * @return {Specification<Employee>} Specification 객체
     */
    public static Specification<Employee> equalTeamName(final String teamCode) {
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(teamCode)) return null;
            else {
                return criteriaBuilder.equal(root.get("teamCode"), teamCode);
            }
        };
    }
}