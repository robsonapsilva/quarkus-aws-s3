package com.robsonapsilva.properties;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "quarkus.s3.aws")
public interface S3ClientProperties {

    String region();

    String bucketName();


}
