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

package com.fujitsu.vdmj.in.patterns;

import java.util.Vector;

import com.fujitsu.vdmj.lex.LexLocation;
import com.fujitsu.vdmj.runtime.Context;
import com.fujitsu.vdmj.runtime.PatternMatchException;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCTypeSet;
import com.fujitsu.vdmj.tc.types.TCUnknownType;
import com.fujitsu.vdmj.util.Utils;
import com.fujitsu.vdmj.values.NameValuePairList;
import com.fujitsu.vdmj.values.Value;

@SuppressWarnings("serial")
public class INNamePatternPairList extends Vector<INNamePatternPair>
{
	@Override
	public String toString()
	{
		return Utils.listToString(this);
	}

	public NameValuePairList getNamedValues(Value value, Context ctxt)
		throws PatternMatchException
	{
		NameValuePairList list = new NameValuePairList();

		for (INNamePatternPair npp: this)
		{
			list.addAll(npp.pattern.getNamedValues(value, ctxt));
		}

		return list;
	}

	public TCType getPossibleType(LexLocation location)
	{
		switch (size())
		{
			case 0:
				return new TCUnknownType(location);

			case 1:
				return get(0).pattern.getPossibleType();

			default:
        		TCTypeSet list = new TCTypeSet();

        		for (INNamePatternPair npp: this)
        		{
        			list.add(npp.pattern.getPossibleType());
        		}

        		return list.getType(location);		// NB. a union of types
		}
	}

	public boolean isConstrained()
	{
		for (INNamePatternPair npp: this)
		{
			if (npp.pattern.isConstrained()) return true;		// NB. OR
		}

		return false;
	}
}
