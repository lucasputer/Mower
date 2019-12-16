package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Grid;
import main.exception.GridException;
import main.exception.NegativeGridException;
import main.exception.PositionTakenException;


class GridTest {

	@Test
	void NegativeGridShouldFailTest() throws GridException{
		try {
			@SuppressWarnings("unused")
			Grid grid = new Grid(-1,4);
			fail("No exception was thrown");
		}catch(NegativeGridException e) {
		}		
	}
	
	@Test
	void PositiveGridShouldNotFailTest() throws GridException {
		Grid grid = new Grid(1,4);
		assertEquals(grid.getLimit().getX(),1);
		assertEquals(grid.getLimit().getY(),4);
	}
	
	@Test
	void InsertMowerInEmptyGridTest() throws GridException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0, "N", "");		
	}
	
	@Test
	void InsertTwoMowersTest() throws GridException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0, "N", "");	
		grid.placeMower(0, 1, "N", "");				
	}
	
	@Test
	void InsertTwoMowersInSamePositionTest() throws GridException{
		Grid grid = new Grid(4,4);
		grid.placeMower(0, 0, "N", "");
		try {
			grid.placeMower(0, 0, "N", "");	
			fail("Cannot place two mowers in the same position");
		}catch(PositionTakenException e) {
			
		}
					
	}
	


}
