package test;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static org.junit.jupiter.api.Assertions.*;

import main.InputParser;
import main.exception.mower.InvalidCommandException;
import main.exception.mower.InvalidOrientationException;
import main.model.Command;
import main.model.Orientation;

@RunWith(Suite.class)
@SuiteClasses({})
class InputParserTest {

	@Test
	void mowerWithInvalidOrientationTest() {
		try {
		InputParser.parseOrientation("R");	
		fail("No exception was thrown");
		}catch(InvalidOrientationException e) {
			
		}
	}
	
	@Test
	void mowerWithValidOrientationTest() throws InvalidOrientationException{
		assertEquals(InputParser.parseOrientation("N"),Orientation.NORTH);
	}
		
	@Test
	void invalidCommandTest(){
		try {
			InputParser.parseCommands("T");	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}	
		
		try {
			InputParser.parseCommands("RRT");	
			fail("No exception was thrown");
		}catch(InvalidCommandException e) {
			
		}
	}
	
	@Test
	void validCommandTest() throws InvalidCommandException{
		LinkedList<Command> q = InputParser.parseCommands("R");	
		assertEquals(q.peek(),Command.RIGHT);
		
		q = InputParser.parseCommands("RRLLFF");
		LinkedList<Command> q2 = new LinkedList<Command>();
		q2.add(Command.RIGHT);
		q2.add(Command.RIGHT);
		q2.add(Command.LEFT);
		q2.add(Command.LEFT);
		q2.add(Command.FORWARD);
		q2.add(Command.FORWARD);		
		
		while(!q.isEmpty()) {
			assertEquals(q.poll(), q2.poll());
		}
	}

}
