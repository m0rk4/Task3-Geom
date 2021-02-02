package by.bsuir.mark.task.third.parsing.impl;

import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.parsing.PolyhedronDataParser;
import by.bsuir.mark.task.third.parsing.PolyhedronParserException;
import by.bsuir.mark.task.third.validation.impl.TetrahedronDataValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TetrahedronDataParserImpl implements PolyhedronDataParser {
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronDataValidatorImpl.class);
    private static final String POINT_DELIMITER_REG_EXP = "\\|";
    private static final String COORDINATE_DELIMITER_REG_EXP = "\\s+";

    @Override
    public Tetrahedron parsePolyhedronData(String tetrahedronData) throws PolyhedronParserException {
        LOGGER.info("IN parseTetrahedronData - Data to parse: {}", tetrahedronData);
        List<Point3D> points = new ArrayList<>(4);
        String[] triplets = tetrahedronData.split(POINT_DELIMITER_REG_EXP);
        for (String triplet : triplets) {
            points.add(extractPoint(triplet));
        }
        Tetrahedron tetrahedron = new Tetrahedron(points);
        LOGGER.info("IN parseTetrahedronData - Tetrahedron: {} successfully created", tetrahedron);
        return tetrahedron;
    }

    private Point3D extractPoint(String triplet) throws PolyhedronParserException {
        String[] stringCoordinates = triplet.split(COORDINATE_DELIMITER_REG_EXP);
        double[] coordinates = new double[stringCoordinates.length];
        try {
            for (int i = 0; i < stringCoordinates.length; i++) {
                double v = Double.parseDouble(stringCoordinates[i]);
                if (Double.isInfinite(v)) {
                    throw new PolyhedronParserException("Too long number to parse");
                }
                coordinates[i] = v;
            }
        } catch (NumberFormatException e) {
            throw new PolyhedronParserException(e.getMessage(), e);
        }
        return new Point3D(coordinates[0], coordinates[1], coordinates[2]);
    }
}
