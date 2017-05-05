package HackerRank;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        int gridRows = in.nextInt();
        int gridcolumns = in.nextInt();
        String grid[] = new String[gridRows];
        for(int i = 0; i < gridRows; i++){
            grid[i] = in.next();
        }
        int testRows = in.nextInt();
        int testColumns = in.nextInt();
        String testGrid[] = new String[testRows];
        for(int i = 0; i < testRows; i++){
            testGrid[i] = in.next();
        }
        
        int j = 0;
        int columnMatch = 0;
        int rowMatch = -1;
        
        for(int i = 0; i < gridRows; i++){
            if(testGrid[j].contains(grid[i])){
                columnMatch = grid[i].indexOf(testGrid[j]);
                rowMatch = i;
            }
        }
        int matching = 0;
        for(int i = rowMatch; i < (rowMatch + testRows); i++){                                 
         StringBuilder str = new StringBuilder(grid[i]);
            if(!str.substring(columnMatch, columnMatch + testColumns - 1).matches(testGrid[j])){
               matching++;
               j++;
            }
        }
                
        if(matching == columnMatch){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
                                     
                                         
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
