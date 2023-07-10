package com.neusoft.xlm.entity.year;

import java.util.List;

public class MovieCountByYearDTO {
    private List<String> rows;
    private List<Integer> cols;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<Integer> getCols() {
        return cols;
    }

    public void setCols(List<Integer> cols) {
        this.cols = cols;
    }
}
