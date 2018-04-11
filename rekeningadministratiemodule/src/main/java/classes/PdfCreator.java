package classes;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.datatable.DataTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfCreator
{

    public void createFacature(Invoice invoice)
    {
        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
            PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
            createHeader(document, contentStream, invoice);
            createTable(document, contentStream, page, invoice);


// Make sure that the content stream is closed:
            contentStream.close();

// Save the results and ensure that the document is properly closed:
            document.save(createFilename(invoice));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createHeader(PDDocument document, PDPageContentStream contentStream, Invoice invoice) throws IOException
    {
        String ownerLastname = invoice.getOwner().getLastName();

        PDFont font = PDType1Font.HELVETICA_BOLD;

        String image = Image.class.getResource("/logo.png").getFile();
        PDImageXObject pdImage = PDImageXObject.createFromFile(image, document);
        contentStream.drawImage(pdImage, 0, 700);

        contentStream.beginText();

        contentStream.setLeading(12);

        contentStream.setFont(font, 12);
        contentStream.transform(Matrix.getTranslateInstance(100, 700));
        contentStream.showText("Geachte heer " + ownerLastname + ",");
        contentStream.newLine();
        contentStream.showText("Dit is de vacature van " + invoice.getDate().name());

        contentStream.endText();
    }

    public void createTable(PDDocument document, PDPageContentStream contentStream, PDPage page,  Invoice invoice) throws IOException
    {
        BaseTable table = new BaseTable(0, 0, 0, 150, 5, document, page, true,
                false);
        //Create Header row
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Awesome Facts About Belgium");
//        cell.setFont(PDType1Font.HELVETICA_BOLD);
//        cell.setFillColor(Color.BLACK);
        table.addHeaderRow(headerRow);
//        List<String[]> facts = Arrays.asList(new String[]{"man"}, new String[]{"Man"}, new String[]{"man"});
//        for (String[] fact : facts) {
//            Row<PDPage> row = table.createRow(10f);
//            cell = row.createCell((100 / 3.0f) * 2, fact[0] );
//            for (int i = 1; i < fact.length; i++) {
//                cell = row.createCell((100 / 9f), fact[i]);
//            }
//        }
        table.draw();
    }

    public String createFilename(Invoice invoice)
    {
        String filename = invoice.getOwner()
                                 .getCitizenId() + "";
        filename += "_";
        filename += invoice.getOwner()
                           .getFirstName();
        filename += "_";
        filename += invoice.getOwner()
                           .getLastName();
        filename += "_";
        filename += invoice.getDate();
        filename += ".pdf";

        return filename;
    }

    public void createPdf(String text)
    {
        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage();
            document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
            PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.moveTextPositionByAmount(100, 700);
            contentStream.drawString(text);
            contentStream.endText();

// Make sure that the content stream is closed:
            contentStream.close();

// Save the results and ensure that the document is properly closed:
            document.save("Hello World.pdf");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
