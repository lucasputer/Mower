package main.model;

import java.util.ArrayList;
import java.util.HashMap;

import main.exception.grid.GridException;
import main.exception.grid.MowerOutOfRangeException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;

public class Grid {
	
	private Position gridLimit;
	private ArrayList<Mower> mowers;
	private HashMap<Position,Mower> takenPositions;
	private int currentMower;
	
	public Grid(int x, int y) throws GridException{
		if(x < 0 || y < 0) {
			throw new NegativeGridException();
		}
		gridLimit = new Position(x,y);
		this.mowers = new ArrayList<Mower>();
		this.takenPositions = new HashMap<Position,Mower>();
		this.currentMower = -1;
	}

	public Position getLimit() {
		return this.gridLimit;
	}

	public void placeMower(int mowerX, int mowerY, Mower mower) throws GridException, MowerException {
		
		if(mowerX > gridLimit.getX() || mowerY > gridLimit.getY() ||
				0 > gridLimit.getX() || 0 > gridLimit.getY() )
			throw new MowerOutOfRangeException();
		
		Position position = new Position(mowerX,mowerY);
		
		if(takenPositions.containsKey(position))
			throw new PositionTakenException();
		
		mowers.add(mower);
		takenPositions.put(position, mower);
		
		if(currentMower == -1)
			currentMower = 0;
		
	}

}
