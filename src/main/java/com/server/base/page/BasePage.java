package com.server.base.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据传输对象基类
 * 这个类是一个通用的分页数据传输对象，用于封装分页查询的结果。
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/2/19 17:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePage<T> {
    /**
     * 总记录数
     * 表示查询结果的总记录数。
     */
    private long total;

    /**
     * 数据列表
     * 表示当前页的数据列表。
     */
    private List<T> data;

    /**
     * 当前页码
     * 表示当前请求的页码。
     */
    private Long pageNum;

    /**
     * 每页记录数
     * 表示每页显示的记录数。
     */
    private Long pageSize;

    /**
     * 构造函数
     * 使用 IPage 对象初始化 BasePage 的属性。
     *
     * @param page IPage 对象，包含分页信息。
     */
    public BasePage(IPage<T> page) {
        this.total = page.getTotal();
        this.data = page.getRecords();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }

    public BasePage(PageInfo<T> page) {
        this.total = page.getTotal();
        this.data = page.getList();
        this.pageNum = (long) page.getPageNum();
        this.pageSize = (long) page.getSize();
    }
}
