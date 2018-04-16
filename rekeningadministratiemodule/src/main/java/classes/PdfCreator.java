package classes;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PdfCreator
{
    // todo add files here

    public static void main(String[] args)
    {
        Owner owner = new Owner(5, "firstname", "middelname", "lastname", "a", "c");

        Invoice invoice = new Invoice("id", owner, 10, PaymentEnum.Open, MonthEnum.December);
        invoice.setInvoiceId(5);

        new PdfCreator().createHTMLTemplateForInvoice(invoice);
    }

    public void createInvoicePdf(Invoice invoice)
    {
        String path = createHTMLTemplateForInvoice(invoice);

        exportToPdfBoxFromHtml5File(path, "output.pdf");
    }

    public void exportToPdfBoxFromHtml5File(
            String path,
            String out)
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path)
                                        .getFile());

        exportToPdfBox(file.toURI()
                           .toString(), out);
    }

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

    private void setInvoiceNumber(
            String file,
            Invoice invoice)
    {
        replaceOnFile(file, "\\{\\{ invoice_id \\}\\}", invoice.getInvoiceId() + "");
    }

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

    private void setInvoiceCreateDate(
            String file,
            Invoice invoice)
    {
        SimpleDateFormat dt = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = dt.format(new Date());

        replaceOnFile(file, "\\{\\{ created_date \\}\\}", date);
    }

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

    private void setInvoiceCarData(
            String file,
            Invoice invoice)
    {
        String htmlTable = new StringBuilder().append("<tr class=\"item\">\n")
                                              .append("            <td>23</td>\n")
                                              .append("            <td>723</td>\n")
                                              .append("            <td>54 ads d</td>\n")
                                              .append("            <td>Fuel</td>\n")
                                              .append("            <td>C</td>\n")
                                              .append("            <td>12354 km</td>\n")
                                              .append("            <td>142 Euro</td>\n")
                                              .append("        </tr>")
                                              .toString();


        StringBuilder output = new StringBuilder();

        for (int i = 0; i < 10; i++)
        {
            output.append(htmlTable)
                  .append("\n");
        }

        replaceOnFile(file, "\\{\\{ car_invoice \\}\\}", output.toString());
    }

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

    private String createHTMLTemplateForInvoice(Invoice invoice)
    {
        Path output = null;
        try
        {
            String filename = invoice.getInvoiceId() + " " + invoice.getOwner()
                                                                    .getFirstName() + ".html";

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("templates/invoiceTemplate.html")
                                            .getFile());
            output = Paths.get(filename);

            Files.copy(file.toPath(), output);

            setInvoiceNumber(filename, invoice);

            setInvoiceOwner(filename, invoice);

            setInvoiceCreateDate(filename, invoice);

            setInvoiceDueDate(filename, invoice);

            setInvoiceCarData(filename, invoice);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output.toFile()
                     .toURI()
                     .toString();
    }

    private void exportToPdfBox(
            String url,
            String out)
    {
        OutputStream os = null;

        try
        {
            os = new FileOutputStream(out);

            try
            {
                // There are more options on the builder than shown below.
                PdfRendererBuilder builder = new PdfRendererBuilder();

                builder.withW3cDocument(html5ParseDocument(url, 10), url);
                builder.toStream(os);
                builder.run();

            }
            catch (Exception e)
            {
                e.printStackTrace();
                // LOG exception
            }
            finally
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    // swallow
                }
            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
            // LOG exception.
        }
    }
}
