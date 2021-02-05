package by.bsuir.mark.task.third.repository.impl;

import by.bsuir.mark.task.third.repository.IdGenerator;

public class IntegerIdGeneratorImpl implements IdGenerator<Integer> {
    public static final Integer DEFAULT_START_VALUE = 0;

    private Integer id;

    public IntegerIdGeneratorImpl(Integer start) {
        this.id = start;
    }

    public IntegerIdGeneratorImpl() {
        this.id = DEFAULT_START_VALUE;
    }

    @Override
    public Integer getNextId() {
        return id++;
    }
}
