package main.model.orientation;

public class EastOrientation implements Orientable {
	
	@Override
	public String getIdentifier(){
		return "E";
	}

	@Override
	public Orientable rotateRight() {
		return new SouthOrientation();
	}

	@Override
	public Orientable rotateLeft() {
		return new NorthOrientation();
	}

}
