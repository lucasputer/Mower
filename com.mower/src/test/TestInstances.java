package test;

import java.util.PriorityQueue;

import main.exception.grid.GridException;
import main.exception.mower.MowerException;
import main.model.Command;
import main.model.Grid;
import main.model.Mower;
import main.model.orientation.NorthOrientation;

public class TestInstances {

	public static Grid getStandardGridWithMowerFacingNorthAndCommandLeft() throws GridException, MowerException{
		return getStandardGridWithMowerFacingNorthAndCommand(Command.LEFT);
	}
	
	public static Grid getStandardGridWithMowerFacingNorthAndCommandForward() throws GridException, MowerException{
		return getStandardGridWithMowerFacingNorthAndCommand(Command.FORWARD);
	}
	
	public static Grid getStandardGridWithMowerFacingNorthAndCommand(Command command)throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		PriorityQueue<Command> q = new PriorityQueue<Command>();
		q.add(command);
		grid.placeMower(0, 0,new Mower(new NorthOrientation(), q));
		return grid;
	}
	
	public static Mower getMowerWithFullTurn(Command command) throws MowerException {
		
		PriorityQueue<Command> q = new PriorityQueue<Command>();
		q.add(command);
		q.add(command);
		q.add(command);
		q.add(command);
		
		return new Mower(new NorthOrientation(), q);		
	}
}
