package demo.tohtml;

import demo.entity.Keyword;
import demo.entity.Template;
import demo.entity.TemplateOfQuarter;

import java.util.List;

/**
 * Created by hhh on 2017/4/13.
 */
public class ToHtml {

    public String templatesToTables (TemplateOfQuarter templateOfQuarter) {
        String tables = "";

        for (Template template :templateOfQuarter.getTemplates()) {
                tables += templateToTable(template);
            }

            return tables;
        }

    public String templateToTable(Template template){ //todo  支持传入自定义过滤器来判断 template 是否过滤

        String table = "<table><tr>";
        int rowIndex = 0;
        List<Keyword> keywords = template.getKeywords();

        rowIndex = keywords.get(0).getRowIndex();

        for (int i = 0; i < keywords.size(); i++) {
            Keyword keyword = keywords.get(i);
            if(keyword.getRowIndex() == rowIndex){
                table += keywordToHtmlTd(keyword);
            } else {
                table += "</tr><tr>";
                rowIndex = keyword.getRowIndex();
            }
        }
        if(table.endsWith("</td>")){
            table += "</tr>";
        }
        if(table.endsWith("<tr>")){
            table = table.substring(0,table.length()-4);
        }

        table += "</table>";

        return table;
    }


    public String keywordToHtmlTd (Keyword keyword){
        String html = "";

        html += "<td " ;

        html += "name=\"" + keyword.getName() + "\" ";

        html += ">";

        //判断是否可编辑
        if(keyword.isCanModify()){
            html += "<input value=\"" + keyword.getValue() + "\" />";
        }else{
            html += "<span>" + keyword.getValue() + "</span>";
        }
        html += "</td>";

        return html;
    }
}
