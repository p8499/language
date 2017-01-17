package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class LanguageMask implements Mask
{	protected boolean lsid=false;
	protected boolean lsname=false;
	protected boolean lsloc=false;
	protected boolean lssort=false;

	public LanguageMask(boolean lsid,boolean lsname,boolean lsloc,boolean lssort)
	{	this.lsid=lsid;
		this.lsname=lsname;
		this.lsloc=lsloc;
		this.lssort=lssort;
	}
	public LanguageMask()
	{	
	}
	@Override
	public LanguageMask all(boolean b)
	{	this.lsid=b;
		this.lsname=b;
		this.lsloc=b;
		this.lssort=b;
	return this;
	}
	public boolean getLsid()
	{	return lsid;
	}
	public LanguageMask setLsid(boolean lsid)
	{	this.lsid=lsid;
		return this;
	}
	public boolean getLsname()
	{	return lsname;
	}
	public LanguageMask setLsname(boolean lsname)
	{	this.lsname=lsname;
		return this;
	}
	public boolean getLsloc()
	{	return lsloc;
	}
	public LanguageMask setLsloc(boolean lsloc)
	{	this.lsloc=lsloc;
		return this;
	}
	public boolean getLssort()
	{	return lssort;
	}
	public LanguageMask setLssort(boolean lssort)
	{	this.lssort=lssort;
		return this;
	}
}