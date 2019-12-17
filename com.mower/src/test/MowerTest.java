package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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

@RunWith(Suite.class)
@SuiteClasses({})
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
			LinkedList<Command> q = new LinkedList<Command>();
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
			Mower mower = new Mower(new NorthOrientation(), new LinkedList<Command>());
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}	
}
