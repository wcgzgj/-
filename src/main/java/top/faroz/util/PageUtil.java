package top.faroz.util;

import java.util.List;

/**
 * @ClassName PageUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/19 上午11:16
 * @Version 1.0
 **/
public class PageUtil {
    private Integer pageIndex=1;
    //我们每页的条数，一般都设置为5
    private Integer pageSize=5;
    //总条数
    private Integer total=0;
    //页码数
    private Integer totalPages;
    //查询结果列表
    private List dataList;


    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 因为 el 表达式，获取的是 get方法，所以，这里会去计算
     * @return
     */
    public Integer getTotalPages() {
        if (total>0) {
            this.totalPages = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
            return this.totalPages;
        }
        return 0;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
}
