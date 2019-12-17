package main.model;

import java.util.LinkedList;
import java.util.Queue;

import main.exception.mower.InvalidCommandException;
import main.exception.mower.MowerException;
import main.exception.mower.NullGridException;
import main.model.orientation.NorthOrientation;
import main.model.orientation.Orientable;

public class Mower {
	
	private Orientable orientation;
	private Queue<Command> commands;
	private Command currentCommand;
	private Grid grid;
	
	
	public Mower() {
		this.grid = null;
		this.currentCommand = null;
		this.orientation = new NorthOrientation();
		this.commands = null;
		this.currentCommand = this.commands.poll();	
	}
	public Mower(Orientable orientation, LinkedList<Command> commands) throws MowerException{
		this.grid = null;
		this.currentCommand = null;
		this.orientation = orientation;
		this.commands = commands;
		this.currentCommand = this.commands.poll();		
	}
	
	public void setGrid(Grid grid) throws MowerException {
		if(this.grid != null)
			throw new MowerException();		
		this.grid = grid;		
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
