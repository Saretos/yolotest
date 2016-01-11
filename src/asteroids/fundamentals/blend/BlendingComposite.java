package asteroids.fundamentals.blend;

import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;

/**
 *
 * @author nilsg
 */
public class BlendingComposite
{
    public static Composite add = new Composite() {

        @Override
        public CompositeContext createContext(ColorModel srcColorModel,
                ColorModel dstColorModel, RenderingHints hints)
        {
            return new AdditiveBlendingContext();
        }
    };
    
    public static Composite sub = new Composite() {

        @Override
        public CompositeContext createContext(ColorModel srcColorModel,
                ColorModel dstColorModel, RenderingHints hints)
        {
            return new SubtractiveBlendingContext();
        }
    };
    public static Composite multiply = new Composite() {

        @Override
        public CompositeContext createContext(ColorModel srcColorModel,
                ColorModel dstColorModel, RenderingHints hints)
        {
            return new MultiplyBlendingContext();
        }
    };
    public static Composite diff = new Composite() {

        @Override
        public CompositeContext createContext(ColorModel srcColorModel,
                ColorModel dstColorModel, RenderingHints hints)
        {
            return new DifferenceBlendingContext();
        }
    };
    
}
