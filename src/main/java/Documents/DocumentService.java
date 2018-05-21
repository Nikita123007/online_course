package Documents;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DocumentService {
    private static final Integer MAX_WIDTH = 200;

    private static Integer GetWidth(XSSFCell cell){
        XSSFFont xssfFont = cell.getCellStyle().getFont();
        FontFamily family = FontFamily.valueOf(((XSSFFont) xssfFont).getFamily());
        java.awt.Font font = new Font(family.name(),Font.PLAIN, xssfFont.getFontHeightInPoints());
        FontMetrics metrics = new FontMetrics(font) {
        };
        Rectangle2D bounds = metrics.getStringBounds(cell.getStringCellValue() + " ", null);
        int widthInPixels = (int) bounds.getWidth();
        if(widthInPixels > MAX_WIDTH)
            return MAX_WIDTH;
        else
            return widthInPixels;
    }

    static void styleTable(XSSFWorkbook workbook, XSSFSheet sheet){
        XSSFCellStyle style = workbook.createCellStyle();
        style.setShrinkToFit(true);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        Integer maxCount = 0;
        for(int i = 0; i <= sheet.getPhysicalNumberOfRows(); i++){
            if(sheet.getRow(i) == null) continue;
            Integer count = sheet.getRow(i).getPhysicalNumberOfCells();
            if(count > maxCount)
                maxCount = count;
        }

        ArrayList<Integer> columnMaxWidths = new ArrayList<>();
        for (int i = 0; i <= maxCount; i++)
            columnMaxWidths.add(0);

        for(int i = 0; i <= sheet.getPhysicalNumberOfRows(); i++){
            int maxWidth = 0;
            if(sheet.getRow(i) == null) continue;
            for(int j = 0; j <= sheet.getRow(i).getPhysicalNumberOfCells(); j++){
                if(sheet.getRow(i).getCell(j) == null) continue;
                XSSFCell cell = sheet.getRow(i).getCell(j);
                cell.getCellStyle().getFont();
                cell.setCellStyle(style);
                Integer width = GetWidth(cell);
                if(columnMaxWidths.get(j) < width)
                    columnMaxWidths.set(j, width);
            }
        }
        for(int i = 0; i < maxCount; i++)
            sheet.setColumnWidth(i, columnMaxWidths.get(i) * 50);
    }

    public ByteArrayOutputStream generatePdf(Integer key){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            Security.addProvider(new BouncyCastleProvider());
            writer.setEncryption(null, PdfUtil.OWNER_PASSWORD.getBytes(), 0 , PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();

            Image image = Image.getInstance(Objects.requireNonNull(this.getClass().getClassLoader().getResource("background.png")));
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            writer.setPageEvent(new ImageBackgroundHelper(image));

            PdfUtil.addTitle(document, getTitle());
            for(Table table : getTables(key)){
                int columnCount = table.rows.get(0).length;
                PdfPTable pdfTable = new PdfPTable(columnCount);
                int[] widths = new int[columnCount];
                for(int i = 0; i < columnCount; i++){
                    widths[i] = 1;
                }
                pdfTable.setWidths(widths);

                PdfUtil.addTableHeader(pdfTable, Element.ALIGN_LEFT, table.name);
                for(int i = 1; i < columnCount; i++){
                    PdfUtil.addTableHeader(pdfTable, Element.ALIGN_LEFT, "");
                }

                for(String[] row : table.rows){
                    for(String cell : row){
                        PdfUtil.addCell(pdfTable, Element.ALIGN_LEFT, cell);
                    }
                }
                document.add(pdfTable);
                PdfUtil.addParagraph(document, new Chunk(" "),new Chunk(" "));
            }

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (DocumentException e){
            e.printStackTrace();
        }
        finally {
            document.close();
        }

        return outputStream;
    }

    public ByteArrayOutputStream generateCSV(Integer key){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try{
            Writer writer = new OutputStreamWriter(outputStream);
            CSVWriter csvWriter = new CSVWriter(writer, ';');

            for(Table table : getTables(key)){
                csvWriter.writeNext(new String[]{table.name});
                for(String[] row : table.rows){
                    csvWriter.writeNext(row);
                }
            }
            csvWriter.flush();
            csvWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return outputStream;
    }

    public ByteArrayOutputStream generateExcel(Integer key){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook();

            List<String[]> rows = new ArrayList<>();
            for(Table table : getTables(key)){
                String[] titleRows = new String[table.rows.get(0).length];
                titleRows[0] = table.name;
                rows.add(titleRows);
                rows.addAll(table.rows);
            }

            XSSFSheet sheet = workbook.createSheet(getTitle());
            ExcelUtil.createTable(sheet, rows, getTitle(), 1, 1);
            styleTable(workbook, sheet);
            workbook.write(outputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return outputStream;
    }

    public abstract String getTitle();
    abstract List<Table> getTables(Integer key);
}