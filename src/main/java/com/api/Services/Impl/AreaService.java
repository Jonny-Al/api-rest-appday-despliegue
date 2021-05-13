package com.api.Services.Impl;

import com.api.Entity.Areas;
import com.api.ModelVO.AreaVO;
import com.api.Repository.IAreaRepository;
import com.api.Services.IAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaService implements IAreaService {
    private final ModelMapper modelMap = new ModelMapper();

    @Autowired
    IAreaRepository areaRepository;

    @Override
    public List<AreaVO> listAreas() {
        List<Areas> listareas = areaRepository.findAll();
        return (listareas.size() > 0) ? mapperList(listareas) : null;
    }

    @Override
    public AreaVO searchArea(Long area) {
        Optional<Areas> areaEntity = areaRepository.findById(area);
        return (areaEntity.isPresent()) ? convertToVo(areaEntity.get()) : null;
    }

    @Override
    public List<AreaVO> filerAreas(String area) {
        return mapperList(areaRepository.filerAreas(area));
    }

    // ====== MAPPER LISTA
    public List<AreaVO> mapperList(List<Areas> list) {
        return list.stream().map(Areas -> modelMap.map(Areas, AreaVO.class)).collect(Collectors.toList());
    }

    // ========= MAPPER CLASS
    private AreaVO convertToVo(Areas entity) {
        return modelMap.map(entity, AreaVO.class);
    }
}
