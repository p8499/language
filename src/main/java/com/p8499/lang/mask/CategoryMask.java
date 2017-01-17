package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class CategoryMask implements Mask
{	protected boolean cgid=false;
	protected boolean cglsid=false;
	protected boolean cgsi=false;
	protected boolean cgpsi=false;
	protected boolean cgname=false;

	public CategoryMask(boolean cgid,boolean cglsid,boolean cgsi,boolean cgpsi,boolean cgname)
	{	this.cgid=cgid;
		this.cglsid=cglsid;
		this.cgsi=cgsi;
		this.cgpsi=cgpsi;
		this.cgname=cgname;
	}
	public CategoryMask()
	{	
	}
	@Override
	public CategoryMask all(boolean b)
	{	this.cgid=b;
		this.cglsid=b;
		this.cgsi=b;
		this.cgpsi=b;
		this.cgname=b;
	return this;
	}
	public boolean getCgid()
	{	return cgid;
	}
	public CategoryMask setCgid(boolean cgid)
	{	this.cgid=cgid;
		return this;
	}
	public boolean getCglsid()
	{	return cglsid;
	}
	public CategoryMask setCglsid(boolean cglsid)
	{	this.cglsid=cglsid;
		return this;
	}
	public boolean getCgsi()
	{	return cgsi;
	}
	public CategoryMask setCgsi(boolean cgsi)
	{	this.cgsi=cgsi;
		return this;
	}
	public boolean getCgpsi()
	{	return cgpsi;
	}
	public CategoryMask setCgpsi(boolean cgpsi)
	{	this.cgpsi=cgpsi;
		return this;
	}
	public boolean getCgname()
	{	return cgname;
	}
	public CategoryMask setCgname(boolean cgname)
	{	this.cgname=cgname;
		return this;
	}
}