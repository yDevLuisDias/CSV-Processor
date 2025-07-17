package com;

import com.service.ExportService;
import com.service.ItemsService;
import com.service.ValidationsService;

public class Main {
    public static void main(String[] args){
        ValidationsService validations = new ValidationsService();
        ExportService export = new ExportService();
        ItemsService service = new ItemsService(validations, export);

        service.readItems();
    }
}