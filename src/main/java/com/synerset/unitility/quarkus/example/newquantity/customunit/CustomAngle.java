package com.synerset.unitility.quarkus.example.newquantity.customunit;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;

import java.util.Objects;

public class CustomAngle implements CalculableQuantity<AngleUnit, CustomAngle> {

    private final double value;
    private final double baseValue;
    private final AngleUnit unitType;

    public CustomAngle(double value, AngleUnit unitType) {
        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static CustomAngle of(double value, AngleUnit unit) {
        return new CustomAngle(value, unit);
    }

    public static CustomAngle of(double value, String unitSymbol) {
        AngleUnit angleUnit = CustomAngleUnits.fromSymbol(unitSymbol);
        return new CustomAngle(value, angleUnit);
    }

    public static CustomAngle ofRevolutions(double value) {
        return new CustomAngle(value, CustomAngleUnits.REVOLUTIONS);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public AngleUnit getUnit() {
        return unitType;
    }

    @Override
    public CustomAngle toBaseUnit() {
        double degrees = unitType.toValueInBaseUnit(value);
        return CustomAngle.of(degrees, AngleUnits.DEGREES);
    }

    @Override
    public CustomAngle toUnit(AngleUnit targetUnit) {
        double valueInDegrees = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInDegrees);
        return CustomAngle.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public PhysicalQuantity<AngleUnit> toUnit(String targetUnit) {
        return null;
    }

    @Override
    public CustomAngle withValue(double value) {
        return CustomAngle.of(value, unitType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomAngle inputQuantity = (CustomAngle) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().contains("°") ? "" : " ";
        return "CustomAngle{" + value + separator + unitType.getSymbol() + '}';
    }

}