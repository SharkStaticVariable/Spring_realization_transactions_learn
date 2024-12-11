package org.example.spring_realization_transactions.controllers;

import org.example.spring_realization_transactions.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ExcelController {
    @Autowired
    public ExcelService excelService;

    @GetMapping("/create-excel")
    public ResponseEntity<byte[]> createExcel() {
//        byte[] excel = excelService.createExcel();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.xlsx")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(excel);
        return null;
    }
}
