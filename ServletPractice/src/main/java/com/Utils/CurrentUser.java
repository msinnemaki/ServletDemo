package com.Utils;

public class CurrentUser
{
    public static CurrentUser currentUser;
    public static Integer user_ID;
    public static String first_Name;
    public static String last_name;
    public static String email;

//    public CurrentUser()
//    {
//
//    }
    public CurrentUser(Integer user_ID,String first_Name, String last_name, String email)
    {
        this.user_ID = user_ID;
        this.first_Name = first_Name;
        this.last_name = last_name;
        this.email = email;
    }
}
