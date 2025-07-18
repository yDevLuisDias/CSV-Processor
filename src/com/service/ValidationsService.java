package com.service;

import com.model.ItemsEntity;

import java.util.ArrayList;
import java.util.List;

public class ValidationsService {
    public List<String> validationItems(String id, String name,String price, String category, String quantity) {
        List<String> error = new ArrayList<>();

        //Validar ID
        if (id == null || id.isEmpty()){
            error.add("Id está vazio");
        }else {
            try{
                Long l = Long.parseLong(id);

                if (l < 0){
                    error.add("Id está negativa - inválido");
                }
            }catch (NumberFormatException e){
                error.add("Error <id> Formato incompativél - Formato desejado : Long");
            }
        }

        //Validar Name
        if (name == null || name.isBlank()){
            error.add("Nome está vazio");
        }

        //validar price
        if (price == null || price.isBlank()){
            error.add("Preço está vazio");
        } else {
            try{
                double d = Double.parseDouble(price);

                if (d < 0 ){
                    error.add("Preço está negativo - inválido");
                }
            }catch (NumberFormatException e){
                error.add("Error <price> Formato incompativél - Formato desejado : double");
            }
        }

        //Validar Category
        if (category == null || category.isBlank()){
            error.add("Categoria está vazia");
        }

        //validar quantity
        if (quantity == null || quantity.isBlank()){
            error.add("Quantidade está vazia");
        } else{
            try {
                int i = Integer.parseInt(quantity);

                if (i < 0){
                    error.add("Quantidade em estoque está negativa - inválido");
                }
            }catch (NumberFormatException e){
                error.add("Error : <quantity> Formato incompativél - Formato desejado : Integer");
            }
        }
        return error;
    }

    public ItemsEntity getErrorItems(String[] data){
        ItemsEntity items = new ItemsEntity();
        try{
            long id = 0;
            String name = "Error";
            double price = 0.0;
            String category = "Error";
            int quantity = 0;

            if (!data[0].isBlank()){
                try{
                    id = Integer.parseInt(data[0]);

                }catch (NumberFormatException e){}
            }

            if (!data[1].isBlank()){
                name = data[1];
            }

            if (!data[2].isBlank()){
                try{
                    price = Double.parseDouble(data[2]);

                } catch (NumberFormatException e) {}
            }

            if (!data[3].isBlank()){
                category = data[3];
            }

            if (!data[4].isBlank()){
                try{
                    quantity = Integer.parseInt(data[4]);
                    if (quantity < 0){
                        quantity = 0;
                    }
                } catch (NumberFormatException e) {}

                return new ItemsEntity(id, name, price, category, quantity);
            }

        }catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            return null;
        }
        return items;
    }
}
