package demo.dao;

import demo.entity.Keyword;
import demo.entity.TemplateOfQuarter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hhh on 2017/4/12.
 */
public interface KeywordDAO {
    boolean save(TemplateOfQuarter templateOfQuarter) throws SQLException;

    TemplateOfQuarter searchTemplateOfQuarterByTime (String year , String quarter) throws SQLException;

    List<Keyword> searchAllKeyword();
}
