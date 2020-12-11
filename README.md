# webServiceOpenWeatherMap
A Java application that acts as a client of a meteorological forecast web service. 
The application uses OpenWeatherMap (http://openweathermap.org/) which provides meteorological data through the OpenWeatherMap API (http://openweathermap.org/API). 
The API uses RESTful calls and answering messages in JSON format. Accessing the API requires a registration process to obtain an API key (http://openweathermap.org/appid).
Java wrappers libraries have been developed that greatly simplify the use of the OpenWeatherMap API (http://openweathermap.org/examples#java). An example of one library is owm-japis (https://bitbucket.org/akapribot/owm-japis/overview).
The client dynamically accepts (from 2 textfields of an HTML form) the city / country as well as the duration of the forecast and displays the information (meteorological data about this area) which will be returned by the service. 
