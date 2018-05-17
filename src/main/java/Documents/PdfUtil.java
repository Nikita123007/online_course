package Documents;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;

public class PdfUtil {

    public static String USER_PASSWORD = "password";
    public static String OWNER_PASSWORD = "secured";

    public static Font boldFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
    public static Font textFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);

    public static void addTitle(Document document, String text) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLACK);
        Paragraph title = new Paragraph(text, titleFont);
        title.setSpacingAfter(10f);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
    }

    public static void addUnderlinedHeader(Document document, String text, int alignment) throws DocumentException {
        Font underlineFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.UNDERLINE);

        Chunk patientChunk = new Chunk(text, underlineFont);
        Paragraph header = new Paragraph(patientChunk);
        header.setSpacingAfter(10f);
        header.setAlignment(alignment);
        document.add(header);
    }

    public static void addParagraph(Document document, Chunk ...chunks) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingAfter(10f);

        for (Chunk chunk : chunks) {
            paragraph.add(chunk);
        }

        document.add(paragraph);
    }

    public static void addListItem(Document document, Chunk chunk) throws DocumentException {
        Paragraph listItem = new Paragraph();

        Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 8);
        Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);

        listItem.setSpacingAfter(10f);
        listItem.add(Chunk.TABBING);
        listItem.add(bullet);
        listItem.add("   ");
        listItem.add(chunk);

        document.add(listItem);
    }

    public static void addParagraphsToListItem(Document document, Chunk ...chunks) throws DocumentException {
        for (Chunk chunk : chunks) {
            Paragraph paragraph = new Paragraph();

            paragraph.setSpacingAfter(10f);
            paragraph.add(Chunk.TABBING);
            paragraph.add(Chunk.TABBING);
            paragraph.add(chunk);

            document.add(paragraph);
        }
    }

    public static void addTableHeader(PdfPTable table, int horizontalAlignment, String ...headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
            cell.setLeft(0);
            cell.setBorder(0);
            cell.setHorizontalAlignment(horizontalAlignment);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }


    public static PdfPCell CreateCell( int horizontalAlignment, String rowCell){
        PdfPCell cell = new PdfPCell(new Phrase(rowCell, FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.NORMAL)));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

    public static void addCell(PdfPTable table, int horizontalAlignment, String rowCell) {
        Font font;
        try{
            font = new Font(BaseFont.createFont("c:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H , BaseFont.EMBEDDED));
        }
        catch (DocumentException e){return;}
        catch (IOException e){return;}

        table.addCell(new Phrase(rowCell, font));
    }

    public static void addTableRow(PdfPTable table, int horizontalAlignment, String ...rowCells) {
        for (String cellText : rowCells) {
            PdfPCell cell = CreateCell(horizontalAlignment, cellText);
            table.addCell(cell);
        }
    }
}
