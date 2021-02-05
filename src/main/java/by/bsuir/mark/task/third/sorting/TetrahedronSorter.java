package by.bsuir.mark.task.third.sorting;

import by.bsuir.mark.task.third.entity.Tetrahedron;

import java.util.List;

public interface TetrahedronSorter {
    void sort(List<Tetrahedron> tetrahedrons, TetrahedronComparator comparator);
}
