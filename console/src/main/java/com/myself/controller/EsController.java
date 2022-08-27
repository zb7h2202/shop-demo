package com.myself.controller;

import com.myself.es.ESService;
import com.myself.res.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EsController {

    @Autowired
    ESService esService;

    @GetMapping("/es/save")
    public ResponseInfo save(@RequestParam("index")String index){
        try {
            esService.createIndex(index);
            esService.bulkIndex(index);
//            esService.getIndexById(index,null).var;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseInfo.succ(true);
    }



}
