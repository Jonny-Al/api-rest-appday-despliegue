package com.api.Repository;

import com.api.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuarios, Long> {
    @Query ("SELECT u FROM Usuarios u WHERE u.usestado = 1")
    List<Usuarios> listUsersActive();

    @Query ("SELECT u FROM Usuarios u WHERE u.usestado = 2")
    List<Usuarios> listUsersInactive();

    @Query ("SELECT u FROM Usuarios u WHERE u.uscorreo = :correo OR u.uscorreoalternativo = :correo")
    Usuarios searchEmail(String correo);

    @Procedure ("Us_UpdatePassword")
    void updatePassword(long id, @Param ("clave") String passwordNew);

    @Procedure ("Us_UpdateInfoTrabajo")
    void updateInformatioLaboral(long id, String email, long area, long rol);

    @Procedure ("Us_UpdateDatosPersonales")
    void updateInformationPersonal(long id, String name, String lastname, String emailalternative, String telephone);

    /*
    Este metodo si sirve asi como esta pero no se esta usando, si se usa se deben usar estos import:

    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.transaction.annotation.Transactional;

    @Transactional
    @Modifying
    @Query ("UPDATE Usuarios u SET u.usestado = :estado WHERE u.usid = :id")
    void updateStatus(int estado, long id);*/

}
