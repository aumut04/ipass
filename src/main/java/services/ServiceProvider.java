package services;

public class ServiceProvider {
	private static gezinslidService glService = new gezinslidService();

	public static gezinslidService glService() {
		return glService;
	}
}
