package demo.entity;

/**
 * Created by hhh on 2017/4/8.
 */
public class Keyword implements Cloneable{
    /**
     * id
     */
    private String id;
    /**
     * 关键字名
     */
    private String name;
    /**
     * 行号
     */
    private int rowIndex;
    /**
     * 列号
     */
    private int colIndex;
    /**
     * 值 类型
     */
    private Type type ;
    /**
     * 是否重复
     */
    private boolean isRepeat ;
    /**
     * 重复行间隔
     */
    private int vectorRow ;
    /**
     * 重复列间隔
     */
    private int vectorCol ;
    /**
     * 值
     */
    private Object value ;
    /**
     * 是否可编辑
     */
    private boolean canModify ;
    /**
     * 分组标签
     */
    private String templateTag ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateTag() {
        return templateTag;
    }

    public void setTemplateTag(String templateTag) {
        this.templateTag = templateTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public int getVectorRow() {
        return vectorRow;
    }

    public void setVectorRow(int vectorRow) {
        this.vectorRow = vectorRow;
    }

    public int getVectorCol() {
        return vectorCol;
    }

    public void setVectorCol(int vectorCol) {
        this.vectorCol = vectorCol;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isCanModify() {
        return canModify;
    }

    public void setCanModify(boolean canModify) {
        this.canModify = canModify;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "name='" + name + '\'' +
                ", rowIndex=" + rowIndex +
                ", colIndex=" + colIndex +
                ", type=" + type +
                ", isRepeat=" + isRepeat +
                ", vectorRow=" + vectorRow +
                ", vectorCol=" + vectorCol +
                ", value=" + value +
                ", canModify=" + canModify +
                ", templateTag='" + templateTag + '\'' +
                '}';
    }
}
