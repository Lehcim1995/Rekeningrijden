package classes;

import javax.xml.parsers.*;
import java.util.List;

public class VehicleParser
{
    public static void main(String argv[]) {

        //https://codereview.stackexchange.com/questions/79024/get-the-first-10-lines-of-a-file
        try { // https://stackoverflow.com/questions/26310595/how-to-parse-big-50-gb-xml-files-in-java
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            VehicleHandler vehicleHandler = new VehicleHandler();

            saxParser.parse("data.xml", vehicleHandler);

            List<?> cars = vehicleHandler.getVehicles();

            System.out.println(cars);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
