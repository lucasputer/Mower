package test;


import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import main.exception.grid.GridException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;
import main.model.Grid;
import main.model.Mower;


class GridTest {
	
	@Test
	void negativeGridShouldFailTest() throws GridException{
		try {
			@SuppressWarnings("unused")
			Grid grid = new Grid(-1,4);
			fail("No exception was thrown");
		}catch(NegativeGridException e) {
		}		
	}
	
	@Test
	void positiveGridShouldNotFailTest() throws GridException {
		Grid grid = new Grid(1,4);
		assertEquals(grid.getLimit().getX(),1);
		assertEquals(grid.getLimit().getY(),4);
	}
	
	@Test
	void insertMowerInEmptyGridTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", ""));		
	}
	
	@Test
	void insertTwoMowersTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", ""));	
		grid.placeMower(0, 1,new Mower( "N", ""));				
	}
	
	@Test
	void InsertTwoMowersInSamePositionTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", ""));	
		try {
			grid.placeMower(0, 0,new Mower( "N", ""));	
			fail("Cannot place two mowers in the same position");
		}catch(PositionTakenException e) {
			
		}					
	}
	
	@Test
	void executeOneWithNoMowersDoesNothingTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.executeOne();
		assertEquals(grid,new Grid(4,4));
	}
	
	@Test
	void printOneMovementMowerTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "L"));
		grid.executeOne();
		assertEquals("0 0 W\n",outContent.toString());
	}
	
	@Test
	void noPrintTwoMovementMowerTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "LL"));
		grid.executeOne();
		assertEquals("",outContent.toString());
	}
	
	@Test
	void printTwoMowersTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "L"));
		grid.placeMower(4, 4,new Mower( "S", "F"));
		grid.executeOne();
		grid.executeOne();
		assertEquals("0 0 W\n4 3 S\n",outContent.toString());
	}
	
	@Test
	void executeAllTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent1));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "L"));
		grid.placeMower(4, 4,new Mower( "S", "F"));
		grid.executeOne();
		grid.executeOne();
		
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent2));
		Grid grid2 = new Grid(4,4);
		grid2.placeMower(0, 0,new Mower( "N", "L"));
		grid2.placeMower(4, 4,new Mower( "S", "F"));
		grid2.executeAll();	
		assertEquals(outContent2.toString(),outContent1.toString());
	}
	
	
	@Test
	void mowerMovesForwardWithBlockingMowerTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "F"));
		grid.placeMower(0, 1,new Mower( "S", "R"));
		grid.executeAll();
		assertEquals("0 0 N\n0 1 W\n",outContent.toString());		
	}
	
	@Test
	void mowerMovesForwardWithBlockingMowerButNextOneMovesTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0,new Mower( "N", "F"));
		grid.placeMower(0, 1,new Mower( "N", "F"));
		grid.executeAll();
		assertEquals("0 0 N\n0 2 N\n",outContent.toString());		
	}
	
	@Test
	void mowerMovesOutOfBoundsTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		grid.placeMower(4, 4,new Mower( "N", "F"));
		grid.executeAll();
		assertEquals("4 4 N\n",outContent.toString());			
	}		
}
