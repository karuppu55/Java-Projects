package com.zoho.E_Shopping;
class OrderNotFoundException extends RuntimeException
{
	OrderNotFoundException(String str)
	{
		super(str);
	}
}