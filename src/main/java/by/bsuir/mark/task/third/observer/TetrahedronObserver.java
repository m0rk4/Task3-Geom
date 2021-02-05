package by.bsuir.mark.task.third.observer;

import by.bsuir.mark.task.third.entity.TetrahedronParameters;
import by.bsuir.mark.task.third.logic.PolyhedronLogic;
import by.bsuir.mark.task.third.logic.impl.TetrahedronLogic;

import java.util.HashMap;
import java.util.Map;

public class TetrahedronObserver implements Observer {

    private static TetrahedronObserver instance;

    private TetrahedronObserver() {
        polyhedronLogic = new TetrahedronLogic();
        parameters = new HashMap<>();
    }

    public static TetrahedronObserver getInstance() {
        if (instance == null) {
            instance = new TetrahedronObserver();
        }
        return instance;
    }

    private final PolyhedronLogic polyhedronLogic;
    private final Map<Integer, TetrahedronParameters> parameters;

    @Override
    public void update(TetrahedronObservable tetrahedronObservable) {
        TetrahedronParameters params = calculateTetrahedronParameters(tetrahedronObservable);
        parameters.put(tetrahedronObservable.getId(), params);
    }

    private TetrahedronParameters calculateTetrahedronParameters(TetrahedronObservable tetrahedronObservable) {
        double volume = polyhedronLogic.getPolyhedronVolume(tetrahedronObservable);
        double surfaceArea = polyhedronLogic.getPolyhedronSurfaceArea(tetrahedronObservable);
        return new TetrahedronParameters(volume, surfaceArea);
    }

    public double getTetrahedronVolume(Integer id) {
        TetrahedronParameters tetrahedronParameters = parameters.get(id);
        return tetrahedronParameters.getVolume();
    }

    public double getTetrahedronSurfaceArea(Integer id) {
        TetrahedronParameters tetrahedronParameters = parameters.get(id);
        return tetrahedronParameters.getSurfaceArea();
    }
}
