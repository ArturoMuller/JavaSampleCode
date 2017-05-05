package Chapter6;

import java.util.ArrayList;
import java.util.List;

import Chapter15.Direction;
public class p6s11 {
	
	
	public static List<Integer> spiral(int[][] input){
		List<Integer> output = new ArrayList<Integer>();
		Direction dir = Direction.right;
		int rows = input.length;
		int columns = input[0].length;
		
		int currRow = 0;
		int currColumn = 0;
		
		int maxRight = columns ;
		int maxLeft = -1;
		int maxUp = 0;
		int maxDown =  rows;
		for(int i = 0; i < (rows * columns); i++){
				switch(dir){
				case right:
					output.add(input[currRow][currColumn++]);
					if(currColumn == maxRight ){
						currColumn--;
						maxUp = currRow++;
						dir = dir.next;
					}
					break;
				case down:
					output.add(input[currRow++][currColumn]);
					if(currRow == maxDown){
						currRow--;
						maxRight = currColumn--;
						dir = dir.next;
					}
					break;
				case left:
					output.add(input[currRow][currColumn--]);
					if(currColumn == maxLeft){
						currColumn++;
						maxDown = currRow--;
						dir = dir.next;
					}
					break;
				case up:
					output.add(input[currRow--][currColumn]);
					if(currRow == maxUp){
						currRow++;
						maxLeft =  currColumn++;
						dir = dir.next;
					}
					break;
				default:
					break; 
					
				}
			}
		return output;
		}
		
	
	public static void main(String[] args){
		int[][]  a=  {{1,2, 3 ,4 }, { 5,6, 7, 8},{9, 10, 11,12}, {13, 14, 15, 16}};
		System.out.print(spiral( a));
	}
	
}
