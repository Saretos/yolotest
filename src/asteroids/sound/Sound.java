package asteroids.sound;

public final class Sound
{

  byte[][] wavData;
  float pitchVariance;
  boolean loop;

  public Sound(byte[][] wavData, float p, boolean l)
  {
    this.wavData = wavData;
    this.pitchVariance = p;
    this.loop = l;
  }
}
