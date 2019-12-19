package org.schorn.ella.ws.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.schorn.ella.services.ActiveServices;
import org.springframework.stereotype.Component;

//@Path("/meta")
//@Consumes()
//@Produces({"text/html"})

/**
 *
 * @author schorn
 *
 */
@Component
@Path("/meta/html")
//@Api(value = "Meta HTML API")
public class MetaHtmlController {

    //@Context
    //private HttpHeaders headers;

    /*
	 * This will return the member types for a object type
     */
    @GET
    @Path("/object_type/{context}/{object_type}")
    public Response getWithContextAndObjectType(
            @PathParam("context") String context,
            @PathParam("object_type") String object_type) {
        String html = ActiveServices.contentTypeOutput().getHTMLForm(context, object_type);
        return Response.ok(html).build();
    }

}


/*
    @GET
    @Path("/html/{context}")
    public Response getWithContext(@PathParam("context") String context) {
        return Response.ok(context).build();
    }

    @GET
    @Path("/html/{context}/{object_type}/{member_type}")
    public Response getWithContextAndObjectTypeAndMemberType(
            @PathParam("context") String context,
            @PathParam("object_type") String object_type,
            @PathParam("member_type") String member_type
    ) {
        return Response.ok(context).build();
    }

    @OPTIONS
    @Path("/html/{context}")
    public Response optionsWithContext(
            @PathParam("context") String context
    ) {
        return Response.ok(context).build();
    }

    @OPTIONS
    @Path("/html/{context}/{object_type}")
    public Response optionsWithContextAndObjectType(
            @PathParam("context") String context,
            @PathParam("object_type") String object_type
    ) {
        return Response.ok(context).build();
    }

    @OPTIONS
    @Path("/html/{context}/{object_type}/{member_type}")
    public Response optionsWithContextAndObjectTypeAndMemberType(
            @PathParam("context") String context,
            @PathParam("object_type") String object_type,
            @PathParam("member_type") String member_type
    ) {
        return Response.ok(context).build();
    }

 */
