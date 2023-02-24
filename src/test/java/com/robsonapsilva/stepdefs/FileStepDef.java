package com.robsonapsilva.stepdefs;

import com.robsonapsilva.resource.S3Resource;
import com.robsonapsilva.utils.FileUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.quarkus.test.common.QuarkusTestResource;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import software.amazon.awssdk.services.s3.S3Client;

@QuarkusTestResource(S3Resource.class)
@RequiredArgsConstructor
public class FileStepDef {

    private static final String PATH = "mock-files/";
    private final S3Client s3Client;
    private Response response;

    @Dado("Que o bucket {string} esta disponivel")
    public void que_o_bucket_esta_disponivel(String bucketName) {
        s3Client.createBucket(builder -> builder.bucket(bucketName).build());
    }

    @Quando("Realizo upload do arquivo {string}")
    public void realizo_upload_do_arquivo(String fileName) {
        response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("file", FileUtils.getFile(PATH.concat(fileName)))
                .multiPart("key", fileName)
                .multiPart("mimeType", "text/plain;")
                .post("/v1/files")
                .thenReturn();
    }

    @Quando("Realizo download do arquivo {string}")
    public void realizo_download_do_arquivo(String fileName) {
        response = RestAssured.given()
                .get("/v1/files/" + fileName)
                .thenReturn();
    }

    @Entao("Tenho codigo de retorno {int}")
    public void tenho_codigo_de_retorno(Integer statusCode) {
        Assert.assertTrue(statusCode.equals(response.getStatusCode()));
    }


}