package world;

public class Agent implements AgentInterface {
	
	private int x = 0; 	// Agent's 
	private int y = 0;
	private Environment environment;
	private int battery = 0;
	private int performance = 0;


	
	public Agent(int x, int y, Environment env){
		this.x = x;
		this.y = y;
		this.environment = env;
		Run(env.world);
	}

	// Your code here ....
}
