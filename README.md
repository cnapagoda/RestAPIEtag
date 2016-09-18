# RestAPIEtag

HTTP Caching(Etag) example with JAX-RS

### [What is Etag] (https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/ETag) :

The ETag HTTP response header is an identifier for a specific version of a resource. It allows caches to be more efficient, and saves bandwidth, as a web server does not need to send a full response if the content has not changed. On the other side, if the content has changed, etags are useful to help prevent simultaneous updates of a resource from overwriting each other ("mid-air collisions").

### [If-None-Match](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/If-None-Match) Header:

The `If-None-Match` HTTP request header makes the request conditional. For GET and HEAD methods, the server will send back the requested resource, with a 200 status, only if doesn't have a ETag matching the given one. Otherwise(if matching etag exist), server will return 304  status.


## Before you begin

### Before running this sample, you must:

 Download and install the Java SE Development Kit (JDK): [Download JDK] (http://www.oracle.com/technetwork/java/javase/downloads/index.html)

 Download [Apache Maven] (https://maven.apache.org/download.cgi) version 3.0.5 or greater:

 Install and configure Maven for your local development environment.

## Building and running project.

Need to rebuild the project so that maven can download all new dependencies using following command.

    mvn clean install

After that, can run project using following command.

    mvn jetty:run

## Test using CURL

### Test Request

Get Request  : `curl -v http://localhost:8080/info`

Get with Header : `curl -v http://localhost:8080/info --header 'If-None-Match: "Sun Sep 18 14:40:19 IST 2016"'` <br/>
<br/>


    curl -v http://localhost:8080/info
   
 Output:
    
    > GET /info HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.47.0
    > Accept: */*
    > 
    < HTTP/1.1 200 OK
    < Date: Sun, 18 Sep 2016 09:06:49 GMT
    < Cache-Control: no-transform,max-age=86400
    < Content-Type: application/json
    < Date: Sun, 18 Sep 2016 09:06:49 GMT
    < ETag: "Sun Sep 18 14:04:58 IST 2016"
    < Transfer-Encoding: chunked
    < Server: Jetty(9.3.11.v20160721)
    < 
    * Connection #0 to host localhost left intact
    {"fname":"Chandana","lname":"Napagoda","lastUpdateTime":1474187698758,"id":1}


 Second Request output:

    > GET /info HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.47.0
    > Accept: */*
    > 
    < HTTP/1.1 200 OK
    < Date: Sun, 18 Sep 2016 09:08:14 GMT
    < Cache-Control: no-transform,max-age=86400
    < Content-Type: application/json
    < Date: Sun, 18 Sep 2016 09:08:14 GMT
    < ETag: "Sun Sep 18 14:04:58 IST 2016"
    < Transfer-Encoding: chunked
    < Server: Jetty(9.3.11.v20160721)
    < 
    * Connection #0 to host localhost left intact
    {"fname":"Chandana","lname":"Napagoda","lastUpdateTime":1474187698758,"id":1}

#### Can see that Etag is same for both request

### Send Update Request 

    curl -v -X PUT http://localhost:8080/info

 Output:
 
    * Connected to localhost (127.0.0.1) port 8080 (#0)
    > PUT /info HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.47.0
    > Accept: */*
    > 
    < HTTP/1.1 200 OK
    < Date: Sun, 18 Sep 2016 09:10:19 GMT
    < Date: Sun, 18 Sep 2016 09:10:19 GMT
    < Content-Length: 0
    < Server: Jetty(9.3.11.v20160721)
    < 
    * Connection #0 to host localhost left intact

#### Preceding GET requests have a diffrent Etag


    * Connected to localhost (127.0.0.1) port 8080 (#0)
    > GET /info HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.47.0
    > Accept: */*
    > 
    < HTTP/1.1 200 OK
    < Date: Sun, 18 Sep 2016 09:18:53 GMT
    < Cache-Control: no-transform,max-age=86400
    < Content-Type: application/json
    < Date: Sun, 18 Sep 2016 09:18:53 GMT
    < ETag: "Sun Sep 18 14:40:19 IST 2016"
    < Transfer-Encoding: chunked
    < Server: Jetty(9.3.11.v20160721)
    < 
    * Connection #0 to host localhost left intact
    {"fname":"Chandana","lname":"9e783957-017c-4989-a0ad-5f575523cea6","lastUpdateTime":1474189819306,"id":1}
    
    
Also You can pasrse 

If-Modified-Since is compared to the Last-Modified whereas If-None-Match is compared to ETag. Both Modified-Since and ETag can be used to identify a specific variant of a resource.
    
    
Developed by [Chandana Napagoda] (http://chandana.napagoda.com)