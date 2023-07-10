package com.neusoft.xlm.entity.year;

import java.util.List;

public class MovieRateByYearDTO {
    public List<Double> rows;
    public List<String> cols;

    public List<Double> getRows() {
        return rows;
    }

    public void setRows(List<Double> rows) {
        this.rows = rows;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }
}
