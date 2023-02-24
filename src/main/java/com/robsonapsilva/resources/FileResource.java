package com.robsonapsilva.resources;

import com.robsonapsilva.service.S3ClientService;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import software.amazon.awssdk.core.ResponseBytes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;

@Path("/v1/files")
@RequiredArgsConstructor
public class FileResource {

    private final S3ClientService s3ClientService;

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(String key) {
        ResponseBytes responseBytes = s3ClientService.download(key);
        return Response.ok().entity(responseBytes).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@RestForm("file") File file,
                           @RestForm @PartType(MediaType.TEXT_PLAIN) String key,
                           @RestForm @PartType(MediaType.TEXT_PLAIN) String mimeType) {
        s3ClientService.upload(file, key, mimeType);
        return Response.created(URI.create(key).normalize()).build();
    }

}
