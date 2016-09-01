package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class ArticleMask implements Mask
{	protected boolean atid=false;
	protected boolean atcgid=false;
	protected boolean atsi=false;
	protected boolean atname=false;
	protected boolean atusid=false;
	protected boolean atupdd=false;
	protected boolean atupdt=false;
	protected boolean atcgname=false;
	protected boolean atcsa=false;
	protected boolean atcsb=false;
	protected boolean atcsc=false;
	protected boolean atcsd=false;
	protected boolean atcse=false;
	protected boolean atcsf=false;

	public ArticleMask(boolean atid,boolean atcgid,boolean atsi,boolean atname,boolean atusid,boolean atupdd,boolean atupdt,boolean atcgname,boolean atcsa,boolean atcsb,boolean atcsc,boolean atcsd,boolean atcse,boolean atcsf)
	{	this.atid=atid;
		this.atcgid=atcgid;
		this.atsi=atsi;
		this.atname=atname;
		this.atusid=atusid;
		this.atupdd=atupdd;
		this.atupdt=atupdt;
		this.atcgname=atcgname;
		this.atcsa=atcsa;
		this.atcsb=atcsb;
		this.atcsc=atcsc;
		this.atcsd=atcsd;
		this.atcse=atcse;
		this.atcsf=atcsf;
	}
	public ArticleMask()
	{	
	}
	public boolean getAtid()
	{	return atid;
	}
	public ArticleMask setAtid(boolean atid)
	{	this.atid=atid;
		return this;
	}
	public boolean getAtcgid()
	{	return atcgid;
	}
	public ArticleMask setAtcgid(boolean atcgid)
	{	this.atcgid=atcgid;
		return this;
	}
	public boolean getAtsi()
	{	return atsi;
	}
	public ArticleMask setAtsi(boolean atsi)
	{	this.atsi=atsi;
		return this;
	}
	public boolean getAtname()
	{	return atname;
	}
	public ArticleMask setAtname(boolean atname)
	{	this.atname=atname;
		return this;
	}
	public boolean getAtusid()
	{	return atusid;
	}
	public ArticleMask setAtusid(boolean atusid)
	{	this.atusid=atusid;
		return this;
	}
	public boolean getAtupdd()
	{	return atupdd;
	}
	public ArticleMask setAtupdd(boolean atupdd)
	{	this.atupdd=atupdd;
		return this;
	}
	public boolean getAtupdt()
	{	return atupdt;
	}
	public ArticleMask setAtupdt(boolean atupdt)
	{	this.atupdt=atupdt;
		return this;
	}
	public boolean getAtcgname()
	{	return atcgname;
	}
	public ArticleMask setAtcgname(boolean atcgname)
	{	this.atcgname=atcgname;
		return this;
	}
	public boolean getAtcsa()
	{	return atcsa;
	}
	public ArticleMask setAtcsa(boolean atcsa)
	{	this.atcsa=atcsa;
		return this;
	}
	public boolean getAtcsb()
	{	return atcsb;
	}
	public ArticleMask setAtcsb(boolean atcsb)
	{	this.atcsb=atcsb;
		return this;
	}
	public boolean getAtcsc()
	{	return atcsc;
	}
	public ArticleMask setAtcsc(boolean atcsc)
	{	this.atcsc=atcsc;
		return this;
	}
	public boolean getAtcsd()
	{	return atcsd;
	}
	public ArticleMask setAtcsd(boolean atcsd)
	{	this.atcsd=atcsd;
		return this;
	}
	public boolean getAtcse()
	{	return atcse;
	}
	public ArticleMask setAtcse(boolean atcse)
	{	this.atcse=atcse;
		return this;
	}
	public boolean getAtcsf()
	{	return atcsf;
	}
	public ArticleMask setAtcsf(boolean atcsf)
	{	this.atcsf=atcsf;
		return this;
	}
}