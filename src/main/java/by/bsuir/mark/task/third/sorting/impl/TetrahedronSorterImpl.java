package by.bsuir.mark.task.third.sorting.impl;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.sorting.TetrahedronComparator;
import by.bsuir.mark.task.third.sorting.TetrahedronSorter;

import java.util.List;

public class TetrahedronSorterImpl implements TetrahedronSorter {
    @Override
    public void sort(List<Tetrahedron> tetrahedrons, TetrahedronComparator comparator) {
        tetrahedrons.sort(comparator);
    }
}
