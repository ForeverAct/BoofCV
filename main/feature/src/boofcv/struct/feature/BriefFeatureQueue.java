/*
 * Copyright (c) 2011-2013, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.struct.feature;

import boofcv.struct.FastQueue;


/**
 * {@link boofcv.struct.FastQueue} for {@link TupleDesc_B}.
 *
 * @author Peter Abeles
 */
public class BriefFeatureQueue extends FastQueue<TupleDesc_B> {

	int numBits;

	public BriefFeatureQueue(int numBits) {
		super(TupleDesc_B.class,true);
		this.numBits = numBits;
		growArray(10);
	}

	@Override
	protected TupleDesc_B createInstance() {
		return new TupleDesc_B(numBits);
	}
}
