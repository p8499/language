package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class PronounceMask implements Mask
{	protected boolean pnid=false;
	protected boolean pnlsid=false;
	protected boolean pnct=false;
	protected boolean pnpi=false;
	protected boolean pntn=false;
	protected boolean pnco=false;
	protected boolean pnvo=false;
	protected boolean pncl=false;
	protected boolean pnrm=false;

	public PronounceMask(boolean pnid,boolean pnlsid,boolean pnct,boolean pnpi,boolean pntn,boolean pnco,boolean pnvo,boolean pncl,boolean pnrm)
	{	this.pnid=pnid;
		this.pnlsid=pnlsid;
		this.pnct=pnct;
		this.pnpi=pnpi;
		this.pntn=pntn;
		this.pnco=pnco;
		this.pnvo=pnvo;
		this.pncl=pncl;
		this.pnrm=pnrm;
	}
	public PronounceMask()
	{	
	}
	@Override
	public PronounceMask all(boolean b)
	{	this.pnid=b;
		this.pnlsid=b;
		this.pnct=b;
		this.pnpi=b;
		this.pntn=b;
		this.pnco=b;
		this.pnvo=b;
		this.pncl=b;
		this.pnrm=b;
	return this;
	}
	public boolean getPnid()
	{	return pnid;
	}
	public PronounceMask setPnid(boolean pnid)
	{	this.pnid=pnid;
		return this;
	}
	public boolean getPnlsid()
	{	return pnlsid;
	}
	public PronounceMask setPnlsid(boolean pnlsid)
	{	this.pnlsid=pnlsid;
		return this;
	}
	public boolean getPnct()
	{	return pnct;
	}
	public PronounceMask setPnct(boolean pnct)
	{	this.pnct=pnct;
		return this;
	}
	public boolean getPnpi()
	{	return pnpi;
	}
	public PronounceMask setPnpi(boolean pnpi)
	{	this.pnpi=pnpi;
		return this;
	}
	public boolean getPntn()
	{	return pntn;
	}
	public PronounceMask setPntn(boolean pntn)
	{	this.pntn=pntn;
		return this;
	}
	public boolean getPnco()
	{	return pnco;
	}
	public PronounceMask setPnco(boolean pnco)
	{	this.pnco=pnco;
		return this;
	}
	public boolean getPnvo()
	{	return pnvo;
	}
	public PronounceMask setPnvo(boolean pnvo)
	{	this.pnvo=pnvo;
		return this;
	}
	public boolean getPncl()
	{	return pncl;
	}
	public PronounceMask setPncl(boolean pncl)
	{	this.pncl=pncl;
		return this;
	}
	public boolean getPnrm()
	{	return pnrm;
	}
	public PronounceMask setPnrm(boolean pnrm)
	{	this.pnrm=pnrm;
		return this;
	}
}