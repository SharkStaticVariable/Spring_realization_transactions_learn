package org.example.spring_realization_transactions.service;

import org.example.spring_realization_transactions.entity.UsersEntity;
import org.example.spring_realization_transactions.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private UsersRepository usersRepository;

//    public byte[] createExcel() {
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            Sheet sheet = workbook.createSheet("Пользователи");
//
//            // Создание заголовка
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("ID");
//            headerRow.createCell(1).setCellValue("Логин");
//            headerRow.createCell(3).setCellValue("Возраст");
//            headerRow.createCell(4).setCellValue("Фамилия");
//            headerRow.createCell(5).setCellValue("Имя");
//            headerRow.createCell(6).setCellValue("Отчество");
//            headerRow.createCell(7).setCellValue("Номер телефона");
//            headerRow.createCell(8).setCellValue("Адресс");
//            headerRow.createCell(9).setCellValue("Эл. почта");
//
//
//            // Получение пользователей из базы данных
//            List<UsersEntity> users = usersRepository.findAll();
//            int rowIdx = 1;
//            for (UsersEntity user : users) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(user.getId());
//                row.createCell(1).setCellValue(user.getUsername());
//                row.createCell(2).setCellValue(user.getAge());
//                row.createCell(3).setCellValue(user.getLastName());
//                row.createCell(4).setCellValue(user.getFirstName());
//                row.createCell(5).setCellValue(user.getMiddleName());
//                row.createCell(6).setCellValue(user.getPhoneNumber());
//                row.createCell(7).setCellValue(user.getAddress());
//                row.createCell(8).setCellValue(user.getEmail());
//
//            }
//
//            workbook.write(baos);
//            return baos.toByteArray();
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to create Excel document", e);
//        }
//    }
}
