package edu.iu.lvanjelg.weathermonitoring.model;

import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    List<Observer> getObservers();
}
