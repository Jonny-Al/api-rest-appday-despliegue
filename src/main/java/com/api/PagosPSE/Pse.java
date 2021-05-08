package com.api.PagosPSE;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping ("api/pse")
public class Pse {

    @Value ("#{'${pse.public.key}'}")
    private String PUBLIC_KEY;
    @Value ("#{'${pse.http.path}'}")
    private String URL_PSE;

    private static final Logger logger = LoggerFactory.getLogger(Pse.class);
    private static final ObjectMapper objMap = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();
    // https://secure.payco.co/restpagos/pse/bancos.json?public_key=e5b73685f785d8115ba51f0a8494af82

    @GetMapping (value = "/banks", produces = MediaType.APPLICATION_JSON_VALUE)
    public void listBancos() {
        try {
            ResponseEntity<String> responseBancos = restTemplate.getForEntity(URL_PSE + "/bancos.json?public_key=" + PUBLIC_KEY, String.class);

            if (responseBancos.getStatusCode().equals(HttpStatus.OK)) {
                // objMap.configure = Evita el error --> out of START_OBJECT token
                objMap.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                DataResponse dataResponse = objMap.readValue(responseBancos.getBody(), DataResponse.class);

                if (dataResponse.isSuccess()) {
                    logger.info("Se obtienen los bancos");

                    // EJEMPLO DEL JSON QUE SE OBTIENE
                    /*{
                        "success": true,
                            "title_response": "Ok",
                            "text_response": "Bancos consultados exitosamente",
                            "last_action": "Query Bancos",
                            "data": [
                          {
                            "bankCode": "0",
                             "bankName":"A continuaci\ón seleccione su banco"
                          },
                          {
                            "bankCode": "1022",
                            "bankName": "Banco Union Colombiano"
                          }
                     ],
                        "enpruebas": 2
                    }*/

                    // listBanks = esta la lista de bancos
                    List<Data> listBanks = dataResponse.getData();
                } else {
                    logger.warn("No se obtiene lista de bancos");
                    logger.warn(responseBancos.getBody());
                }

            } else if (responseBancos.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.info("No se obtienen los bancos");
            } else {
                logger.info("Sin información");
            }


        } catch (Exception e) {
            logger.error("Error al obtener la lista de bancos: " + e);
        }
    }


    @ResponseBody
    @GetMapping (value = "/rs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> probando() {
        JSONObject json = new JSONObject("");
        return new ResponseEntity("value", HttpStatus.OK);
    }
}
