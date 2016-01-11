package asteroids.fundamentals;

import java.awt.Color;

/**
 * A Class representing Colours in the HSV Color Model.
 *
 * @author Raildex
 * @see https://de.wikipedia.org/wiki/HSV-Farbraum
 * @version 1.0
 */
public final class ColorHSV
{
  /**
   * Constructs a black HSV Color
   */
  public ColorHSV()
  {
    this.hue = 360;
    this.saturation = 1;
    this.value = 1;
  }
  /**
   * Hue Value, between 0 and 360 degrees
   */
  private float hue;
  /**
   * Saturation, between 0f and 1f
   */
  private float saturation;
  /**
   * Value, between 0f and 1f
   */
  private float value;

  /**
   * Constructs a new Color in the HSV Color Model, based on a AWT Color
   *
   * @param rgb the Color to be converted
   */
  public ColorHSV(Color rgb)
  {
    this(rgb.getRed() / 255f, rgb.getGreen() / 255f, rgb.getBlue() / 255f);
  }

  /**
   * Constructs a new HSV Color
   *
   * @param hue
   * @param saturation
   * @param value
   */
  public ColorHSV(double hue, double saturation, double value)
  {
    this.hue = (float) hue;
    this.saturation = (float) saturation;
    this.value = (float) value;
  }

  /**
   * Constructs a new Color in the HSV Color Model based on separate R,G and B
   * Values between 0 and 255
   *
   * @param r Red Component between 0 and 255
   * @param g Green Component between 0 and 255
   * @param b Blue Component between 0 and 255
   */
  public ColorHSV(int r, int g, int b)
  {
    this(r / 255f, g / 255f, b / 255f);
  }

  public ColorHSV(float hue, float saturation, float value)
  {
    this.hue = hue;
    this.saturation = saturation;
    this.value = value;
  }

  @Override
  public String toString()
  {
    return this.getClass().getName() + "[Hue= " + hue + "°, Saturation= " + saturation + ", Value= " + value + "]";
  }

  @Override
  public boolean equals(Object o)
  {
    return (o instanceof ColorHSV) && (((ColorHSV) o).hue == this.hue && ((ColorHSV) o).value == this.value && ((ColorHSV) o).saturation == this.saturation);
  }

  @Override
  public int hashCode()
  {
    return (int) (getSaturation() + getValue() * 255 + getHue() * 255);
  }

  /**
   * @return the hue
   */
  public float getHue()
  {
    return hue;
  }

  public void setHue(float hue)
  {
    this.hue = hue;
  }

  /**
   * @return the saturation
   */
  public float getSaturation()
  {
    return saturation;
  }

  public void setSaturation(float saturation)
  {
    this.saturation = saturation;
  }

  /**
   * @return the value
   */
  public float getValue()
  {
    return value;
  }

  public void setValue(float value)
  {
    this.value = value;
  }

  /**
   * Converts the Color in HSV space into a color in RGB Space
   *
   * @return the HSV Color in RGB Space,
   */
  public Color toRGB()
  {
    int h = (int) Math.floor(hue / 60);
    float f = (hue / 60) - h;
    float p = value * (1 - saturation);
    float q = value * (1 - saturation * f);
    float t = value * (1 - saturation * (1 - f));
    if (h == 0 || h == 6)
    {
      return new Color(value, t, p);
    }
    else if (h == 1)
    {
      return new Color(q, value, p);
    }
    else if (h == 2)
    {
      return new Color(p, value, t);
    }
    else if (h == 3)
    {
      return new Color(p, q, value);
    }
    else if (h == 4)
    {
      return new Color(t, p, value);
    }
    else if (h == 5)
    {
      return new Color(value, p, q);
    }
    else
    {
      return new Color(value, t, p);
    }
  }

}
