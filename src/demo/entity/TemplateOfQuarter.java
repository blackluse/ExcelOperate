package demo.entity;

import java.util.List;

/**
 * Created by hhh on 2017/4/12.
 */
public class TemplateOfQuarter {
    private String id;

    private String year;

    private String quarter;

    private List<Template> templates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "TemplateOfQuarter{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", quarter='" + quarter + '\'' +
                ", templates=" + templates +
                '}';
    }
}
