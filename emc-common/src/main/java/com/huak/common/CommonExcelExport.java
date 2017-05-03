/**
 * 通用的excel导出
 */
package com.huak.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：
 *
 * @author yx
 *         创建时间：2012-12-27上午9:54:29
 */
@Component
public class CommonExcelExport {

    public void excelExport(HttpServletResponse response,
                            java.util.Map<String, String> cellName,                                //列标题
                            java.util.List<Map<String, Object>> cellValues,                    //列值
                            String workBookName) throws IOException {                                            //工作簿名称
        OutputStream out = null;
        //获取居民信息
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell;
        int i = 0;
        for (Map.Entry<String, String> entry : cellName.entrySet()) {
            cell = row.createCell(i);
            cell.setCellValue(entry.getValue());
            i++;
        }
        for (int j = 0; j < cellValues.size(); j++) {                                            //单元格值填充
            row = sheet.createRow(j + 1);
            Map<String, Object> ret = cellValues.get(j);
            int icell = 0;
            for (Map.Entry<String, String> entry : cellName.entrySet()) {
                Object value = ret.get(entry.getKey().toLowerCase());
                String cellValue = "";
                if (value != null) {
                    cellValue = value.toString();
                }
                row.createCell(icell).setCellValue(cellValue);
                icell++;
            }
        }

        //response输出流导出excel

        String mimetype = "application/vnd.ms-excel";
        response.setContentType(mimetype);
        response.setCharacterEncoding("UTF-8");
        String fileName = workBookName + ".xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();

    }


    /**
     * 需要合并单元格 特殊的excel导出
     */
    public void mergeExcelExport(HttpServletResponse response,
                                 HSSFWorkbook wb,
                                 int startRow,
                                 List<Map<Integer, Object>> cellValues,
                                 String workBookName) {
        OutputStream out = null;
        //获取工作簿中的第一个sheet
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        for (int i = 0; i < cellValues.size(); i++) {
            row = sheet.createRow(startRow++);                                    //插入一行
            Map<Integer, Object> record = new HashMap<Integer, Object>();
            record = cellValues.get(i);
            for (int j = 0; j < record.size(); j++) {                            //填充一条记录
                Object value = record.get(j);
                String cellValue = "";
                if (value != null)
                    cellValue = value.toString();
                row.createCell(j).setCellValue(cellValue);
            }
        }

        //response输出流导出excel
        try {
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

