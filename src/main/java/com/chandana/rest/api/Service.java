package com.chandana.rest.api;


import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

public class Service {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsset(@PathParam("id") String id, @Context Request req) {

        CacheControl cc = new CacheControl();
        cc.setMaxAge(86400);

        Response.ResponseBuilder rb;
        EntityTag etag = new EntityTag(DataStoreSimulator.getUser().getLastUpdateTime() + "");
        rb = req.evaluatePreconditions(etag);

        if (rb != null) {
            return rb.cacheControl(cc).tag(etag).build();
        }

        rb = Response.ok(DataStoreSimulator.getUser()).cacheControl(cc).tag(etag);
        return rb.build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAsset(@PathParam("id") String id) {
        DataStoreSimulator.updateUser();
        return Response.ok().build();

    }


}