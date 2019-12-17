package main.model.orientation;

public class SouthOrientation implements Orientable {
	
	@Override
	public String getIdentifier(){
		return "S";
	}

	@Override
	public Orientable rotateRight() {
		return new WestOrientation();
	}

	@Override
	public Orientable rotateLeft() {
		return new EastOrientation();
	}

}
