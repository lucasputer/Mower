package main.model.orientation;

public class NorthOrientation implements Orientable {
	
	@Override
	public String getIdentifier(){
		return "N";
	}

	@Override
	public Orientable rotateRight() {
		return new EastOrientation();
	}

	@Override
	public Orientable rotateLeft() {
		return new WestOrientation();
	}

}
