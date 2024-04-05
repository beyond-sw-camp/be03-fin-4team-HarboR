package com.example.harbor_employee.global.util;

import com.example.harbor_employee.Employee.domain.Code;
import com.example.harbor_employee.Employee.domain.Department;
import com.example.harbor_employee.Employee.domain.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class EmployeeSpecification {
    /**
     * @description like 조건문 생성
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
     * @description like 조건문 생성
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
     * @description 코드 테이블 join 후 equal 조건문 생성
     * @param department {String} 조건값
     * @return {Specification<Employee>} Specification 객체
     */
    public static Specification<Employee> equalDepartment(final String department) {
        return (root, query, criteriaBuilder) -> {
            if(!StringUtils.hasText(department)) return null;
            else {
                Join<Employee, Department> departmentJoin = root.join("department", JoinType.INNER);
                return criteriaBuilder.equal(departmentJoin.get("departmentName"), department);
            }
        };
    }
    /**
     * @description 코드 테이블 join 후 equal 조건문 생성
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