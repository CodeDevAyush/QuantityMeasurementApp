public class QuantityMeasurementApp {

    // ---------- LENGTH UNIT ----------
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        double toBase(double value) {
            return value * factor;
        }

        double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }

    // ---------- LENGTH ----------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
        private static final double EPS = 1e-6;

        QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        QuantityLength convertTo(LengthUnit target) {
            if (target == null) throw new IllegalArgumentException();
            double base = unit.toBase(value);
            return new QuantityLength(target.fromBase(base), target);
        }

        QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        QuantityLength add(QuantityLength other, LengthUnit target) {
            if (other == null || target == null) throw new IllegalArgumentException();
            double sum = this.unit.toBase(this.value) + other.unit.toBase(other.value);
            return new QuantityLength(target.fromBase(sum), target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength q = (QuantityLength) obj;
            return Math.abs(this.unit.toBase(this.value) - q.unit.toBase(q.value)) < EPS;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ---------- WEIGHT UNIT ----------
    enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        double toBase(double value) {
            return value * factor;
        }

        double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }

    // ---------- WEIGHT ----------
    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;
        private static final double EPS = 1e-6;

        QuantityWeight(double value, WeightUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        QuantityWeight convertTo(WeightUnit target) {
            if (target == null) throw new IllegalArgumentException();
            double base = unit.toBase(value);
            return new QuantityWeight(target.fromBase(base), target);
        }

        QuantityWeight add(QuantityWeight other) {
            return add(other, this.unit);
        }

        QuantityWeight add(QuantityWeight other, WeightUnit target) {
            if (other == null || target == null) throw new IllegalArgumentException();
            double sum = this.unit.toBase(this.value) + other.unit.toBase(other.value);
            return new QuantityWeight(target.fromBase(sum), target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityWeight)) return false;
            QuantityWeight q = (QuantityWeight) obj;
            return Math.abs(this.unit.toBase(this.value) - q.unit.toBase(q.value)) < EPS;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        QuantityLength l1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, LengthUnit.INCHES);
        System.out.println(l1.add(l2)); // 2 FEET

        QuantityWeight w1 = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); // true
        System.out.println(w1.add(w2));    // 2 KG
        System.out.println(w1.convertTo(WeightUnit.POUND));
    }
}