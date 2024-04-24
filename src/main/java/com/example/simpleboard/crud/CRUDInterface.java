package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 추상화를 통해 CRUD를 자동으로 만들어주는 인터페이스
 */
public interface CRUDInterface<DTO> {

    DTO create(DTO dto);

    Optional<DTO> read(Long id);

    DTO update(DTO dto);

    void delete(Long id);

    Api<List<DTO>>list(Pageable pageable);

}
