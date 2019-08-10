package design.maliang.hive.util;

import java.util.List;
import java.util.Map;

public class HiveTableEntry {

    private boolean isExternal;
    private boolean probe;
    private Map<String,Object> fields;
    private Map<String,Object> partitions;
    private List<String> clusters;
    private Map<String,Boolean> sort;
    private int numBuckets;
    private String rowFormat;
    private String fileFormat;
    private String hdfsPath;

    public HiveTableEntry() {
    }

    public HiveTableEntry(Map<String, Object> fields) {
        this.fields = fields;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public boolean isProbe() {
        return probe;
    }

    public void setProbe(boolean probe) {
        this.probe = probe;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public Map<String, Object> getPartitions() {
        return partitions;
    }

    public void setPartitions(Map<String, Object> partitions) {
        this.partitions = partitions;
    }

    public List<String> getClusters() {
        return clusters;
    }

    public void setClusters(List<String> clusters) {
        this.clusters = clusters;
    }

    public Map<String, Boolean> getSort() {
        return sort;
    }

    public void setSort(Map<String, Boolean> sort) {
        this.sort = sort;
    }

    public int getNumBuckets() {
        return numBuckets;
    }

    public void setNumBuckets(int numBuckets) {
        this.numBuckets = numBuckets;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getHdfsPath() {
        return hdfsPath;
    }

    public void setHdfsPath(String hdfsPath) {
        this.hdfsPath = hdfsPath;
    }
}
