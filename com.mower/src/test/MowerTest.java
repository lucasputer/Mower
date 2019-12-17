package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import main.exception.grid.GridException;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.MowerException;
import main.exception.mower.NullGridException;
import main.model.Command;
import main.model.Mower;
import main.model.orientation.EastOrientation;
import main.model.orientation.NorthOrientation;
import main.model.orientation.SouthOrientation;
import main.model.orientation.WestOrientation;

class MowerTest {
	
	@Test
	void rotateMowerRightTest() throws MowerException, GridException{
		Mower mower = TestInstances.getMowerWithFullTurn(Command.RIGHT);
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
		Mower mower = TestInstances.getMowerWithFullTurn(Command.LEFT);
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
		Mower mower = TestInstances.getMowerWithFullTurn(Command.RIGHT);
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
		mower.executeAll();
		assertEquals(mower.getOrientation().getIdentifier(),new NorthOrientation().getIdentifier());
	}
	
	
	
	@Test
	void moveForwardWithNoGridFailsTest() throws MowerException{
		try {
			PriorityQueue<Command> q = new PriorityQueue<Command>();
			q.add(Command.FORWARD);
			Mower mower = new Mower(new NorthOrientation(), q);
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(NullGridException e) {
			
		}
	}
	
	@Test
	void executeOneWithNoCommandsFailsTest() throws MowerException{
		try {
			Mower mower = new Mower(new NorthOrientation(), new PriorityQueue<Command>());
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}	
}
