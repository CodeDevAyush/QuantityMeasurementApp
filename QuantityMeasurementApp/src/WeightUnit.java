package model;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001);

    private final double toKg;

    WeightUnit(double toKg) {
        this.toKg = toKg;
    }

    public double toBaseUnit(double value) {
        return value * toKg;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toKg;
    }
}