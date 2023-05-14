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
import org.unitime.timetable.form.BlankForm;
import org.unitime.timetable.security.rights.Right;
import org.unitime.timetable.webutil.SimpleStudentClassListBuilder;

/**
 * @author seam-project
 */
@Action(value = "studentDetail", results = {
        @Result(name = "showStudentDetail", type = "tiles", location = "studentDetail.tiles")
})
@TilesDefinition(name = "studentDetail.tiles", extend = "baseLayout", putAttributes = {
        @TilesPutAttribute(name = "title", value = "Student Detail"),
        @TilesPutAttribute(name = "body", value = "/user/studentDetail.jsp")
})
public class StudentDetailAction extends UniTimeAction<BlankForm> {

    private static final long serialVersionUID = 7592940798685460713L;

    public String execute() throws IOException {
        sessionContext.checkPermission(Right.StudentDetail);

        String studentId = request.getParameter("studentId");
        request.setAttribute("studentId", studentId);
        SimpleStudentClassListBuilder clb = new SimpleStudentClassListBuilder();
        String tblData = clb.getList(sessionContext, studentId);
        if (tblData != null && !tblData.trim().isEmpty())
            request.setAttribute("studentDetails", tblData);
        return "showStudentDetail";

    }
}
