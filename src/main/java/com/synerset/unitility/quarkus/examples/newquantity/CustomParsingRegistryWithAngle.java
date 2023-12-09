package com.synerset.unitility.quarkus.examples.newquantity;

import com.synerset.unitility.quarkus.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import io.quarkus.arc.DefaultBean;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@DefaultBean
@ApplicationScoped
public class CustomParsingRegistryWithAngle implements PhysicalQuantityParsingRegistry {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> customRegistry;

    public CustomParsingRegistryWithAngle() {
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registry = new ConcurrentHashMap<>();
        registry.put(CustomAngle.class, CustomAngle::of);
        // We need to merge our map with a default parsing map. This bean must contain all parsing functions, not only the new one.
        registry.putAll(PhysicalQuantityParsingRegistry.DEFAULT_PARSING_REGISTRY.getRegistryMap());
        this.customRegistry = Collections.unmodifiableMap(registry);
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getRegistryMap() {
        return customRegistry;
    }

}
