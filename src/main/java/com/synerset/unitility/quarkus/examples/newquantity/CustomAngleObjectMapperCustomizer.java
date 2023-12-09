package com.synerset.unitility.quarkus.examples.newquantity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.synerset.unitility.jackson.PhysicalQuantityDeserializer;
import com.synerset.unitility.quarkus.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomAngleObjectMapperCustomizer implements ObjectMapperCustomizer {

    private final PhysicalQuantityParsingRegistry customRegistry;

    public CustomAngleObjectMapperCustomizer(CustomParsingRegistryWithAngle customRegistry) {
        this.customRegistry = customRegistry;
    }

    @Override
    public void customize(ObjectMapper objectMapper) {
        // Create new JacksonModule and register deserializer. Serializer is already defined once for all PhysicalQuantity classes.
        SimpleModule customAngleModule = new SimpleModule("CustomAngles");
        customAngleModule.addDeserializer(CustomAngle.class, new PhysicalQuantityDeserializer<>(CustomAngle.class, customRegistry));
        objectMapper.registerModule(customAngleModule);
    }

}