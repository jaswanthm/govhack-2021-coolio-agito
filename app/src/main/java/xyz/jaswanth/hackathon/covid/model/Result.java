
package xyz.jaswanth.hackathon.covid.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("include_total")
    @Expose
    private Boolean includeTotal;
    @SerializedName("resource_id")
    @Expose
    private String resourceId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;
    @SerializedName("records_format")
    @Expose
    private String recordsFormat;
    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Boolean getIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(Boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getRecordsFormat() {
        return recordsFormat;
    }

    public void setRecordsFormat(String recordsFormat) {
        this.recordsFormat = recordsFormat;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("includeTotal");
        sb.append('=');
        sb.append(((this.includeTotal == null)?"<null>":this.includeTotal));
        sb.append(',');
        sb.append("resourceId");
        sb.append('=');
        sb.append(((this.resourceId == null)?"<null>":this.resourceId));
        sb.append(',');
        sb.append("fields");
        sb.append('=');
        sb.append(((this.fields == null)?"<null>":this.fields));
        sb.append(',');
        sb.append("recordsFormat");
        sb.append('=');
        sb.append(((this.recordsFormat == null)?"<null>":this.recordsFormat));
        sb.append(',');
        sb.append("q");
        sb.append('=');
        sb.append(((this.q == null)?"<null>":this.q));
        sb.append(',');
        sb.append("records");
        sb.append('=');
        sb.append(((this.records == null)?"<null>":this.records));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("total");
        sb.append('=');
        sb.append(((this.total == null)?"<null>":this.total));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
