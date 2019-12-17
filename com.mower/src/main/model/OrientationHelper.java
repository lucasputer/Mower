package main.model;

public class OrientationHelper{
	public static Orientation rotateRight(Orientation orientation) {
		switch (orientation) {
		case NORTH:
			return Orientation.EAST;
		case WEST:
			return Orientation.NORTH;
		case SOUTH:
			return Orientation.WEST;
		case EAST:
			return Orientation.SOUTH;
		}
		return orientation;			
	}
	public static Orientation rotateLeft(Orientation orientation) {
		switch (orientation) {
		case NORTH:
			return Orientation.WEST;
		case WEST:
			return Orientation.SOUTH;
		case SOUTH:
			return Orientation.EAST;
		case EAST:
			return Orientation.NORTH;
		}
		return orientation;			
	}
	
	public static String getIdentifier(Orientation orientation) {
		switch (orientation) {
		case NORTH:
			return "N";
		case WEST:
			return "W";
		case SOUTH:
			return "S";
		case EAST:
			return "E";
		default:
			return "";
		}
	}
}
