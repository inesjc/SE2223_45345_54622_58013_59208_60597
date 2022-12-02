/*
Copyright 2014 BarD Software s.r.o

This file is part of GanttProject, an opensource project management tool.

GanttProject is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

GanttProject is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with GanttProject.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.sourceforge.ganttproject.task.algorithm;

import java.math.BigDecimal;

import net.sourceforge.ganttproject.TestSetupHelper;
import net.sourceforge.ganttproject.TestSetupHelper.TaskManagerBuilder;
import net.sourceforge.ganttproject.resource.HumanResource;
import net.sourceforge.ganttproject.task.Task;
import biz.ganttproject.core.time.GanttCalendar;
import net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade;
import net.sourceforge.ganttproject.test.task.TaskTestCase;

/**
 * Tests for current date tasks number
 *
 * @author dbarashev (Ines Carvalho)
 */
public class CurrentDateTasksNumberTest extends TaskTestCase {
    public void testCurrentDateTasksNumberWithAllTasksCurrentDay() {
        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        int result = getTaskManager().getCurrentDateTasksNumber();
        assertEquals(3, result);
    }

    public void testCurrentDateTasksNumberWithOneTaskPreviousDay() {
        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        task3.setStart(GanttCalendar.parseXMLDate("2022-01-12T12:13:14Z"));
        task3.setEnd(GanttCalendar.parseXMLDate("2022-01-12T12:43:14Z"));
        int result = getTaskManager().getCurrentDateTasksNumber();
        assertEquals(2, result);
    }
 
 /**
 * Test for current date tasks number
 *
 * @author dbarashev (Simão Carrasco)
 */
 public void testCurrentDateTasksNumberAfterRemove() {
        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        int result = getTaskManager().getCurrentDateTasksNumber();
        assertEquals(3, result);
        getTaskManager().deleteTask(task3);
        result=getTaskManager().getCurrentDateTasksNumber();
        assertEquals(2, result);
    }
}
