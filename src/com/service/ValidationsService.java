package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidationsService {

    public List<String> validationItems(
            String id, String name, String price, String category, String quantity) {
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

        //Validar Price
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

                if (i <= 0){
                    error.add("Quantidade em estoque está negativa - inválido");
                }
            }catch (NumberFormatException e){
                error.add("Error : <quantity> Formato incompativél - Formato desejado : Integer");
            }
        }
        return error;
    }
}
