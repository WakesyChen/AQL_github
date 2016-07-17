package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/17.
 */
public class ColumnInfo {

    public long columnid;
    public long pcolumnid;
    public String image;
    public String columnname;
    public long sort;

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnid=" + columnid +
                ", pcolumnid=" + pcolumnid +
                ", image=" + image +
                ", columnname='" + columnname + '\'' +
                ", sort=" + sort +
                '}';
    }
}
