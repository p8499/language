package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class SentenceMask implements Mask
{	protected boolean asid=false;
	protected boolean asatid=false;
	protected boolean assi=false;
	protected boolean ascont=false;
	protected boolean asst=false;
	protected boolean asusid=false;
	protected boolean asupdd=false;
	protected boolean asupdt=false;

	public SentenceMask(boolean asid,boolean asatid,boolean assi,boolean ascont,boolean asst,boolean asusid,boolean asupdd,boolean asupdt)
	{	this.asid=asid;
		this.asatid=asatid;
		this.assi=assi;
		this.ascont=ascont;
		this.asst=asst;
		this.asusid=asusid;
		this.asupdd=asupdd;
		this.asupdt=asupdt;
	}
	public SentenceMask()
	{	
	}
	public boolean getAsid()
	{	return asid;
	}
	public SentenceMask setAsid(boolean asid)
	{	this.asid=asid;
		return this;
	}
	public boolean getAsatid()
	{	return asatid;
	}
	public SentenceMask setAsatid(boolean asatid)
	{	this.asatid=asatid;
		return this;
	}
	public boolean getAssi()
	{	return assi;
	}
	public SentenceMask setAssi(boolean assi)
	{	this.assi=assi;
		return this;
	}
	public boolean getAscont()
	{	return ascont;
	}
	public SentenceMask setAscont(boolean ascont)
	{	this.ascont=ascont;
		return this;
	}
	public boolean getAsst()
	{	return asst;
	}
	public SentenceMask setAsst(boolean asst)
	{	this.asst=asst;
		return this;
	}
	public boolean getAsusid()
	{	return asusid;
	}
	public SentenceMask setAsusid(boolean asusid)
	{	this.asusid=asusid;
		return this;
	}
	public boolean getAsupdd()
	{	return asupdd;
	}
	public SentenceMask setAsupdd(boolean asupdd)
	{	this.asupdd=asupdd;
		return this;
	}
	public boolean getAsupdt()
	{	return asupdt;
	}
	public SentenceMask setAsupdt(boolean asupdt)
	{	this.asupdt=asupdt;
		return this;
	}
}