package com.robsonapsilva.mapper;


import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class S3ExceptionMapper implements ExceptionMapper<S3Exception> {


    @Override
    public Response toResponse(S3Exception s3Exception) {
        if ((s3Exception instanceof NoSuchBucketException)
                || (s3Exception instanceof NoSuchKeyException)) {
            return Response.status(404)
                    .entity(s3Exception.getLocalizedMessage())
                    .build();
        }
        return Response.status(500).entity("Internal Server Error").build();
    }
}
