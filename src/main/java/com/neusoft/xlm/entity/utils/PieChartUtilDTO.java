package com.neusoft.xlm.entity.utils;

import java.util.List;

public class PieChartUtilDTO {
    private List<Double> rows;
    private List<Integer> cols;

    public List<Double> getRows() {
        return rows;
    }

    public void setRows(List<Double> rows) {
        this.rows = rows;
    }

    public List<Integer> getCols() {
        return cols;
    }

    public void setCols(List<Integer> cols) {
        this.cols = cols;
    }
}
