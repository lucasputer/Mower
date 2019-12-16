package main;

import java.util.ArrayList;

public class Mower {

	public enum Orientation {
	    NORTH,
	    EAST,
	    WEST,
	    SOUTH
	  }
	
	public enum Command{
		LEFT,
		RIGHT,
		FORWARD
	}
	
	private Position position;
	private Orientation orientation;
	private ArrayList<Command> commands;
	private int currentCommand;
	

	public Mower(Position position, String orientation, String commands){
		this.position = position;
		parseOrientation(orientation);
		parseCommands(commands);
	}
	
	private void parseCommands(String commands){
		this.commands = new ArrayList<Command>();
		
	}
	
	private void parseOrientation(String orientation){

	}


	public Position getPosition() {
		return position;
	}



	public Orientation getOrientation() {
		return orientation;
	}

}
