package classes;

import domain.Vehicle;

import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleParser
{

    public List<Vehicle> Parse(String file)
    {
        List<Vehicle> cars = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            VehicleHandler vehicleHandler = new VehicleHandler();

            saxParser.parse(file, vehicleHandler);

            cars = vehicleHandler.getVehicles();

            System.out.println(cars); // TODO remove maybe?

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }

    public static void main(String argv[]) {

        new VehicleParser().Parse("newdata.xml");

//        try
//        {
//            Path dir = new File("").toPath();
//            Path fileToCreatePath = dir.resolve("newdata.xml");
//            File newFile = Files.createFile(fileToCreatePath)
//                                .toFile();
//
//            try (FileWriter fw = new FileWriter(newFile))
//            {
//                Files.lines(Paths.get("D:\\School\\Jaar 3\\Semester 6\\Proftaak\\ESStatistikListeModtag.xml")).limit(100000).forEach(s -> {
//                    try
//                    {
//                        fw.write(s + "\n");
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                });
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
}
