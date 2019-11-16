package EMOJI;
public interface Observable<T> {
	public void registerObserver(Observer<T> observer);
	public void removeObserver(Observer<T> observer);
	public void notifyObservers();
}