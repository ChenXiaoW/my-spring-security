package ink.cwblog.demo1.http;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author daihaoran
 */
@Data
public class ListQuery {
    /**
     * 每页大小
     */
    @NotNull
    protected Integer pageSize;

    /**
     * 当前页
     */
    @NotNull
    protected Integer pageNum;

    /**
     * 过滤类型,从0开始
     */
    protected Integer filterType;

    /**
     * 过滤值
     */
    protected String filterValue;

    /**
     * 排序值
     */
    protected Integer sortType;

    /**
     * 排序方式，0-升序，1-降序(默认)
     */
    protected Integer sortMode = 1;
}
