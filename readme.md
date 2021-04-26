# Build image and run with docker
1. run `mvn clean install` in the root directory to build the main artifact
1. go to docker folder
1. run `./build-image.sh`
1. run `docker run -d -p 8080:8080 livibank-ans`
1. The application should be launch on localhost:8080
1. The log and config directory can be mounted to the host machine with docker volume for easier configuration

# Swagger documentation
1. The swagger documentation is in Swagger OAS v3 API specification
1. The API document can be accessed through http://localhost:8080/api-docs
1. The Swagger UI is on http://localhost:8080/swagger-ui.html, showing the required model format, headers...

# Configuration
1. The application consume a yaml configuration file `liviBankAns.yaml`
1. The Score mapping is stored as a map with the prefix "rules" in the config file, this is a temporary solution, actually implementation should be stored in database like MongoDB
1. cors.allowOrigin property config the allow origin of this application, will be set in the Access-Control-Allow-Origin header in the response to protect the api endpoints.

# Authentication and Authorization
This application supports two authentication method:

1. With jwt token
    1. POST http://localhost:8080/authenticate { "username": "normal", "password": "password" } Receiving the JWT token.
    1. For further request, attach the header: "Authorization=Bearer ${jwtToken}"
    1. There are two dummy user defined in the UserDetailsServiceImpl.java: 
        1. normal -> with CALCULATOR role -> should correctly return the calculated score in the calculator API, given that input is valid.
        1. hello -> no role -> should return a 403 Forbidden for the calculator API.
    1. The jwt is stored in the config file in plain text, may need further optimized to use key encyption 
    1. See JwtRequestFilter.java for the detailed implementation.
    1. Possible further improvement -> use Jasypt to encrypt secret in property file 
1. With github access token
    1. This application act as the resource server. 
    1. When user login with github oauth, the client application retrieve the access token from github using the user code.
    1. The client application send request to this application attaching the header: "Authorization=Github Access Token ${accessToken}"
    1. This application verify the access token by request the https://api.github.com/user endpoint
    1. By default, all user authenticated with github access token have the CALCULATOR role
    1. See AccessTokenRequestFilter for detailed implementation.

# Cucumber test
1. The feature file is defined in src/test/resources/creditAssessmentCalculator.feature
1. Including the basic cases, 400, 401, 403 cases
1. The cucumber is run during the integration test maven life cycle. 
 

 