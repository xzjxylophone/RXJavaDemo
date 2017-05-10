package cn.yiyizuche.common.sys.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: 壹壹OA
 * @Package : cn.yiyizuche.common.sys.util
 * @FileName : ExcelOperation.java
 * @Description : POI操作excel工具类
 * @author : lipeng
 * @CreateDate : 2017/3/10 17:30
 * @version : V1.0
 * @Copyright: 2017 yizu Inc. All rights reserved.
 * @Reviewed :
 * @UpateLog:	Name	Date	Reason/Contents
 * 			---------------------------------------
 * 				***		****	****
 *
 */
public class ExcelOperation {


    //%%%%%%%%-------常量部分 开始----------%%%%%%%%%
    /**
     * 默认的开始读取的行位置为第一行（索引值为0）
     */
    private final static int READ_START_POS = 0;

    /**
     * 默认结束读取的行位置为最后一行（索引值=0，用负数来表示倒数第n行）
     */
    private final static int READ_END_POS = 0;

    /**
     * 默认Excel内容的开始比较列位置为第一列（索引值为0）
     */
    private final static int COMPARE_POS = 0;

    /**
     * 默认多文件合并的时需要做内容比较（相同的内容不重复出现）
     */
    private final static boolean NEED_COMPARE = true;

    /**
     * 默认多文件合并的新文件遇到名称重复时，进行覆盖
     */
    private final static boolean NEED_OVERWRITE = true;

    /**
     * 默认只操作一个sheet
     */
    private final static boolean ONLY_ONE_SHEET = true;

    /**
     * 默认读取第一个sheet中（只有当ONLY_ONE_SHEET = true时有效）
     */
    private final static int SELECTED_SHEET = 0;

    /**
     * 默认从第一个sheet开始读取（索引值为0）
     */
    private final static int READ_START_SHEET = 0;

    /**
     * 默认在最后一个sheet结束读取（索引值=0，用负数来表示倒数第n行）
     */
    private final static int READ_END_SHEET = 0;

    /**
     * 默认打印各种信息
     */
    private final static boolean PRINT_MSG = true;

    //%%%%%%%%-------常量部分 结束----------%%%%%%%%%


    //%%%%%%%%-------字段部分 开始----------%%%%%%%%%
    /**
     * Excel文件路径
     */
    private String excelPath = "data.xlsx";

    /**
     * 设定开始读取的位置，默认为0
     */
    private int startReadPos = READ_START_POS;

    /**
     * 设定结束读取的位置，默认为0，用负数来表示倒数第n行
     */
    private int endReadPos = READ_END_POS;

    /**
     * 设定开始比较的列位置，默认为0
     */
    private int comparePos = COMPARE_POS;

    /**
     * 设定汇总的文件是否需要替换，默认为true
     */
    private boolean isOverWrite = NEED_OVERWRITE;

    /**
     * 设定是否需要比较，默认为true(仅当不覆写目标内容是有效，即isOverWrite=false时有效)
     */
    private boolean isNeedCompare = NEED_COMPARE;

    /**
     * 设定是否只操作第一个sheet
     */
    private boolean onlyReadOneSheet = ONLY_ONE_SHEET;

    /**
     * 设定操作的sheet在索引值
     */
    private int selectedSheetIdx = SELECTED_SHEET;

    /**
     * 设定操作的sheet的名称
     */
    private String selectedSheetName = "";

    /**
     * 设定开始读取的sheet，默认为0
     */
    private int startSheetIdx = READ_START_SHEET;

    /**
     * 设定结束读取的sheet，默认为0，用负数来表示倒数第n行
     */
    private int endSheetIdx = READ_END_SHEET;

    /**
     * 设定是否打印消息
     */
    private boolean printMsg = PRINT_MSG;


    //%%%%%%%%-------字段部分 结束----------%%%%%%%%%

    /**
     * 无参数构造器
     * @author: lipeng
     * @CreateDate: 2017/3/10 17:44
     * @param
     */
    public ExcelOperation() {

    }

    /**
     * 带参数构造器
     * @author: lipeng
     * @CreateDate: 2017/3/10 17:44
     * @param excelPath 文件路径
     */
    public ExcelOperation(String excelPath) {
        this.excelPath = excelPath;
    }

    /**
     * @Method: RestoreSettings
     * @Description: 还原设定（其实是重新new一个新的对象并返回）
     * @return cn.yiyizuche.common.sys.util.ExcelOperation 返回新构造的对象
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 17:32
     */
    public ExcelOperation RestoreSettings() {
        ExcelOperation instance = new ExcelOperation(this.excelPath);
        return instance;
    }

    /**
     * @Method: readExcel
     * @Description: 自动根据文件扩展名，调用对应的读取方法
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> 返回Row对象集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 17:33
     */
    public List<Row> readExcel() throws IOException {
        return readExcel(this.excelPath);
    }

    /**
     * @Method: readExcel
     * @Description: 自动根据文件扩展名，调用对应的读取方法
     * @param xlsPath
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> 返回Row对象集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 17:33
     */
    public List<Row> readExcel(String xlsPath) throws IOException {

        //扩展名为空时，
        if (xlsPath.equals("")) {
            throw new IOException("文件路径不能为空！");
        } else {
            File file = new File(xlsPath);
            if (!file.exists()) {
                throw new IOException("文件不存在！");
            }
        }

        //获取扩展名
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

        try {

            if ("xls".equals(ext)) {              //使用xls方式读取
                return readExcel_xls(xlsPath);
            } else if ("xlsx".equals(ext)) {       //使用xlsx方式读取
                return readExcel_xlsx(xlsPath);
            } else {                                  //依次尝试xls、xlsx方式读取
                out("您要操作的文件没有扩展名，正在尝试以xls方式读取...");
                try {
                    return readExcel_xls(xlsPath);
                } catch (IOException e1) {
                    out("尝试以xls方式读取，结果失败！，正在尝试以xlsx方式读取...");
                    try {
                        return readExcel_xlsx(xlsPath);
                    } catch (IOException e2) {
                        out("尝试以xls方式读取，结果失败！\n请您确保您的文件是Excel文件，并且无损，然后再试。");
                        throw e2;
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * @Method: readExcelByInputStream
     * @Description: 直接以流方式读取excel文件
     * @param is 输入流
     * @param ext 扩展名
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> 返回Row集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:25
     */
    public List<Row> readExcelByInputStream(InputStream is, String ext) throws IOException{

        if ("xls".equals(ext)) {              //使用xls方式读取
            return readExcelByInputStream_xls(is);
        } else if ("xlsx".equals(ext)) {       //使用xlsx方式读取
            return readExcelByInputStream_xlsx(is);
        } else {
            out("读入文件流时候没有传入文件扩展名，不能判断使用哪种格式解析...");
        }
        return null;
    }

    /**
     * @Method: writeExcel
     * @Description: 自动根据文件扩展名，调用对应的写入方法
     * @param rowList Row对象集合
     * @return void
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 17:34
     */
    public void writeExcel(List<Row> rowList) throws IOException {
        writeExcel(rowList, excelPath);
    }

    /**
     * @Method: writeExcel
     * @Description: 自动根据文件扩展名，调用对应的写入方法
     * @param rowList Row对象集合
     * @param xlsPath xls格式文件的路径
     * @return void
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 17:35
     */
    public void writeExcel(List<Row> rowList, String xlsPath) throws IOException {

        //扩展名为空时，
        if (xlsPath.equals("")) {
            throw new IOException("文件路径不能为空！");
        }

        //获取扩展名
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

        try {

            if ("xls".equals(ext)) {              //使用xls方式写入
                writeExcel_xls(rowList, xlsPath);
            } else if ("xlsx".equals(ext)) {       //使用xlsx方式写入
                writeExcel_xlsx(rowList, xlsPath);
            } else {                                  //依次尝试xls、xlsx方式写入
                out("您要操作的文件没有扩展名，正在尝试以xls方式写入...");
                try {
                    writeExcel_xls(rowList, xlsPath);
                } catch (IOException e1) {
                    out("尝试以xls方式写入，结果失败！，正在尝试以xlsx方式读取...");
                    try {
                        writeExcel_xlsx(rowList, xlsPath);
                    } catch (IOException e2) {
                        out("尝试以xls方式写入，结果失败！\n请您确保您的文件是Excel文件，并且无损，然后再试。");
                        throw e2;
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * @Method: writeExcel_xls
     * @Description: 修改Excel（97-03版，xls格式）
     * @param rowList Row对象集合
     * @param dist_xlsPath 输出xls格式文件的路径
     * @return void (返回类型描述)
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:29
     */
    public void writeExcel_xls(List<Row> rowList, String dist_xlsPath) throws IOException {
        writeExcel_xls(rowList, excelPath, dist_xlsPath);
    }

    /**
     * @Method: writeExcel_xls
     * @Description: 修改Excel（97-03版，xls格式）
     * @param rowList Row对象集合
     * @param src_xlsPath 输入xls格式文件的路径
     * @param dist_xlsPath 输出xls格式文件的路径
     * @return void
     * @throws  IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:30
     */
    public void writeExcel_xls(List<Row> rowList, String src_xlsPath, String dist_xlsPath) throws IOException {

        // 判断文件路径是否为空
        if (dist_xlsPath == null || dist_xlsPath.equals("")) {
            out("文件路径不能为空");
            throw new IOException("文件路径不能为空");
        }
        // 判断文件路径是否为空
        if (src_xlsPath == null || src_xlsPath.equals("")) {
            out("文件路径不能为空");
            throw new IOException("文件路径不能为空");
        }

        // 判断列表是否有数据，如果没有数据，则返回
        if (rowList == null || rowList.size() == 0) {
            out("文档为空");
            return;
        }

        try {
            HSSFWorkbook wb = null;

            // 判断文件是否存在
            File file = new File(dist_xlsPath);
            if (file.exists()) {
                // 如果复写，则删除后
                if (isOverWrite) {
                    file.delete();
                    // 如果文件不存在，则创建一个新的Excel
                    // wb = new HSSFWorkbook();
                    // wb.createSheet("Sheet1");
                    wb = new HSSFWorkbook(new FileInputStream(src_xlsPath));
                } else {
                    // 如果文件存在，则读取Excel
                    wb = new HSSFWorkbook(new FileInputStream(file));
                }
            } else {
                // 如果文件不存在，则创建一个新的Excel
                // wb = new HSSFWorkbook();
                // wb.createSheet("Sheet1");
                wb = new HSSFWorkbook(new FileInputStream(src_xlsPath));
            }

            // 将rowlist的内容写到Excel中
            writeExcel(wb, rowList, dist_xlsPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method: writeExcel_xlsx
     * @Description: 修改Excel（97-03版，xls格式）
     * @param rowList Row对象集合
     * @param dist_xlsPath 输出xlsx格式文件的路径
     * @return void (返回类型描述)
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:32
     */
    public void writeExcel_xlsx(List<Row> rowList, String dist_xlsPath) throws IOException {
        writeExcel_xls(rowList, excelPath, dist_xlsPath);
    }

    /**
     * @Method: writeExcel_xlsx
     * @Description: 修改Excel（2007版，xlsx格式）
     * @param rowList Row对象集合
     * @param src_xlsxPath 输入xlsx格式文件的路径
     * @param dist_xlsxPath 输出xlsx格式文件的路径
     * @return void (返回类型描述)
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:38
     */
    public void writeExcel_xlsx(List<Row> rowList, String src_xlsxPath, String dist_xlsxPath) throws IOException {

        // 判断文件路径是否为空
        if (dist_xlsxPath == null || dist_xlsxPath.equals("")) {
            out("文件路径不能为空");
            throw new IOException("文件路径不能为空");
        }
        // 判断文件路径是否为空
        if (src_xlsxPath == null || src_xlsxPath.equals("")) {
            out("文件路径不能为空");
            throw new IOException("文件路径不能为空");
        }

        // 判断列表是否有数据，如果没有数据，则返回
        if (rowList == null || rowList.size() == 0) {
            out("文档为空");
            return;
        }

        try {
            // 读取文档
            XSSFWorkbook wb = null;

            // 判断文件是否存在
            File file = new File(dist_xlsxPath);
            if (file.exists()) {
                // 如果复写，则删除
                if (isOverWrite) {
                    file.delete();
                    // 如果文件不存在，则创建一个新的Excel
                    // wb = new XSSFWorkbook();
                    // wb.createSheet("Sheet1");
                    wb = new XSSFWorkbook(new FileInputStream(src_xlsxPath));
                } else {
                    // 如果文件存在，则读取Excel
                    wb = new XSSFWorkbook(new FileInputStream(file));
                }
            } else {
                // 如果文件不存在，则创建一个新的Excel
                // wb = new XSSFWorkbook();
                // wb.createSheet("Sheet1");
                wb = new XSSFWorkbook(new FileInputStream(src_xlsxPath));
            }
            // 将rowlist的内容添加到Excel中
            writeExcel(wb, rowList, dist_xlsxPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method: readExcel_xlsx
     * @Description: 读取Excel 2007版，xlsx格式
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> Row对象集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:41
     */
    public List<Row> readExcel_xlsx() throws IOException {
        return readExcel_xlsx(excelPath);
    }

    /**
     * @Method: readExcel_xlsx
     * @Description: 读取Excel 2007版，xlsx格式
     * @param xlsxPath 读取xlsx格式文件的路径
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> Row对象集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:42
     */
    public List<Row> readExcel_xlsx(String xlsxPath) throws IOException {
        // 判断文件是否存在
        File file = new File(xlsxPath);
        if (!file.exists()) {
            throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
        }

        FileInputStream fis = new FileInputStream(file);
        List<Row> rowList = readExcelByInputStream_xlsx(fis);
        return rowList;
    }

    /**
     * @Method: readExcelByInputStream_xlsx
     * @Description: 流方式读取xlsx格式的文件
     * @param is 输入流
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:43
     */
    public List<Row> readExcelByInputStream_xlsx(InputStream is){
        List<Row> rowList = new ArrayList<Row>();
        try {
            XSSFWorkbook wb = null;
            // 去读Excel
            wb = new XSSFWorkbook(is);
            // 读取Excel 2007版，xlsx格式
            rowList = readExcel(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @Method: readExcel_xls
     * @Description: 读取Excel(97-03版，xls格式)
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> Row对象集合
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:43
     */
    public List<Row> readExcel_xls() throws IOException {
        return readExcel_xls(excelPath);
    }

    /**
     * @Method: readExcel_xls
     * @Description: 读取Excel(97-03版，xls格式)
     * @param xlsPath 读取xls格式文件的路径
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> (返回类型描述)
     * @throws IOException
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:45
     */
    public List<Row> readExcel_xls(String xlsPath) throws IOException {

        // 判断文件是否存在
        File file = new File(xlsPath);
        if (!file.exists()) {
            throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
        }

        InputStream is = new FileInputStream(file);
        List<Row> rowList = readExcelByInputStream_xls(is);
        return rowList;
    }

    /**
     * @Method: readExcelByInputStream_xls
     * @Description: 流方式读取xls格式的文件
     * @param is 输入流
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:45
     */
    public List<Row> readExcelByInputStream_xls(InputStream is){
        List<Row> rowList = new ArrayList<Row>();
        HSSFWorkbook wb = null;// 用于Workbook级的操作，创建、删除Excel
        try {
            // 读取Excel
            wb = new HSSFWorkbook(is);

            // 读取Excel 97-03版，xls格式
            rowList = readExcel(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @Method: getCellValue
     * @Description: 读取单元格的值
     * @param cell Cell对象
     * @return java.lang.String 单元格的值
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:46
     */
    public static String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

    /**
     * @Method: readExcel
     * @Description: 通用读取Excel
     * @param wb Workbook对象
     * @return java.util.List<org.apache.poi.ss.usermodel.Row> Row对象集合
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:47
     */
    private List<Row> readExcel(Workbook wb) {
        List<Row> rowList = new ArrayList<Row>();

        int sheetCount = 1;//需要操作的sheet数量

        Sheet sheet = null;
        if (onlyReadOneSheet) {   //只操作一个sheet
            // 获取设定操作的sheet(如果设定了名称，按名称查，否则按索引值查)
            sheet = selectedSheetName.equals("") ? wb.getSheetAt(selectedSheetIdx) : wb.getSheet(selectedSheetName);
        } else {                          //操作多个sheet
            sheetCount = wb.getNumberOfSheets();//获取可以操作的总数量
        }

        // 获取sheet数目
        for (int t = startSheetIdx; t < sheetCount + endSheetIdx; t++) {
            // 获取设定操作的sheet
            if (!onlyReadOneSheet) {
                sheet = wb.getSheetAt(t);
            }

            //获取最后行号
            int lastRowNum = sheet.getLastRowNum();

            if (lastRowNum > 0) {    //如果>0，表示有数据
                out("\n开始读取名为【" + sheet.getSheetName() + "】的内容：");
            }

            Row row = null;
            // 循环读取
            for (int i = startReadPos; i <= lastRowNum + endReadPos; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    rowList.add(row);
                    out("第" + (i + 1) + "行：", false);
                    // 获取每一单元格的值
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        String value = getCellValue(row.getCell(j));
                        if (!value.equals("")) {
                            out(value + " | ", false);
                        }
                    }
                    out("");
                }
            }
        }
        return rowList;
    }

    /**
     * @Method: writeExcel
     * @Description: 修改Excel，并另存为
     * @param wb Workbook对象
     * @param rowList Row集合
     * @param xlsPath 文件路径
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:51
     */
    private void writeExcel(Workbook wb, List<Row> rowList, String xlsPath) {

        if (wb == null) {
            out("操作文档不能为空！");
            return;
        }

        Sheet sheet = wb.getSheetAt(0);// 修改第一个sheet中的值

        // 如果每次重写，那么则从开始读取的位置写，否则果获取源文件最新的行。
        int lastRowNum = isOverWrite ? startReadPos : sheet.getLastRowNum() + 1;
        int t = 0;//记录最新添加的行数
        out("要添加的数据总条数为：" + rowList.size());
        for (Row row : rowList) {
            if (row == null) continue;
            // 判断是否已经存在该数据
            int pos = findInExcel(sheet, row);

            Row r = null;// 如果数据行已经存在，则获取后重写，否则自动创建新行。
            if (pos >= 0) {
                sheet.removeRow(sheet.getRow(pos));
                r = sheet.createRow(pos);
            } else {
                r = sheet.createRow(lastRowNum + t++);
            }

            //用于设定单元格样式
            CellStyle newstyle = wb.createCellStyle();

            //循环为新行创建单元格
            for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
                Cell cell = r.createCell(i);// 获取数据类型
                cell.setCellValue(getCellValue(row.getCell(i)));// 复制单元格的值到新的单元格
                // cell.setCellStyle(row.getCell(i).getCellStyle());//出错
                if (row.getCell(i) == null) continue;
                copyCellStyle(row.getCell(i).getCellStyle(), newstyle); // 获取原来的单元格样式
                cell.setCellStyle(newstyle);// 设置样式
                // sheet.autoSizeColumn(i);//自动跳转列宽度
            }
        }
        out("其中检测到重复条数为:" + (rowList.size() - t) + " ，追加条数为：" + t);

        // 统一设定合并单元格
        setMergedRegion(sheet);

        try {
            // 重新将数据写入Excel中
            FileOutputStream outputStream = new FileOutputStream(xlsPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            out("写入Excel时发生错误！ ");
            e.printStackTrace();
        }
    }

    /**
     * @Method: findInExcel
     * @Description: 查找某行数据是否在Excel表中存在，返回行数。
     * @param sheet Sheet对象
     * @param row Row对象
     * @return int 行数
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:52
     */
    private int findInExcel(Sheet sheet, Row row) {
        int pos = -1;

        try {
            // 如果覆写目标文件，或者不需要比较，则直接返回
            if (isOverWrite || !isNeedCompare) {
                return pos;
            }
            for (int i = startReadPos; i <= sheet.getLastRowNum() + endReadPos; i++) {
                Row r = sheet.getRow(i);
                if (r != null && row != null) {
                    String v1 = getCellValue(r.getCell(comparePos));
                    String v2 = getCellValue(row.getCell(comparePos));
                    if (v1.equals(v2)) {
                        pos = i;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pos;
    }

    /**
     * @Method: copyCellStyle
     * @Description: 复制一个单元格样式到目的单元格样式
     * @param fromStyle 源单元格样式
     * @param toStyle 目标单元格样式
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:53
     */
    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());
        // 边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        // 背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        // 数据格式
        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
        // toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());// 首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());// 旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());

    }

    /**
     * @Method: setMergedRegion
     * @Description: 设置合并单元格的值
     * @param sheet Sheet对象
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:55
     */
    public void setMergedRegion(Sheet sheet) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            // 获取合并单元格位置
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstRow = ca.getFirstRow();
            if (startReadPos - 1 > firstRow) {// 如果第一个合并单元格格式在正式数据的上面，则跳过。
                continue;
            }
            int lastRow = ca.getLastRow();
            int mergeRows = lastRow - firstRow;// 合并的行数
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            // 根据合并的单元格位置和大小，调整所有的数据行格式，
            for (int j = lastRow + 1; j <= sheet.getLastRowNum(); j++) {
                // 设定合并单元格
                sheet.addMergedRegion(new CellRangeAddress(j, j + mergeRows, firstColumn, lastColumn));
                j = j + mergeRows;// 跳过已合并的行
            }

        }
    }

    /**
     * @Method: out
     * @Description: 打印日志消息
     * @param msg 日志消息
     * @return void
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:57
     */
    private void out(String msg) {
        if (printMsg) {
            out(msg, true);
        }
    }

    /**
     * @Method: out
     * @Description: 打印日志消息
     * @param msg 日志消息
     * @param tr 换行
     * @return void (返回类型描述)
     * @throws
     * @author : lipeng
     * @CreateDate : 2017/3/10 18:58
     */
    private void out(String msg, boolean tr) {
        if (printMsg) {
            //System.out.println(">>"+msg + (tr ? "\n" : ""));
            _log.info(msg + (tr ? "\n" : ""));
        }
    }

    /**
     * @Method: copyCell
     * @Description: 拷贝单元格
     * @param srcCell 源单元格
     * @param distCell 目标单元格
     * @return void
     * @author : lipeng
     * @CreateDate : 2017/3/15 19:01
     */
    public static void copyCell(Cell srcCell, Cell distCell){
        distCell.setCellStyle(srcCell.getCellStyle());
        if(srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);

        if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                distCell.setCellValue(srcCell.getDateCellValue());
            } else {
                distCell.setCellValue(srcCell.getNumericCellValue());
            }
        } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
            distCell.setCellValue(srcCell.getRichStringCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
            //nothing
        } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
            distCell.setCellValue(srcCell.getBooleanCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
            distCell.setCellErrorValue(srcCell.getErrorCellValue());
        } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
            distCell.setCellFormula(srcCell.getCellFormula());
        } else {
            //nothing
        }
    }


    /**
     * @Method: copyRows
     * @Description: 拷贝行
     * @param st excelsheet对象
     * @param startRow 开始行
     * @param endRow 结束行
     * @param pPosition 位置
     * @return void
     * @author : lipeng
     * @CreateDate : 2017/3/15 19:02
     */
    public static void copyRows(Sheet st,int startRow,int endRow,int pPosition) {
        int pStartRow = startRow - 1;
        int pEndRow = endRow - 1;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region = null;
        int i;
        int j;

        if(pStartRow==-1||pEndRow ==-1) return;

        //merged cells
        for (i = 0; i < st.getNumMergedRegions(); i++) {
            region = st.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow)&& (region.getLastRow() <= pEndRow)) {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;

                CellRangeAddress newRegion = region.copy();

                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
                st.addMergedRegion(newRegion);
            }
        }
        //set the column height and value
        for (i = pStartRow; i <= pEndRow; i++) {
            Row sourceRow = st.getRow(i);
            columnCount = sourceRow.getLastCellNum();
            if(sourceRow != null) {
                Row newRow = st.createRow(pPosition+i);
                newRow.setHeight(sourceRow.getHeight());
                for(j=0;j<columnCount;j++) {
                    Cell templateCell = sourceRow.getCell(j);
                    if(templateCell != null) {
                        Cell newCell = newRow.createCell(j);
                        copyCell(templateCell,newCell);
                    }
                }
            }
        }
    }

    public String getExcelPath() {
        return this.excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public boolean isNeedCompare() {
        return isNeedCompare;
    }

    public void setNeedCompare(boolean isNeedCompare) {
        this.isNeedCompare = isNeedCompare;
    }

    public int getComparePos() {
        return comparePos;
    }

    public void setComparePos(int comparePos) {
        this.comparePos = comparePos;
    }

    public int getStartReadPos() {
        return startReadPos;
    }

    public void setStartReadPos(int startReadPos) {
        this.startReadPos = startReadPos;
    }

    public int getEndReadPos() {
        return endReadPos;
    }

    public void setEndReadPos(int endReadPos) {
        this.endReadPos = endReadPos;
    }

    public boolean isOverWrite() {
        return isOverWrite;
    }

    public void setOverWrite(boolean isOverWrite) {
        this.isOverWrite = isOverWrite;
    }

    public boolean isOnlyReadOneSheet() {
        return onlyReadOneSheet;
    }

    public void setOnlyReadOneSheet(boolean onlyReadOneSheet) {
        this.onlyReadOneSheet = onlyReadOneSheet;
    }

    public int getSelectedSheetIdx() {
        return selectedSheetIdx;
    }

    public void setSelectedSheetIdx(int selectedSheetIdx) {
        this.selectedSheetIdx = selectedSheetIdx;
    }

    public String getSelectedSheetName() {
        return selectedSheetName;
    }

    public void setSelectedSheetName(String selectedSheetName) {
        this.selectedSheetName = selectedSheetName;
    }

    public int getStartSheetIdx() {
        return startSheetIdx;
    }

    public void setStartSheetIdx(int startSheetIdx) {
        this.startSheetIdx = startSheetIdx;
    }

    public int getEndSheetIdx() {
        return endSheetIdx;
    }

    public void setEndSheetIdx(int endSheetIdx) {
        this.endSheetIdx = endSheetIdx;
    }

    public boolean isPrintMsg() {
        return printMsg;
    }

    public void setPrintMsg(boolean printMsg) {
        this.printMsg = printMsg;
    }

    /**
     * 日志对象
     */
    private static final Logger _log = LoggerFactory.getLogger(ExcelOperation.class);
}