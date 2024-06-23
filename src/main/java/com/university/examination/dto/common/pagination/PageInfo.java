package com.university.examination.dto.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.university.examination.util.DataUtil.isNullObject;

@Data
@AllArgsConstructor(staticName = "of")
public class PageInfo {
    private Integer currentPage = 0;
    private Integer pageSize;
    public Integer getCurrentPage() {
        return isNullObject(currentPage) ? 0 : currentPage;
    }

    public Integer getPageSize() {
        return isNullObject(pageSize) ? Integer.MAX_VALUE : pageSize;
    }
}
