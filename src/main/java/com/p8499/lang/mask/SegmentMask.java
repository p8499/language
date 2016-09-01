package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class SegmentMask implements Mask
{	protected boolean trasid=false;
	protected boolean trpi=false;
	protected boolean trhz=false;
	protected boolean trst=false;
	protected boolean trusid=false;
	protected boolean trupdd=false;
	protected boolean trupdt=false;

	public SegmentMask(boolean trasid,boolean trpi,boolean trhz,boolean trst,boolean trusid,boolean trupdd,boolean trupdt)
	{	this.trasid=trasid;
		this.trpi=trpi;
		this.trhz=trhz;
		this.trst=trst;
		this.trusid=trusid;
		this.trupdd=trupdd;
		this.trupdt=trupdt;
	}
	public SegmentMask()
	{	
	}
	public boolean getTrasid()
	{	return trasid;
	}
	public SegmentMask setTrasid(boolean trasid)
	{	this.trasid=trasid;
		return this;
	}
	public boolean getTrpi()
	{	return trpi;
	}
	public SegmentMask setTrpi(boolean trpi)
	{	this.trpi=trpi;
		return this;
	}
	public boolean getTrhz()
	{	return trhz;
	}
	public SegmentMask setTrhz(boolean trhz)
	{	this.trhz=trhz;
		return this;
	}
	public boolean getTrst()
	{	return trst;
	}
	public SegmentMask setTrst(boolean trst)
	{	this.trst=trst;
		return this;
	}
	public boolean getTrusid()
	{	return trusid;
	}
	public SegmentMask setTrusid(boolean trusid)
	{	this.trusid=trusid;
		return this;
	}
	public boolean getTrupdd()
	{	return trupdd;
	}
	public SegmentMask setTrupdd(boolean trupdd)
	{	this.trupdd=trupdd;
		return this;
	}
	public boolean getTrupdt()
	{	return trupdt;
	}
	public SegmentMask setTrupdt(boolean trupdt)
	{	this.trupdt=trupdt;
		return this;
	}
}