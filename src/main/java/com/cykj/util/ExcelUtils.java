package com.cykj.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.style.StyleUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author guoc
 * <a href="https://cnblogs.com/mlxs/p/16745344.html">参考</a>
 * @date 2024/8/14 13:10
 */
public class ExcelUtils {
    /**
     * 下载 Excel 模板 (只有表头的 Excel)
     *
     * @param response response
     * @param headers  表头
     * @param fileName 文件名
     */
    public static void exportHeaders(HttpServletResponse response, List<String> headers, String fileName) throws IOException {
        export(response, null, headers, fileName);
    }

    /**
     * 导出或下载 Excel 公用方法
     *
     * @param response 当前请求的 HttpServletResponse
     * @param rows     需要写入的数据 List
     * @param fileName 最终文件名
     */
    public static void export(HttpServletResponse response, List<Map<String, Object>> rows, List<String> headers, String fileName) throws IOException {
        // 获取 Writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 写入表头
        writer = writer.writeHeadRow(headers);
        // 若是有数据 则写入数据
        if (CollectionUtils.isNotEmpty(rows)) {
            writer.write(rows);
        }
        Workbook workbook = writer.getWorkbook();
        CellStyle defaultCellStyle = StyleUtil.createDefaultCellStyle(workbook);
        DataFormat format = writer.getWorkbook().createDataFormat();
        // 默认为 文本格式
        defaultCellStyle.setDataFormat(format.getFormat("@"));
        // 默认为 居中
        defaultCellStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < headers.size(); i++) {
            String h = headers.get(i);
            h = h == null ? " " : h;
            writer.setColumnStyle(i, defaultCellStyle);
            // 默认宽度为 3 * 表头字符数
            writer.setColumnWidth(i, 3 * h.length());
        }

        // 弹出下载对话框的文件名，不能为中文，中文请自行编码
        fileName = URLUtil.encode(fileName);
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType(ExcelUtil.XLSX_CONTENT_TYPE + ";charset=utf-8");
        // 输出
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 读取上传到后台的excel(暂未实现，下载文件为空表)
     *
     * @param file MultipartFile 上传的文件
     * @param clazz 对应的类
     * @return 数据列表
     * @throws IOException e
     */
    public static <T> List<T> readExcelRows(MultipartFile file, Class clazz) throws IOException {
        return ExcelUtil.getReader(file.getInputStream()).readAll(clazz);
    }

    /**
     * 从项目中(WEB-INF下)读取excel文件并导出
     * @param path 文件路径
     * @param fileName 文件名
     * @param response  响应servlet
     * @throws IOException
     */
    public static void readAndExport(String path, String fileName,HttpServletResponse response) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(new File(FileUtil.getWebRoot().getPath() + "/WEB-INF" + path));
        //设置响应头
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType(ExcelUtil.XLSX_CONTENT_TYPE + ";charset=utf-8");

        //写出文件
        ServletOutputStream out = response.getOutputStream();
        reader.getWorkbook().write(out);
        out.flush();
        reader.getWorkbook().close();
        reader.close();
        IoUtil.close(out);
    }

}
