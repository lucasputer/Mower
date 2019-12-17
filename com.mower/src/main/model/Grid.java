package main.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

import main.exception.grid.GridException;
import main.exception.grid.MowerOutOfRangeException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;

public class Grid {
	
	private Position gridLimit;
	private HashMap<Mower,Position> mowerPositions;
	private LinkedList<Mower> mowers;
	private Mower currentMower;
	private boolean[][] positions;
	
	public Grid(int x, int y) throws GridException{
		if(x < 0 || y < 0) {
			throw new NegativeGridException();
		}
		gridLimit = new Position(x,y);
		this.mowerPositions = new HashMap<Mower,Position>();
		this.mowers = new LinkedList<Mower>();
		this.currentMower = null;
		this.positions = new boolean[x+1][y+1];
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
		mowers.add(mower);
		
		
		if(currentMower == null)
			currentMower = mowers.pop();
		
		mower.setGrid(this);		
	}
	
	public void moveForward(Mower mower, Function<Position, Position> fn){
		Position currentPosition = this.getMowerPosition(mower);
		Position wantedPosition = fn.apply(currentPosition);
		if(wantedPosition.getX() >= 0 &&  wantedPosition.getY() >= 0 
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

	private void printMower() {
		if(currentMower != null) {
			Position currentPosition = this.getMowerPosition(currentMower);
			System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " " + currentMower.getOrientation().getIdentifier());
		}		
	}

	public void executeOne() throws MowerException {
		if(currentMower != null && currentMower.hasCommand()) {
			currentMower.executeOne();
		}
		if(currentMower != null && !currentMower.hasCommand()) {
			printMower();
			currentMower = mowers.poll();						
		}	
	}
	
	public void executeAll() throws MowerException {
		while(currentMower != null)
			executeOne();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentMower == null) ? 0 : currentMower.hashCode());
		result = prime * result + ((gridLimit == null) ? 0 : gridLimit.hashCode());
		result = prime * result + ((mowerPositions == null) ? 0 : mowerPositions.hashCode());
		result = prime * result + ((mowers == null) ? 0 : mowers.hashCode());
		result = prime * result + Arrays.deepHashCode(positions);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (currentMower == null) {
			if (other.currentMower != null)
				return false;
		} else if (!currentMower.equals(other.currentMower))
			return false;
		if (gridLimit == null) {
			if (other.gridLimit != null)
				return false;
		} else if (!gridLimit.equals(other.gridLimit))
			return false;
		if (mowerPositions == null) {
			if (other.mowerPositions != null)
				return false;
		} else if (!mowerPositions.equals(other.mowerPositions))
			return false;
		if (mowers == null) {
			if (other.mowers != null)
				return false;
		} else if (!mowers.equals(other.mowers))
			return false;
		if (!Arrays.deepEquals(positions, other.positions))
			return false;
		return true;
	}
}
