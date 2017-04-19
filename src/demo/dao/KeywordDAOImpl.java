package demo.dao;

import demo.entity.Keyword;
import demo.entity.Template;
import demo.entity.TemplateOfQuarter;
import demo.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hhh on 2017/4/12.
 */
public class KeywordDAOImpl implements KeywordDAO {

    private Connection connection;

    public KeywordDAOImpl (Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(TemplateOfQuarter templateOfQuarter) throws SQLException {

        String insertTemplateQuarterSql = "INSERT INTO template_quarter(id,year,quarter,template_tag) VALUES (?,?,?,?) ";
        String insertKeywordSql = "INSERT INTO keyword (id , template_tag , name , row_index , col_index , type , is_repeat , vector_row , vector_col , value , modify ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        connection.setAutoCommit(false);
        try {

        /*
            template_quarter插入
         */
            PreparedStatement templateStatement = connection.prepareStatement(insertTemplateQuarterSql);
            PreparedStatement keywordStatement = connection.prepareStatement(insertKeywordSql);

            int templateStatementResult = 1;
            int keywordStatementResult = 1;

            for (Template template : templateOfQuarter.getTemplates()) {
//                String id = templateOfQuarter.getId() == null ? UUID.randomUUID().toString():templateOfQuarter.getId();
                templateStatement.setString(1, UUID.randomUUID().toString());
                templateStatement.setString(2, templateOfQuarter.getYear());
                templateStatement.setString(3, templateOfQuarter.getQuarter());
                templateStatement.setString(4, template.getTemplateTag());
                templateStatementResult = templateStatement.executeUpdate() * templateStatementResult;

                for (Keyword keyword : template.getKeywords()) {
                    keywordStatement.setString(1, keyword.getId() == null ? UUID.randomUUID().toString() : keyword.getId());
                    keywordStatement.setString(2, keyword.getTemplateTag());
                    keywordStatement.setString(3, keyword.getName());
                    keywordStatement.setInt(4, keyword.getRowIndex());
                    keywordStatement.setInt(5, keyword.getColIndex());
                    keywordStatement.setString(6, keyword.getType().toString());
                    keywordStatement.setString(7, String.valueOf(keyword.isRepeat()));
                    keywordStatement.setInt(8, keyword.getVectorRow());
                    keywordStatement.setInt(9, keyword.getVectorCol());
                    keywordStatement.setString(10, String.valueOf(keyword.getValue()));
                    keywordStatement.setString(11, String.valueOf(keyword.isCanModify()));
//                    keywordStatement.setString(12, id);

                    keywordStatementResult = keywordStatement.executeUpdate() * keywordStatementResult;
                }

            }

            if (templateStatementResult == 1 && keywordStatementResult == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (Exception e){
            connection.rollback();
//            e.printStackTrace();
            throw e;
        }
//        return false;
    }

    @Override
    public TemplateOfQuarter searchTemplateOfQuarterByTime(String year, String quarter) throws SQLException {

        TemplateOfQuarter templateOfQuarter = null;

        String selectTemplates = "SELECT template_tag FROM template_quarter WHERE year = ? AND quarter = ?";
        String selectKeywords = "SELECT * FROM keyword WHERE template_tag = ? ORDER BY row_index , col_index";

        PreparedStatement templateStatement = connection.prepareStatement(selectTemplates);
        PreparedStatement keywordStatement = connection.prepareStatement(selectKeywords);

        templateStatement.setString(1,year);
        templateStatement.setString(2,quarter);

        ResultSet resultSetOfTemplates = templateStatement.executeQuery();

        if(resultSetOfTemplates.first()){
            System.out.println(resultSetOfTemplates.getString("template_tag"));
            templateOfQuarter = new TemplateOfQuarter();
            List<Template> templates = new ArrayList<>();

            templateOfQuarter.setYear(year);
            templateOfQuarter.setQuarter(quarter);

            do{
                //根据template_tag 查询 同模板的 keyword
                keywordStatement.setString(1,resultSetOfTemplates.getString("template_tag"));

                System.out.println(resultSetOfTemplates.getString("template_tag"));

                ResultSet resultSetOfKeywords = keywordStatement.executeQuery();
                if(resultSetOfKeywords.first()){
                    Template template = new Template();
                    List<Keyword> keywords = new ArrayList<>();

                    do{
                        Keyword keyword = new Keyword();
                        keyword.setName(resultSetOfKeywords.getString("name"));
                        keyword.setRowIndex(resultSetOfKeywords.getInt("row_index"));
                        keyword.setColIndex(resultSetOfKeywords.getInt("col_index"));
                        keyword.setType(Type.toType(resultSetOfKeywords.getString("type")));
                        keyword.setValue(resultSetOfKeywords.getObject("value"));
                        keyword.setCanModify(resultSetOfKeywords.getBoolean("modify"));

                        keywords.add(keyword);
                    }while (resultSetOfKeywords.next());

                    template.setKeywords(keywords);

                    templates.add(template);
                }

            }while (resultSetOfTemplates.next());

            templateOfQuarter.setTemplates(templates);
        }

        return templateOfQuarter;
    }

    @Override
    public List<Keyword> searchAllKeyword() {
        //todo
        return null;
    }
}
