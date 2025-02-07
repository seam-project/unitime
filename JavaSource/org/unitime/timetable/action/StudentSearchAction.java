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
package org.unitime.timetable.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesPutAttribute;
import org.unitime.localization.impl.Localization;
import org.unitime.localization.messages.CourseMessages;
import org.unitime.timetable.form.BlankForm;
import org.unitime.timetable.security.rights.Right;
import org.unitime.timetable.webutil.StudentListBuilder;

/**
 * @author seam-project
 */
@Action(value = "studentSearch", results = {
        @Result(name = "showList", type = "tiles", location = "studentSearch.tiles")
})
@TilesDefinition(name = "studentSearch.tiles", extend = "baseLayout", putAttributes = {
        @TilesPutAttribute(name = "title", value = "Students"),
        @TilesPutAttribute(name = "body", value = "/user/studentSearch.jsp")
})
public class StudentSearchAction extends UniTimeAction<BlankForm> {
    private static final long serialVersionUID = 5199990990419667211L;

    protected static final CourseMessages MSG = Localization.create(CourseMessages.class);

    @Override
    public String execute() throws IOException {
        sessionContext.checkPermission(Right.Students);

        if (!MSG.actionSearchStudents().equals(getOp())) {
            return "showList";
        }

        StudentListBuilder slb = new StudentListBuilder();
        String tblData = slb.htmlTableForStudent(sessionContext);
        if (tblData == null || tblData.trim().isEmpty())
            addActionError(MSG.errorNoStudentsFoundInSearch());
        else
            request.setAttribute("studentList", tblData);

        return "showList";
    }
}
