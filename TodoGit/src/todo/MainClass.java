/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *known bugs:
 * 1: Size of listbox is dynamic
 * Solution: wrap the listbox in a JScrollPane
 * 2: Size of JOptionPane is dynamic if fields have already been assigned a value
 * Solution: ?
 */
public class MainClass {
    public static void main(String[] args) {
         try {
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
              Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
         }
           
        SwingUtilities.invokeLater(() -> { //Lambda expression
            
            MainFrame frame = new MainFrame("Todo");
            frame.setVisible(true);
        });
    }
    
}

