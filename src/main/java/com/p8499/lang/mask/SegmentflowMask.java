package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class SegmentflowMask implements Mask
{	protected boolean taid=false;
	protected boolean taasid=false;
	protected boolean tasi=false;
	protected boolean tapi=false;
	protected boolean tahz=false;
	protected boolean tast=false;
	protected boolean tausid=false;
	protected boolean tacrdd=false;
	protected boolean tacrdt=false;
	protected boolean taupdd=false;
	protected boolean taupdt=false;

	public SegmentflowMask(boolean taid,boolean taasid,boolean tasi,boolean tapi,boolean tahz,boolean tast,boolean tausid,boolean tacrdd,boolean tacrdt,boolean taupdd,boolean taupdt)
	{	this.taid=taid;
		this.taasid=taasid;
		this.tasi=tasi;
		this.tapi=tapi;
		this.tahz=tahz;
		this.tast=tast;
		this.tausid=tausid;
		this.tacrdd=tacrdd;
		this.tacrdt=tacrdt;
		this.taupdd=taupdd;
		this.taupdt=taupdt;
	}
	public SegmentflowMask()
	{	
	}
	@Override
	public SegmentflowMask all(boolean b)
	{	this.taid=b;
		this.taasid=b;
		this.tasi=b;
		this.tapi=b;
		this.tahz=b;
		this.tast=b;
		this.tausid=b;
		this.tacrdd=b;
		this.tacrdt=b;
		this.taupdd=b;
		this.taupdt=b;
	return this;
	}
	public boolean getTaid()
	{	return taid;
	}
	public SegmentflowMask setTaid(boolean taid)
	{	this.taid=taid;
		return this;
	}
	public boolean getTaasid()
	{	return taasid;
	}
	public SegmentflowMask setTaasid(boolean taasid)
	{	this.taasid=taasid;
		return this;
	}
	public boolean getTasi()
	{	return tasi;
	}
	public SegmentflowMask setTasi(boolean tasi)
	{	this.tasi=tasi;
		return this;
	}
	public boolean getTapi()
	{	return tapi;
	}
	public SegmentflowMask setTapi(boolean tapi)
	{	this.tapi=tapi;
		return this;
	}
	public boolean getTahz()
	{	return tahz;
	}
	public SegmentflowMask setTahz(boolean tahz)
	{	this.tahz=tahz;
		return this;
	}
	public boolean getTast()
	{	return tast;
	}
	public SegmentflowMask setTast(boolean tast)
	{	this.tast=tast;
		return this;
	}
	public boolean getTausid()
	{	return tausid;
	}
	public SegmentflowMask setTausid(boolean tausid)
	{	this.tausid=tausid;
		return this;
	}
	public boolean getTacrdd()
	{	return tacrdd;
	}
	public SegmentflowMask setTacrdd(boolean tacrdd)
	{	this.tacrdd=tacrdd;
		return this;
	}
	public boolean getTacrdt()
	{	return tacrdt;
	}
	public SegmentflowMask setTacrdt(boolean tacrdt)
	{	this.tacrdt=tacrdt;
		return this;
	}
	public boolean getTaupdd()
	{	return taupdd;
	}
	public SegmentflowMask setTaupdd(boolean taupdd)
	{	this.taupdd=taupdd;
		return this;
	}
	public boolean getTaupdt()
	{	return taupdt;
	}
	public SegmentflowMask setTaupdt(boolean taupdt)
	{	this.taupdt=taupdt;
		return this;
	}
}