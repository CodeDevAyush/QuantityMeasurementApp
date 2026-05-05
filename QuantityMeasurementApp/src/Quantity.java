package model;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ================= ENUM (UC13) =================
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    // ================= VALIDATION =================
    private void validate(Quantity<U> other, U targetUnit, boolean checkTarget) {
        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric values");

        if (checkTarget && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    // ================= CORE HELPER =================
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        double base1 = unit.toBaseUnit(this.value);
        double base2 = other.unit.toBaseUnit(other.value);
        return op.compute(base1, base2);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ================= ADD =================
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double finalVal = targetUnit.fromBaseUnit(resultBase);

        return new Quantity<>(round(finalVal), targetUnit);
    }

    // ================= SUBTRACT =================
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double finalVal = targetUnit.fromBaseUnit(resultBase);

        return new Quantity<>(round(finalVal), targetUnit);
    }

    // ================= DIVIDE =================
    public double divide(Quantity<U> other) {
        validate(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}