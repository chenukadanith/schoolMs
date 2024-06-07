package com.system.ms.schoolMs.dto.paginated;

import com.system.ms.schoolMs.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDto<T> {
    List<T> list;
    private long totalItems;
    private int currentPage;
    private int totalPages;
}
