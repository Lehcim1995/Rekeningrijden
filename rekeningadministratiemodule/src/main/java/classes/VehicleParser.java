package classes;

import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class VehicleParser
{
    public static void main(String argv[]) {

        //https://codereview.stackexchange.com/questions/79024/get-the-first-10-lines-of-a-file
        try { // https://stackoverflow.com/questions/26310595/how-to-parse-big-50-gb-xml-files-in-java
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            VehicleHandler vehicleHandler = new VehicleHandler();

            saxParser.parse("newdata.xml", vehicleHandler);

            List<?> cars = vehicleHandler.getVehicles();

            System.out.println(cars);

        } catch (Exception e) {
            e.printStackTrace();
        }


//        try
//        {
//            Path dir = new File("").toPath();
//            Path fileToCreatePath = dir.resolve("newdata.xml");
//            File newFile = Files.createFile(fileToCreatePath)
//                                .toFile();
//
//            try (FileWriter fw = new FileWriter(newFile))
//            {
//                Files.lines(Paths.get("D:\\School\\Jaar 3\\Semester 6\\Proftaak\\ESStatistikListeModtag.xml")).limit(10000).forEach(s -> {
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
