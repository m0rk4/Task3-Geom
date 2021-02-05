package by.bsuir.mark.task.third.specification;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.specification.impl.TetrahedronByIdSpecification;
import org.junit.Assert;
import org.junit.Test;

public class TetrahedronByIdSpecificationTest {

    private static final Integer TEST_ID = 1;
    private final TetrahedronSpecification tetrahedronSpecification = new TetrahedronByIdSpecification(TEST_ID);

    @Test
    public void testIsSpecifiedShouldReturnTrueWhenIdIsCorrect() {
        // given
        Tetrahedron tetrahedron = new Tetrahedron(TEST_ID);
        // when
        boolean res = tetrahedronSpecification.isSpecified(tetrahedron);
        // then
        Assert.assertTrue(res);
    }

    @Test
    public void testIsSpecifiedShouldReturnFalseWhenIdIsNotCorrect() {
        // given
        Tetrahedron tetrahedron = new Tetrahedron(9999999);
        // when
        boolean res = tetrahedronSpecification.isSpecified(tetrahedron);
        // then
        Assert.assertFalse(res);
    }
}
