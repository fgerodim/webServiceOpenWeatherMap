
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.aksingh.owmjapis.*;
import org.json.JSONException;


/**
 *
 * @author Theofanis Gerodimos
 */
@WebServlet(urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.JSONException
     */
    //private method that converts speed (m/sec) to beaufort
    private int beaufortConverter(double mPerSec){
        int beaufort;
        if (mPerSec < 0.3) {            beaufort=0;
        } else if (mPerSec <1.6) {      beaufort=1;
        } else if (mPerSec  < 3.4) {    beaufort=2;
        } else if (mPerSec <5.5) {      beaufort=3;
        } else if (mPerSec <8.0) {      beaufort=4;
        } else if (mPerSec  < 10.8){    beaufort=5;
        } else if (mPerSec <13.9) {     beaufort=6;
        } else if (mPerSec <17.2) {     beaufort=7;
        } else if (mPerSec  < 20.8) {   beaufort=8;
        } else if (mPerSec <24.5) {     beaufort=9;
        } else if (mPerSec <28.5) {     beaufort=10;
        } else if (mPerSec  < 32.7) {   beaufort=11;
        } else{                         beaufort=12;
        }
        return beaufort;
    }
    //basic mathod of the servlet
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, JSONException {
        
        //Get data from HTML form on submit
        String city= request.getParameter("city"); 
        String country=request.getParameter("country");
        String numOfDays=request.getParameter("period");
        response.setContentType("text/html;charset=UTF-8");
        // declaring object of "OpenWeatherMap" class with my own unice code
        OpenWeatherMap owm = new OpenWeatherMap("e6555a4f6cf257cdbf4a4da939df3c16");
        //Call dailyForecastByCityName method of owm object. The second argument is the number of the days
        //for this reason i call Byte.parseByte for convert a string to integer
        DailyForecast df = owm.dailyForecastByCityName(city+", "+country, Byte.parseByte(numOfDays));
        
        try (PrintWriter out = response.getWriter()) {
            // All output of my page is here. I include html code that creates the static web page 
            //with the data that returns me the web service i have called.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Forecast</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+numOfDays+"-day weather forecast for the city of</h1>");
            out.println("<h1 style=\"color:red;\">"+city +"-"+country+"</h1>");
            //A for statement for every day forecast
            for (int i = 0; i < df.getForecastCount(); i++) {
                DailyForecast.Forecast forecast = df.getForecastInstance(i);
                if (forecast.hasDateTime()) {
                    out.println("<p><b>"+forecast.getDateTime()+"</b></p><br>");
                    //I have used the formula (T-32)*5/9 to convert temperature from Fahrenheit scale to celsious scale
                    out.println("<p>Temperature: "+Math.round((forecast.getTemperatureInstance().getMinimumTemperature()-32.)*(5./ 9.)*100.)/100.+" to "+Math.round((forecast.getTemperatureInstance().getMaximumTemperature()-32.)*(5./9.)*100.)/100.+" (Celsious Scale)</p>");
                    out.println("<p>Humidity:        "+forecast.getHumidity()+"%</p>");
                    out.println("<p>Pressure:        "+forecast.getPressure()+" mbar</p>");
                    out.println("<p>Clouds:          "+forecast.getPercentageOfClouds()+" %</p>");
                    //need to round in two decimal digits the speed of wind.
                    double windSpeed=Math.round(forecast.getWindSpeed()*100.)/100.;
                    out.println("<p>Wind Speed:      "+windSpeed+" m/sec   ("+beaufortConverter(windSpeed)+"  Beaufort)</p><br><br><br>");
                }
                System.out.println();
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
