package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class ChunkcriteriaMask implements Mask
{	protected boolean ccid=false;
	protected boolean cccpid=false;
	protected boolean ccsi=false;
	protected boolean cctk=false;
	protected boolean cctg=false;

	public ChunkcriteriaMask(boolean ccid,boolean cccpid,boolean ccsi,boolean cctk,boolean cctg)
	{	this.ccid=ccid;
		this.cccpid=cccpid;
		this.ccsi=ccsi;
		this.cctk=cctk;
		this.cctg=cctg;
	}
	public ChunkcriteriaMask()
	{	
	}
	public boolean getCcid()
	{	return ccid;
	}
	public ChunkcriteriaMask setCcid(boolean ccid)
	{	this.ccid=ccid;
		return this;
	}
	public boolean getCccpid()
	{	return cccpid;
	}
	public ChunkcriteriaMask setCccpid(boolean cccpid)
	{	this.cccpid=cccpid;
		return this;
	}
	public boolean getCcsi()
	{	return ccsi;
	}
	public ChunkcriteriaMask setCcsi(boolean ccsi)
	{	this.ccsi=ccsi;
		return this;
	}
	public boolean getCctk()
	{	return cctk;
	}
	public ChunkcriteriaMask setCctk(boolean cctk)
	{	this.cctk=cctk;
		return this;
	}
	public boolean getCctg()
	{	return cctg;
	}
	public ChunkcriteriaMask setCctg(boolean cctg)
	{	this.cctg=cctg;
		return this;
	}
}