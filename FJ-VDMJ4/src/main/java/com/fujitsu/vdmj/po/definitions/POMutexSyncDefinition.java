/*******************************************************************************
 *
 *	Copyright (c) 2016 Fujitsu Services Ltd.
 *
 *	Author: Nick Battle
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package com.fujitsu.vdmj.po.definitions;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.tc.lex.TCNameList;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCUnknownType;
import com.fujitsu.vdmj.util.Utils;

public class POMutexSyncDefinition extends PODefinition
{
	private static final long serialVersionUID = 1L;
	public final TCNameList operations;

	public POMutexSyncDefinition(LexLocation location, TCNameList operations)
	{
		super(location, null);
		this.operations = operations;
	}

	@Override
	public TCType getType()
	{
		return new TCUnknownType(location);
	}

	@Override
	public TCNameList getVariableNames()
	{
		return new TCNameList();
	}

	@Override
	public String toString()
	{
		return "mutex(" +
			(operations.isEmpty() ? "all)" :
				Utils.listToString("", operations, ", ", ")"));
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof POMutexSyncDefinition)
		{
			return toString().equals(other.toString());
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}
}
