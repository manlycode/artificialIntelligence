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
		x--;
		BatteryReduction();
	}

	public void MoveRight() {
		x++;
		BatteryReduction();
	}

	public void MoveDown() {
		y++;
		BatteryReduction();
	}

	public void MoveUp() {
		y--;
		BatteryReduction();
	}

	public void MoveLeftUp() {
		x--;
		y--;
		BatteryReduction();
	}

	public void MoveLeftDown() {
		x--;
		y++;
		BatteryReduction();
	}

	public void MoveRightUp() {
		x++;
		y--;
		BatteryReduction();
	}

	public void MoveRightDown() {
		x++;
		y++;
		BatteryReduction();

	}
	// Use this method to get the agent to change direction,
	// when it hits the end of the world or an obstacle
	public void GetNewDirection() {
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
			CleanDirt(world);
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
		while(env.numOfDirtyTiles != 0 && battery != 0) {
			CheckForDirt(world);
			GetNewDirection();
		}
		if(battery == 0) {
			System.out.println("The agent ran out of battery before");
			System.out.println("cleaning all the tiles...");
		}
	}
	// Print current state of vacuum world?
	public void PrintWorld(String [][] world) {
		for(int i = 0; i < world.length; i++) {
			for(int j = 0; j < world.length; j++) {
				System.out.print(world[i][j]);
			}
				System.out.println();
		}
	}

	public void BatteryReduction() {
		battery--;
		performance -= 2;
	}

}
