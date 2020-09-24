package com.mic.generator.frame.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 分页Bean
 * User: admin
 * Date: 13-5-9
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -3284694395882955584L;

    public static final int DEFAULT_PAGESIZE = 10;

    /**
     * 当前页
     */
    private final int page;

    /**
     * 每页多少项
     */
    private final int pageSize;

    /**
     * 总项数
     */
    private final int itemCount;

    /**
     * 总页数
     */
    private final int pageCount;

    /**
     * 排序参数
     */
    private LinkedHashMap<String, String> sortItemMap;

    /**
     * 数据列表
     */
    private List<T> underly;

    public LinkedHashMap<String, String> getSortItemMap() {
        return sortItemMap;
    }

    public void setSortItemMap(LinkedHashMap<String, String> sortItemMap) {
        this.sortItemMap = sortItemMap;
    }

    public PageBean() {
        this(1, 0, DEFAULT_PAGESIZE);
    }

    public PageBean(int page) {
        this(page, 0, DEFAULT_PAGESIZE);
    }

    public PageBean(int page, int pageSize) {
        this(page, 0, pageSize);
    }

    public PageBean(int page, int itemCount, int pageSize) {
        if (page <= 0) {
            page = 1;
        }
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGESIZE;
        }
        if (itemCount < 0) {
            itemCount = 0;
        }
        this.page = page;
        this.pageSize = pageSize;
        this.itemCount = itemCount;
        this.pageCount = (int) Math.ceil(((double) itemCount / (double) pageSize));
        this.underly = new ArrayList<T>(pageSize);
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getStartIndex() {
        return (page - 1) * pageSize + 1;
    }

    public int getEndIndex() {
        int end = page * pageSize;
        if (end > itemCount) {
            end = itemCount;
        }
        return end;
    }

    public List<T> getUnderly() {
        return underly;
    }

    public void setUnderly(List<T> underly) {
        this.underly = underly;
    }

    public boolean addAll(Collection<? extends T> c) {
        return underly.addAll(c);
    }
}
