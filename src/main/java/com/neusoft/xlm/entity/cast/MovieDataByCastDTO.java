package com.neusoft.xlm.entity.cast;

import java.util.List;

public class MovieDataByCastDTO {
    public List<String> rows;
    public List<Integer> cols;

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
