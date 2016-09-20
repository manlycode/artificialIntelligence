package world;
import java.util.*;

public class Agent implements AgentInterface {

	private int x = 0; 	// Agent's
	private int y = 0;
	private Environment env;
	private int battery = 50;
	private int performance = 0;

	public Agent(int x, int y, Environment env){
		this.x = x;
		this.y = y;
		this.env = env;
		Run(env.world);
	}

	// Your code here ....
	public void MoveLeft() {
		  if (x == 0) return;
      if (x > 0) {
		   x--;
		   BatteryReduction();
      } else return;
	}

	public void MoveRight() {
		  if (x == 5) return;
      if (x < 5) {
   		x++;
   		BatteryReduction();
      } else return;
	}

	public void MoveDown() {
      if (y == 5) return;
      if (y < 5) {
		   y++;
	   	BatteryReduction();
      } else return;
	}

	public void MoveUp() {
		  if (y == 0) return;
      if (y > 0) {
   		y--;
   		BatteryReduction();
      } else return;
	}

	public void MoveLeftUp() {
		  if (x == 0 && y == 0) return;
      if (x > 0 && y > 0) {
   		x--;
   		y--;
   		BatteryReduction();
      } else return;
	}

	public void MoveLeftDown() {
			if (x == 0 && y == 5) return;
      if ((y< env.height) && (x > 0)) {
   		x--;
   		y++;
   		BatteryReduction();
      } else return;
	}

	public void MoveRightUp() {
      if (x == 5 && y == 0) return;
			if ((x < env.width) && (y > 0)) {
	   	x++;
	   	y--;
	   	BatteryReduction();
      } else return;
	}

	public void MoveRightDown() {
			if (x == 5 && y == 5) return;
      if ((x < env.width && y < env.height)) {
   		x++;
   		y++;
   		BatteryReduction();
      } else return;

	}
	// Use this method to get the agent to change direction,
	// when it hits the end of the world or an obstacle
	public void GetNewDirection() throws ArrayIndexOutOfBoundsException{
		Random randomInt = new Random();
		int ran = randomInt.nextInt(8) + 1;

			switch (ran) {
				case 1: MoveLeft();
								break;
				case 2: MoveRight();
								break;
				case 3: MoveDown();
								break;
				case 4: MoveUp();
								break;
				case 5: MoveLeftUp();
								break;
				case 6: MoveLeftDown();
								break;
				case 7: MoveRightUp();
								break;
				case 8: MoveRightDown();
								break;
         }
	}
	// P is for path, O is for obstacle, D is for dirt
	// If value at position of vacuum is D, call
	// CleanDirt and return true, else return false
	public boolean CheckForDirt(String [][] world) {
		if(world[x][y].equals("D")) {
			return true;
		}
		return false;
	}

	public void CleanDirt(String [][] world) {
		world[x][y] = "P";
		env.RemoveDirt();
		performance += 15;
	}
	// Not sure what this is for yet.
	public void Run(String [][] world) {
		while(battery != 0 && env.getNumberOfDirtyTiles() != 0) {
      PrintWorld(env.world);
      System.out.println();
			if(CheckForDirt(env.world)) {
				CleanDirt(env.world);
			}
			GetNewDirection();
		}
		if(battery == 0) {
			System.out.println("The agent ran out of battery before");
			System.out.println("cleaning all the tiles...");
		}
	}
	// Print current state of vacuum world?
	public void PrintWorld(String [][] world) {
		for(int i = 0; i < env.world.length; i++) {
			for(int j = 0; j < env.world.length; j++) {
				System.out.print(env.world[i][j]);
			}
				System.out.println();
		}
	}

	public boolean withinBoundsMin(int x, int y) {
      return false;
	}

	public boolean withinBoundsMax(int x, int y) {
      return false;
	}

	public void BatteryReduction() {
		battery--;
		performance -= 2;
	}

}
