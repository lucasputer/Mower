package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import main.Main;
import main.exception.grid.GridException;
import main.exception.grid.MowerOutOfRangeException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;

@RunWith(Suite.class)
@SuiteClasses({})
public class IntegrationTest {

	@Test
	void validInputTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("1 3 N\n5 1 E\n",outContent.toString());		
	}
	
	@Test
	void negativeXGridTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("-5 5\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);	    
	    try {
	    	Main.main(null);
	    	fail("should have thrown exception");
	    }catch(NegativeGridException e) {
	    	
	    }		
	}
	
	@Test
	void negativeYGridTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 -5\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);	    
	    try {
	    	Main.main(null);
	    	fail("should have thrown exception");
	    }catch(NegativeGridException e) {
	    	
	    }		
	}
	
	@Test
	void invalidGridTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("f 5\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);	    
	    try {
	    	Main.main(null);
	    	fail("should have thrown exception");
	    }catch(NumberFormatException e) {
	    	
	    }		
	}
	
	@Test
	void mowerOutOfBoundsGridTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("1 1\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);	    
	    try {
	    	Main.main(null);
	    	fail("should have thrown exception");
	    }catch(MowerOutOfRangeException e) {
	    	
	    }		
	}
	
	@Test
	void mowersSamePositionTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n1 2 N\nLFLFLFLFF\n1 2 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);	    
	    try {
	    	Main.main(null);
	    	fail("should have thrown exception");
	    }catch(PositionTakenException e) {
	    	
	    }		
	}
	
	@Test
	void mowerWithNoCommandsTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n1 2 N\n\n3 3 E\nFFRFFRFRRF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("1 2 N\n5 1 E\n",outContent.toString());	
	}
	
	@Test
	void mowerGoesToUpperBorderTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n1 2 N\nFFFF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("1 5 N\n",outContent.toString());	
	}
	
	@Test
	void mowerGoesToRightBorderTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n2 2 E\nFFFF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("5 2 E\n",outContent.toString());	
	}
	
	@Test
	void mowerGoesToLowerBorderTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n2 2 S\nFFFF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("2 0 S\n",outContent.toString());	
	}
	
	@Test
	void mowerGoesToLeftBorderTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n2 2 W\nFFFF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("0 2 W\n",outContent.toString());	
	}
	
	@Test
	void twoMowersCollideTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5\n2 2 N\nFFFF\n2 3 N\nF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("2 2 N\n2 4 N\n",outContent.toString());	
	}
	
	@Test
	void bigGridTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("2147483647 2147483647\n2 2 N\nFFFF\n2 3 N\nF".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("2 2 N\n2 4 N\n",outContent.toString());	
	}
	
	@Test
	void manyMowersTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("10 10\n0 0 N\nFF\n1 1 N\nFF\n2 2 N\nFF\n3 3 N\nFF\n4 4 N\nFF\n5 5 N\nFF\n".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("0 2 N\n1 3 N\n2 4 N\n3 5 N\n4 6 N\n5 7 N\n",outContent.toString());	
	}
	
	@Test
	void noMowersTest() throws NumberFormatException, GridException, MowerException {
		ByteArrayInputStream in = new ByteArrayInputStream("5 5".getBytes());
		System.setIn(in);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		Main.main(null);
		
		assertEquals("",outContent.toString());	
	}
	
	
}
