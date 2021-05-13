package com.api.Services;

import com.api.ModelVO.RolVO;
import java.util.List;

public interface IRolService {
    RolVO searchRol(Long rol);

    List<RolVO> listRoles();

    List<RolVO> filterRol(String rol);
}
