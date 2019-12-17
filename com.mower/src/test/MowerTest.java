package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import main.exception.mower.InvalidCommandException;
import main.exception.mower.MowerException;
import main.exception.mower.NullGridException;
import main.model.Command;
import main.model.Mower;

@RunWith(Suite.class)
@SuiteClasses({})
class MowerTest {
	@Test
	void rotateRightWithNoGridFailsTest() throws MowerException{
		try {
			LinkedList<Command> q = new LinkedList<Command>();
			q.add(Command.RIGHT);
			Mower mower = new Mower(q);
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(NullGridException e) {
			
		}
	}
	
	@Test
	void rotateLeftWithNoGridFailsTest() throws MowerException {
		try {
			LinkedList<Command> q = new LinkedList<Command>();
			q.add(Command.LEFT);
			Mower mower = new Mower(q);
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(NullGridException e) {
			
		}
	}
	
	
	@Test
	void moveForwardWithNoGridFailsTest() throws MowerException{
		try {
			LinkedList<Command> q = new LinkedList<Command>();
			q.add(Command.FORWARD);
			Mower mower = new Mower(q);
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(NullGridException e) {
			
		}
	}
	
	@Test
	void executeOneWithNoCommandsFailsTest() throws MowerException{
		try {
			Mower mower = new Mower(new LinkedList<Command>());
			mower.executeOne();	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}	
}
