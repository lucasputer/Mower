package main;

import java.util.LinkedList;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.model.Command;
import main.model.Orientation;
public class InputParser {
	
	public static Orientation parseOrientation(String orientation) throws InvalidOrientationException{
		if(orientation.equals("N")) {
			return Orientation.NORTH;
		}else if(orientation.equals("E")) {
			return Orientation.EAST;
		}else if(orientation.equals("W")) {
			return Orientation.WEST;
		}else if(orientation.equals("S")) {
			return Orientation.SOUTH;
		}else {
			throw new InvalidOrientationException();
		}
	}
	
	public static LinkedList<Command> parseCommands(String commands) throws InvalidCommandException{
		LinkedList<Command> commandQueue = new LinkedList<Command>();
		for(int i = 0; i < commands.length(); i++) {
			if(commands.charAt(i) == 'F') {
				commandQueue.add(Command.FORWARD);
			}else if(commands.charAt(i) == 'L') {
				commandQueue.add(Command.LEFT);
			}else if(commands.charAt(i) == 'R') {
				commandQueue.add(Command.RIGHT);
			}else {
				throw new InvalidCommandException();
			}	
		}
		return commandQueue;
	}
	

}
