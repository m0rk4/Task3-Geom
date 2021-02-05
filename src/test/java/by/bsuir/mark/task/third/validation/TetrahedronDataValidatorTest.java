package by.bsuir.mark.task.third.validation;

import by.bsuir.mark.task.third.validation.impl.TetrahedronDataValidatorImpl;
import org.junit.Assert;
import org.junit.Test;

public class TetrahedronDataValidatorTest {
    private final PolyhedronDataValidator tetrahedronDataValidator = new TetrahedronDataValidatorImpl();

    @Test
    public void testIsValidShouldReturnFalseWhenEmptyStringProvided() {
        // given
        String sample = "";
        // when
        boolean res = tetrahedronDataValidator.isValid(sample);
        // then
        Assert.assertFalse(res);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenIncorrectStringProvided() {
        // given
        String sample = "1.0z 23.23 12.33|323.323    32.1 3.|1 23 3|9 5 3";
        // when
        boolean res = tetrahedronDataValidator.isValid(sample);
        // then
        Assert.assertFalse(res);
    }

    @Test
    public void testIsValidShouldReturnTrueWhenCorrectStringProvided() {
        // given
        String sample = "1.0 23.23 12.33|323.323    32.1 3|1 23 3|9 5 3";
        // when
        boolean res = tetrahedronDataValidator.isValid(sample);
        // then
        Assert.assertTrue(res);
    }
}
