package it.unicam.cs.ids.loyaltyPlatform.tests;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramParameter;

import java.util.Arrays;

public class ClasseC extends ClasseA{
    public static void main(String[] args){
        //System.out.println("choose the parameter type for the program: \"day\" or \"product\"");
        System.out.println( Arrays.toString(ProgramParameter.values()) +": "+
                "ENTER the position of the parameter:");
    }
}
