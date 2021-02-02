package by.bsuir.mark.task.third.validation.impl;

import by.bsuir.mark.task.third.validation.PolyhedronDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class TetrahedronDataValidatorImpl implements PolyhedronDataValidator {
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronDataValidatorImpl.class);
    private static final String TETRAHEDRON_DATA_REG_EXP = "^((-?\\d+(\\.\\d+)?\\s+){2}(-?\\d+(\\.\\d+)?\\|)){3}(-?\\d+(\\.\\d+)?\\s+){2}(-?\\d+(\\.\\d+)?)$";

    @Override
    public boolean isValid(String tetrahedronContents) {
        boolean isValid = Pattern.matches(TETRAHEDRON_DATA_REG_EXP, tetrahedronContents);
        LOGGER.info("IN isValid - Validation result for {} is {}", tetrahedronContents, isValid);
        return isValid;
    }
}
