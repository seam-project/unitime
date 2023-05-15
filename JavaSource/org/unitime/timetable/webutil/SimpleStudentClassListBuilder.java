package org.unitime.timetable.webutil;

import java.util.Set;

import org.unitime.timetable.model.CourseOffering;
import org.unitime.timetable.model.Student;
import org.unitime.timetable.model.StudentClassEnrollment;
import org.unitime.timetable.security.SessionContext;

public class SimpleStudentClassListBuilder {
    public String getList(SessionContext context, String studentId) {
        Student student = Student.findByExternalId(context.getUser().getCurrentAcademicSessionId(), studentId);
        if (student == null) {
            return null;
        }
        StringBuilder table = new StringBuilder();
        Set<StudentClassEnrollment> enrollments = student.getClassEnrollments();
        for (StudentClassEnrollment enrollment : enrollments) {
            CourseOffering o = enrollment.getCourseOffering();
            table.append(o.getSubjectArea() + " " + o.getCourseNbr() + "<br />");
        }
        return table.toString();
    }
}
