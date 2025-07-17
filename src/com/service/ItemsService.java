package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.model.ItemsEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ItemsService {

    private final ValidationsService validations;

    public ItemsService(ValidationsService validations) {
        this.validations = validations;
    }

    public List<ItemsEntity> readItems(){
        String path = "./src/com/data/file.csv";
        List <ItemsEntity> list = new ArrayList<>();

        File file = new File(path);
        System.out.println("arquivo : " + file.getName());

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            String line = bf.readLine();

            int i = 0;
            List<Long> lId = new ArrayList<>();
            List<String> sName = new ArrayList<>();

            while((line = bf.readLine()) != null){
                var data = line.split(",");

                ItemsEntity items = getItemsEntity(line);

                if (items == null){
                    i++;

                    if (!data[0].isBlank()){
                        try{
                            Long id =parseLong(data[0]);
                            lId.add(id);

                        }catch (NumberFormatException e){
                            System.out.println("Error : " + e.getMessage());
                        }
                    }else {
                        sName.add(data[1]);
                    }

                }else {
                    list.add(items);
                }

                System.out.println(items);
                System.out.println("\n" + "<---- <> ---->");
            }

            System.out.print("Quantidade de itens que retornaram erro : " + i
                    + "\n" + "id's que retornaram null : " + Arrays.toString(lId.toArray())
            );
            if (!sName.isEmpty()){
                System.out.println("\n" + "itens que retornaram null : "
                        + Arrays.toString(sName.toArray()));
            }

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }

        return list;
    }

    private ItemsEntity getItemsEntity(String line) {
        var data = line.split(",");

        List<String> error = validations.validationItems(data[0],data[1],data[2],data[3],data[4]);

        /*
         * [0] = id
         * [1] = name;
         * [2] = price;
         * [3] = category;
         * [4] = quantity;
         */

        String id = data[0];

        if (error.isEmpty()) {
            try {
                return new ItemsEntity(
                        parseLong(data[0])
                        , data[1]
                        , parseDouble(data[2])
                        , data[3]
                        , parseInt(data[4])
                );
            }catch (NumberFormatException e){
                System.out.println("Error : " + e.getMessage());
            }
        } else {
            if ( !id.isBlank()){
                System.out.println("Error no id : " + id);
            }else {
                System.out.println("Error no item : " + data[1]);
            }
            for (String outPutError : error) {
                System.out.println(" <--<!>--> " + outPutError);
            }
        }
        return null;
    }

}