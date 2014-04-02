package com.learn.hibernate.sql.joins_sql.model.onetoone.casewithownkeys;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 9:48
 * To change this template use File | Settings | File Templates.
 */
public class TestOneToOne {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        System.out.println("Hibernate One-To-One example (Annotation)");

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        EmployeeDetail employeeDetail = new EmployeeDetail("10th Street", "LA", "San Francisco", "U.S.");
        EmployeeDetail employeeDetail2 = new EmployeeDetail("11th Street", "LA", "San Francisco", "U.S.");

        Employee employee = new Employee("Nina", "Mayers", new Date(121212),
                "114-857-965");
        Employee employee2 = new Employee("Nina2", "Mayers", new Date(121212),
                "114-857-965");

        employee.setEmployeeDetail(employeeDetail);
        employeeDetail.setEmployee(employee2);
        employeeDetail.setEmployee(employee);
        employeeDetail2.setEmployee(employee);
        employeeDetail2.setEmployee(employee2);
        employee.setEmployeeDetail(employeeDetail2);

        session.save(employeeDetail);
        session.save(employeeDetail2);
        session.save(employee);
        session.save(employee2);


//        List<Employee> employees = session.createQuery("from Employee").list();
//        for (Employee employee1 : employees) {
//            System.out.println(employee1.getFirstname() + " , "
//                    + employee1.getLastname() + ",     "
//                    + employee1.getEmployeeDetail().getState());
//        }

        session.getTransaction().commit();
        session.close();

    }
}
