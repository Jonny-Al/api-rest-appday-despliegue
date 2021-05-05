package com.api.Services.Impl;

import com.api.Entity.Usuarios;
import com.api.ModelVO.UsuarioVO;
import com.api.Repository.IUsuarioRepository;
import com.api.Services.IAreaService;
import com.api.Services.IRolService;
import com.api.Services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    private final ModelMapper modelMap = new ModelMapper();

    @Autowired
    IUsuarioRepository usrepository;
    @Autowired
    IRolService rolService;
    @Autowired
    IAreaService areaService;

    @Override
    public List<UsuarioVO> listUsers(String option) {
        List<Usuarios> listusers = null;

        try {
            switch (option) {

                case "all": // Todos
                    listusers = usrepository.findAll();
                    break;
                case "assets": // Activos
                    listusers = usrepository.listUsersActive();
                    break;
                case "inactive": // Inactivos
                    listusers = usrepository.listUsersInactive();
                    break;

            }
        } catch (Exception e) {
            logger.error("Error al obtener lista de usuarios en option: " + option);
        }
        return (listusers != null) ? mapperList(listusers) : null;
    }

    @Override
    public UsuarioVO searchUser(long id) {
        Optional<Usuarios> usentity = usrepository.findById(id);
        return (usentity.isPresent()) ? convertToVO(usentity.get()) : null;
    }

    @Override
    public UsuarioVO searchUser(String email) {
        Usuarios usentity = usrepository.searchEmail(email);
        return (usentity != null) ? convertToVO(usrepository.searchEmail(email)) : null;
    }

    @Override
    public String createUser(UsuarioVO usvo) {
        String response = "";
        try {
            if (rolService.searchRol(usvo.getRolid()) != null && areaService.searchArea(usvo.getArid()) != null) {
                if (searchUser(usvo.getUscorreo()) == null) {
                    Usuarios usentity = convertToEntity(usvo);
                    usentity.setUscorreoalternativo(null);
                    usentity.setUsclave(Base64.getEncoder().encodeToString(usvo.getUsclave().getBytes()));
                    usrepository.save(usentity);
                    response = "Agregado";
                } else {
                    response = "ExisteCorreo";
                    logger.warn("Para add el correo: " + usvo.getUscorreo() + " ya existe.");
                }
            } else {
                response = "AreaORolInvalido";
                logger.warn("El area: " + usvo.getArid() + " o " + "El rol: " + usvo.getRolid() + " no existen");
            }
        } catch (Exception e) {
            logger.error("Error al registrar usuario: " + e);
        }
        return response;
    }

    @Override
    public Boolean updatePassword(long id, String passwordOld, String passwordNew) {
        try {
            Optional<Usuarios> usentity = usrepository.findById(id);
            passwordOld = Base64.getEncoder().encodeToString(passwordOld.getBytes());
            if (usentity.get().getUsclave().equals(passwordOld)) {
                passwordNew = Base64.getEncoder().encodeToString(passwordNew.getBytes());
                usrepository.updatePassword(id, passwordNew);
                return true;
            }
        } catch (Exception e) {
            logger.error("Error al actualizar clave: " + e);
        }
        return false;
    }

    @Override
    public String updateInformation(UsuarioVO usvo, String type, long id) {
        String response = "NoActualizado";
        try {
            UsuarioVO vo = searchUser(id); // -> es el id del que esta actualizando

            if (vo != null && searchUser(usvo.getUsid()) != null) {

                if (type.equalsIgnoreCase("Personal")) {
                    // Para actualizar informacion personal
                    if (id == usvo.getUsid()) {
                        usrepository.updateInformationPersonal(usvo.getUsid(), usvo.getUsnombres(), usvo.getUsapellidos(), usvo.getUscorreoalternativo(), usvo.getUstelefono());
                        response = "Actualizado";
                    } else {
                        response = "NoPuedeActualizar";
                    }
                } else if (type.equalsIgnoreCase("Laboral")) {
                    if (rolService.searchRol(usvo.getRolid()) != null && areaService.searchArea(usvo.getArid()) != null) {
                        if (vo.getRolid() == 1 || vo.getRolid() == 2 && vo.getArid() == usvo.getArid() || vo.getArid() == 1) {
                            usrepository.updateInformatioLaboral(usvo.getUsid(), usvo.getUscorreo(), usvo.getArid(), usvo.getRolid());
                            response = "Actualizado";
                        } else {
                            response = "NoPuedeActualizar";
                        }
                    } else {
                        response = "AreaORolInvalido";
                    }
                }
            } else {
                response = "InformaciónInvalida";
            }
        } catch (Exception e) {
            logger.error("Error al actualizar usuario: " + e);
        }
        return response;
    }

    // ====== MAPPER LISTA
    private List<UsuarioVO> mapperList(List<Usuarios> list) {
        return list.stream().map(Usuarios -> modelMap.map(Usuarios, UsuarioVO.class)).collect(Collectors.toList());
    }

    // ====== MAPPER CLASS
    private UsuarioVO convertToVO(Usuarios usentity) {
        return modelMap.map(usentity, UsuarioVO.class);
    }

    private Usuarios convertToEntity(UsuarioVO usvo) {
        return modelMap.map(usvo, Usuarios.class);
    }
}
