package com.api.Controllers;

import com.api.ModelVO.UsuarioVO;
import com.api.Services.IUsuarioService;
import com.api.Utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@CrossOrigin (origins = "*", maxAge = 3600)
@RestController
@RequestMapping ("/api/users")
public class UsuarioController {

    @Value ("#{'${path.api.users}'}")
    private String PATH_API_USERS;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private static final ObjectMapper objMap = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    IUsuarioService service;

    // Lista de usuarios
    @GetMapping (value = "/list/{option}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> listUsers(@PathVariable String option) {
        List<UsuarioVO> listusers = service.listUsers(option);
        return ResponseEntity.status(listusers != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(listusers != null ? listusers : Util.messageJson("Sin información"));
    }

    // Consulta usuario por id
    @GetMapping (value = "/search/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> searchUser(@PathVariable long id) {
        UsuarioVO usvo = service.searchUser(id);
        return ResponseEntity.status(usvo != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(usvo != null ? usvo : Util.messageJson("Este usuario no existe"));
    }

    // Consulta usuario por correo
    @GetMapping (value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> searchUser(@RequestParam String email) {
        UsuarioVO usvo = service.searchUser(email);
        return ResponseEntity.status(usvo != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(usvo != null ? usvo : Util.messageJson("Información no encontrada"));
    }

    // Registrar usuario
    @PostMapping (value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> createUser(@Valid @RequestBody UsuarioVO usvo, BindingResult result) {
        if (result.hasErrors() || usvo.getUsclave() == null || usvo.getRolid() < 1 || usvo.getArid() < 1) {
            if (usvo.getUsclave() == null) {
                result.rejectValue("usclave", "400", "La clave es obligatoria");
            } else if (usvo.getRolid() < 1) {
                result.rejectValue("rolid", "400", "Seleccione el rol");
            }
            if (usvo.getArid() < 1) {
                result.rejectValue("arid", "400", "Seleccione el area");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Util.errorsJson(result));
        }
        String add = service.createUser(usvo);
        return ResponseEntity.status(add.equals("Agregado") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(Util.messageJson(add));
    }

    // Actualizar usuario
    @Scheduled(fixedRate = 2000)
    @PutMapping (value = "/update/information/{type}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> updateInformation(@Valid @RequestBody UsuarioVO usvo, BindingResult result, @PathVariable String type) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Util.errorsJson(result));
        }
        String update = service.updateInformation(usvo, type);
        return ResponseEntity.status(update.equals("Actualizado") ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(Util.messageJson(update));
    }

    // Actualiza clave
    @PutMapping ("/update/password")
    private ResponseEntity<Boolean> updatePassword(@RequestParam long id, @RequestParam String passwordOld, @RequestParam String passwordNew) {
        boolean updatepassword = service.updatePassword(id, passwordOld, passwordNew);
        return ResponseEntity.status(updatepassword ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(updatepassword);
    }

    // ============ METODOS QUE CONSUMEN API PARA HACER PRUEBAS DE CONSUMO CON JAVA
    @GetMapping ("/consulta/{id}")
    private void consultarUsuario(@PathVariable long id) {
        try {

            ResponseEntity<String> response = restTemplate.getForEntity(PATH_API_USERS + "/search/" + id, String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                // Mapea objeto JSON de UsuarioVO
                UsuarioVO usvo = objMap.readValue(response.getBody(), UsuarioVO.class);
                logger.info("Nombres: " + usvo.getUsnombres());
                logger.info("Area: " + usvo.getArea().getArNombre());
                logger.info("Rol: " + usvo.getRol().getRolNombre());

            } else if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.info("Sin informacion");
            }

        } catch (Exception e) {
            logger.error("ERROR AL CONSUMIR API:  " + e);
        }
    }

    @GetMapping ("/lista")
    private void listarUsuarios() {
        try {

            ResponseEntity<String> listusers = restTemplate.exchange(PATH_API_USERS + "/list/all", HttpMethod.GET, Util.getHttpEntity(), String.class);

            logger.info(listusers.getBody());
            if (listusers.getStatusCode().equals(HttpStatus.OK)) {

                UsuarioVO[] usvoArray = objMap.readValue(listusers.getBody(), UsuarioVO[].class);

                for (UsuarioVO us : usvoArray) {
                    UsuarioVO usvo = new UsuarioVO();
                    usvo.setUsnombres(us.getUsnombres());
                    usvo.setArea(us.getArea());
                    usvo.setRol(us.getRol());
                    logger.info("Nombres: " + usvo.getUsnombres());
                    logger.info("Area: " + usvo.getArea().getArNombre());
                    logger.info("Rol: " + usvo.getRol().getRolNombre());
                    break;
                }

            } else if (listusers.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.warn("No se obtuvo estado 500 con lista:");
            }

        } catch (Exception e) {
            logger.error("Error al obtener lista: " + e);
        }
    }


    @PostMapping ("/agregar")
    public void agregarUsuario() {

        try {
            // === Envia post con objeto class

            /* UsuarioVO usvo = new UsuarioVO();
            usvo.setUsnombres("Alejandro");
            usvo.setUsapellidos("Garcia");
            usvo.setUscorreo("alejo@gmail.com");
            usvo.setUsclave("miclave");
            usvo.setUsestado(1);
            usvo.setRolid(2);
            usvo.setArid(3);

            ResponseEntity<String> requestAdd = restTemplate.exchange(PATH_API_USERS + "/add", HttpMethod.POST,Util.getHttpEntity(usvo), String.class);
            logger.info("requestAdd: " + requestAdd.getBody());*/
            // === Envia por post con objeto json

            JSONObject json = new JSONObject();
            json.put("nombres", "Spring").put("apellidos", "boot");
            json.put("telefono", "88888").put("correo", "spring@gmail.com").put("clave", "passboot");
            json.put("estado", 1).put("Idrol", 3).put("IdArea", 1);

            ResponseEntity<String> request = restTemplate.postForEntity(PATH_API_USERS + "/add", Util.getHttpEntity(json), String.class);
            logger.info("request: " + request.getBody());

            if (request.getStatusCode().equals(HttpStatus.CREATED)) {
                logger.info("Usuario agregado");
            }


        } catch (Exception e) {
            logger.error("Error al agregar el usuario desde el metodo agregarUsuario: " + e);
        }
    }
}
