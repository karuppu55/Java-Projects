package com.zoho.E_Shopping;
class ItemNotDeliveredException extends RuntimeException
{
	ItemNotDeliveredException(String str)
	{
		super(str);
	}
}