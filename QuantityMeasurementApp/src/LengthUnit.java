package model;

public enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    public double toBaseUnit(double value) {
        return value * toFeet;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toFeet;
    }
}