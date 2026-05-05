public class QuantityMeasurementApp {

    // Generic demonstration methods
    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U target) {
        System.out.println(q + " -> " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, target));
    }

    public static void main(String[] args) {

        // ----- LENGTH -----
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCHES);

        demonstrateEquality(l1, l2);
        demonstrateConversion(l1, LengthUnit.INCHES);
        demonstrateAddition(l1, l2, LengthUnit.FEET);

        // ----- WEIGHT -----
        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        demonstrateEquality(w1, w2);
        demonstrateConversion(w1, WeightUnit.GRAM);
        demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

        // ----- CROSS CATEGORY CHECK -----
        System.out.println(l1.equals(w1)); // false
    }
}