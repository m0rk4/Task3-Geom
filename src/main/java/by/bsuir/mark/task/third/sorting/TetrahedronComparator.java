package by.bsuir.mark.task.third.sorting;

import by.bsuir.mark.task.third.entity.Tetrahedron;

import java.util.Comparator;

public interface TetrahedronComparator extends Comparator<Tetrahedron> {
    @Override
    int compare(Tetrahedron t1, Tetrahedron t2);
}
