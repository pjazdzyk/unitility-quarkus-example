package com.synerset.unitility.quarkus.example.defaultunit;

import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.validation.PhysicalMax;
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
    public Temperature getTemperatureByPath(@PathParam("temperature") @PhysicalMax("50oC")  Temperature temperature) {
        logger.info("ENG format: {}", temperature.toEngineeringFormat());
        logger.info("ENG format in F: {}", temperature.toFahrenheit().toEngineeringFormat());
        // Delegate to service, processing
        return temperature;
    }

    @POST
    @Path("/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature getTemperatureAsBody(Temperature temperature) {
        logger.info("ENG format: {}", temperature.toEngineeringFormat());
        logger.info("ENG format in F: {}", temperature.toFahrenheit().toEngineeringFormat());
        // Delegate to service, processing
        return temperature;
    }

    @POST
    @Path("/latitude")
    @Produces(MediaType.APPLICATION_JSON)
    public Latitude getLatitudeAsBody(Latitude latitude) {
        logger.info("DMS Format: {}", latitude.toDMSFormat());
        logger.info("DMS Format: {}", latitude.toEngineeringFormat());
        // Delegate to service, processing
        return latitude;
    }
    @GET
    @Path("/latitudes/{latitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Latitude getLatitudeByParam(@PathParam("latitude") Latitude latitude) {
        logger.info("DMS Format: {}", latitude.toDMSFormat());
        logger.info("ENG Format: {}", latitude.toEngineeringFormat());
        // Delegate to service, processing
        return latitude;
    }

    @GET
    @Path("/longitudes/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Longitude getLatitudeByParam(@PathParam("longitude") Longitude longitude) {
        logger.info("DMS Format: {}", longitude.toDMSFormat());
        logger.info("ENG Format: {}", longitude.toEngineeringFormat());
        // Delegate to service, processing
        return longitude;
    }

    @POST
    @Path("/coordinate")
    @Produces(MediaType.APPLICATION_JSON)
    public GeoCoordinate getLatitudeAsBody(GeoCoordinate geoCoordinate) {
        logger.info("DMS Format: {}", geoCoordinate.toDMSFormat());
        logger.info("DMS Format: {}", geoCoordinate.toEngineeringFormat());
        // Delegate to service, processing
        return geoCoordinate;
    }

    @POST
    @Path("/geodistance")
    @Produces(MediaType.APPLICATION_JSON)
    public GeoDistance getLatitudeAsBody(GeoDistance geoDistance) {
        logger.info("Target coordinate in DMS format: {}", geoDistance.getTargetCoordinate().toDMSFormat(3));
        logger.info("Bearing in decimal degrees: {}", geoDistance.getTrueBearing().getInDegrees());
        logger.info("Distance in ENG Format: {}", geoDistance.toEngineeringFormat());
        // Delegate to service, processing
        return geoDistance;
    }

}