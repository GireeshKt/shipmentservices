package com.blumeglobal.shipmentmanagement.service.helper;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.blumeglobal.shipmentmanagement.request.PaginationRequest;

@Component
public class PaginationHelper {

	public Pageable createPaginationRequest(int startRow, int endRow, List<String> sortBy)
			throws Exception {
		int pagesize=(endRow - startRow)+1;
		int offset =  startRow;
		Pageable pageable = null;
		List<String> sortByProperties = sortBy;
		if(sortBy==null || sortBy.isEmpty()) {
			pageable = new PaginationRequest(offset,pagesize);
		}else {
			pageable = new PaginationRequest(offset,pagesize,new Sort(Sort.Direction.fromString("DESC"), sortByProperties));
		}
		return pageable;
	}
}
