package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class UsercreationMask implements Mask
{	protected boolean ucid=false;
	protected boolean uccrdd=false;
	protected boolean uccrdt=false;
	protected boolean ucusid=false;
	protected boolean ucpv=false;
	protected boolean ucmv=false;
	protected boolean ucst=false;

	public UsercreationMask(boolean ucid,boolean uccrdd,boolean uccrdt,boolean ucusid,boolean ucpv,boolean ucmv,boolean ucst)
	{	this.ucid=ucid;
		this.uccrdd=uccrdd;
		this.uccrdt=uccrdt;
		this.ucusid=ucusid;
		this.ucpv=ucpv;
		this.ucmv=ucmv;
		this.ucst=ucst;
	}
	public UsercreationMask()
	{	
	}
	@Override
	public UsercreationMask all(boolean b)
	{	this.ucid=b;
		this.uccrdd=b;
		this.uccrdt=b;
		this.ucusid=b;
		this.ucpv=b;
		this.ucmv=b;
		this.ucst=b;
	return this;
	}
	public boolean getUcid()
	{	return ucid;
	}
	public UsercreationMask setUcid(boolean ucid)
	{	this.ucid=ucid;
		return this;
	}
	public boolean getUccrdd()
	{	return uccrdd;
	}
	public UsercreationMask setUccrdd(boolean uccrdd)
	{	this.uccrdd=uccrdd;
		return this;
	}
	public boolean getUccrdt()
	{	return uccrdt;
	}
	public UsercreationMask setUccrdt(boolean uccrdt)
	{	this.uccrdt=uccrdt;
		return this;
	}
	public boolean getUcusid()
	{	return ucusid;
	}
	public UsercreationMask setUcusid(boolean ucusid)
	{	this.ucusid=ucusid;
		return this;
	}
	public boolean getUcpv()
	{	return ucpv;
	}
	public UsercreationMask setUcpv(boolean ucpv)
	{	this.ucpv=ucpv;
		return this;
	}
	public boolean getUcmv()
	{	return ucmv;
	}
	public UsercreationMask setUcmv(boolean ucmv)
	{	this.ucmv=ucmv;
		return this;
	}
	public boolean getUcst()
	{	return ucst;
	}
	public UsercreationMask setUcst(boolean ucst)
	{	this.ucst=ucst;
		return this;
	}
}