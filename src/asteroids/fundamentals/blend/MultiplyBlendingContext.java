package asteroids.fundamentals.blend;

import java.awt.CompositeContext;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 *
 * @author nilsg
 */
class MultiplyBlendingContext implements CompositeContext
{

  @Override
  public void compose(Raster src, Raster dstIn, WritableRaster dstOut)
  {
    int w = Math.min(src.getWidth(), dstIn.getWidth());
    int h = Math.min(src.getHeight(), dstIn.getHeight());
    int srcMinX = src.getMinX();
    int srcMinY = src.getMinY();
    int dstInMinX = dstIn.getMinX();
    int dstInMinY = dstIn.getMinY();
    int dstOutMinX = dstOut.getMinX();
    int dstOutMinY = dstOut.getMinY();
    int[] srcRgba = new int[4];
    int[] dstRgba = new int[4];

    for (int x = 0; x < w; x++)
    {
      for (int y = 0; y < h; y++)
      {
        src.getPixel(x + srcMinX, y + srcMinY, srcRgba);
        dstIn.getPixel(x + dstInMinX, y + dstInMinY, dstRgba);
        for (int i = 0; i < 3; i++)
        {
          dstRgba[i]= Math.min(dstRgba[i] *= srcRgba[i], 255);
         
        }
        dstOut.setPixel(x + dstOutMinX, y + dstOutMinY, dstRgba);
      }
    }
  }

  @Override
  public void dispose()
  {
  }

}
