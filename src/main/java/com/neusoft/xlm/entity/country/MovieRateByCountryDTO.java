package com.neusoft.xlm.entity.country;

import java.util.List;

public class MovieRateByCountryDTO {
    private List<String> rows;
    private List<Double> cols;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<Double> getCols() {
        return cols;
    }

    public void setCols(List<Double> cols) {
        this.cols = cols;
    }
}
