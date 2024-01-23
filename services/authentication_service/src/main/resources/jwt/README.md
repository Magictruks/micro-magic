In this file, generate keys for JWT auth :

Generate keys :

$ cd /src/main/resources/jwt

private : openssl pkcs8 -topk8 -inform PEM -in rsakey.pem -nocrypt -outform PEM -out rsakey_pkcs8.pem
public : openssl rsa -pubout -in rsakey.pem -out rsapublickey.pem