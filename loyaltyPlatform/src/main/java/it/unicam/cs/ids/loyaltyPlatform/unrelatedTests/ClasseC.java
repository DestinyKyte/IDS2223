package it.unicam.cs.ids.loyaltyPlatform.unrelatedTests;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramParameter;

import java.time.LocalDate;
import java.util.*;

public class ClasseC extends ClasseB{
    public static void main(String[] args){
        //System.out.println("choose the parameter type for the program: \"day\" or \"product\"");
        System.out.println( Arrays.toString(ProgramParameter.values()) +": "+
                "ENTER the position of the parameter:");

        Date date = new Date(2023, 7, 1);
        System.out.println(date);
        ClasseC c = new ClasseC();
        c.m1(0);
        System.out.println(c.getClass());

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate day = LocalDate.of(2023, 7, 1);

        
        
/*
        System.out.println(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));

        System.out.println(localDate.compareTo(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault())));
*/
    }
}
