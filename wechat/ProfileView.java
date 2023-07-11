package com.zoho.WeChat;
class ProfileView
{
    public void profile(User us)
    {
        System.out.println("\n_______________________________________________________________________________________________");
        System.out.println("\n\t\t\t\tPROFILE");
        System.out.println("\n_______________________________________________________________________________________________");
        System.out.println("\n\t\tFULL NAME    :"+us.getFullname());
        System.out.println("\n\t\tUSER NAME    :"+us.getName());
        System.out.println("\n\t\tEMAIL   :"+us.getEmail());
        System.out.println("\n\t\tACCOUNT OPENED ON   :"+us.getCreatedOn());
        System.out.println("\n\t\tACCOUNT TYPE    :"+us.getPrivacy());
    }
}