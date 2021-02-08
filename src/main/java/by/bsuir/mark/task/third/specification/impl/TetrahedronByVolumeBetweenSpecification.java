package by.bsuir.mark.task.third.specification.impl;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.PolyhedronLogic;
import by.bsuir.mark.task.third.logic.impl.TetrahedronLogic;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

public class TetrahedronByVolumeBetweenSpecification implements TetrahedronSpecification {

    private final PolyhedronLogic polyhedronLogic;
    private final double from;
    private final double to;

    public TetrahedronByVolumeBetweenSpecification(double from, double to) {
        this.polyhedronLogic = new TetrahedronLogic();
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        double volume = polyhedronLogic.getPolyhedronVolume(tetrahedron);
        return volume >= from && volume <= to;
    }
}
