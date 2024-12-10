package com.github.pieter_groenendijk.validator;

    public static class GenderCheck
    {
        public enum Gender {
            MALE('M'),
            FEMALE('F'),
            OTHER('O');
        }

        public static boolean exists(char code) {
            for (Gender gender : Gender.values()) {
                if (gender.code == code) {
                    return true;
                }
            }
            return false;
        }
    
    }

    