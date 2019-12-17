package main.model.orientation;

public class WestOrientation implements Orientable {
	
	@Override
	public String getIdentifier(){
		return "W";
	}

	@Override
	public Orientable rotateRight() {
		return new NorthOrientation();
	}

	@Override
	public Orientable rotateLeft() {
		return new SouthOrientation();
	}

}
