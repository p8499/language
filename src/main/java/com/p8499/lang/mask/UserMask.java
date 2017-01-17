package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class UserMask implements Mask
{	protected boolean usid=false;
	protected boolean uspswd=false;
	protected boolean usname=false;
	protected boolean usst=false;
	protected boolean uslsid=false;
	protected boolean uspn=false;

	public UserMask(boolean usid,boolean uspswd,boolean usname,boolean usst,boolean uslsid,boolean uspn)
	{	this.usid=usid;
		this.uspswd=uspswd;
		this.usname=usname;
		this.usst=usst;
		this.uslsid=uslsid;
		this.uspn=uspn;
	}
	public UserMask()
	{	
	}
	@Override
	public UserMask all(boolean b)
	{	this.usid=b;
		this.uspswd=b;
		this.usname=b;
		this.usst=b;
		this.uslsid=b;
		this.uspn=b;
	return this;
	}
	public boolean getUsid()
	{	return usid;
	}
	public UserMask setUsid(boolean usid)
	{	this.usid=usid;
		return this;
	}
	public boolean getUspswd()
	{	return uspswd;
	}
	public UserMask setUspswd(boolean uspswd)
	{	this.uspswd=uspswd;
		return this;
	}
	public boolean getUsname()
	{	return usname;
	}
	public UserMask setUsname(boolean usname)
	{	this.usname=usname;
		return this;
	}
	public boolean getUsst()
	{	return usst;
	}
	public UserMask setUsst(boolean usst)
	{	this.usst=usst;
		return this;
	}
	public boolean getUslsid()
	{	return uslsid;
	}
	public UserMask setUslsid(boolean uslsid)
	{	this.uslsid=uslsid;
		return this;
	}
	public boolean getUspn()
	{	return uspn;
	}
	public UserMask setUspn(boolean uspn)
	{	this.uspn=uspn;
		return this;
	}
}