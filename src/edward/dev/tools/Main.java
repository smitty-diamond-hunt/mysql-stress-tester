package edward.dev.tools;

public class Main {
	
	
	public static void main(String[]args) throws ClassNotFoundException {
		double currentTime = System.currentTimeMillis(); 
		
		int threads = Integer.parseInt(Env.getInstance().getValue("THREADS"));
		int iterations = Integer.parseInt(Env.getInstance().getValue("ITERATIONS"));
		Messenger messenger = new Messenger(threads, iterations);
		messenger.begin();
		
		System.out.println("Execute Time (ms): " + (System.currentTimeMillis() - currentTime));
	}
}
