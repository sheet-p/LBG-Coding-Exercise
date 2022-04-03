# LBG-Coding-Exercise

This exercise uses the open-api for Lloyds ATMs which can be found here
https://developer.lloydsbank.com/opendata-v2.2#get-atms-2.2
1. Create a java spring-boot microservice using gradle exposing one GET method
requiring two values:
&quot;url&quot;: &quot;https://api.lloydsbank.com/open-banking/v2.2/atms&quot;,
&quot;identification&quot;:&quot;30847300&quot;
This should return the identification value as well as full details of ATMs from the
opendata-v2.2#get-atms-2.2 api.
2. Create logging and demonstrate it by logging to the console.
3. Provide OpenAPI swagger specs functionality for your api.
4. Activate the actuator endpoint.
5. Create a jar file which can be used to run as a docker image.
6. Check the code into a public GitHub and provide repo links to review and run the
code.
