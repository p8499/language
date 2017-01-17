package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class WordflowMask implements Mask
{	protected boolean waid=false;
	protected boolean wawoid=false;
	protected boolean wasi=false;
	protected boolean wapt=false;
	protected boolean wast=false;
	protected boolean wausid=false;
	protected boolean wacrdd=false;
	protected boolean wacrdt=false;
	protected boolean waupdd=false;
	protected boolean waupdt=false;

	public WordflowMask(boolean waid,boolean wawoid,boolean wasi,boolean wapt,boolean wast,boolean wausid,boolean wacrdd,boolean wacrdt,boolean waupdd,boolean waupdt)
	{	this.waid=waid;
		this.wawoid=wawoid;
		this.wasi=wasi;
		this.wapt=wapt;
		this.wast=wast;
		this.wausid=wausid;
		this.wacrdd=wacrdd;
		this.wacrdt=wacrdt;
		this.waupdd=waupdd;
		this.waupdt=waupdt;
	}
	public WordflowMask()
	{	
	}
	@Override
	public WordflowMask all(boolean b)
	{	this.waid=b;
		this.wawoid=b;
		this.wasi=b;
		this.wapt=b;
		this.wast=b;
		this.wausid=b;
		this.wacrdd=b;
		this.wacrdt=b;
		this.waupdd=b;
		this.waupdt=b;
	return this;
	}
	public boolean getWaid()
	{	return waid;
	}
	public WordflowMask setWaid(boolean waid)
	{	this.waid=waid;
		return this;
	}
	public boolean getWawoid()
	{	return wawoid;
	}
	public WordflowMask setWawoid(boolean wawoid)
	{	this.wawoid=wawoid;
		return this;
	}
	public boolean getWasi()
	{	return wasi;
	}
	public WordflowMask setWasi(boolean wasi)
	{	this.wasi=wasi;
		return this;
	}
	public boolean getWapt()
	{	return wapt;
	}
	public WordflowMask setWapt(boolean wapt)
	{	this.wapt=wapt;
		return this;
	}
	public boolean getWast()
	{	return wast;
	}
	public WordflowMask setWast(boolean wast)
	{	this.wast=wast;
		return this;
	}
	public boolean getWausid()
	{	return wausid;
	}
	public WordflowMask setWausid(boolean wausid)
	{	this.wausid=wausid;
		return this;
	}
	public boolean getWacrdd()
	{	return wacrdd;
	}
	public WordflowMask setWacrdd(boolean wacrdd)
	{	this.wacrdd=wacrdd;
		return this;
	}
	public boolean getWacrdt()
	{	return wacrdt;
	}
	public WordflowMask setWacrdt(boolean wacrdt)
	{	this.wacrdt=wacrdt;
		return this;
	}
	public boolean getWaupdd()
	{	return waupdd;
	}
	public WordflowMask setWaupdd(boolean waupdd)
	{	this.waupdd=waupdd;
		return this;
	}
	public boolean getWaupdt()
	{	return waupdt;
	}
	public WordflowMask setWaupdt(boolean waupdt)
	{	this.waupdt=waupdt;
		return this;
	}
}