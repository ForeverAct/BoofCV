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

package boofcv.abst.geo.pose;

import boofcv.abst.geo.Estimate1ofPnP;
import boofcv.alg.geo.f.EpipolarTestSimulation;
import boofcv.struct.geo.Point2D3D;
import georegression.struct.point.Point2D_F64;
import georegression.struct.point.Point3D_F64;
import georegression.struct.se.Se3_F64;
import org.ejml.ops.MatrixFeatures;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * General tests for implementations of Estimate1ofPnP.  Ensures that the returned motion estimate is in
 * the correct direction and works under nominal conditions
 *
 *
 * @author Peter Abeles
 */
public abstract class CheckEstimate1ofPnP extends EpipolarTestSimulation {

	// algorithm being tested
	Estimate1ofPnP alg;
	// true if it can only process the minimum number of observations
	boolean onlyMinimum;

	protected CheckEstimate1ofPnP(Estimate1ofPnP alg, boolean onlyMinimum) {
		this.alg = alg;
		this.onlyMinimum = onlyMinimum;

	}

	/**
	 * Feed it perfect observations and see if it returns nearly perfect results
	 */
	@Test
	public void perfectObservations() {
		if( !onlyMinimum ) {
			// test it with extra observations
			perfectObservations(alg.getMinimumPoints()+20);
		}
		// test it with the minimum number
		perfectObservations(alg.getMinimumPoints());
	}

	private void perfectObservations( int numSample ) {
		init(numSample,false);

		List<Point2D3D> inputs = new ArrayList<Point2D3D>();

		for( int i = 0; i < currentObs.size(); i++ ) {
			Point2D_F64 o = currentObs.get(i);
			Point3D_F64 X = worldPts.get(i);

			inputs.add( new Point2D3D(o,X));
		}

		Se3_F64 found = new Se3_F64();
		assertTrue(alg.process(inputs,found));


		assertTrue(MatrixFeatures.isIdentical(worldToCamera.getR(), found.getR(), 1e-8));
		assertTrue(found.getT().isIdentical(worldToCamera.getT(), 1e-8));
	}

	/**
	 * Sanity check to see if the minimum number of observations has been set.
	 */
	@Test
	public void checkMinimumPoints() {
		assertTrue(alg.getMinimumPoints() != 0 );
	}
}
