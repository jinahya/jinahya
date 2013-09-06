/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.xml.bind.test.map;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlRootElement
public class Department7 extends AbstractDepartment {


    @XmlElement(name = "employee")
    private List<Employee> getEmployeeList() {

        final Collection<Employee> employees = getEmployees().values();

        if (employees instanceof List) {
            return (List<Employee>) employees;
        }

        return new ArrayList<>(employees);
    }


    private void setEmployeeList(final List<Employee> employeeList) {

        for (Employee employee : employeeList) {
            getEmployees().put(employee.getId(), employee);
        }
    }


    @XmlTransient
    @Override
    public Map<Long, Employee> getEmployees() {

        return super.getEmployees();
    }


}
