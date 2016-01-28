/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

/**
 *
 * @author Jonathan
 */

public class Todo implements Comparable {
    /*
    0 = least
    1
    2
    3 = most
    */
    private int priority;
    private String entry;
    private String title;
    
    public Todo(String Title, String Description, int pr) {
        this.priority = pr;
        this.entry = Description;
        this.title = Title;
    }
    
    public int GetPriority() {
        return priority;
    }
    
    public String GetEntry() {
        return entry;
    }
    
    public String GetTitle() {
        return title; 
    }
    
    public void SetPriority(int prior) {
        priority = prior;
    }
    
    public void SetEntry(String ent) {
        entry = ent;
    }
    
    public void SetTitle(String tit) {
        title = tit; 
    }
   

    @Override
    public int compareTo(Object comparesTodo) {
        int comparepriority = ((Todo)comparesTodo).GetPriority();
        return comparepriority - this.priority;
    }
    
    @Override
    public String toString() {
        String object = title + "," + entry + "," + priority; // might want to add + "\n"
        return object;
    }
    
}


    /**
     * @param args the command line arguments
     */
    
