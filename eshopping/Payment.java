package com.zoho.E_Shopping;
import java.sql.Connection;
import java.io.*;
class Payment
{
    public void payByUpi(double amount)throws PaymentCancelledException,IOException
    {
        String upiid=Validation.getInstance().isUPI("UPI ID");
        while(amount!=Double.valueOf(Validation.getInstance().isAmount("ENTER AMOUNT")))
        {
            System.out.println("ENTER CORRECT AMOUNT");
        }
           if(Validation.getInstance().isYesorNo("PRESS Y TO PAYMENT").toLowerCase().equals("n"))
           {
                throw new PaymentCancelledException("PAYMENT CANCELLED");
           }
    }
}