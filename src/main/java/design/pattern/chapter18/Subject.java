package design.pattern.chapter18;

public interface Subject {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notiObserver();
}
