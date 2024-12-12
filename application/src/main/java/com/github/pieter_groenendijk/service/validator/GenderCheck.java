package com.github.pieter_groenendijk.service.validator;

    public class GenderCheck
    {
        public enum Gender {
            MALE('M'),
            FEMALE('F'),
            OTHER('O');

            private final char code;

            Gender(char code) {
                this.code = code;
            }
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

    