package by.bsuir.mark.task.third.observer;

import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Tetrahedron;

import java.util.*;

public class TetrahedronObservable extends Tetrahedron implements Observable {

    private final List<Observer> observers;

    public TetrahedronObservable(Integer id, Point3D... points) {
        super(id, points);
        this.observers = new ArrayList<>();
    }

    public TetrahedronObservable(Integer id, Collection<Point3D> points) {
        super(id, points);
        this.observers = new ArrayList<>();
    }

    @Override
    public void setPoints(Set<Point3D> points) {
        super.setPoints(points);
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
