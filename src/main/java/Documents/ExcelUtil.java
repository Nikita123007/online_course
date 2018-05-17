package Documents;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public class ExcelUtil {
    public static void createTable(XSSFSheet spreadsheet, List<String[]> rowsList, String title, int rowId, int cellId) {
        XSSFRow titleRow = spreadsheet.createRow(rowId);
        Cell titleCell = titleRow.createCell(cellId);
        XSSFCellStyle titleStyle = spreadsheet.getWorkbook().createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);
        spreadsheet.addMergedRegion(new CellRangeAddress(
                rowId,
                rowId,
                cellId,
                cellId + rowsList.get(0).length - 1
        ));
        rowId += 1;

        for (String[] row : rowsList) {
            XSSFRow xRow = spreadsheet.createRow(rowId++);

            int currCell = cellId;
            for (String value : row) {
                Cell cell = xRow.createCell(currCell);
                cell.setCellValue(value);
                spreadsheet.autoSizeColumn(currCell);
                currCell += 1;
            }
        }
    }
}
