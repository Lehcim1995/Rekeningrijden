package classes;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleHandler extends DefaultHandler
{

    private Map<String, String> elementNameMap;

    private Map<String, Boolean> elementBooleanMap;

    private List<Vehicle> vehicles;

    private Vehicle vehicle;

    public VehicleHandler() {
        elementNameMap = new HashMap<>();
        elementNameMap.put("Start", "ns:Statistik");
        elementNameMap.put("Vehicle id", "ns:KoeretoejIdent");
        elementNameMap.put("Vehicle licences", "ns:RegistreringNummerNummer");
        elementNameMap.put("Vehicle active", "ns:KoeretoejOplysningStatus");
        elementNameMap.put("Vehicle weight total", "ns:KoeretoejOplysningTotalVaegt");
        elementNameMap.put("Vehicle build year", "ns:KoeretoejOplysningFoersteRegistreringDato");
//        elementNameMap.put("Vehicle weight own", "ns:KoeretoejOplysningEgenVaegt");
        elementNameMap.put("Vehicle fuel type", "ns:DrivkraftTypeNavn");

        //

        elementBooleanMap = new HashMap<>();
        for (Map.Entry<String, String> entry : elementNameMap.entrySet())
        {
            String key = entry.getKey();
            elementBooleanMap.put(key, false);
        }

        vehicles = new ArrayList<>();

    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) throws SAXException
    {
//        System.out.println("Start Element :" + qName);

        if (getKeyFromValue(qName).equals("Start"))
        {
            vehicle = new Vehicle();
        }

        if (hasValue(qName))
        {
            String key = getKeyFromValue(qName);
            elementBooleanMap.put(key, true);
        }
    }

    @Override
    public void endElement(
            String uri,
            String localName,
            String qName) throws SAXException
    {
//        System.out.println("End Element :" + qName);

        // TODO check for the end of vehicle tag to add object

        if (getKeyFromValue(qName).equals("Start"))
        {
            vehicles.add(vehicle);
        }

    }

    @Override
    public void characters(
            char[] ch,
            int start,
            int length) throws SAXException
    {
        for (Map.Entry<String, Boolean> entry : elementBooleanMap.entrySet())
        {
            if (entry.getValue())
            {
                String value = new String(ch, start, length);

                System.out.println(entry.getKey() + " : " + value);
                entry.setValue(false);

                if (entry.getKey()
                         .equals("Vehicle licences"))
                {
                    vehicle.setLicensePlate(value);
                }

                if (entry.getKey()
                         .equals("Vehicle fuel type"))
                {

                    FuelEnum fuel;

                    switch (value)
                    {
                        case "Benzin":
                            fuel = FuelEnum.Benzine;
                            break;
                        case "Diesel":
                            fuel = FuelEnum.Diesel;
                            break;
                        default:
                            fuel = FuelEnum.Elektrisch;
                    }

                    vehicle.setFueltype(fuel);
                }

                if (entry.getKey()
                         .equals("Vehicle id"))
                {
                    if (value.length() <= 10)
                    {
                        value = "101010101010101"; // TODO find better solution for this
                    }
                    int id = Long.valueOf(value.substring(10))
                                 .intValue();

                    System.out.println("Vehicle id (int): " + id);
                    vehicle.setID(id);
                }
            }
        }
    }

    public List<Vehicle> getVehicles()
    {
        return vehicles;
    }

    private boolean hasValue(String value)
    {
        return elementNameMap.values()
                             .contains(value);
    }

    private String getKeyFromValue(String value)
    {
        for (Map.Entry<String, String> entry : elementNameMap.entrySet())
        {
            if (entry.getValue()
                     .equals(value))
            {
                return entry.getKey();
            }
        }

        return "";
    }
}
