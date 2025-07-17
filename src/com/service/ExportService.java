package com.service;

import com.model.ItemsEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportService {

    public void validPath(List<ItemsEntity> items, String outputPath){
        exportValidationData(items, outputPath);
    }

    public void invalidPath(List<ItemsEntity> items, String outputPath){
        exportValidationData(items, outputPath);
    }

    public void exportValidationData(List<ItemsEntity> items, String outputPath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))){

            bw.write("id,name,price,category,quantity");
            bw.newLine();

            for (ItemsEntity item : items) {
                bw.write(item.toLine());
                bw.newLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
