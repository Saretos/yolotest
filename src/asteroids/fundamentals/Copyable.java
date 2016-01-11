
package asteroids.fundamentals;


public interface Copyable<T>
{
  /**
   * Deep copies an Object
   * @return a deep copy of this object
   */
  public T copy();
}
