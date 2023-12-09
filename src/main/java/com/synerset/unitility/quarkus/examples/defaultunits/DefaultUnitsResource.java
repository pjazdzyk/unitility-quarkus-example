package com.synerset.unitility.quarkus.examples.defaultunits;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("")
public class DefaultUnitsResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GET
    @Path("/temperatures/{temperature}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature getCustomAnglePath(@PathParam("temperature") Temperature temperature) {
        processTemperature(temperature);
        return temperature;
    }

    @POST
    @Path("/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature getCustomAngleBody(Temperature temperature) {
        processTemperature(temperature);
        return temperature;
    }

    private void processTemperature(Temperature temperature) {
        String tempInCelsiusAsString = temperature.toEngineeringFormat();
        logger.info("Input in eng. format: {}", tempInCelsiusAsString);
        String tempInFahrenheitsAsString = temperature.toFahrenheit().toEngineeringFormat(3);
        logger.info("In Fahrenheits: {}", tempInFahrenheitsAsString);
    }

}