package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class SegmentvoteMask implements Mask
{	protected boolean tvid=false;
	protected boolean tvtaid=false;
	protected boolean tvsi=false;
	protected boolean tvusid=false;
	protected boolean tvpo=false;
	protected boolean taupdd=false;
	protected boolean taupdt=false;

	public SegmentvoteMask(boolean tvid,boolean tvtaid,boolean tvsi,boolean tvusid,boolean tvpo,boolean taupdd,boolean taupdt)
	{	this.tvid=tvid;
		this.tvtaid=tvtaid;
		this.tvsi=tvsi;
		this.tvusid=tvusid;
		this.tvpo=tvpo;
		this.taupdd=taupdd;
		this.taupdt=taupdt;
	}
	public SegmentvoteMask()
	{	
	}
	public boolean getTvid()
	{	return tvid;
	}
	public SegmentvoteMask setTvid(boolean tvid)
	{	this.tvid=tvid;
		return this;
	}
	public boolean getTvtaid()
	{	return tvtaid;
	}
	public SegmentvoteMask setTvtaid(boolean tvtaid)
	{	this.tvtaid=tvtaid;
		return this;
	}
	public boolean getTvsi()
	{	return tvsi;
	}
	public SegmentvoteMask setTvsi(boolean tvsi)
	{	this.tvsi=tvsi;
		return this;
	}
	public boolean getTvusid()
	{	return tvusid;
	}
	public SegmentvoteMask setTvusid(boolean tvusid)
	{	this.tvusid=tvusid;
		return this;
	}
	public boolean getTvpo()
	{	return tvpo;
	}
	public SegmentvoteMask setTvpo(boolean tvpo)
	{	this.tvpo=tvpo;
		return this;
	}
	public boolean getTaupdd()
	{	return taupdd;
	}
	public SegmentvoteMask setTaupdd(boolean taupdd)
	{	this.taupdd=taupdd;
		return this;
	}
	public boolean getTaupdt()
	{	return taupdt;
	}
	public SegmentvoteMask setTaupdt(boolean taupdt)
	{	this.taupdt=taupdt;
		return this;
	}
}