package by.bsuir.mark.task.third.logic.impl.comparator;

import by.bsuir.mark.task.third.entity.Tetrahedron;

import java.util.Comparator;

public class TetrahedronIdComparator implements Comparator<Tetrahedron> {
    @Override
    public int compare(Tetrahedron tetrahedronFirst, Tetrahedron tetrahedronSecond) {
        Integer id1 = tetrahedronFirst.getId();
        Integer id2 = tetrahedronSecond.getId();
        return id1.compareTo(id2);
    }
}
