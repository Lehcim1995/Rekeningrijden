package classes;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class PdfCreator
{
    // todo add files here

    // todo split generation from invoice and pdf and html

    private static final String RESOURCE_PATH = "rekeningadministratiemodule/src/main/resources/";

    //D:\School\JavaProjects\ProftaakSem6\rekeningadministratiemodule\src\main\resources\templates\invoiceTemplate.html
    private static final String INVOICE_TEMPLATE = RESOURCE_PATH + "templates/invoiceTemplate.html";

    private static final String INVOICES_FOLDER = RESOURCE_PATH + "invoices/";


    public static void main(String[] args)
    {
        Owner owner = new Owner(6, "firstname", "middelname", "lastname", "a", "c");

        Invoice invoice = new Invoice("id", owner, 10, PaymentEnum.Open, MonthEnum.December);
        invoice.setInvoiceId(6);

        List<InvoiceData> data = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, i); //minus number would decrement the days

            data.add(new InvoiceData("1", cal.getTime(), 10d));
        }

        invoice.setInvoiceData(data);


        new PdfCreator().createInvoicePdf(invoice);
    }

    /**
     * @param invoice
     */
    public void createInvoicePdf(Invoice invoice)
    {
        String path = createHTMLTemplateForInvoice(invoice);

        String output = INVOICES_FOLDER + createFileName(invoice, "pdf");

        exportToPdfBox(path, output);

        try
        {
            URL url = new URL(path);
            File f = new File(url.getFile());
            f.delete();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            // TODO throw nice error
        }
    }

    /**
     * @param urlStr
     * @param timeoutMs
     * @return
     * @throws IOException
     */
    public org.w3c.dom.Document html5ParseDocument(
            String urlStr,
            int timeoutMs) throws IOException
    {
        URL url = new URL(urlStr);
        org.jsoup.nodes.Document doc;

        if (url.getProtocol()
               .equalsIgnoreCase("file"))
        {
            doc = Jsoup.parse(new File(url.getPath()), "UTF-8");
        }
        else
        {
            doc = Jsoup.parse(url, timeoutMs);
        }

        return DOMBuilder.jsoup2DOM(doc);
    }

    /**
     * @param invoice
     * @param extension
     * @return
     */
    private String createFileName(
            Invoice invoice,
            String extension)
    {
        return invoice.getInvoiceId() + "_" + invoice.getOwner()
                                                     .getFirstName() + "." + extension;
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceNumber(
            String file,
            Invoice invoice)
    {
        replaceOnFile(file, "\\{\\{ invoice_id \\}\\}", invoice.getInvoiceId() + "");
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceOwner(
            String file,
            Invoice invoice)
    {
        Owner owner = invoice.getOwner();

        String user = new StringBuilder().append(owner.getFirstName())
                                         .append(" ")
                                         .append(owner.getMiddleName())
                                         .append(" ")
                                         .append(owner.getLastName())
                                         .append("\n")
                                         .toString();

        replaceOnFile(file, "\\{\\{ invoice_user \\}\\}", user);
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceVehicleInformation(
            String file,
            Invoice invoice)
    {

        replaceOnFile(file, "\\{\\{ car_info \\}\\}", "<p> TODO fix this </p> ");
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceCreateDate(
            String file,
            Invoice invoice)
    {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = dt.format(new Date());

        replaceOnFile(file, "\\{\\{ created_date \\}\\}", date);
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceDueDate(
            String file,
            Invoice invoice)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 30); //minus number would decrement the days

        SimpleDateFormat dt = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = dt.format(cal.getTime());

        replaceOnFile(file, "\\{\\{ due_date \\}\\}", date);
    }

    /**
     * @param file
     * @param invoice
     */
    private void setInvoiceCarData(
            String file,
            Invoice invoice)
    {
        String outputString = createHtmlTable(invoice.getInvoiceData(), InvoiceData.class);

        replaceOnFile(file, "\\{\\{ car_invoice \\}\\}", outputString);
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    private <T> String createHtmlTable(
            final List<T> data,
            Class<T> instance)
    {
        //TODO refactor this

        if (data == null || data.isEmpty())
        {
            return "<p> no data found </p>";
        }

        // Load first item
        // TODO try, to load field header even if there is no data
        T single = data.get(0);


        String table; // return this

        String header;

        // get all the field names and store them in a list
        // TODO java.beans is better

        try
        {
            final BeanInfo beanInfo = Introspector.getBeanInfo(instance);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        }
        catch (IntrospectionException e)
        {
            e.printStackTrace();
        }

        List<String> objectFields = new ArrayList<>(Arrays.asList(instance.getDeclaredFields())).stream()
                                                                                                .map(Field::getName)
                                                                                                .collect(Collectors.toList());

        // loop though all the field names and create the table header
        StringBuilder headerBuilder = new StringBuilder("<tr class=\"heading\">");
        for (String fields : objectFields)
        {
            headerBuilder.append("<td>")
                         .append(fields)
                         .append("</td> \n");
        }
        header = headerBuilder.toString();
        header += "</tr>";


        // Start table data
        String tableData = "";

        // loop though the list of data
        for (T d : data)
        {

            String tableItem = "<tr class=\"item\">";
            for (String fields : objectFields)
            {
                // get the field name and capitalize the first letter
                String field = fields.substring(0, 1)
                                     .toUpperCase() + fields.substring(1);

                // try to make the method name
                String methodName = "get" + field;
                try
                {
                    // excecute the method name, in this case a getter for the field
                    String get = d.getClass()
                                  .getMethod(methodName)
                                  .invoke(d)
                                  .toString();

                    // add result to the table
                    tableItem += "<td>" + get + "</td> \n";
                }
                catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                {
                    e.printStackTrace();
                    // TODO make error nice
                    tableItem += "<td> no data found </td> \n";
                }
            }
            tableItem += "</tr> \n";
            tableData += tableItem;

        }

        tableData += "\n";

        table = header + "\n" + tableData;


        return table;
    }

    /**
     * @param file
     * @param original
     * @param replace
     */
    private void replaceOnFile(
            String file,
            String original,
            String replace)
    {
        Path path = Paths.get(file);
        Charset charset = StandardCharsets.UTF_8;

        try
        {
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(original, replace);
            Files.write(path, content.getBytes(charset));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param invoice
     * @return
     */
    private String createHTMLTemplateForInvoice(Invoice invoice)
    {
        Path output = null;
        try
        {
            String filename = createFileName(invoice, "html");

            File file = new File(INVOICE_TEMPLATE);

            File outputFile = new File(INVOICES_FOLDER + filename);

            output = outputFile.toPath();

            Files.copy(file.toPath(), outputFile.toPath(), REPLACE_EXISTING);

            setInvoiceNumber(outputFile.toPath()
                                       .toString(), invoice);

            setInvoiceOwner(outputFile.toPath()
                                      .toString(), invoice);

            setInvoiceCreateDate(outputFile.toPath()
                                           .toString(), invoice);

            setInvoiceDueDate(outputFile.toPath()
                                        .toString(), invoice);

            setInvoiceCarData(outputFile.toPath()
                                        .toString(), invoice);

            setInvoiceVehicleInformation(outputFile.toPath()
                                                   .toString(), invoice);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output.toFile()
                     .toURI()
                     .toString();
    }

    /**
     * @param url
     * @param out
     */
    private void exportToPdfBox(
            String url,
            String out)
    {
        try (OutputStream os = new FileOutputStream(out))
        {

            // There are more options on the builder than shown below.
            PdfRendererBuilder builder = new PdfRendererBuilder();


            builder.withW3cDocument(html5ParseDocument(url, 10), url);
            builder.toStream(os);
            builder.run();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
            // LOG exception.
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // LOG exception
        }
    }
}
