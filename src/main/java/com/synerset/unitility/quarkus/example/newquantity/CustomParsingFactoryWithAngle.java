package com.synerset.unitility.quarkus.example.newquantity;

import com.synerset.unitility.quarkus.example.newquantity.customunit.CustomAngle;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityAbstractParsingFactory;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@DefaultBean
@ApplicationScoped
public class CustomParsingFactoryWithAngle extends PhysicalQuantityAbstractParsingFactory {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> customRegistry;

    public CustomParsingFactoryWithAngle() {
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registry = new ConcurrentHashMap<>();
        registry.put(CustomAngle.class, CustomAngle::of);
        // We need to merge our map with a default parsing map. This bean must contain all parsing functions, not only the new one.
        registry.putAll(PhysicalQuantityParsingFactory.getDefaultParsingFactory().getClassRegistry());
        this.customRegistry = Collections.unmodifiableMap(registry);
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry() {
        return customRegistry;
    }

}
