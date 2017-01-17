package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class SegmentvoteMask implements Mask
{	protected boolean tvid=false;
	protected boolean tvtaid=false;
	protected boolean tvsi=false;
	protected boolean tvusid=false;
	protected boolean tvpo=false;
	protected boolean tvupdd=false;
	protected boolean tvupdt=false;

	public SegmentvoteMask(boolean tvid,boolean tvtaid,boolean tvsi,boolean tvusid,boolean tvpo,boolean tvupdd,boolean tvupdt)
	{	this.tvid=tvid;
		this.tvtaid=tvtaid;
		this.tvsi=tvsi;
		this.tvusid=tvusid;
		this.tvpo=tvpo;
		this.tvupdd=tvupdd;
		this.tvupdt=tvupdt;
	}
	public SegmentvoteMask()
	{	
	}
	@Override
	public SegmentvoteMask all(boolean b)
	{	this.tvid=b;
		this.tvtaid=b;
		this.tvsi=b;
		this.tvusid=b;
		this.tvpo=b;
		this.tvupdd=b;
		this.tvupdt=b;
	return this;
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
	public boolean getTvupdd()
	{	return tvupdd;
	}
	public SegmentvoteMask setTvupdd(boolean tvupdd)
	{	this.tvupdd=tvupdd;
		return this;
	}
	public boolean getTvupdt()
	{	return tvupdt;
	}
	public SegmentvoteMask setTvupdt(boolean tvupdt)
	{	this.tvupdt=tvupdt;
		return this;
	}
}