package com.api.Services.Impl;

import com.api.Entity.Rol;
import com.api.ModelVO.RolVO;
import com.api.Repository.IRolRepository;
import com.api.Services.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService implements IRolService {

    @Autowired
    IRolRepository rolRepository;
    private final ModelMapper modelMap = new ModelMapper();

    @Override
    public List<RolVO> listRoles() {
        List<Rol> listRoles = rolRepository.findAll();
        return (listRoles.size() > 0) ? mapperList(listRoles) : null;
    }

    @Override
    public RolVO searchRol(Long rol) {
        Optional<Rol> rolEntity = rolRepository.findById(rol);
        return (rolEntity.isPresent()) ? convertToVo(rolEntity.get()) : null;
    }

    // Filtra los roles con letras que coinciden con -> rolfilter
    @Override
    public List<RolVO> filterRol(String rol) {
        return mapperList(rolRepository.filterRol(rol));
    }

    // ====== MAPPER LIST
    private List<RolVO> mapperList(List<Rol> list) {
        return list.stream().map(Rol -> modelMap.map(Rol, RolVO.class)).collect(Collectors.toList());
    }

    // ====== MAPPER CLASS
    private RolVO convertToVo(Rol entity) {
        return modelMap.map(entity, RolVO.class);
    }
}
