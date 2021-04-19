package com.api.Services.Impl;

import com.api.Entity.Areas;
import com.api.ModelVO.AreaVO;
import com.api.Repository.IAreaRepository;
import com.api.Services.IAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AreaService implements IAreaService {
    @Autowired
    IAreaRepository areaRepository;

    private final ModelMapper modelMap = new ModelMapper();

    @Override
    public AreaVO searchArea(Long area) {
        Optional<Areas> areaEntity = areaRepository.findById(area);
        return (areaEntity.isPresent() ? convertToVo(areaEntity.get()) : null);
    }

    // ========= MAPPER CLASS
    private AreaVO convertToVo(Areas entity) {
        return modelMap.map(entity, AreaVO.class);
    }
}
