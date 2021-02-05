package by.bsuir.mark.task.third.sorting.impl;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.sorting.TetrahedronComparator;

public class TetrahedronIdComparator implements TetrahedronComparator {
    @Override
    public int compare(Tetrahedron t1, Tetrahedron t2) {
        Integer id1 = t1.getId();
        Integer id2 = t2.getId();
        return id1.compareTo(id2);
    }
}
