package main.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.exception.mower.MowerException;
import main.exception.mower.NullGridException;
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
	private Queue<Command> commands;
	private Command currentCommand;
	private Grid grid;
	

	public Mower(String orientation, String commands) throws MowerException{
		this.grid = null;
		this.currentCommand = null;
		parseOrientation(orientation);
		parseCommands(commands);		
	}
	
	public void setGrid(Grid grid) throws MowerException {
		if(this.grid != null)
			throw new MowerException();		
		this.grid = grid;		
	}
	
	private void parseCommands(String commands) throws InvalidCommandException{
		this.commands = new PriorityQueue<Command>();
		for(int i = 0; i < commands.length(); i++) {
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
		this.currentCommand = this.commands.poll();		
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
	
	public Grid getGrid() {
		return this.grid;
	}

	private void rotateRight() {
		this.orientation = this.orientation.rotateRight();		
	}

	private void rotateLeft() {
		this.orientation = this.orientation.rotateLeft();		
	}
	
	private void moveForward() throws MowerException{
		if(this.grid == null)
			throw new NullGridException();
		if(this.getOrientation().getIdentifier() == "N") {
			this.grid.moveForward(this,(p) -> new Position(p.getX(),p.getY() + 1));
		}else if(this.getOrientation().getIdentifier() == "E") {
			this.grid.moveForward(this,(p) -> new Position(p.getX() + 1,p.getY()));
		}else if(this.getOrientation().getIdentifier() == "S") {
			this.grid.moveForward(this,(p) -> new Position(p.getX(),p.getY() - 1));
		}else if(this.getOrientation().getIdentifier() == "W") {
			this.grid.moveForward(this,(p) -> new Position(p.getX() - 1,p.getY()));
		}			
	}
	
	public void executeOne() throws MowerException {
		if(this.currentCommand == null)
			throw new InvalidCommandException();
		if(this.currentCommand == Command.LEFT) {
			this.rotateLeft();
		}else if(this.currentCommand == Command.RIGHT) {
			this.rotateRight();
		}else if(this.currentCommand == Command.FORWARD) {
			this.moveForward();
		}else {
			throw new InvalidCommandException();
		}

		this.currentCommand = this.commands.poll();						
	}
	
	public boolean hasCommand() {
		return this.currentCommand != null;
	}
	
	public void executeAll() throws MowerException {
		while(this.currentCommand != null)
			this.executeOne();
	}

}
