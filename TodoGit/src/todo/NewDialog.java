/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import javax.swing.*;

/**
 *
 * @author Jonathan
 */
public class NewDialog {
    private String newTitle;
    private String newDescription;
    private int newPriority;
    public NewDialog(int whichone) {
        switch(whichone) {
            case 0: //New
                displayNew();
            break;
            case 1: //Edit
            
            break;    
            case 2: //Delete
                
            break;
        }
    }
    private void displayNew() {
        JPanel newPanel = new JPanel();
        Box panel = Box.createVerticalBox();
        Box title = Box.createHorizontalBox();
        //Title fields
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleInput = new JTextField();
        title.add(titleLabel);
        title.add(titleInput);
        panel.add(title);
        //Description fields
        Box description = Box.createHorizontalBox();
        JLabel descriptionLabel = new JLabel("Edit: ");
        JTextField descriptionInput = new JTextField();
        description.add(descriptionLabel);
        description.add(descriptionInput);
        panel.add(description);
        //Priority fields
        Box priority = Box.createHorizontalBox();
        JLabel priorityLabel = new JLabel("Priority: ");
        Integer[] priorities = {0, 1, 2, 3};
        JComboBox priorityCombo = new JComboBox(priorities);
        priority.add(priorityLabel);
        priority.add(priorityCombo);
        panel.add(priority);
        //Add the box to the panel
        newPanel.add(panel);
        int result =JOptionPane.showConfirmDialog(null,
                        newPanel,
                        "Enter new todo here",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {
            newTitle = titleInput.getText();
            newDescription = descriptionInput.getText();
            newPriority = (int) priorityCombo.getSelectedItem();
        }
    }
    
    private void displayEdit() {
        
    }
    
    private void displayDelete() {
        
    }
    
}
