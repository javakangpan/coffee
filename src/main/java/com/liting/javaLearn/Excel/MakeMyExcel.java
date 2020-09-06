package com.liting.javaLearn.Excel;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * 制作自己的excel
 *
 * @author 康盼Java开发工程师
 * @since 2020年09月04日
 */
@Slf4j
public class MakeMyExcel {

    private String excelName;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private List<String> headData;
    private List<ExcelDataVO> data;
    private int begin;

    public static void main(String[] args) throws IOException{
        MakeMyExcel makeMyExcel = new MakeMyExcel();
        makeMyExcel.test();
    }

    public void test() throws IOException {
        init("测试.xml","sheet1",0,null,null);
        createHeads();
        createColunms();
        String fileName = "../test.xls";
        try(OutputStream outputStream = new FileOutputStream(fileName)){
            workbook.write(outputStream);
        }
    }



    /**
     * 初始化
     *
     * @param excelName 导出的文件名
     * @param firstSheetName 第一个sheet页名
     */
    public void init(String excelName, String firstSheetName, int begin, List<ExcelDataVO> data, List<String> headData) {
        this.begin = begin;
        this.excelName = excelName;
        this.workbook = new HSSFWorkbook();
        createSheet(firstSheetName);
        if(CollectionUtils.isEmpty(headData)) {
            headData = defaultHead();
        }
        this.headData = headData;
        if(CollectionUtils.isEmpty(data)) {
            data = testData();
        }
        this.data = data;
    }

    private List<ExcelDataVO> testData() {
        List<ExcelDataVO> testData = Lists.newArrayList();
        List<String> cons = Lists.newArrayList("手机","微信","QQ");
        List<String> nums = Lists.newArrayList("13728897992","13728897992","997241010");
        ExcelDataVO excelDataVO = ExcelDataVO.builder().name("康盼").age("30").sex("男").cons(cons).nums(nums).remark("架构师").build();
        testData.add(excelDataVO);
        return testData;
    }

    private List<String> defaultHead() {
        return Lists.newArrayList("姓名","性别","年龄","联系方式","号码","备注");
    }

    /**
     * 创建sheet页
     *
     * @param name sheet页名称
     */
    public void createSheet(String name) {
        this.sheet = workbook.createSheet(name);
    }

    /**
     * 创建单元格
     * @param row 第几行
     * @param num 第几个单元格
     * @param value 单元格的值
     * @return
     */
    public HSSFCell createCell(HSSFRow row, int num, String value) {
        HSSFCell cell = row.createCell(num);
        cell.setCellValue(value);
        return cell;
    }

    /**
     * 创建单元格
     * @param row 第几行
     * @param num 第几个单元格
     * @param value 单元格的值
     * @param cellStyle 单元格样式
     * @return
     */
    public HSSFCell createCell(HSSFRow row, int num, String value, HSSFCellStyle cellStyle) {
        HSSFCell cell = createCell(row, num, value);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    /**
     * 设置列头样式
     *
     * @param cellStyle
     */
    public void setStyle(HSSFCellStyle cellStyle) {
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.PALE_BLUE.getIndex());
        commonStyle(cellStyle);
    }

    /**
     * 通用样式
     *
     * @param cellStyle
     */
    public void commonStyle(HSSFCellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
    }

    /**
     * 字体
     *
     * @param cellStyle
     * @param color 字体颜色
     * @param fontName 字体名称
     * @param isBold 是否粗体
     */
    public void setFont(HSSFCellStyle cellStyle, short color, String fontName, boolean isBold) {
        HSSFFont font = workbook.createFont();
        font.setColor(color);
        font.setFontName(fontName);
        font.setBold(isBold);
        cellStyle.setFont(font);
    }

    // 创建列头
    private void createHeads() {
        HSSFRow head = sheet.createRow(begin);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        setFont(cellStyle,HSSFColor.HSSFColorPredefined.WHITE.getIndex(),"微软雅黑",true);
        setStyle(cellStyle);
        IntStream.range(0, headData.size()).forEach(n -> createCell(head, n, headData.get(n), cellStyle));
    }

    // 创建数据列
    private void createColunms() {
        int rowSize = data.size();
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        setFont(cellStyle,HSSFColor.HSSFColorPredefined.BLACK.getIndex(),"微软雅黑",false);
        commonStyle(cellStyle);
        IntStream.range(0, rowSize).forEach(n -> {
            int meBegin = n + begin + 1;
            HSSFRow row = sheet.createRow(meBegin);
            createCell(row,0, data.get(n).getName(),cellStyle);
            createCell(row,1, data.get(n).getSex(),cellStyle);
            createCell(row,2, data.get(n).getAge(),cellStyle);
            createDropDownCell(meBegin, data.get(n).getCons().toArray(new String[3]), dataValidationHelper);
            createAutoSetValueCell(row,meBegin + 1, data.get(n).getCons(), data.get(n).getNums());
            createCell(row,5, data.get(n).getRemark(),cellStyle);
        });
    }

    private void createDropDownCell(int r, String[] value,DataValidationHelper dataValidationHelper) {
        log.info("createDropDownCell : {} {} {} {}", value[0],value[1],value[2],r);
        DataValidationConstraint dataValidationConstraint = dataValidationHelper.createExplicitListConstraint(value);
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(r, r, 3,3);
        DataValidation dataValidation = dataValidationHelper.createValidation(dataValidationConstraint,cellRangeAddressList);
        sheet.addValidationData(dataValidation);
    }

    private void createAutoSetValueCell(HSSFRow row,int meBegin,List<String> cons, List<String> nums) {
        HSSFCell cell = row.createCell(4);
        cell.setCellFormula(getFx(meBegin, cons, nums));
    }

    private String getFx(int meBegin,List<String> cons, List<String> nums) {
        // IF(H14="A10",10,IF(H14="B20",20,IF(H14="C30",30)))
        // IF(H14="A10",10,IF(H14="B20",20))
        String str = "IF(D" + meBegin + "=\"%s\",\"%s\"";
        StringBuilder result = new StringBuilder();
        int total = cons.size();
        StringBuilder close = new StringBuilder();
        IntStream.range(0, total).forEach(n -> close.append(")"));
        IntStream.range(0, total).forEach(n -> {
            String s = String.format(Locale.ROOT,str,cons.get(n),nums.get(n));
            if(n != (total - 1)) {
                result.append(s).append(",");
            } else {
                result.append(s).append(close);
            }
        });
        log.info("fx: {}" ,result.toString());
        return result.toString();
    }
}
