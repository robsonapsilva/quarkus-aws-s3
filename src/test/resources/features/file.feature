# coding: utf-8
# language: pt

Funcionalidade: FileResource - Endpoint's  para manipulação de arquivos no S3.

  Esquema do Cenário: UPLOAD <name>
    Dado  Que o bucket <bucket> esta disponivel
    Quando Realizo upload do arquivo <fileName>
    Entao Tenho codigo de retorno <statusCode>
    Exemplos: upload
      | name                               | bucket                  | fileName                    | statusCode |
      | "[NOT FOUND] - BUCKET NONEXISTENT" | "bucket-nonexistent"    | "file-alderaan-success.csv" | 404        |
      | "[CREATED] - ALDERAAN"             | "quarkus-aws-s3-bucket" | "file-alderaan-success.csv" | 201        |
      | "[CREATED] - TATOOINE"             | "quarkus-aws-s3-bucket" | "file-tatooine-success.csv" | 201        |
      | "[CREATED] - YAVIN IV"             | "quarkus-aws-s3-bucket" | "file-yavin-iv-success.csv" | 201        |

  Esquema do Cenário: DOWNLOAD <name>
    Dado  Que o bucket <bucket> esta disponivel
    Quando Realizo download do arquivo <fileName>
    Entao Tenho codigo de retorno <statusCode>
    Exemplos: download
      | name                            | bucket                  | fileName                    | statusCode |
      | "[NOT FOUND] - KEY NONEXISTENT" | "quarkus-aws-s3-bucket" | "key-nonexistent.csv"       | 404        |
      | "[SUCCESS] - ALDERAAN"          | "quarkus-aws-s3-bucket" | "file-alderaan-success.csv" | 200        |