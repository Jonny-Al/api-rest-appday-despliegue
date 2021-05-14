package com.api.Services;

import com.api.Entity.Usuarios;
import com.api.ModelVO.UsuarioVO;
import java.util.List;

public interface IUsuarioService {

    List<UsuarioVO> listUsers(String option);

    UsuarioVO searchUser(long id);

    UsuarioVO searchUser(String email);

    String createUser(UsuarioVO usvo);

    Boolean updatePassword(long id, String passwordOld, String passwordNew);

    String updateInformation(UsuarioVO usvo, String type);

    UsuarioVO updateStatus(int estado, long id);

    boolean deleteUser(long id);

}
