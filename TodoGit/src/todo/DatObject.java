/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import static java.nio.file.Files.newBufferedReader;
import java.nio.file.Files.*;
import static java.nio.file.Files.newBufferedReader;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Jonathan
 */
public class DatObject {
    private ArrayList<Todo> Objects = new ArrayList<Todo>();
    private String currentDir = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");
    private Path toData = Paths.get(currentDir + separator + "data.txt");
    //private File data = new File(toData.toString());
    public DatObject() {
        
        //File data = new File("/path/directory");
        if(Files.exists(toData)) {
          readData(); 
        }
        else {
          newData(); 
        }
        
    
    }
    
    public void readData() {
        String tempInput = " ";
        String tempDesc;
        String tempTitle;
        int tempPriority;
        Todo tempTodo;
        String[] newData = new String[3];
        //Scanner takeFromString;
        

        try {
            FileReader in = new FileReader(toData.toString());   
            BufferedReader dataRead = new BufferedReader(new FileReader(toData.toString()));
            while((tempInput = dataRead.readLine()) != null) {
                int i = 0;
                System.out.println(tempInput);
                for(String New : tempInput.split(",")) {
                    newData[i] = New;
                    i++;
                }
                
                for(String out : newData) {
                    System.out.println(out);
                }
                tempTitle = newData[0];
                //System.out.println(tempInput);
                //System.out.println(tempTitle);
                tempDesc = newData[1];
                tempPriority = Integer.parseInt(newData[2]);
                tempTodo = new Todo(tempTitle, tempDesc, tempPriority);
                Objects.add(tempTodo);
                //takeFromString.reset();
                
            }
            Collections.sort(Objects);
        } catch (IOException ex) {
            Logger.getLogger(DatObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchElementException ex) {
            System.out.println("There is no data in the file yet");
            Logger.getLogger(DatObject.class.getName()).log(Level.INFO, null, ex);
            //System.out.println(ex);
        }
    }
    
    public void newData() {
        
        try {
            Files.createFile(toData);
        }
        catch (FileAlreadyExistsException ex) {
            System.out.println(ex);
            Logger.getLogger(DatObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFileException ex) {
            System.out.println(ex);
            Logger.getLogger(DatObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(DatObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void addTodo(Todo to) {
        Objects.add(to);
        sortObjects();
    }
    public void deleteTodo(int index) {
        Objects.remove(index);
        sortObjects();
    }
    public void writeToData() {
        try {
            BufferedWriter writeData = Files.newBufferedWriter(toData);
        for(Todo obj: Objects) {
            String strData = obj.toString();
            writeData.write(strData, 0, strData.length());
            writeData.newLine();
        }
        writeData.flush();
        writeData.close();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(DatObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Todo getObject(int index) {
        return Objects.get(index);
    }
    
    public void sortObjects() {
        Collections.sort(Objects);
    }
    
    public void setObjects(int index, Todo obj) {
        Objects.set(index, obj);
        sortObjects();
    }
    
    public int getSize() {
        return Objects.size();
    }
    
}
