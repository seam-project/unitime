<%--
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
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tt" uri="http://www.unitime.org/tags-custom" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="loc" uri="http://www.unitime.org/tags-localization" %>

<s:form action="studentDetail">
<table class="unitime-MainTable">
	<tr><td>
		<tt:section-header>
			<tt:section-title>
				<div style="padding-right: 3px; display: block; margin-bottom: 3px; line-height: 25px;">
                    Student Details - <s:property value="#request.studentId"/>
				</div>
			</tt:section-title>
		</tt:section-header>
		</td></tr>
		<tr><td colspan="2" align="center"><s:actionerror/></td></tr>
	</table>
	<s:if test="#request.studentDetails != null">
		<table class="unitime-MainTable" style="padding-top: 20px;">
			<s:property value="%{#request.studentDetails}" escapeHtml="false"/>
		</table>
	</s:if>
</s:form>
