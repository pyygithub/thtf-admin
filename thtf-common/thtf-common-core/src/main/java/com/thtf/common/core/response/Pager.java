package com.thtf.common.core.response;

import java.util.List;

/**
 * ---------------------------
 * 分页查询结果
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 13:59
 * 版本：  v1.0
 * ---------------------------
 */
public class Pager<T> {

    /**
     * 数据总数
     */
    private long total;

    /**
     * 数据列表
     */
    private List<T> records;

    public Pager() {
    }

    public Pager(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
