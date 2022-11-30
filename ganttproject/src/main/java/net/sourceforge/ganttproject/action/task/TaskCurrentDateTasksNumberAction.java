/*
GanttProject is an opensource project management tool.
Copyright (C) 2011 GanttProject team

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.sourceforge.ganttproject.action.task;

import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.action.GPAction;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.gui.UIUtil;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sourceforge.ganttproject.action.CancelAction;
import net.sourceforge.ganttproject.action.OkAction;
import net.sourceforge.ganttproject.language.GanttLanguage;

import java.awt.event.ActionEvent;
import java.util.List;

public class TaskCurrentDateTasksNumberAction extends GPAction {
    private final IGanttProject myProject;
    private final UIFacade myUiFacade;


    public TaskCurrentDateTasksNumberAction(IGanttProject project, UIFacade uiFacade) {
        this(project, uiFacade, IconSize.MENU);
    }

    private TaskCurrentDateTasksNumberAction(IGanttProject project, UIFacade uiFacade, IconSize size) {
        super("Today's Number of Tasks", size.asString());
        myProject = project;
        myUiFacade = uiFacade;
    }

    @Override
    public GPAction withIcon(IconSize size) {
        return new TaskCurrentDateTasksNumberAction(myProject, myUiFacade, size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (calledFromAppleScreenMenu(e)) {
            return;
        }
        OkAction okAction = new OkAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        myUiFacade.createDialog(getComponent(),new Action[] { okAction }, "Todays number of tasks:").show();
    }

    protected TaskManager getTaskManager() {
        return myProject.getTaskManager();
    }

    protected UIFacade getUIFacade() {
        return myUiFacade;
    }

    @Override
    public void updateAction() {
        super.updateAction();
    }

    @Override
    public TaskCurrentDateTasksNumberAction asToolbarAction() {
        TaskCurrentDateTasksNumberAction result = new TaskCurrentDateTasksNumberAction(myProject, myUiFacade);
        result.setFontAwesomeLabel(UIUtil.getFontawesomeLabel(result));
        return result;
    }

    private Component getComponent() {
        int currentDateNumberOfTasks = getTaskManager().getCurrentDateTasksNumber();
        final JLabel numberPanel = new JLabel(String.valueOf(currentDateNumberOfTasks));
        final JPanel contentPanel = new JPanel();
        contentPanel.add(numberPanel);
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setPreferredSize(new Dimension(300,30));
        JPanel contentPanelWrapper = new JPanel(new BorderLayout());
        contentPanelWrapper.add(contentPanel, BorderLayout.NORTH);
        rootPanel.add(contentPanelWrapper, BorderLayout.CENTER);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return rootPanel;
    }
}