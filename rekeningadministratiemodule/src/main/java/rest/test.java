package rest;

import classes.*;

public class test
{
    public static void main(String[] args) {

        PdfCreator pdfc = new PdfCreator();

        Owner owner = new Owner(12451, "Firstname", "Middlename", "Lastame", "adddress", "city");

        Invoice invoice = new Invoice("car", owner, 1000, PaymentEnum.Open, MonthEnum.December);

        pdfc.createFacature(invoice);
    }
}
