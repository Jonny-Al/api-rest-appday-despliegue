package com.api.Services;

import com.api.ModelVO.UsuarioVO;
import java.util.List;

public interface IUsuarioService {
    List<UsuarioVO> listUsers(String option);

    UsuarioVO searchUser(long id);

    String createUser(UsuarioVO usvo);

    String updateInformation(UsuarioVO usvo, String type);

    Boolean updatePassword(long id, String passwordOld, String passwordNew);

    UsuarioVO searchUser(String email);
}
