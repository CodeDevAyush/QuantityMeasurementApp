package model;

public enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001);

    private final double toLitre;

    VolumeUnit(double toLitre) {
        this.toLitre = toLitre;
    }

    public double toBaseUnit(double value) {
        return value * toLitre;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toLitre;
    }
}