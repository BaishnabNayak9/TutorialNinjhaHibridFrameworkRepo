package Experiment;

public class GetAllSystemProperties {
	public static void main(String[] args) {
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getenv("user.name"));
		System.out.println(System.getProperty("java.version"));
	}

}
