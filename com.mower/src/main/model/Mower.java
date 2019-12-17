package main.model;

import java.util.LinkedList;
import java.util.Queue;

import main.exception.mower.InvalidCommandException;
import main.exception.mower.MowerException;
import main.exception.mower.NullGridException;

public class Mower {
	
	private Queue<Command> commands;
	private Command currentCommand;
	private Grid grid;
	
	public Mower(LinkedList<Command> commands) throws MowerException{
		this.grid = null;
		this.currentCommand = null;
		this.commands = commands;
		this.currentCommand = this.commands.poll();		
	}
	
	public void setGrid(Grid grid) throws MowerException {
		if(this.grid != null)
			throw new MowerException();		
		this.grid = grid;		
	}
	
	public Grid getGrid() {
		return this.grid;
	}

	private void rotateRight() throws NullGridException {
		if(this.grid == null)
			throw new NullGridException();
		this.grid.rotateRight(this);	
	}

	private void rotateLeft() throws NullGridException {
		if(this.grid == null)
			throw new NullGridException();
		this.grid.rotateLeft(this);		
	}
	
	private void moveForward() throws MowerException{
		if(this.grid == null)
			throw new NullGridException();
		this.grid.moveForward(this);			
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
