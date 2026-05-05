// ----- VOLUME -----
System.out.println("\n=== VOLUME OPERATIONS ===");

Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);
Quantity<VolumeUnit> v3 = new Quantity<>(1, VolumeUnit.GALLON);

// Equality
demonstrateEquality(v1, v2);
demonstrateEquality(v1, v3);

// Conversion
demonstrateConversion(v1, VolumeUnit.MILLILITRE);
demonstrateConversion(v3, VolumeUnit.LITRE);

// Addition
demonstrateAddition(v1, v2, VolumeUnit.LITRE);
demonstrateAddition(v1, v3, VolumeUnit.MILLILITRE);