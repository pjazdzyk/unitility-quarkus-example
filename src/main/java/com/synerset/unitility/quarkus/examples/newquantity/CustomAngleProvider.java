package com.synerset.unitility.quarkus.examples.newquantity;

import com.synerset.unitility.quarkus.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.quarkus.serdes.PhysicalQuantityParamJakartaConverter;
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

    public CustomAngleProvider(CustomParsingFactoryWithAngle parsingFactory) {
        // Registry initialization
        ConcurrentHashMap<Class<?>, ParamConverter<?>> convertersByClass = new ConcurrentHashMap<>();
        // Register your custom quantities
        convertersByClass.put(CustomAngle.class, new PhysicalQuantityParamJakartaConverter<>(CustomAngle.class, parsingFactory));
        customConverters = Collections.unmodifiableMap(convertersByClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> targetClass, Type parametrizedType, Annotation[] annotations) {
        return (ParamConverter<T>) customConverters.get(targetClass);
    }

}
