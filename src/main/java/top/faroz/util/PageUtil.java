package top.faroz.util;

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
    private Integer total=0;


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
     * 获取页码数
     * @return
     */
    public Integer getTotalPages() {
        if (total>0) {
            return total % 5 > 0 ? total / 5 + 1 : total / 5;
        }
        return 0;
    }
}
