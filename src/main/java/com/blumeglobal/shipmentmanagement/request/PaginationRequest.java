package com.blumeglobal.shipmentmanagement.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationRequest implements Pageable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int limit;
    private int offset;
    private Sort sort;
    
    public PaginationRequest(int offset,int limit,Sort sort) {
    	this.offset = offset;
    	this.limit = limit;        
        this.sort = sort;
    }
    
    public PaginationRequest(int offset,int limit) {
    	this.offset = offset;
    	this.limit = limit; 
    }
    
    @Override
    public int getPageNumber() {
        return (offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
    	return new PaginationRequest((int) (getOffset() + getPageSize()), getPageSize(), getSort());
    }

    public PaginationRequest  previous() {
        return hasPrevious() ? new PaginationRequest ( (int) (getOffset() - getPageSize()), getPageSize(), getSort()) : this;	
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new PaginationRequest (0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PaginationRequest)) return false;

        PaginationRequest  that = (PaginationRequest) o;

        return new EqualsBuilder()
                .append(limit, that.limit)
                .append(offset, that.offset)
                .append(sort, that.sort)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(limit)
                .append(offset)
                .append(sort)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("limit", limit)
                .append("offset", offset)
                .append("sort", sort)
                .toString();
    }

}
