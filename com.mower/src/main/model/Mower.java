package main.model;

import java.util.ArrayList;

import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.exception.mower.MowerException;
import main.model.orientation.EastOrientation;
import main.model.orientation.NorthOrientation;
import main.model.orientation.Orientable;
import main.model.orientation.SouthOrientation;
import main.model.orientation.WestOrientation;

public class Mower {
	
	public enum Command{
		FORWARD,
		LEFT,
		RIGHT
	}
	
	private Orientable orientation;
	private ArrayList<Command> commands;
	private int currentCommand;
	private Grid grid;
	

	public Mower(String orientation, String commands, Grid grid) throws MowerException{
		this.grid = grid;
		this.currentCommand = -1;
		parseOrientation(orientation);
		parseCommands(commands);		
	}
	
	private void parseCommands(String commands) throws InvalidCommandException{
		this.commands = new ArrayList<Command>();
		for(int i = 0; i < commands.length(); i++) {
			Command command = null;
			if(commands.charAt(i) == 'F') {
				this.commands.add(Command.FORWARD);
			}else if(commands.charAt(i) == 'L') {
				this.commands.add(Command.LEFT);
			}else if(commands.charAt(i) == 'R') {
				this.commands.add(Command.RIGHT);
			}else {
				throw new InvalidCommandException();
			}			
		}
		if(!commands.isEmpty())
			this.currentCommand = 0;		
	}
	
	private void parseOrientation(String orientation) throws InvalidOrientationException{
		if(orientation == "N") {
			this.orientation = new NorthOrientation();
		}else if(orientation == "E") {
			this.orientation = new EastOrientation();
		}else if(orientation == "W") {
			this.orientation = new WestOrientation();
		}else if(orientation == "S") {
			this.orientation = new SouthOrientation();
		}else {
			throw new InvalidOrientationException();
		}
	}

	public Orientable getOrientation() {
		return orientation;
	}

	private void rotateRight() {
		this.orientation = this.orientation.rotateRight();		
	}

	private void rotateLeft() {
		this.orientation = this.orientation.rotateLeft();		
	}
	
	private void moveForward() {
//		this.grid.moveForward(this);
	}
	
	public void executeOne() throws InvalidCommandException {
		if(this.currentCommand == -1)
			throw new InvalidCommandException();
		if(this.commands.get(currentCommand) == Command.LEFT) {
			this.rotateLeft();
		}else if(this.commands.get(currentCommand) == Command.RIGHT) {
			this.rotateRight();
		}else if(this.commands.get(currentCommand) == Command.FORWARD) {
			this.moveForward();
		}else {
			throw new InvalidCommandException();
		}
		this.currentCommand++;
		if(currentCommand == commands.size())
			currentCommand = -1;
	}

}
