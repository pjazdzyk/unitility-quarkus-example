package com.synerset.unitility.quarkus.examples.newquantity;

import com.synerset.unitility.quarkus.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("")
public class CustomAngleResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GET
    @Path("/angles/{customAngle}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomAngle getCustomAngleFromPathVariable(@PathParam("customAngle") CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }

    @POST
    @Path("/angles")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomAngle getCustomAngleFromRequestBody(CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }


    private void processCustomAngle(CustomAngle customAngle) {
        String customAngleAsString = customAngle.toEngineeringFormat();
        logger.info("Revolutions: {}", customAngleAsString);
        String anglesInDegAsString = customAngle.toBaseUnit().toEngineeringFormat(3);
        logger.info("Degrees: {}", anglesInDegAsString);
        // Because our custom angle implements AngleUnit, we can convert easily between Angle and CustomAngle:
        String angleInRadians = customAngle.toUnit(AngleUnits.RADIANS).toEngineeringFormat(3);
        logger.info("Radians: {}", angleInRadians);
    }

}
