package model;

public interface IMeasurable {
    double toBaseUnit(double value);
    double fromBaseUnit(double baseValue);
}