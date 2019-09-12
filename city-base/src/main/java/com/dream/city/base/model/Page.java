package com.dream.city.base.model;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 分页类
 */
@Data
public class Page<T>
{
    public static int DEFAULT_START = 1;//默认开始行
    
    public static int DEFAULT_LIMIT = 20;//默认每页显示行数
    
    public static int DEFAULT_PAGE = 1;//默认页
    
    private int start;//开始行
    
    private int pageSize;//每页显示纪录数
    
    private int pageNo;//当前页
    
	private int totalCount;//总纪录数
    
    private Object condition;//查询条件
    
	private List<T> result = Collections.emptyList();//查询结果
    
    @SuppressWarnings("static-access")
    public Page()
    {
        this.start = DEFAULT_START;//默认开始行
        this.pageSize = DEFAULT_LIMIT;//默认每页显示行数
        this.pageNo = DEFAULT_PAGE;//默认页
    }
    
    public Page(int start,int pageSize)
    {
        this.start = start;
        this.pageSize = pageSize;
    }

    public List<T> getResult()
    {
        return result;
    }
    
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setResult(List<T> result)
    {
        this.result = result;
    }
    
    public Object getCondition() {
		return condition;
	}

	public void setCondition(Object condition) {
		this.condition = condition;
	}

    public int getStart()
    {
    	//this.start = this.pageSize * (this.pageNo - 1) + 1;
    	this.start = this.pageSize * (this.pageNo - 1);
        return this.start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }


    public static int getDefaultStart() {
        return DEFAULT_START;
    }

    public static void setDefaultStart(int defaultStart) {
        DEFAULT_START = defaultStart;
    }

    public static int getDefaultLimit() {
        return DEFAULT_LIMIT;
    }

    public static void setDefaultLimit(int defaultLimit) {
        DEFAULT_LIMIT = defaultLimit;
    }

    public static int getDefaultPage() {
        return DEFAULT_PAGE;
    }

    public static void setDefaultPage(int defaultPage) {
        DEFAULT_PAGE = defaultPage;
    }

    public int getTotalCount() {
        if (!CollectionUtils.isEmpty(result)){
            this.totalCount = result.size();
        }
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", totalCount=" + totalCount +
                ", condition=" + condition +
                ", result=" + result +
                '}';
    }


}
