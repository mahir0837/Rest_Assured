package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id",allowSetters = true)
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;

    public static Spartan createSpartan(){
        Faker faker=new Faker();
        Spartan spartan=new Spartan();
        spartan.setName(faker.gameOfThrones().character().substring(0,7));
        int number= faker.number().randomDigit();
        if (number%2==0){
            spartan.setGender("Male");
        }else{
            spartan.setGender("Female");
        }
        spartan.setPhone(Long.parseLong(faker.numerify("###########")));

        return spartan;
    }
}
