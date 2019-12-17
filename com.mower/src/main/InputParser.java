package main;

import java.util.LinkedList;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.model.Command;
import main.model.orientation.EastOrientation;
import main.model.orientation.NorthOrientation;
import main.model.orientation.Orientable;
import main.model.orientation.SouthOrientation;
import main.model.orientation.WestOrientation;

public class InputParser {
	
	public static Orientable parseOrientation(String orientation) throws InvalidOrientationException{
		if(orientation.equals("N")) {
			return new NorthOrientation();
		}else if(orientation.equals("E")) {
			return new EastOrientation();
		}else if(orientation.equals("W")) {
			return new WestOrientation();
		}else if(orientation.equals("S")) {
			return new SouthOrientation();
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
