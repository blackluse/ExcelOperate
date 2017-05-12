package demo;

import demo.entity.Keyword;
import demo.entity.Template;
import demo.entity.TemplateOfQuarter;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhh on 2017/4/8.
 */
/*
    1、关键字配置信息
        根据此配置确定 读取excel内容 最小模板组合

    2、读取内容
        读取内容时 根据 文件内容 创建 内容数据 组成的 实际数据模板分组

 */
public class ImportExcelTemplate {

    private List<Template> templateList = new ArrayList<>();


    public TemplateOfQuarter readExcelFile(String filePath,List<Keyword> keywordsOfQuarter, Template template){
        TemplateOfQuarter templateOfQuarter = null;
        int defaultSheetIndex = 0; //默认上传的模板工作簿为 0

        File file = new File(filePath);
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(defaultSheetIndex);

            templateOfQuarter = readSheetValueToTemplateOfQuarter(sheet,keywordsOfQuarter,template);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return templateOfQuarter;
    }

    public TemplateOfQuarter readSheetValueToTemplateOfQuarter (Sheet sheet , List<Keyword> keywordsOfQuarter , Template template){
        TemplateOfQuarter templateOfQuarter = new TemplateOfQuarter();

        for (Keyword keyword :keywordsOfQuarter) {
            getValueFromSheetByKeyword(sheet,keyword);
            if("yearValue".equals(keyword.getName())){
                templateOfQuarter.setYear(String.valueOf(keyword.getValue()));
            }else if("quarterValue".equals(keyword.getName())){
                templateOfQuarter.setQuarter(String.valueOf(keyword.getValue()));
            }
        }

        List<Template> templateList = readSheetValueToTemplates(sheet,template);

        templateOfQuarter.setTemplates(templateList);

        return templateOfQuarter;
    }

    public List<Template> readSheetValueToTemplates(Sheet sheet , Template template) {
//        List<Template> templateList = new ArrayList<Template>();
        if(template == null){
            return templateList;
        }
        //跳出条件
        Row row = sheet.getRow(template.getKeywords().get(0).getRowIndex());
        if(row == null){
            return templateList;
        }else if(row.getCell(template.getKeywords().get(0).getColIndex()) == null) {
            return templateList;
        }

        readExcelValueByTemplate(sheet,template);

        templateList.add(template);

        Template newTemplate = createNewTemplateByVector(template);

        readSheetValueToTemplates(sheet,newTemplate);

        return templateList;
    }

    /**
     * 根据 模板 读取内容
     * @param sheet
     * @param template
     * @return
     */
    public Template readExcelValueByTemplate (Sheet sheet , Template template) {
        for (Keyword keyword :template.getKeywords()) {
            getValueFromSheetByKeyword(sheet, keyword);
        }
        return template;
    }

    /**
     * 根据关键字配置的 位移矢量 计算获得新的 空模板
     * @param template
     * @return
     */
    public Template createNewTemplateByVector (Template template) {

        List<Keyword> keywords = new ArrayList<>();
        for (Keyword keyword :template.getKeywords()) {
            Keyword newKeyword = new Keyword();

            if(!keyword.isRepeat()){
                continue;
            }

            newKeyword.setName(keyword.getName());
            newKeyword.setVectorRow(keyword.getVectorRow());
            newKeyword.setVectorCol(keyword.getVectorCol());
            newKeyword.setCanModify(keyword.isCanModify());
            newKeyword.setRepeat(keyword.isRepeat());
            newKeyword.setType(keyword.getType());

            newKeyword.setRowIndex(keyword.getRowIndex() + keyword.getVectorRow());
            newKeyword.setColIndex(keyword.getColIndex() + keyword.getVectorCol());
            keywords.add(newKeyword);
        }
        if(keywords.size() == 0) {
            return null;
        } else {
            Template newTemplate = new Template(keywords);
            return newTemplate;
        }
    }

    public Keyword getValueFromSheetByKeyword(Sheet sheet, Keyword keyword){
        Row row = sheet.getRow(keyword.getRowIndex());
        Cell cell = row.getCell(keyword.getColIndex());
        Object value = null;
        //根据配合的数据类型取cell中的值]
        switch (keyword.getType()){
            case String:
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
                break;
            case Numeric:
                cell.setCellType(CellType.NUMERIC);
                value = cell.getNumericCellValue();
                break;
            case Date:
                cell.setCellType(CellType.STRING); //todo  暂时用String 读取日期
                value = cell.getDateCellValue();
                break;
            default:
                value = cell.getStringCellValue();
                break;
        }
        keyword.setValue(value);
        return keyword;
    }
}
