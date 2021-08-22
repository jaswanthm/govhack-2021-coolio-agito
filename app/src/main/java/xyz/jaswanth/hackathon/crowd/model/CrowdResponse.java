package xyz.jaswanth.hackathon.crowd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrowdResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("transportType")
    @Expose
    private String transportType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("capacityLevel")
    @Expose
    private Integer capacityLevel;
    @SerializedName("capacityClass")
    @Expose
    private String capacityClass;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacityLevel() {
        return capacityLevel;
    }

    public void setCapacityLevel(Integer capacityLevel) {
        this.capacityLevel = capacityLevel;
    }

    public String getCapacityClass() {
        return capacityClass;
    }

    public void setCapacityClass(String capacityClass) {
        this.capacityClass = capacityClass;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CrowdResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("transportType");
        sb.append('=');
        sb.append(((this.transportType == null)?"<null>":this.transportType));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("label");
        sb.append('=');
        sb.append(((this.label == null)?"<null>":this.label));
        sb.append(',');
        sb.append("capacity");
        sb.append('=');
        sb.append(((this.capacity == null)?"<null>":this.capacity));
        sb.append(',');
        sb.append("capacityLevel");
        sb.append('=');
        sb.append(((this.capacityLevel == null)?"<null>":this.capacityLevel));
        sb.append(',');
        sb.append("capacityClass");
        sb.append('=');
        sb.append(((this.capacityClass == null)?"<null>":this.capacityClass));
        sb.append(',');
        sb.append("distance");
        sb.append('=');
        sb.append(((this.distance == null)?"<null>":this.distance));
        sb.append(',');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null)?"<null>":this.lat));
        sb.append(',');
        sb.append("lng");
        sb.append('=');
        sb.append(((this.lng == null)?"<null>":this.lng));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
