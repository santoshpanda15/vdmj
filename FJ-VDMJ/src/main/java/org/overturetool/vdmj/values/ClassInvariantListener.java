/*******************************************************************************
 *
 *	Copyright (C) 2008 Fujitsu Services Ltd.
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

package org.overturetool.vdmj.values;

import java.io.Serializable;

import org.overturetool.vdmj.Settings;
import org.overturetool.vdmj.lex.LexLocation;
import org.overturetool.vdmj.runtime.Context;
import org.overturetool.vdmj.runtime.ContextException;
import org.overturetool.vdmj.runtime.ExceptionHandler;
import org.overturetool.vdmj.runtime.ValueException;

public class ClassInvariantListener implements ValueListener, Serializable
{
    private static final long serialVersionUID = 1L;
	public final OperationValue invopvalue;
	public boolean doInvariantChecks = true;

	public ClassInvariantListener(OperationValue invopvalue)
	{
		this.invopvalue = invopvalue;
	}

	public void changedValue(LexLocation location, Value value, Context ctxt) throws ValueException
	{
		if (doInvariantChecks && Settings.invchecks)
		{
			// In VDM++ and VDM-RT, we do not want to do thread swaps half way
			// through an invariant check, so we set the atomic flag around the
			// conversion. This also stops VDM-RT from performing "time step"
			// calculations.
			
			try
			{
				ctxt.threadState.setAtomic(true);
				boolean inv = invopvalue.eval(location, new ValueList(), ctxt).boolValue(ctxt);
			
    			if (!inv)
    			{
    				ExceptionHandler.handle(new ContextException(
        					4130, "Instance invariant violated: " + invopvalue.name, location, ctxt));
    			}
			}
			finally
			{
				ctxt.threadState.setAtomic(false);
			}
		}
	}
}
