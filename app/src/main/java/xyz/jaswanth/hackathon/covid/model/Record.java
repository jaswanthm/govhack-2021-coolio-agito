
package xyz.jaswanth.hackathon.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("population")
    @Expose
    private String population;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("cases")
    @Expose
    private String cases;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("band")
    @Expose
    private String band;
    @SerializedName("data_date")
    @Expose
    private String dataDate;
    @SerializedName("file_processed_date")
    @Expose
    private String fileProcessedDate;
    @SerializedName("rank")
    @Expose
    private Double rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getFileProcessedDate() {
        return fileProcessedDate;
    }

    public void setFileProcessedDate(String fileProcessedDate) {
        this.fileProcessedDate = fileProcessedDate;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Record.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("postcode");
        sb.append('=');
        sb.append(((this.postcode == null)?"<null>":this.postcode));
        sb.append(',');
        sb.append("population");
        sb.append('=');
        sb.append(((this.population == null)?"<null>":this.population));
        sb.append(',');
        sb.append("active");
        sb.append('=');
        sb.append(((this.active == null)?"<null>":this.active));
        sb.append(',');
        sb.append("cases");
        sb.append('=');
        sb.append(((this.cases == null)?"<null>":this.cases));
        sb.append(',');
        sb.append("rate");
        sb.append('=');
        sb.append(((this.rate == null)?"<null>":this.rate));
        sb.append(',');
        sb.append("_new");
        sb.append('=');
        sb.append(((this._new == null)?"<null>":this._new));
        sb.append(',');
        sb.append("band");
        sb.append('=');
        sb.append(((this.band == null)?"<null>":this.band));
        sb.append(',');
        sb.append("dataDate");
        sb.append('=');
        sb.append(((this.dataDate == null)?"<null>":this.dataDate));
        sb.append(',');
        sb.append("fileProcessedDate");
        sb.append('=');
        sb.append(((this.fileProcessedDate == null)?"<null>":this.fileProcessedDate));
        sb.append(',');
        sb.append("rank");
        sb.append('=');
        sb.append(((this.rank == null)?"<null>":this.rank));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
