package com.qccr.sh.page;

/**
 * Created by xianchao.yan on 2015/11/4.
 */
public class PageQuery<T> {

    private static final int DEFAULT_PAGE_SIZE = 12;

    private static final int DEFAULT_CURRENT_PAGE = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;//每页记录数

    private int currentPage = DEFAULT_CURRENT_PAGE;//查哪一页

    private T param;

    public PageQuery(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageQuery(int currentPage, T param) {
        this.currentPage = currentPage;
        this.setParam(param);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getStart() {
        return getPageSize() * (currentPage - 1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
