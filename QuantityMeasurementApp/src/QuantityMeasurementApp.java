package app;

import model.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("=== UC13 DEMO ===");

        // LENGTH
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        System.out.println("Subtract: " + q1.subtract(q2));
        System.out.println("Subtract (INCHES): " + q1.subtract(q2, LengthUnit.INCHES));
        System.out.println("Divide: " + q1.divide(q2));

        // WEIGHT
        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GRAM);

        System.out.println("Subtract: " + w1.subtract(w2));
        System.out.println("Divide: " + w1.divide(w2));

        // VOLUME
        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2, VolumeUnit.LITRE);

        System.out.println("Subtract: " + v1.subtract(v2, VolumeUnit.MILLILITRE));
        System.out.println("Divide: " + v1.divide(v2));
    }
}