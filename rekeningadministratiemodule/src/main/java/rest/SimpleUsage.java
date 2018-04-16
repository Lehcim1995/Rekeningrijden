package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;

public class SimpleUsage
{
    public static void main(String[] args)
    {
        new SimpleUsage().exportToPdfBox("file://D:/School/JavaProjects/ProftaakSem6/rekeningadministratiemodule/src/main/webapp/templates/invoice.html", "output.pdf");
    }

    public void exportToPdfBox(String url, String out)
    {
        OutputStream os = null;

        try {
            os = new FileOutputStream(out);

            try {
                // There are more options on the builder than shown below.
                PdfRendererBuilder builder = new PdfRendererBuilder();

//                builder.withUri(url);
                builder.withW3cDocument(html5ParseDocument(url, 10), url );
                builder.toStream(os);
                builder.run();

            } catch (Exception e) {
                e.printStackTrace();
                // LOG exception
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    // swallow
                }
            }
        }
        catch (IOException e1) {
            e1.printStackTrace();
            // LOG exception.
        }
    }

    public org.w3c.dom.Document html5ParseDocument(String urlStr, int timeoutMs) throws IOException
    {
        URL url = new URL(urlStr);
        org.jsoup.nodes.Document doc;

        if (url.getProtocol().equalsIgnoreCase("file")) {
            doc = Jsoup.parse(new File(url.getPath()), "UTF-8");
        }
        else {
            doc = Jsoup.parse(url, timeoutMs);
        }

        return DOMBuilder.jsoup2DOM(doc);
    }
}
