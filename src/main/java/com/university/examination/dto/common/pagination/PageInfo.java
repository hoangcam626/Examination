package com.university.examination.dto.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PageInfo {
    private Integer currentPage = 0;
    private Integer pageSize;
}
