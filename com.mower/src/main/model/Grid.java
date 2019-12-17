package main.model;

import java.util.HashMap;
import java.util.LinkedList;
import main.exception.grid.GridException;
import main.exception.grid.MowerOutOfRangeException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;
import main.helper.OrientationHelper;

public class Grid {
	
	private Position gridLimit;
	private HashMap<Mower,Position> mowerPositions;
	private HashMap<Mower,Orientation> mowerOrientations;
	private LinkedList<Mower> mowers;
	private Mower currentMower;
	private HashMap<Position,Boolean> positions;
	
	public Grid(int x, int y) throws GridException{
		if(x < 0 || y < 0) {
			throw new NegativeGridException();
		}
		gridLimit = new Position(x,y);
		this.mowerPositions = new HashMap<Mower,Position>();
		this.mowerOrientations = new HashMap<Mower, Orientation>();
		this.mowers = new LinkedList<Mower>();
		this.currentMower = null;
		this.positions = new HashMap<Position, Boolean>();
	}

	public Position getLimit() {
		return this.gridLimit;
	}

	public void placeMower(Position position, Orientation orientation, Mower mower) throws GridException, MowerException {		
		if(position.getX() > gridLimit.getX() || position.getY() > gridLimit.getY() || position.getX() < 0 || position.getY() < 0 )
			throw new MowerOutOfRangeException();
				
		if(positions.containsKey(position) && positions.get(position))
			throw new PositionTakenException();
		positions.put(position,true);
		mowerPositions.put(mower,position);
		mowerOrientations.put(mower, orientation);
		mowers.add(mower);
		
		
		if(currentMower == null)
			currentMower = mowers.pop();
		
		mower.setGrid(this);		
	}
	
	public void moveForward(Mower mower) {		
		Position currentPosition = this.getMowerPosition(mower);
		Position wantedPosition = currentPosition;
		Orientation orientation = getMowerOrientation(mower);
		if(orientation == Orientation.NORTH) {
			wantedPosition = new Position(currentPosition.getX(),currentPosition.getY() + 1);
		}else if(orientation == Orientation.EAST) {
			wantedPosition = new Position(currentPosition.getX() + 1,currentPosition.getY());
		}else if(orientation == Orientation.SOUTH) {
			wantedPosition = new Position(currentPosition.getX(),currentPosition.getY() - 1);
		}else if(orientation == Orientation.WEST) {
			wantedPosition = new Position(currentPosition.getX() - 1,currentPosition.getY());
		}
		if(wantedPosition.getX() >= 0 &&  wantedPosition.getY() >= 0 
				&& wantedPosition.getX() <= gridLimit.getX() && wantedPosition.getY() <= gridLimit.getY() &&
				(!positions.containsKey(wantedPosition)|| !positions.get(wantedPosition))) {
			positions.put(currentPosition,false);
			mowerPositions.put(mower, wantedPosition);
			positions.put(wantedPosition,true);
		}		
	}

	public Position getMowerPosition(Mower mower){
		return mowerPositions.get(mower);
	}
	
	public Orientation getMowerOrientation(Mower mower){
		return mowerOrientations.get(mower);
	}

	private void printMower() {
		if(currentMower != null) {
			Position currentPosition = this.getMowerPosition(currentMower);
			System.out.println(currentPosition.getX() + " " + currentPosition.getY() + " " + OrientationHelper.getIdentifier(mowerOrientations.get(currentMower)));
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
		result = prime * result + positions.hashCode();
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
		if (!positions.equals(other.positions))
			return false;
		return true;
	}

	public void rotateRight(Mower mower) {
		mowerOrientations.put(mower,OrientationHelper.rotateRight(getMowerOrientation(mower)));
		
	}
	
	public void rotateLeft(Mower mower) {
		mowerOrientations.put(mower,OrientationHelper.rotateLeft(getMowerOrientation(mower)));
		
	}
}
