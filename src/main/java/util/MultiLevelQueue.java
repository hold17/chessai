package util;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Items gets assigned to a level. A levels acts as a dimension.
 *
 * @param <E>
 */
public class MultiLevelQueue<E> {
    private int size;
    final private TreeMap<String, Queue<E>> levels;

    public MultiLevelQueue() {
        levels = new TreeMap<>();
    }

    /**
     * Adds a new level with an empty queue.
     *
     * @param levelName  The name to be used as the level key, cannot be null
     * @throws NullPointerException  if levelName is null
     * @throws DuplicateLevelException  if levelName is not unique
     */
    public void addLevel(final String levelName) {
        if (levelName == null) throw new NullPointerException("Level name cannot be null.");
        if (levels.containsKey(levelName)) throw new DuplicateLevelException();

        levels.put(levelName, new LinkedList<>());
    }

    /**
     * Removes the queue at the current level.
     */
    public void removeLevel() {
        clearLevelAndDecreaseCount();
    }

    private void clearLevelAndDecreaseCount() {
        final Queue<E> level = getHeadLevel();
        size -= level.size();
        level.clear();
    }

    /**
     * Get the current level.
     *
     * @throws LevelNotFoundException  if no levels exist
     * @return Queue at the current level
     */
    private Queue<E> getHeadLevel() {
        final Map.Entry<String, Queue<E>> firstEntry = levels.firstEntry();

        if (firstEntry == null) throw new LevelNotFoundException();

        return firstEntry.getValue();
    }

    /**
     * Adds an object to the queue at the current level.
     *
     * @param obj  Element to be added
     * @throws LevelNotFoundException  if no levels exist
     */
    public void add(final E obj)
    {
        final Queue<E> level = getTailLevel();
        level.add(obj);
        size++;
    }

    /**
     * Finds the last queue.
     *
     * @throws LevelNotFoundException  if no levels exist
     * @return The last queue
     */
    private Queue<E> getTailLevel() {
        final Map.Entry<String, Queue<E>> lastEntry = levels.lastEntry();

        if (lastEntry == null) throw new LevelNotFoundException();

        return lastEntry.getValue();
    }

    /**
     * Removes the first element from the first queue and returns that element.
     *
     * @return First element from the first queue
     */
    public E next() {
        removeLevelIfEmpty();

        if (noLevelExist()) return null;

        return popNextObject();
    }

    /**
     * Retrieves the next level and removes the current one.
     *
     * @return The next level
     */
    private E popNextObject() {
        final Queue<E> level = getHeadLevel();

        if (level.size() == 0) return null;

        size--;

        return level.remove();
    }

    /**
     * Checks if a level is empty and removes it if it is.
     */
    private void removeLevelIfEmpty() {
        while (levels.size() > 0) {
            final Map.Entry<String, Queue<E>> levelEntry = levels.firstEntry();

            if (levelEntry.getValue().size() == 0) {
                levels.remove(levelEntry.getKey());
                continue;
            }

            break;
        }
    }

    /**
     * Checks if there exist any levels.
     *
     * @return True if there exist at least 1 level, otherwise false
     */
    private boolean noLevelExist() {
        return levels.size() == 0;
    }

    /**
     * Returns the total number of elements in the multilevel queue.
     *
     * @return Size of the multilevel queue
     */
    public int size() {
        return size;
    }
}
