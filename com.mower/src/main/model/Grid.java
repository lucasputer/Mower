package main.model;

import java.util.HashMap;
import java.util.function.Function;

import main.exception.grid.GridException;
import main.exception.grid.MowerOutOfRangeException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;

public class Grid {
	
	private Position gridLimit;
	private HashMap<Mower,Position> mowerPositions;
	private int currentMower;
	private boolean[][] positions;
	
	public Grid(int x, int y) throws GridException{
		if(x < 0 || y < 0) {
			throw new NegativeGridException();
		}
		gridLimit = new Position(x,y);
		this.mowerPositions = new HashMap<Mower,Position>();
		this.currentMower = -1;
		this.positions = new boolean[x][y];
	}

	public Position getLimit() {
		return this.gridLimit;
	}

	public void placeMower(int mowerX, int mowerY, Mower mower) throws GridException, MowerException {		
		if(mowerX > gridLimit.getX() || mowerY > gridLimit.getY() || mowerX < 0 || mowerY < 0 )
			throw new MowerOutOfRangeException();
		
		Position position = new Position(mowerX,mowerY);
		
		if(positions[mowerX][mowerY] == true)
			throw new PositionTakenException();
		positions[mowerX][mowerY] = true;
		mowerPositions.put(mower,position);
		
		
		if(currentMower == -1)
			currentMower = 0;
		
		mower.setGrid(this);		
	}

	public void moveForward(Mower mower, Function<Position, Position> fn){
		Position currentPosition = this.getMowerPosition(mower);
		Position wantedPosition = fn.apply(currentPosition);
		if(wantedPosition.getX() > 0 &&  wantedPosition.getY() > 0 
				&& wantedPosition.getX() <= gridLimit.getX() && wantedPosition.getY() <= gridLimit.getY() &&
				positions[wantedPosition.getX()][wantedPosition.getY()] == false) {
			positions[currentPosition.getX()][currentPosition.getY()] = false;
			mowerPositions.put(mower, wantedPosition);
			positions[wantedPosition.getX()][wantedPosition.getY()] = true;
		}
	}

	private Position getMowerPosition(Mower mower){
		return mowerPositions.get(mower);
	}

	public void printMower(Mower mower) {
		Position currentPosition = this.getMowerPosition(mower);
		System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " " + mower.getOrientation().getIdentifier());
		
	}

}
