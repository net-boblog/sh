package com.qccr.sh.page;

import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/4.
 */
public class Pagination<T> {

    private int totalCount; // 总行数
    private int pageSize; // 每页行数，默认15
    private int currentPage; // 当前页数
    private List<T> dataList;
    private T data;

    public Pagination(int pageSize,int currentPage, int totalCount) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
    }

    // 设置总行数
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    // 得到总行数
    public int getTotalCount() {
        return totalCount;
    }

    // 得到总页数
    public int getPages() {
        if (this.totalCount % pageSize == 0)
            return this.totalCount / pageSize;
        else
            return this.totalCount / pageSize + 1;
    }

    // 得到当前页数
    public int getCurrentPage() {
        return currentPage;
    }

    // 设置当前页数
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
