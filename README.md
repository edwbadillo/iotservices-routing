# IOT SERVICES - GATEWAY SERVICE
Provides a API Gateway for routing services with Spring Cloud Gateway. Requires a Eureka Server, checkout this [repository](https://github.com/edwbadillo/iotservices-eureka).

By default, this service allow any origin. You can set the origins by editing the `src/main/java/com/edwin/iot/iotspringgateway/filters/Cors.java` file.

You can view all registered routes through the endpoint _"actuator/gateway/routes"_. Check this [section](https://cloud.spring.io/spring-cloud-gateway/reference/html/#actuator-api) from docs.

## Run service

You must specify the address of the Eureka Server, otherwise localhost:8761 will be used.

```
java -jar target/iot-spring-gateway-0.0.1-SNAPSHOT.jar \
    --eureka.client.serviceUrl.defaultZone=http://ipaddress:port/eureka/
```

## Run with docker

Build container:

```
docker build --pull --rm -f "Dockerfile" -t iot-gatewayservice "."
```

Run container:

```
docker run \
    -e EUREKA_SERVER=http://EUREKA_CONTAINER_IP:8761/eureka/ \
    -p 8282:8282 \
    --name iot-gatewayservice \
    iot-gatewayservice
```