package com.zte.auth.utils.execl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zte.auth.utils.DateUtil;
import com.zte.auth.utils.execl.dto.ExcelTableExportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * - 导出
 *     1.正规excel导出 (格式简单,数据量可以,5W以内吧)
 *         注解方式:  ExcelExportUtil.exportExcel(ExportParams entity, Class<?> pojoClass,Collection<?> dataSet)
 *     2.数据量大超过5W,还在100W以内
 *         注解方式 ExcelExportUtil.exportBigExcel(ExportParams entity, Class<?> pojoClass,IExcelExportServer server, Object queryParams)
 *     3.数据量过百万级了.放弃excel吧,csv导出
 *         注解方式: CsvExportUtil.exportCsv(CsvExportParams params, Class<?> pojoClass, OutputStream outputStream)
 */
public class ExeclUtil {
    //1000 2000 7000 15000
    private ExeclUtil() throws IllegalAccessException {
        throw new IllegalAccessException("illegal init");
    }

    /**
     * Excel 单sheet导出
     * @param excelTableExportParams sheet参数
     * @param response 响应体
     * @return 返回值
     * @throws UnsupportedEncodingException
     */
    public static boolean exportExcel(ExcelTableExportParams excelTableExportParams, HttpServletResponse response) throws UnsupportedEncodingException {
        Collection<?> dataSet = excelTableExportParams.getDataSet();
        String fileName = excelTableExportParams.getFileName();
        String sheetName = excelTableExportParams.getSheetName();
        ExportParams exportParams = new ExportParams();
        if (StringUtils.isNotBlank(sheetName)) {
            exportParams.setSheetName(sheetName);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, excelTableExportParams.getCls(),dataSet);
        //判断是否为空
        if(workbook == null) {
            return false;
        }
        return downExcel(workbook,response,fileName);
    }

    /**
     * Excel多sheet导出
     *
     * @param excelTableExportParamsList sheet参数集合
     * @param response 响应体
     * @return 返回值
     * @throws UnsupportedEncodingException
     */
    public static boolean exportSheets(List<ExcelTableExportParams> excelTableExportParamsList,HttpServletResponse response) throws UnsupportedEncodingException {
        Workbook  workbook = createSheets(excelTableExportParamsList);
        if (null == workbook || CollectionUtils.isEmpty(excelTableExportParamsList)) {
            return false;
        }
        return downExcel(workbook,response,excelTableExportParamsList.get(0).getFileName());
    }

    /**
     * 创建多sheets
     * bug: 第二个sheetname 无法显示
     * @param excelTableExportParamsList sheet参数集合
     * @return Workbook
     */
    private static Workbook createSheets(List<ExcelTableExportParams> excelTableExportParamsList){
        List<Map<String,Object>> sheetsList = new ArrayList<>();
        for (ExcelTableExportParams item : excelTableExportParamsList) {
            // 创建多sheet要使用map
            Map<String,Object> map = new HashMap<>(16);
            String sheetName = item.getSheetName();
            Class cls = item.getCls();
            Collection<?> dataSet = item.getDataSet();
            // 创建参数对象（用来设定excel得sheet得内容等信息）
            ExportParams exportParams = new ExportParams();
            if (StringUtils.isNotBlank(sheetName)) {
                exportParams.setSheetName(sheetName);
            }
            // 设置sheet得名称
            exportParams.setSheetName(sheetName);
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            map.put("title",exportParams);
            // 模版导出对应得实体类型
            map.put("entity",cls);
            // sheet中要填充得数据
            map.put("data",dataSet);
            sheetsList.add(map);
        }
        return ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
    }

    /**
     * 下载Excel文件
     * @param workbook  workbook对象
     * @param response 响应体
     * @param fileName 文件名
     * @return boolean 返回值
     * @throws UnsupportedEncodingException
     */
    private static boolean downExcel(Workbook workbook, HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(fileName) && Objects.isNull(fileName)) {
            fileName = DateUtil.date2TimeStr(new Date(),DateUtil.DATE);
        }
        String realFileName = URLEncoder.encode(fileName, "utf-8");
        // 重置响应对象
        response.reset();
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" +realFileName+".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
