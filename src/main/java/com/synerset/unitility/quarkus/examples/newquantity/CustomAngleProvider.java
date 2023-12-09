package com.synerset.unitility.quarkus.examples.newquantity;

import com.synerset.unitility.quarkus.PhysicalQuantityParamJakartaConverter;
import com.synerset.unitility.quarkus.examples.newquantity.customunits.CustomAngle;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Provider
@Priority(1)
@ApplicationScoped
public class CustomAngleProvider implements ParamConverterProvider {

    private final Map<Class<?>, ParamConverter<?>> customConverters;

    public CustomAngleProvider(CustomParsingRegistryWithAngle parsingRegistry) {
        // Registry initialization
        ConcurrentHashMap<Class<?>, ParamConverter<?>> convertersByMap = new ConcurrentHashMap<>();
        // Register your custom quantities
        convertersByMap.put(CustomAngle.class, new PhysicalQuantityParamJakartaConverter<>(CustomAngle.class, parsingRegistry));
        customConverters = Collections.unmodifiableMap(convertersByMap);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> targetClass, Type parametrizedType, Annotation[] annotations) {
        return (ParamConverter<T>) customConverters.get(targetClass);
    }

}
