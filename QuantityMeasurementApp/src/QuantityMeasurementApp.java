public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U unit) {
        System.out.println("Subtraction: " + q1.subtract(q2, unit));
    }

    public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        System.out.println("Division: " + q1.divide(q2));
    }

    public static void main(String[] args) {

        System.out.println("=== UC12 Demo ===");

        // LENGTH
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(l1, l2, LengthUnit.FEET);
        demonstrateDivision(l1, l2);

        // WEIGHT
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        demonstrateSubtraction(w1, w2, WeightUnit.KILOGRAM);
        demonstrateDivision(w1, w2);

        // VOLUME
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
        demonstrateDivision(v1, v2);
    }
}