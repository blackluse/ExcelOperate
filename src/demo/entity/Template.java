package demo.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hhh on 2017/4/9.
 */
public class Template {
    private String templateTag;

    private List<Keyword> keywords = null;

    private Map<String,Integer> indexMap = null;

    public Template() {
    }

    public Template(List<Keyword> keywords) {
        this.keywords = keywords;

        this.indexMap = new HashMap<String,Integer>();
        templateTag = UUID.randomUUID().toString();
        for (int i = 0; i < keywords.size() ; i ++ ) {
            if("".equals(keywords.get(i).getName())){
                throw new RuntimeException("错误：list中第" + i + "个关键字缺少唯一标识！");
            }
            keywords.get(i).setTemplateTag(templateTag);
            this.indexMap.put(keywords.get(i).getName(),i);
        }

    }

    public Keyword getKeyWordByName (String name) {
        Integer index = this.indexMap.get(name);
        return this.keywords.get(index);
    }

    public String getTemplateTag() {
        return templateTag;
    }

    public void setTemplateTag(String templateTag) {
        this.templateTag = templateTag;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateTag='" + templateTag + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
