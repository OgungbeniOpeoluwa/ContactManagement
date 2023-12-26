package org.example.util;

import lombok.Getter;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class EncryptPassword {
        private static final Random random = new SecureRandom();
        private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private static final int iterations = 10000;
        private static final int keyLength = 256;
        @Getter
        private static final int length = 30;


        public static String getSaltValue(){
            StringBuilder finalVal = new StringBuilder();
            for(int count = 0; count < length; count++){
                finalVal.append(characters.charAt(random.nextInt(length)));
            }
            return String.valueOf(finalVal);
        }
        public static byte[] hash(char[] password,byte[]salt){
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password,salt,iterations,keyLength);
            Arrays.fill(password,Character.MIN_VALUE);
            try {
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new AssertionError("Error");
            } finally {
                pbeKeySpec.clearPassword();
            }
        }
        public static String securePassword(String password,String salt){
            byte[] securePassword =hash(password.toCharArray(),salt.getBytes());
            return Base64.getEncoder().encodeToString(securePassword);
        }
        public static String hashPassword(String password) {
            String salt = getSaltValue();
            String hashPassword = securePassword(password, salt);
            return salt + hashPassword;
        }

    }
