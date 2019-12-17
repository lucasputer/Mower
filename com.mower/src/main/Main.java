package main;

import java.util.Scanner;

import main.exception.grid.GridException;
import main.exception.mower.MowerException;
import main.model.Grid;
import main.model.Mower;
import main.model.Position;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, GridException, MowerException {
		
		Scanner s = new Scanner(System.in);
		
		String[] firstLine = s.nextLine().split(" ");
		Grid grid = new Grid(Integer.parseInt(firstLine[0]),Integer.parseInt(firstLine[1]));
					
		String[] line;
		String commands;
		while(s.hasNextLine()) {
			line =  s.nextLine().split(" ");
			commands =  s.nextLine();
			
			grid.placeMower(new Position(Integer.parseInt(line[0]),Integer.parseInt(line[1])),InputParser.parseOrientation(line[2]),new Mower(InputParser.parseCommands(commands)));			
		}
		s.close();
		grid.executeAll();		
	}

}
