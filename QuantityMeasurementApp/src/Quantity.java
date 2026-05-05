class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException();
        }

        double sum = this.unit.convertToBaseUnit(this.value)
                + other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), targetUnit);
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}