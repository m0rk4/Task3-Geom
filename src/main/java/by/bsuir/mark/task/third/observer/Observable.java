package by.bsuir.mark.task.third.observer;

public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
}
