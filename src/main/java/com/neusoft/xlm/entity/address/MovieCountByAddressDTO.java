package com.neusoft.xlm.entity.address;

import java.util.List;

public class MovieCountByAddressDTO {
    public List<Integer> rows;
    public List<String> cols;

    public List<Integer> getRows() {
        return rows;
    }

    public void setRows(List<Integer> rows) {
        this.rows = rows;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }
}
