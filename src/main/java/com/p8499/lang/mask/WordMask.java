package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class WordMask implements Mask
{	protected boolean woid=false;
	protected boolean wolsid=false;
	protected boolean woct=false;
	protected boolean wopt=false;
	protected boolean wocl=false;
	protected boolean wost=false;

	public WordMask(boolean woid,boolean wolsid,boolean woct,boolean wopt,boolean wocl,boolean wost)
	{	this.woid=woid;
		this.wolsid=wolsid;
		this.woct=woct;
		this.wopt=wopt;
		this.wocl=wocl;
		this.wost=wost;
	}
	public WordMask()
	{	
	}
	public boolean getWoid()
	{	return woid;
	}
	public WordMask setWoid(boolean woid)
	{	this.woid=woid;
		return this;
	}
	public boolean getWolsid()
	{	return wolsid;
	}
	public WordMask setWolsid(boolean wolsid)
	{	this.wolsid=wolsid;
		return this;
	}
	public boolean getWoct()
	{	return woct;
	}
	public WordMask setWoct(boolean woct)
	{	this.woct=woct;
		return this;
	}
	public boolean getWopt()
	{	return wopt;
	}
	public WordMask setWopt(boolean wopt)
	{	this.wopt=wopt;
		return this;
	}
	public boolean getWocl()
	{	return wocl;
	}
	public WordMask setWocl(boolean wocl)
	{	this.wocl=wocl;
		return this;
	}
	public boolean getWost()
	{	return wost;
	}
	public WordMask setWost(boolean wost)
	{	this.wost=wost;
		return this;
	}
}