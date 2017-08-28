# jetty-jersey
A Jetty jersey made restful web services

this restful web services is built with jetty & jersey, also use swagger-ui as the integregation test tools

jetty: As the web server
jersey: To impl the restful api
swagger: As the integregation test tools


## Usage

swagger:

```
mvn clean package -DskipTests

# visit localhost:8080/swagger/dist to see swagger-ui 

# in swagger-ui expore http://localhost:8080/swagger/swagger.yaml to test your restful api

```
