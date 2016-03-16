/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import javax.swing.*;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Jonathan
 */
public class MainFrame extends JFrame {
    DatObject data = new DatObject();
    DefaultListModel listboxModel = new DefaultListModel();
    public MainFrame(String title)
    {
        
        super(title);
        //ArrayList<JComponent> components = new ArrayList<>();
        //Begin box layout
        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                data.writeToData();
                System.exit(0);
            }
        } );
        JPanel leftPanel = new JPanel();
        Box leftPanelItems = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();
        //JPanel rightPanel = new JPanel();
        Box buttonGroup = Box.createHorizontalBox();
        //JLabel for leftPanel
        Calendar today = Calendar.getInstance();
        int day = today.get(DAY_OF_MONTH);
        int month = today.get(MONTH)+1;
        int year = today.get(YEAR);
        Box dateLabel = Box.createHorizontalBox();
        JLabel date = new JLabel(month + "/" + day + "/" + year);
        date.setFont(new Font("Arial", Font.BOLD,32));
        dateLabel.add(date);
        //dateLabel.setPreferredSize(new Dimension(32,799));
        //End JLabel for leftPanel
        //Attain items for JList
        //String[] listData = new String[data.getSize()];
        for(int i = 0; i<=data.getSize()-1; i++) {
            Todo tempTodo = data.getObject(i);
            listboxModel.addElement((i+1) + ". " +tempTodo.GetTitle());
        }
        Box boxDescription = Box.createHorizontalBox();
        JTextArea Description = new JTextArea();
        Description.setEditable(false);
        Description.setBackground(white);
        Insets margin = new Insets(1, 1, 1, 1);
        Description.setMargin(margin);
        Description.setPreferredSize(new Dimension(500, 799));
        Description.setLineWrap(true);
        Description.setWrapStyleWord(true);
        Font forTextField = new Font("Arial",Font.BOLD,32);
        Description.setFont(forTextField);
        Description.setBorder(BorderFactory.createLineBorder(Color.black));
        boxDescription.add(Description);
        //Create listbox for leftPanel
        JList listbox = new JList(listboxModel);
        listbox.setFont(new Font("Arial",Font.BOLD,32));
        listbox.setSize(new Dimension(400,693));
        //listbox.setVisibleRowCount(12);
        
       // Todo firstSelect = data.getObject(listbox.getSelectedIndex());
        
        //Updates Description text area when selection is updated
        listbox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //List<String> selectedValuesList = listbox.getSelectedValuesList();
                    //System.out.println(selectedValuesList);
                    //resortListBoxModel();
                    //System.out.println(listbox.getVisibleRowCount());
                    if(listbox.getSelectedValue() != null) {
                        Todo temp = data.getObject(listbox.getSelectedIndex());
                        Description.setText(temp.GetEntry());
                    }
                    
                    if(listboxModel.isEmpty()) {
                        
                        Description.setText(" ");
                    }
                }
            }
        });
        listbox.setSelectedIndex(0);
        JScrollPane listboxScroll = new JScrollPane(listbox);
        listboxScroll.setPreferredSize(new Dimension(400, 671));
        listboxScroll.setBorder(BorderFactory.createLineBorder(Color.black));
        //leftPanel.setPreferredSize(new Dimension(400,799));
        leftPanelItems.add(dateLabel);
        leftPanelItems.add(Box.createRigidArea(new Dimension(0,4)));
        leftPanelItems.add(listboxScroll);
        leftPanel.add(leftPanelItems);
        
        //Right Panel
        //Label
        Box label = Box.createHorizontalBox();
        JLabel description = new JLabel("Description");
        description.setFont(new Font("Arial", Font.BOLD,32));
        label.add(description);
        //Buttons
        JButton New = new JButton("New");
        New.setFont(new Font("Arial", Font.PLAIN,32));
        JButton Edit = new JButton("Edit");
        Edit.setFont(new Font("Arial", Font.PLAIN,32));
        JButton Delete = new JButton("Delete");
        Delete.setFont(new Font("Arial", Font.PLAIN,32));
        buttonGroup.add(New);
        //Event for new button
        New.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // display/center the jdialog when the button is pressed
            displayNew();
        }
        });
        
        //End event
        buttonGroup.add(Edit);
        //Event for Edit button
        Edit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // display/center the jdialog when the button is pressed
            displayEdit(listbox);
        }
        });
        //End event
        buttonGroup.add(Delete);
        //Event for delete button
        Delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // display/center the jdialog when the button is pressed
            displayDelete(listbox.getSelectedIndex());
        }
        });
        //End event
        //Add components to rightPanel
        rightPanel.add(label);
        rightPanel.add(Box.createRigidArea(new Dimension(0,9)));
        boxDescription.add(createRigidArea(new Dimension(15,0)));
        rightPanel.add(boxDescription);
        rightPanel.add(buttonGroup);
        //Add Panels to MainFrame
        this.add(leftPanel);
        this.add(rightPanel);
        //Finalize Box layout
        Container content = this.getContentPane();
        content.setLayout(new BoxLayout(content, X_AXIS));
        content.add(leftPanel);
        content.add(rightPanel);
        //Set first seleciton
        listbox.setSelectedIndex(0);
        //Finalize size of MainFrame
        this.setSize(new Dimension(1000, 800));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.pack();
        
        
    }
    private void displayNew() {
        Todo temp;
        String newTitle;
        String newDescription;
        int newPriority;
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
        JLabel descriptionLabel = new JLabel("Description: ");
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
        Dimension titleInputDim = titleInput.getPreferredSize();
        titleInputDim.setSize(300, titleInputDim.getHeight());
        titleInput.setPreferredSize(titleInputDim);
        int result =JOptionPane.showConfirmDialog(null,
                        newPanel,
                        "Enter new todo here",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {
            newTitle = titleInput.getText();
            newDescription = descriptionInput.getText();
            newPriority = (int) priorityCombo.getSelectedItem();
            temp = new Todo(newTitle, newDescription, newPriority);
            //listboxModel.addElement(temp.GetTitle());
            data.addTodo(temp);
            resortListBoxModel();
            
        }
        data.writeToData();
       
    }
    private void displayEdit(JList jlist) {
        JList listTemp = jlist;
        Todo todoTemp = data.getObject(listTemp.getSelectedIndex());
        String newTitle = todoTemp.GetTitle();
        String newDescription = todoTemp.GetEntry();
        int newPriority = todoTemp.GetPriority();
        JPanel newPanel = new JPanel();
        Box panel = Box.createVerticalBox();
        Box title = Box.createHorizontalBox();
        //Title fields
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleInput = new JTextField();
        titleInput.setText(newTitle);
        title.add(titleLabel);
        title.add(titleInput);
        panel.add(title);
        //Description fields
        Box description = Box.createHorizontalBox();
        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField descriptionInput = new JTextField();
        descriptionInput.setText(newDescription);
        description.add(descriptionLabel);
        description.add(descriptionInput);
        panel.add(description);
        //Priority fields
        Box priority = Box.createHorizontalBox();
        JLabel priorityLabel = new JLabel("Priority: ");
        Integer[] priorities = {0, 1, 2, 3};
        JComboBox priorityCombo = new JComboBox(priorities);
        priorityCombo.setSelectedItem(newPriority);
        priority.add(priorityLabel);
        priority.add(priorityCombo);
        panel.add(priority);
        //Add the box to the panel
        newPanel.add(panel);
        Dimension titleInputDim = titleInput.getPreferredSize();
        titleInputDim.setSize(300, titleInputDim.getHeight());
        titleInput.setPreferredSize(titleInputDim);
        /*Dimension descriptionInputDim = descriptionInput.getPreferredSize();
        descriptionInputDim.setSize(descriptionInputDim.getWidth(), 100);
        descriptionInput.setPreferredSize(descriptionInputDim); */
        int result =JOptionPane.showConfirmDialog(null,
                        newPanel,
                        "Edit your todo here",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {
            newTitle = titleInput.getText();
            newDescription = descriptionInput.getText();
            newPriority = (int) priorityCombo.getSelectedItem();
            todoTemp.SetTitle(newTitle);
            todoTemp.SetEntry(newDescription);
            todoTemp.SetPriority(newPriority);
            data.setObjects(listTemp.getSelectedIndex(), todoTemp);
            resortListBoxModel();
            
        }
        data.writeToData();
    }
    
    private void displayDelete(int index) {
       
        int result =JOptionPane.showConfirmDialog(null,
            "Are you sure you want to \ndelete this from your todo list?",
            "You sure?",
            JOptionPane.YES_NO_OPTION);
                
        if(result == YES_OPTION) {
            data.deleteTodo(index);
            resortListBoxModel();
            //System.out.println();
        }
        data.writeToData();   
    }
    
    
    private void resortListBoxModel() {
        listboxModel.removeAllElements();
        Todo tempTodo;
        for(int i = 0; i<=data.getSize()-1; i++) {
            tempTodo = data.getObject(i);
            listboxModel.addElement((i+1)+ ". " +tempTodo.GetTitle());
        }
    }
    
    
}

