package edu.psu.se411.lab08.observer;

public interface ISubject {
	void register(IObserver o);
	void unregister(IObserver o);
	void notifyObservers();
}
