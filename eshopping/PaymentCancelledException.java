package com.zoho.E_Shopping;
class PaymentCancelledException extends RuntimeException
{
	PaymentCancelledException(String str)
	{
		super(str);
	}
}