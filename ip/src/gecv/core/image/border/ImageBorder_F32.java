/*
 * Copyright 2011 Peter Abeles
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gecv.core.image.border;

import gecv.struct.image.ImageFloat32;
import gecv.struct.image.ImageInteger;

/**
 * Child of {@link ImageBorder} for {@link ImageInteger}.
 *
 * @author Peter Abeles
 */
public abstract class ImageBorder_F32 extends ImageBorder<ImageFloat32> {

	public ImageBorder_F32(ImageFloat32 image) {
		super(image);
	}

	protected ImageBorder_F32() {
	}

	public void set( int x , int y , float val ) {
		if( image.isInBounds(x,y) )
			image.set(x,y,val);

		setOutside(x,y,val);
	}

	public float get( int x , int y ) {
		if( image.isInBounds(x,y) )
			return image.get(x,y);

		return getOutside( x , y );
	}

	public abstract float getOutside( int x , int y );

	public abstract void setOutside( int x , int y , float val );
}