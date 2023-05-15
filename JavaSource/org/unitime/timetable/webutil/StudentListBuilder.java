/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
*/

package org.unitime.timetable.webutil;

import java.util.List;

import org.unitime.timetable.model.Student;
import org.unitime.timetable.security.SessionContext;

/**
 * @author seam-project
 */
public class StudentListBuilder {
    public String nullableToString(String s) {
        return s == null ? "" : s;
    }

    public String htmlTableForStudent(SessionContext context) {
        List<Student> students = Student.findAll(context.getUser().getCurrentAcademicSessionId());
        if (students == null || students.isEmpty())
            return null;
        StringBuilder table = new StringBuilder();
        for (Student student : students) {
            String id = student.getExternalUniqueId();
            table.append(
                    "<a href='studentDetail.action?studentId=" + id + "'>"
                            + id + ". " + nullableToString(student.getFirstName()) + ' '
                            + nullableToString(student.getMiddleName()) + ' '
                            + nullableToString(student.getLastName()) + "</a><br />");
        }
        return table.toString();
    }

}
