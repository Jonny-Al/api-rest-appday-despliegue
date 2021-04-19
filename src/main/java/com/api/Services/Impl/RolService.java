package com.api.Services.Impl;

import com.api.Entity.Rol;
import com.api.ModelVO.RolVO;
import com.api.Repository.IRolRepository;
import com.api.Services.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RolService implements IRolService {

    @Autowired
    IRolRepository rolRepository;
    private final ModelMapper modelMap = new ModelMapper();

    @Override
    public RolVO searchRol(Long rol) {
        Optional<Rol> rolEntity = rolRepository.findById(rol);
        return (rolEntity.isPresent() ? convertToVo(rolEntity.get()) : null);
    }

    // ====== MAPPER CLASS
    private RolVO convertToVo(Rol entity) {
        return modelMap.map(entity, RolVO.class);
    }
}
