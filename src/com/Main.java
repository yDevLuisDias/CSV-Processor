package com;

import com.service.ItemsService;
import com.service.ValidationsService;

public class Main {
    public static void main(String[] args){
        ValidationsService validations = new ValidationsService();
        ItemsService service = new ItemsService(validations);

        service.readItems();
    }
}