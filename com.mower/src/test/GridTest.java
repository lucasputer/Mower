package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		
}
