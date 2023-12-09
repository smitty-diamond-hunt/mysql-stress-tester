package edward.dev.tools;

public class Messenger {

	private DatabaseManager databaseManager;

	private final int THREADS;
	private final int ITERATIONS;
	
	public Messenger(int threads, int iterations) throws ClassNotFoundException {
		this.databaseManager = new DatabaseManager();
		this.THREADS = threads;
		this.ITERATIONS = iterations;
	}
	
	public void begin() {
		for(int i = 0; i < THREADS; i++) {
			Thread thread = new Thread(new Job(this.databaseManager, this.ITERATIONS));
			thread.start();
		}
	}
}

class Job implements Runnable {

	private DatabaseManager databaseManager;
	private int iterations;
	
	public Job(DatabaseManager databaseManager, int iterations) {
		this.databaseManager = databaseManager;
		this.iterations = iterations;
	}
	@Override
	public void run() {
		for(int i = 0; i < iterations; i++) {
			databaseManager.query();
		}
	}
	
}
