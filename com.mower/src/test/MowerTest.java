package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.exception.grid.GridException;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.exception.mower.MowerException;
import main.model.Mower;
import main.model.orientation.EastOrientation;
import main.model.orientation.NorthOrientation;
import main.model.orientation.SouthOrientation;
import main.model.orientation.WestOrientation;

class MowerTest {

	@Test
	void insertMowerWithInvalidOrientationTest() throws MowerException{
		try {
			@SuppressWarnings("unused")
			Mower mower = new Mower("R", "");	
			fail("No exception was thrown");
		}catch(InvalidOrientationException e) {
			
		}	
	}
	
	@Test
	void insertMowerWithValidOrientationTest() throws MowerException{
		Mower mower = new Mower("N", "");
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
	}
	
	@Test
	void insertMowerWithInvalidCommandTest() throws MowerException, GridException{
		try {
			@SuppressWarnings("unused")
			Mower mower = new Mower("N", "T");	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}					
	}
	
	@Test
	void rotateMowerRightTest() throws MowerException, GridException{
		Mower mower = new Mower( "N", "RRRR");
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
		Mower mower = new Mower("N", "LLLL");
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
	
	@Test
	void executeAllTest() throws MowerException, GridException{
		Mower mower = new Mower("N", "LLL");
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
		mower.executeAll();
		assertEquals(mower.getOrientation().getIdentifier(),new EastOrientation().getIdentifier());
	}
	
	
	
	@Test
	void moveForwardWithNoGridFailsTest() throws MowerException{
		try {
			Mower mower = new Mower("N", "F");
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}
	
	@Test
	void executeOneWithNoCommandsFailsTest() throws MowerException{
		try {
			Mower mower = new Mower("N", "");
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}
	
	
	

}
