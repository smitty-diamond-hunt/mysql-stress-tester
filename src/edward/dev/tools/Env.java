package edward.dev.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

// singleton
public class Env {
	private static Env instance;

	private String path;
	private HashMap<String, String> keyValues;
	private final String ENV_FILE_PATH = ".env";

	private Env() {
		this.path = ENV_FILE_PATH;
		this.keyValues = new HashMap<String, String>();
		this.readFile();
	}

	public static Env getInstance() {
		if (instance == null) {
			instance = new Env();
		}

		return instance;
	}

	public String getValue(String key) {
		return this.keyValues.get(key);
	}

	private void readFile() {
		try {
			FileReader fr = new FileReader(this.path);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				if (line.strip().startsWith("#"))
					continue;

				String[] lineAray = line.split("=");
				this.keyValues.put(lineAray[0], lineAray[1]);

				line = br.readLine();
			}

			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
