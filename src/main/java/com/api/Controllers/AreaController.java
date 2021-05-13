package com.api.Controllers;

import com.api.ModelVO.AreaVO;
import com.api.Services.IAreaService;
import com.api.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping ("/api/area")
@RestController
public class AreaController {

    @Autowired
    IAreaService areaService;

    // Lista de areas
    @GetMapping (path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listAreas() {
        List<AreaVO> listareas = areaService.listAreas();
        return ResponseEntity.status(HttpStatus.OK).body(listareas != null ? listareas : Util.messageJson("Sin información"));
    }

    @GetMapping (path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> filterAreas(String area) {
        List<AreaVO> filter = areaService.filerAreas(area);
        return ResponseEntity.status(HttpStatus.OK).body(filter.size() > 0 ? filter : Util.messageJson("Sin información"));
    }

}
