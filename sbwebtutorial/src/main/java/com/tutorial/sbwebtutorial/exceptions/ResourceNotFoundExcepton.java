package com.tutorial.sbwebtutorial.exceptions;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

public class ResourceNotFoundExcepton extends RuntimeException{

    public ResourceNotFoundExcepton(String message){
        super(message);
    }
}
