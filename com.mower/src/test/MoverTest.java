package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.exception.grid.GridException;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.exception.mower.MowerException;
import main.model.Grid;
import main.model.Mower;
import main.model.Position;
import main.model.orientation.EastOrientation;
import main.model.orientation.NorthOrientation;
import main.model.orientation.SouthOrientation;
import main.model.orientation.WestOrientation;

class MoverTest {

	@Test
	void insertMowerWithInvalidOrientationTest() throws MowerException, GridException{
		try {
			@SuppressWarnings("unused")
			Mower mower = new Mower("R", "", new Grid(4,4));	
			fail("No exception was thrown");
		}catch(InvalidOrientationException e) {
			
		}	
	}
	
	@Test
	void insertMowerWithValidOrientationTest() throws MowerException, GridException{
		Mower mower = new Mower("N", "", new Grid(4,4));
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
	}
	
	@Test
	void insertMowerWithInvalidCommandTest() throws MowerException, GridException{
		try {
			@SuppressWarnings("unused")
			Mower mower = new Mower("N", "T", new Grid(4,4));	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}					
	}
	
	@Test
	void rotateMowerRightTest() throws MowerException, GridException{
		Mower mower = new Mower( "N", "RRRR", new Grid(4,4));
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new EastOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new SouthOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new WestOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());

	}
	
	@Test
	void rotateMowerLeftTest() throws MowerException, GridException{
		Mower mower = new Mower("N", "LLLL", new Grid(4,4));
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new WestOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new SouthOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new EastOrientation().getIdentifier());
		mower.executeOne();
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());

	}
	
	
	

}
