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

package com.fujitsu.vdmj.ast.statements;

import java.io.Serializable;

import com.fujitsu.vdmj.ast.patterns.ASTPatternBind;

public class ASTTixeStmtAlternative implements Serializable
{
	private static final long serialVersionUID = 1L;
	public final ASTPatternBind patternBind;
	public final ASTStatement statement;

	public ASTTixeStmtAlternative(ASTPatternBind patternBind, ASTStatement stmt)
	{
		this.patternBind = patternBind;
		this.statement = stmt;
	}

	@Override
	public String toString()
	{
		return patternBind + " |-> " + statement;
	}
}
