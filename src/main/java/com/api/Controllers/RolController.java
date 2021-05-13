package com.api.Controllers;

import com.api.ModelVO.RolVO;
import com.api.Services.IRolService;
import com.api.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping ("/api/rol")
@RestController
public class RolController {

    @Autowired
    IRolService rolService;

    @GetMapping (path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listRoles() {
        List<RolVO> listRoles = rolService.listRoles();
        return ResponseEntity.status(HttpStatus.OK).body(listRoles != null ? listRoles : Util.messageJson("Sin información"));
    }

    @GetMapping (path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> filterRol(@RequestParam String rol) {
        List<RolVO> filter = rolService.filterRol(rol);
        return ResponseEntity.status(HttpStatus.OK).body(filter.size() > 0 ? filter : Util.messageJson("Sin información"));
    }

}
