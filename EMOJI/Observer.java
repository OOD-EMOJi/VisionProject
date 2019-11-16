package EMOJI;
public interface Observer<T> {
	public void update(Observable<T> observable);
}