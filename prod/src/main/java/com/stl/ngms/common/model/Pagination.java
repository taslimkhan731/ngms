package com.stl.ngms.common.model;

import com.stl.ngms.utils.Constants;

import java.util.List;

public class Pagination {
    private int page;
    private int length;
    private int totalElements;
    private int totalPages;
    private List<?> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalElements) {
        this.totalElements = totalElements;
        this.length = Constants.LIMIT;
        totalPages = (int) Math.ceil((float)totalElements / (float)length);
    }
    public void setTotalPages(int totalElements,int limit) {
        this.totalElements = totalElements;
        this.length = limit;
        totalPages = (int) Math.ceil((float)totalElements / (float)length);
    }
    public int getTotalPages() {
        return totalPages;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
