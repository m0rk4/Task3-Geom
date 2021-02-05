package by.bsuir.mark.task.third.entity;

public class TetrahedronParameters {
    private final double volume;
    private final double surfaceArea;

    public TetrahedronParameters(double volume, double surfaceArea) {
        this.volume = volume;
        this.surfaceArea = surfaceArea;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TetrahedronParameters that = (TetrahedronParameters) o;
        return Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.surfaceArea, surfaceArea) == 0;
    }

    @Override
    public int hashCode() {
        int res = 1;
        res = 31 * res + Double.hashCode(volume);
        res = 31 * res + Double.hashCode(surfaceArea);
        return res;
    }

    @Override
    public String toString() {
        return "TetrahedronParameters{" +
                "volume=" + volume +
                ", surfaceArea=" + surfaceArea +
                '}';
    }
}
