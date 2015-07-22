package saltstack4j.api;

public interface APIProvider {

	void initialize();
	
	<T> T get(Class<T> api);
}
