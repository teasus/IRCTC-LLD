package org.booking.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

    public  static  String hashPassword(String plainPassword){

      return BCrypt.hashpw(plainPassword,BCrypt.gensalt(4));
    }

    public  static boolean checkPassword(String plain,String hashed){
        return BCrypt.checkpw(plain, hashed);
    }
}
