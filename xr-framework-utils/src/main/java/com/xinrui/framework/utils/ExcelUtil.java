package com.xinrui.framework.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private ExcelUtil() {

    }

    public static void exportExcelByEasyPoi(String fileName, Workbook wb,
                                            HttpServletRequest request, HttpServletResponse response) {
        String suffix = ".xls";
        String filename = fileName + suffix;
        try {
            if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
                filename = URLEncoder.encode(filename, "utf-8");
            } // 解决火狐下乱码的问题
            response.setContentType("application/vnd.ms-excel;charset=utf-8"); // 通知客户文件的MIME类型：
            response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "iso-8859-1"));
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            response.setStatus(500);
        }
    }
}
