/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.swing.JOptionPane;
/**
 *
 * @author Haseeb Khizar
 */
public class localspider {

    public static Map<String, Path> files = new HashMap <String, Path>();
    public static Map<String, Path> directories = new HashMap<String, Path>();
    
    private static final class PF extends SimpleFileVisitor<Path> {
        @Override public FileVisitResult visitFile(Path fil, BasicFileAttributes att) throws IOException {
          files.put(fil.getFileName().toString(), fil);
          return FileVisitResult.CONTINUE;
        }

        @Override  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes att) throws IOException {
          directories.put(dir.getFileName().toString(), dir);
          return FileVisitResult.CONTINUE;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void indexing(){
        for(String n: files.keySet()){
            Path f = files.get(n);
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(f.toString()))) {//buffered reader to parse csv file
                while ((line = br.readLine()) != null) {//loop to parse through lines of the csv
                    String[] words = line.split("\\W");//splitting the whole line into a string array
                    Vector<Path> p = new Vector<Path>();
                    for(int i = 0; i<words.length;i++){
                        try (FileWriter fw = new FileWriter("index.csv", true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
					out.println(words[i] + "," + f.toString());	
				} catch (Exception e) { 
                                    e.printStackTrace();
				}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void search(){
        Scanner reader = new Scanner(System.in);
        String s = JOptionPane.showInputDialog("Enter word to search");
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("index.csv"))) {//buffered reader to parse csv file
                while ((line = br.readLine()) != null) {//loop to parse through lines of the csv
                    String[] words = line.split(",");//splitting the whole line into a string array
                    if (words[0].equals(s)){
                        System.out.println("Word " + s + " found at:" + words[1]);
                    }
                    
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }
    public static void main(String[] args) throws IOException{
    String root = "C:\\Users\\Haseeb Khizar\\Downloads";
    FileVisitor<Path> crawler = new PF();
    Files.walkFileTree(Paths.get(root), crawler);
    System.out.println("Files: ");
    System.out.println(files);
    System.out.println("Directories: ");
    System.out.println(directories);
    try{
    indexing();
    search();
    } catch(Exception e){
        e.printStackTrace();
    }
    }  
}
