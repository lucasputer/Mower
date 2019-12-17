package test;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import main.exception.grid.GridException;
import main.exception.grid.NegativeGridException;
import main.exception.grid.PositionTakenException;
import main.exception.mower.MowerException;
import main.model.Command;
import main.model.Grid;
import main.model.Mower;
import main.model.Orientation;
import main.model.Position;

@RunWith(Suite.class)
@SuiteClasses({})
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
		grid.placeMower(new Position(0, 0),Orientation.NORTH, new Mower(new LinkedList<Command>()));		
	}
	
	@Test
	void insertTwoMowersTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.placeMower(new Position(0, 0),Orientation.NORTH, new Mower(new LinkedList<Command>()));
		grid.placeMower(new Position(0, 1),Orientation.NORTH, new Mower(new LinkedList<Command>()));			
	}
	
	@Test
	void InsertTwoMowersInSamePositionTest() throws GridException, MowerException{
		Grid grid = new Grid(4,4);
		grid.placeMower(new Position(0, 0),Orientation.NORTH, new Mower(new LinkedList<Command>()));	
		try {
			grid.placeMower(new Position(0, 0),Orientation.NORTH, new Mower(new LinkedList<Command>()));	
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
		Grid grid = TestInstances.getStandardGridWithMowerFacingNorthAndCommandLeft();
		grid.executeOne();
		assertEquals("0 0 W\n",outContent.toString());
	}
	
	@Test
	void noPrintTwoMovementMowerTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		LinkedList<Command> q = new LinkedList<Command>();
		q.add(Command.LEFT);
		q.add(Command.LEFT);
		grid.placeMower(new Position(0, 0),Orientation.NORTH, new Mower(q));
		grid.executeOne();
		assertEquals("",outContent.toString());
	}
	
	@Test
	void printTwoMowersTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    Grid grid = TestInstances.getStandardGridWithMowerFacingNorthAndCommandLeft();
	    
	    LinkedList<Command> q2 = new LinkedList<Command>();
		q2.add(Command.FORWARD);
		grid.placeMower(new Position(4, 4),Orientation.SOUTH, new Mower(q2));		
		grid.executeOne();
		grid.executeOne();
		assertEquals("0 0 W\n4 3 S\n",outContent.toString());
	}
	
	@Test
	void executeAllTest() throws GridException, MowerException{		
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent1));
	    Grid grid = TestInstances.getStandardGridWithMowerFacingNorthAndCommandLeft();
	    
	    LinkedList<Command> q2 = new LinkedList<Command>();
		q2.add(Command.FORWARD);
		grid.placeMower(new Position(4, 4),Orientation.SOUTH, new Mower(q2));		
		grid.executeOne();
		grid.executeOne();
		
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent2));
	    
		Grid grid2 = TestInstances.getStandardGridWithMowerFacingNorthAndCommandLeft();
		
		q2 = new LinkedList<Command>();
		q2.add(Command.FORWARD);
		grid2.placeMower(new Position(4, 4),Orientation.SOUTH, new Mower(q2));
		grid2.executeAll();	
		assertEquals(outContent2.toString(),outContent1.toString());
	}
	
	
	@Test
	void mowerMovesForwardWithBlockingMowerTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = TestInstances.getStandardGridWithMowerFacingNorthAndCommandForward();
		
		LinkedList<Command> q = new LinkedList<Command>();
		q.add(Command.RIGHT);
		grid.placeMower(new Position(0, 1),Orientation.SOUTH, new Mower(q));
		grid.executeAll();
		assertEquals("0 0 N\n0 1 W\n",outContent.toString());		
	}
	
	@Test
	void mowerMovesForwardWithBlockingMowerButNextOneMovesTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = TestInstances.getStandardGridWithMowerFacingNorthAndCommandForward();
		
		LinkedList<Command> q = new LinkedList<Command>();
		q.add(Command.FORWARD);
		grid.placeMower(new Position(0, 1),Orientation.NORTH, new Mower(q));		
		grid.executeAll();
		assertEquals("0 0 N\n0 2 N\n",outContent.toString());		
	}
	
	@Test
	void mowerMovesOutOfBoundsTest() throws GridException, MowerException{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		Grid grid = new Grid(4,4);
		LinkedList<Command> q = new LinkedList<Command>();
		q.add(Command.FORWARD);
		grid.placeMower(new Position(4, 4),Orientation.NORTH, new Mower(q));
		grid.executeAll();
		assertEquals("4 4 N\n",outContent.toString());			
	}
	@Test
	void rotateMowerRightTest() throws MowerException, GridException{
		Mower mower = TestInstances.getMowerWithFullTurn(Command.RIGHT);
		Grid grid = new Grid(4,4);
		grid.placeMower(new Position(4, 4), Orientation.NORTH, mower);
		assertEquals(grid.getMowerOrientation(mower),Orientation.NORTH);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.EAST);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.SOUTH);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.WEST);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.NORTH);
	}
	
	@Test
	void rotateMowerLeftTest() throws MowerException, GridException{
		Mower mower = TestInstances.getMowerWithFullTurn(Command.LEFT);
		Grid grid = new Grid(4,4);
		grid.placeMower(new Position(4, 4), Orientation.NORTH, mower);
		assertEquals(grid.getMowerOrientation(mower),Orientation.NORTH);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.WEST);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.SOUTH);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.EAST);
		mower.executeOne();
		assertEquals(grid.getMowerOrientation(mower),Orientation.NORTH);

	}
}
