package by.bsuir.mark.task.third.creation;

import by.bsuir.mark.task.third.data.DataException;
import by.bsuir.mark.task.third.data.DataReader;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.parsing.PolyhedronDataParser;
import by.bsuir.mark.task.third.validation.PolyhedronDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PolyhedronCreator {
    private static final Logger LOGGER = LogManager.getLogger(PolyhedronCreator.class);

    private final DataReader dataReader;
    private final PolyhedronDataValidator polyhedronDataValidator;
    private final PolyhedronDataParser polyhedronDataParser;

    public PolyhedronCreator(DataReader dataReader, PolyhedronDataValidator polyhedronDataValidator, PolyhedronDataParser polyhedronDataParser) {
        this.dataReader = dataReader;
        this.polyhedronDataValidator = polyhedronDataValidator;
        this.polyhedronDataParser = polyhedronDataParser;
    }

    public List<Polyhedron> createPolyhedrons(String filename) throws PolyhedronCreatorException {
        LOGGER.info("IN createPolyhedrons - From filename: {}", filename);
        List<Polyhedron> polyhedrons = new ArrayList<>();
        try {
            List<String> lines = dataReader.readData(filename);
            for (String line : lines) {
                if (polyhedronDataValidator.isValid(line)) {
                    Polyhedron polyhedron = polyhedronDataParser.parsePolyhedronData(line);
                    polyhedrons.add(polyhedron);
                }
            }
        } catch (DataException e) {
            throw new PolyhedronCreatorException(e.getMessage(), e);
        }
        LOGGER.info("IN createPolyhedrons - List of polyhedrons: {} successfully created", polyhedrons);
        return polyhedrons;
    }
}
