package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class WordvoteMask implements Mask
{	protected boolean wvid=false;
	protected boolean wvwaid=false;
	protected boolean wvsi=false;
	protected boolean wvusid=false;
	protected boolean wvpo=false;
	protected boolean wvupdd=false;
	protected boolean wvupdt=false;

	public WordvoteMask(boolean wvid,boolean wvwaid,boolean wvsi,boolean wvusid,boolean wvpo,boolean wvupdd,boolean wvupdt)
	{	this.wvid=wvid;
		this.wvwaid=wvwaid;
		this.wvsi=wvsi;
		this.wvusid=wvusid;
		this.wvpo=wvpo;
		this.wvupdd=wvupdd;
		this.wvupdt=wvupdt;
	}
	public WordvoteMask()
	{	
	}
	@Override
	public WordvoteMask all(boolean b)
	{	this.wvid=b;
		this.wvwaid=b;
		this.wvsi=b;
		this.wvusid=b;
		this.wvpo=b;
		this.wvupdd=b;
		this.wvupdt=b;
	return this;
	}
	public boolean getWvid()
	{	return wvid;
	}
	public WordvoteMask setWvid(boolean wvid)
	{	this.wvid=wvid;
		return this;
	}
	public boolean getWvwaid()
	{	return wvwaid;
	}
	public WordvoteMask setWvwaid(boolean wvwaid)
	{	this.wvwaid=wvwaid;
		return this;
	}
	public boolean getWvsi()
	{	return wvsi;
	}
	public WordvoteMask setWvsi(boolean wvsi)
	{	this.wvsi=wvsi;
		return this;
	}
	public boolean getWvusid()
	{	return wvusid;
	}
	public WordvoteMask setWvusid(boolean wvusid)
	{	this.wvusid=wvusid;
		return this;
	}
	public boolean getWvpo()
	{	return wvpo;
	}
	public WordvoteMask setWvpo(boolean wvpo)
	{	this.wvpo=wvpo;
		return this;
	}
	public boolean getWvupdd()
	{	return wvupdd;
	}
	public WordvoteMask setWvupdd(boolean wvupdd)
	{	this.wvupdd=wvupdd;
		return this;
	}
	public boolean getWvupdt()
	{	return wvupdt;
	}
	public WordvoteMask setWvupdt(boolean wvupdt)
	{	this.wvupdt=wvupdt;
		return this;
	}
}