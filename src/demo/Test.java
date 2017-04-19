package demo;

import demo.dao.DAOFactory;
import demo.dao.KeywordDAO;
import demo.entity.Keyword;
import demo.entity.Template;
import demo.entity.TemplateOfQuarter;
import demo.entity.Type;
import demo.tohtml.ToHtml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhh on 2017/4/13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\work\\j吉林移动\\工单\\重点工作考核模板.xlsx";

        List<Keyword> keywordsOfQuarter = new ArrayList<>();
        Keyword yearValue = new Keyword();
        yearValue.setName("yearValue");
        yearValue.setRowIndex(0);
        yearValue.setColIndex(1);
        yearValue.setType(Type.String);
        yearValue.setRepeat(false);

        keywordsOfQuarter.add(yearValue);

        Keyword quarterValue = new Keyword();
        quarterValue.setName("quarterValue");
        quarterValue.setRowIndex(0);
        quarterValue.setColIndex(3);
        quarterValue.setType(Type.String);
        quarterValue.setRepeat(false);

        keywordsOfQuarter.add(quarterValue);

        List<Keyword> keywords = new ArrayList<Keyword>();
        Keyword keyword = new Keyword();
        keyword.setName("subject");
        keyword.setRowIndex(1);
        keyword.setColIndex(0);
        keyword.setType(Type.String);
        keyword.setRepeat(true);
        keyword.setVectorRow(13);
        keyword.setVectorCol(0);

        keywords.add(keyword);

        Keyword keyword2 = new Keyword();
        keyword2.setName("subjectValue");
        keyword2.setRowIndex(1);
        keyword2.setColIndex(1);
        keyword2.setType(Type.String);
        keyword2.setRepeat(true);
        keyword2.setVectorRow(13);
        keyword2.setVectorCol(0);

        keywords.add(keyword2);

        Template template = new Template(keywords);

        ImportExcelTemplate importExcelTemplate = new ImportExcelTemplate();

        TemplateOfQuarter templateOfQuarter = importExcelTemplate.readExcelFile(filePath,keywordsOfQuarter,template);

        KeywordDAO keywordDAO = DAOFactory.getInstance();
//        keywordDAO.save(templateOfQuarter);

        TemplateOfQuarter templateOfQuarterE = keywordDAO.searchTemplateOfQuarterByTime("2016","4");

        System.out.println("read:" + templateOfQuarterE);

        ToHtml toHtml = new ToHtml();

        System.out.println(toHtml.templatesToTables(templateOfQuarterE));

    }
}
